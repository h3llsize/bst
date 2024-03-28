package com.bstbackend.bstbackend.fillbase;

import com.bstbackend.bstbackend.entity.*;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.repo.FilterCategoryOptionRepository;
import com.bstbackend.bstbackend.repo.FilterCategoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class BaseFiller {

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("jpa").createEntityManager();

    @PostConstruct
    public void main() throws InterruptedException {
        for (int i = 1000; i < 10000; i++) {
            try {
                if (!entityManager.getTransaction().isActive())
                    entityManager.getTransaction().begin();

                String url = "https://perm.met-trans.ru/api/v1/categories/" + i + "/products/search";
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                System.out.println(url);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                // Здесь вы можете установить необходимые параметры для POST-запроса
                String postData = "{\"page\": 0, \"attributes_json\": {}}";

                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postData);
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();

                if(responseCode == 404)
                    continue;

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();


                JSONObject jsonObject = new JSONObject(response.toString());


                CatalogSubCategory catalogSubcategoryDTO = createSubcategory(jsonObject);

                HashMap<String, FilterCategory> filterCategoryDTOHashMap = filterCategoryOptionDTOS(jsonObject, catalogSubcategoryDTO);


                parsePages(i, jsonObject.getJSONObject("products").getJSONObject("meta").getInt("numItemsPerPage"),
                        jsonObject.getJSONObject("products").getJSONObject("meta").getInt("totalCount"), catalogSubcategoryDTO, filterCategoryDTOHashMap);

                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                e.printStackTrace();
            }

            Thread.sleep(150);
        }
    }

    public void parsePages(int categoryId, int numItemsPerPage, int totalCount, CatalogSubCategory catalogSubCategory, HashMap<String, FilterCategory> filterCategoryDTOHashMap) {

        for (int i = 0; i < totalCount / numItemsPerPage + 1; i++) {
            try {
                System.out.println("total count : " + totalCount + " numItemsPerPage: " + numItemsPerPage);
                String url = "https://perm.met-trans.ru/api/v1/categories/" + categoryId + "/products/search";
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                System.out.println(url);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                // Здесь вы можете установить необходимые параметры для POST-запроса
                String postData = "{\"page\":" + i + "}";

                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postData);
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == 404)
                    continue;

                if(responseCode == 500) {
                    System.out.println("500");
                    break;
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();


                JSONObject jsonObject = new JSONObject(response.toString());

                Thread.sleep(150);
                catalogProductDTOS(catalogSubCategory, filterCategoryDTOHashMap, jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        System.out.println("added all");
    }

    public HashMap<String, FilterCategory> filterCategoryOptionDTOS(JSONObject jsonObject, CatalogSubCategory catalogSubcategoryDTO) {
        JSONObject jsonArray = jsonObject.getJSONObject("attributes");

        HashMap<String, FilterCategory> filterCategoryDTOS = new HashMap<>();

        Iterator<String> keys = jsonArray.keys();

        while (keys.hasNext()) {
            String k = keys.next();

            if (!k.equals("name") && !k.equals("description")) {
                FilterCategory filterCategoryDTO = new FilterCategory();
                filterCategoryDTO.setCatalogCategory(catalogSubcategoryDTO);

                JSONObject attributeObject = jsonObject.getJSONObject("attributes").getJSONObject(k);

                filterCategoryDTO.setName(attributeObject.getString("name"));

                List<FilterCategoryOption> filterCategoryOptionDTOS = new ArrayList<>();

                JSONArray valuesArray = attributeObject.getJSONArray("values");

                for (int i = 0; i < valuesArray.length(); i++) {
                    FilterCategoryOption filterCategoryOptionDTO = new FilterCategoryOption();
                    filterCategoryOptionDTO.setFilterCategory(filterCategoryDTO);
                    filterCategoryOptionDTO.setName(valuesArray.getString(i));


                    filterCategoryOptionDTOS.add(filterCategoryOptionDTO);
                    entityManager.persist(filterCategoryOptionDTO);
                }

                filterCategoryDTO.setOptions(filterCategoryOptionDTOS);
                entityManager.persist(filterCategoryDTO);

                filterCategoryDTOS.put(k, filterCategoryDTO);
            }
        }

        return filterCategoryDTOS;
    }

    public List<CatalogProduct> catalogProductDTOS(CatalogSubCategory catalogSubcategoryDTO, HashMap<String,
            FilterCategory> filterCategoryDTOHashMap, JSONObject jsonObject) {

        List<CatalogProduct> catalogProductDTOS = new ArrayList<>();

        JSONArray jsonArray = jsonObject.getJSONObject("products").getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);

            CatalogProduct catalogProductDTO = new CatalogProduct();
            catalogProductDTO.setName(item.getString("description"));
            catalogProductDTO.setPrice(Long.valueOf(item.getString("price")));
            catalogProductDTO.setCatalogCategory(catalogSubcategoryDTO);

            JSONObject object = item.getJSONObject("attributes_json");

            List<FilterCategoryOption> filterCategoryOptionDTOS = new ArrayList<>();

            Iterator<String> keys = object.keys();

            while (keys.hasNext()) {
                String k = keys.next();

                if (!k.equals("name") && !k.equals("description")) {
                    try {
                        FilterCategoryOption filterCategoryOption = filterCategoryDTOHashMap.get(k).getCategoryByName(object.getString(k));
                        filterCategoryOption.getProducts().add(catalogProductDTO);

                        filterCategoryOptionDTOS.add(filterCategoryOption);
                    } catch (Exception ignored) {
                    }
                }
                catalogProductDTO.setCategoryOptions(filterCategoryOptionDTOS);
            }
            entityManager.persist(catalogProductDTO);
            catalogProductDTOS.add(catalogProductDTO);
        }

        return catalogProductDTOS;
    }

    public CatalogSubCategory createSubcategory(JSONObject jsonObject) {
        CatalogSubCategory catalogSubcategoryDTO = new CatalogSubCategory();
        catalogSubcategoryDTO.setName(jsonObject.getJSONObject("products").getJSONArray("items").getJSONObject(0).getString("sort_name"));
        catalogSubcategoryDTO.setFlag(jsonObject.getJSONObject("products").getJSONArray("items").getJSONObject(0).getString("url"));

        entityManager.persist(catalogSubcategoryDTO);
        return catalogSubcategoryDTO;
    }
}
