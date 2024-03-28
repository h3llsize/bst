package old.fillbase.fillbase.excelparser;

import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

@Component
public class ExcelParser {
    private final String excelPath = "C:\\Users\\shabby\\Downloads\\tablica.xlsx";
    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;


    public ExcelParser(CatalogSubcategoryRepository catalogSubcategoryRepository) {
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
    }

    private void init() throws IOException {
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream(excelPath));

        Iterator<Row> rowIterator = hssfWorkbook.getSheetAt(0).rowIterator();
        System.out.println("Start parsing");
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();



            Long id = (long) row.getCell(0).getNumericCellValue();

            if(id > 90)
                updateCatalogSubcategory(id, row);
        }
    }

    private void updateCatalogSubcategory(Long id, Row row) {
        String sql = "INSERT INTO catalog_subcategory (id, advantages, range, description, manufacturing, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "advantages = EXCLUDED.advantages, range = EXCLUDED.range, " +
                "description = EXCLUDED.description, manufacturing = EXCLUDED.manufacturing, " +
                "image_path = EXCLUDED.image_path";

        jdbcTemplate.update(
                sql,
                id,
                getStringValue(row.getCell(1)),
                getStringValue(row.getCell(2)),
                getStringValue(row.getCell(3)),
                getStringValue(row.getCell(4)),
                "/apps/bst/img/" + getStringValue(row.getCell(5))
        );

        System.out.println("Set for id " + id);
    }

    private String getStringValue(Cell cell) {
        return (cell != null) ? cell.getStringCellValue() : "";
    }
}
