package old.fillbase.fillbase;

import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class CategoryParser {

    private final BaseFiller baseFiller;
    private final CatalogCategoryRepository catalogCategoryRepository;

    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    private ChromeDriver chromeDriver;

    public CategoryParser(BaseFiller baseFiller, CatalogCategoryRepository catalogCategoryRepository, CatalogSubcategoryRepository catalogSubcategoryRepository) {
        this.baseFiller = baseFiller;
        this.catalogCategoryRepository = catalogCategoryRepository;
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
    }


    public void parse() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\shabby\\IdeaProjects\\site\\bst\\met-trans-items-parser\\src\\main\\resources\\chromedriver.exe");
        chromeDriver = new ChromeDriver();

        parseItems();
    }

    public void parseItems() {
        String url = "https://perm.met-trans.ru/metalloprokat";
        chromeDriver.get(url);

        Document document = Jsoup.parse(chromeDriver.getPageSource());
        Elements elements = document.select("div.section-category");

        for (int i = 0 ; i < elements.size(); i++) {
            Element element = elements.get(i);

            Element el = element.select("a.section-category__title").first();

            String categoryTitle = el.text();
            String categoryUrl = el.attr("href");

            parseSubcategoryItems(categoryUrl, categoryTitle);
        }
    }

    public void parseSubcategoryItems(String url, String categoryTitle) {
        chromeDriver.get("https://perm.met-trans.ru" + url);
        Document document = Jsoup.parse(chromeDriver.getPageSource());

        Elements elements = document.select("div.section-category");

        String slug = TextHelper.convertToSlug(categoryTitle);

        CatalogCategory catalogCategory = catalogCategoryRepository.findByFlag(slug);

        if(catalogCategoryRepository.findByFlag(slug) == null) {
            catalogCategory = new CatalogCategory();
            catalogCategory.setFlag(TextHelper.convertToSlug(categoryTitle));
            catalogCategory.setName(categoryTitle);

            catalogCategory = catalogCategoryRepository.save(catalogCategory);
        }


        for (int i = 0 ; i < elements.size(); i++) {
            Element element = elements.get(i);

            Element el = element.select("a.section-category__title").first();

            String subCategoryTitle = el.text();
            String subcategoryUrl = el.attr("href");

            parseSubcategory(subcategoryUrl, subCategoryTitle, catalogCategory);
        }
    }

    public void parseSubcategory(String url, String subCategoryTitle, CatalogCategory catalogCategory) {
        chromeDriver.get("https://perm.met-trans.ru" + url);
        Document document = Jsoup.parse(chromeDriver.getPageSource());

        Elements elements = document.select("div.section-category");
        if(!elements.isEmpty()) {
            parseSubcategoryItems(url, subCategoryTitle);
            }
        else {
            try {
                if(catalogSubcategoryRepository.findByFlag(TextHelper.convertToSlug(subCategoryTitle)) == null)
                    baseFiller.parseCategory(catalogCategory, subCategoryTitle,
                        document.select("div[itemprop=description]").text(), parseJs(document.select("div.layout-product__main").select("script").get(0).data()));
                else
                    baseFiller.parseCategory(catalogCategory,catalogSubcategoryRepository.findByFlag(TextHelper.convertToSlug(subCategoryTitle)), subCategoryTitle,
                            document.select("div[itemprop=description]").text(), parseJs(document.select("div.layout-product__main").select("script").get(0).data()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject parseJs(String jsCode) {
        int start = jsCode.indexOf("{");
        int end = jsCode.lastIndexOf("}") + 1;
        String jsonStr = jsCode.substring(start, end);

        JSONObject jsonObject = new JSONObject(jsonStr);

        return jsonObject;
    }
}
