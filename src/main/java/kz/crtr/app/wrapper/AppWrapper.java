//package kz.crtr.app.wrapper;
//
//import kz.crtr.app.entity.tbl.domain.LastVApp;
//import kz.crtr.app.gson.GsonLastVApp;
//
//import java.util.List;
//
//import static kz.crtr.app.utils.DateUtils.dateToString;
//import static kz.crtr.app.utils.Util.newArrayList;
//
//public class AppWrapper {
//
//    public static List<GsonLastVApp> wrapToGsonLastVAppList(List<LastVApp> list) {
//        List<GsonLastVApp> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonLastVApp(s)));
//        return gsonList;
//    }
//
//    private static GsonLastVApp wrapToGsonLastVApp(LastVApp obj) {
//        if (obj != null) {
//            GsonLastVApp gson = new GsonLastVApp();
//            gson.setId(obj.getId());
//            gson.setAppId(obj.getAppId());
//            gson.setAppNum(obj.getAppNum());
//            gson.setChangedate(dateToString(obj.getChangedate()));
//            gson.setMiddlename(obj.getMiddlename());
//            gson.setUName(obj.getUName());
//            gson.setBirthdate(dateToString(obj.getBirthdate()));
//            gson.setLastname(obj.getLastname());
//            gson.setFirstname(obj.getFirstname());
//            gson.setRn(obj.getIin());
//            return gson;
//        }
//
//        return null;
//    }
//}