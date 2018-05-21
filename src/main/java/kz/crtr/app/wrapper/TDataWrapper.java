//package kz.crtr.app.wrapper;
//
//import kz.crtr.app.entity.gson.GsonTData;
//import kz.crtr.app.entity.tbl.TData;
//import kz.crtr.app.entity.tbl.TImage;
//import kz.crtr.app.repository.TImageRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//import static kz.crtr.app.utils.Util.isNullOrEmpty;
//import static kz.crtr.app.utils.Util.newArrayList;
//
//public class TDataWrapper {
//
//    @Autowired
//    TImageRepo tImageRepo;
//
//    public List<GsonTData> wrapToGsonLastVAppList(List<TData> list) {
//        List<GsonTData> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonLastVApp(s)));
//        return gsonList;
//    }
//
//    private GsonTData wrapToGsonLastVApp(TData obj) {
//        if (obj != null) {
//            GsonTData gson = new GsonTData();
//            gson.setId(obj.getId());
//            gson.setDescr(obj.getDescr());
//            gson.setCost(obj.getCost());
//            gson.setCount(obj.getCount());
//            if (!isNullOrEmpty(obj.getImgId())) {
//                TImage image = tImageRepo.findAllById(obj.getImgId());
//                if (image != null) {
//                    gson.setImageName(image.getFileName());
//                    gson.setImgBdy(new String(image.getContent(), StandardCharsets.UTF_8));
//                }
//            }
//
//            return gson;
//        }
//
//        return null;
//    }
//}
