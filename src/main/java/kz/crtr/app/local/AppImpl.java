package kz.crtr.app.local;

import kz.crtr.app.entity.tbl.DCategory;
import kz.crtr.app.gson.GsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author a.kussein
 */
public interface AppImpl {
    void saveUploadedFiles(MultipartFile file) throws IOException;
    GsonResult saveTData(String fileName, BigDecimal cost, Integer count, String desc, int category);
    List<DCategory> getDcategory();
    GsonResult getDcategoryById(int id);
    GsonResult getTovarByDcategoryByName(String name);
    GsonResult createCat(String name);
}