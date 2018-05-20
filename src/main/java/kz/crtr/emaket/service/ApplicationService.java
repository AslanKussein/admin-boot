package kz.crtr.emaket.service;

import kz.crtr.emaket.entity.gson.GsonTData;
import kz.crtr.emaket.entity.tbl.DCategory;
import kz.crtr.emaket.entity.tbl.TData;
import kz.crtr.emaket.entity.tbl.TImage;
import kz.crtr.emaket.gson.GsonResult;
import kz.crtr.emaket.local.AppImpl;
import kz.crtr.emaket.repository.DCategorryRepo;
import kz.crtr.emaket.repository.TDataRepo;
import kz.crtr.emaket.repository.TImageRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static kz.crtr.emaket.utils.Util.*;

@Service()
@Log
public class ApplicationService implements AppImpl {

    @Autowired
    TImageRepo tImageRepo;
    @Autowired
    TDataRepo dataRepo;
    @Autowired
    DCategorryRepo dCategorryRepo;

    @Getter
    @Setter
    private String cId;

    @Override
    public void saveUploadedFiles(MultipartFile file) throws IOException {
        cId = null;
        byte[] bytes = file.getBytes();
        if (bytes.length != 0) {
            cId = createGuid();
            try {
                TImage image = new TImage();
                image.setId(cId);
                image.setFileName(file.getOriginalFilename());
                image.setContent(bytes);
                tImageRepo.save(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public GsonResult saveTData(String fileName, BigDecimal cost, Integer count, String desc, int category) {
        if (!isNullOrEmpty(cId)) {

            TData tData = new TData();
            tData.setId(createGuid());
            tData.setImgId(cId);
            tData.setName(fileName);
            tData.setCost(cost);
            tData.setCount(count);
            tData.setDescr(desc);
            tData.setCatId(category);
            dataRepo.save(tData);

            return getGsonResult(Boolean.TRUE, "Сакталды");
        }
        return getGsonResult(Boolean.FALSE, "Суретты сактаныз");
    }

    @Override
    public List<DCategory> getDcategory() {
        return dCategorryRepo.findAll();
    }

    @Override
    public GsonResult getDcategoryById(int id) {
        List<TData> list = dataRepo.findAllByCatId(id);
        if (!isNullOrEmptyCollection(list)) {
            return getGsonResult(Boolean.TRUE, wrapToGsonLastVAppList(list));
        }
        return getGsonResult(Boolean.FALSE, "тауар жок");
    }

    private List<GsonTData> wrapToGsonLastVAppList(List<TData> list) {
        List<GsonTData> gsonList = newArrayList();
        list.forEach(s -> gsonList.add(wrapToGsonLastVApp(s)));
        return gsonList;
    }

    private GsonTData wrapToGsonLastVApp(TData obj) {
        if (obj != null) {
            GsonTData gson = new GsonTData();
            gson.setId(obj.getId());
            gson.setDescr(obj.getDescr());
            gson.setCost(obj.getCost());
            gson.setCount(obj.getCount());
            gson.setName(obj.getName());
            if (!isNullOrEmpty(obj.getImgId())) {
                TImage image = tImageRepo.findAllById(obj.getImgId());
                if (image != null) {
                    gson.setImageName(image.getFileName());
//
                    StringBuilder sb = new StringBuilder();
                    sb.append("data:image/png;base64,");
                    sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getContent(), false)));

                    gson.setImgBdy(sb.toString());
                }
            }

            return gson;
        }

        return null;
    }

    @Override
    public GsonResult createCat(String name) {
        int id = dCategorryRepo.findAll().size();

        DCategory dCategory = new DCategory();
        dCategory.setId(id+1);
        dCategory.setName(name);
        dCategorryRepo.save(dCategory);
        return getGsonResult(Boolean.TRUE, "Сакталды");
    }

    @Override
    public GsonResult getTovarByDcategoryByName(String name) {
        List<TData> list = dataRepo.findAllByNameContaining(name.toUpperCase());
        if (!isNullOrEmptyCollection(list)) {
            return getGsonResult(Boolean.TRUE, wrapToGsonLastVAppList(list));
        }
        return getGsonResult(Boolean.FALSE, "тауар жок");
    }
}