//package kz.crtr.app.wrapper;
//
//import kz.crtr.app.domain.PageCommon;
//import kz.crtr.app.entity.tbl.domain.ExcControlLog;
//import kz.crtr.app.entity.tbl.view.SCalendar;
//import kz.crtr.app.entity.tbl.view.VChangedSicid;
//import kz.crtr.app.entity.tbl.view.VInflation;
//import kz.crtr.app.entity.tbl.view.VMaketCombine;
//import kz.crtr.app.entity.websecurity.SCalendarLog;
//import kz.crtr.app.gson.*;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//import static kz.crtr.app.service.OtherService.getType;
//import static kz.crtr.app.utils.DateUtils.dateToString;
//import static kz.crtr.app.utils.Util.*;
//
//public class OtherWrapper {
//
//    private static GsonVInflation wrapToGsonVInflation(VInflation obj) {
//        if (obj != null) {
//            GsonVInflation gson = new GsonVInflation();
//            gson.setYear(obj.getYear());
//            gson.setJan(getDelimetredSummForGgos(obj.getJan(), "##0.00"));
//            gson.setFeb(getDelimetredSummForGgos(obj.getFeb(), "##0.00"));
//            gson.setMar(getDelimetredSummForGgos(obj.getMar(), "##0.00"));
//            gson.setApr(getDelimetredSummForGgos(obj.getApr(), "##0.00"));
//            gson.setMay(getDelimetredSummForGgos(obj.getMay(), "##0.00"));
//            gson.setJun(getDelimetredSummForGgos(obj.getJun(), "##0.00"));
//            gson.setJul(getDelimetredSummForGgos(obj.getJul(), "##0.00"));
//            gson.setAug(getDelimetredSummForGgos(obj.getAug(), "##0.00"));
//            gson.setSep(getDelimetredSummForGgos(obj.getSep(), "##0.00"));
//            gson.setOct(getDelimetredSummForGgos(obj.getOct(), "##0.00"));
//            gson.setNov(getDelimetredSummForGgos(obj.getNov(), "##0.00"));
//            gson.setDec(getDelimetredSummForGgos(obj.getDec(), "##0.00"));
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonVInflation> wrapToGsonVInflationcList(List<VInflation> list) {
//        List<GsonVInflation> gsonList = newArrayList();
//        list.forEach(o -> gsonList.add(wrapToGsonVInflation(o)));
//        return gsonList;
//    }
//
//    private static GsonScalendar wrapGsoSCalendarData(SCalendar obj) {
//        if (obj != null) {
//            GsonScalendar gson = new GsonScalendar();
//            gson.setType(getType(obj.getType()));
//            gson.setId(dateToString(obj.getDat()));
//            gson.setTypeId(obj.getType());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonScalendar> wrapToGsonSCalendarList(List<SCalendar> list) {
//        List<GsonScalendar> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapGsoSCalendarData(s)));
//        return gsonList;
//    }
//
//    private static GsonScalendar wrapGsoSCalendarLogData(SCalendarLog obj) {
//        if (obj != null) {
//            GsonScalendar gson = new GsonScalendar();
//            gson.setId(dateToString(obj.getDat()));
//            gson.setType(getType(obj.getType()));
//            gson.setUser(obj.getUser());
//            gson.setIp(obj.getIpAddress());
//            gson.setHost(obj.getHost());
//            gson.setCDate(dateToString(obj.getChangedate()));
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonScalendar> wrapToGsonSCalendarLogList(List<SCalendarLog> list) {
//        List<GsonScalendar> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapGsoSCalendarLogData(s)));
//        return gsonList;
//    }
//
//    private static GsonExceptionData wrapToGsonExcControlLog(ExcControlLog obj) {
//        if (obj != null) {
//            GsonExceptionData gson = new GsonExceptionData();
//            gson.setId(obj.getId());
//            gson.setName(getUserFullName(obj.getPerson().getFirstname(), obj.getPerson().getLastname(), obj.getPerson().getMiddlename()));
//            gson.setAction(obj.getType().equalsIgnoreCase("insert") ? "Добавлено" : "Удалено");
//            gson.setDat(dateToString(obj.getDat()));
//            gson.setUName(obj.getUser());
//            gson.setId(obj.getId());
//            gson.setRn(obj.getPerson().getRn());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonExceptionData> wrapToGsonExcControlLogList(List<ExcControlLog> list) {
//        List<GsonExceptionData> gsonList = newArrayList();
//        list.forEach(o -> gsonList.add(wrapToGsonExcControlLog(o)));
//        return gsonList;
//    }
//
//    // wrapper оболочка для Page
//    public static PageCommon wrapPageToPageCommon(Page page) {
//        PageCommon gson = new PageCommon();
//        gson.setNumber(page.getNumber());
//        gson.setSize(page.getSize());
//        gson.setTotalElements(page.getTotalElements());
//        gson.setTotalPages(page.getTotalPages());
//        gson.setContent(wrapToGsonExcControlLogList(page.getContent()));
//        return gson;
//    }
//
//    private static GsonVCombinEmd wrapToGsonVMaketCombine(VMaketCombine obj) {
//        if (obj != null) {
//            GsonVCombinEmd gson = new GsonVCombinEmd();
//            gson.setId(obj.getId());
//            gson.setPc(obj.getPc());
//            gson.setNumb(obj.getNumb());
//            gson.setZDate(dateToString(obj.getZDate()));
//            gson.setDNaz(dateToString(obj.getDNaz()));
//            gson.setNsum(obj.getNsum());
//            gson.setNResh(obj.getNResh());
//            gson.setSt(obj.getSt());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonVCombinEmd> wrapToGsonVMaketCombineList(List<VMaketCombine> list) {
//        List<GsonVCombinEmd> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonVMaketCombine(s)));
//        return gsonList;
//    }
//
//    private static GsonVCombinEmd wrapToGsonVChangedSicid(VChangedSicid obj) {
//        if (obj != null) {
//            GsonVCombinEmd gson = new GsonVCombinEmd();
//            gson.setId(obj.getSid());
//            gson.setPc(obj.getPc());
//            gson.setZDate(dateToString(obj.getZDate()));
//            gson.setNResh(obj.getNResh());
//            gson.setSt(obj.getSt());
//            gson.setMid(obj.getMid());
//            gson.setBrid(obj.getBrid());
//            gson.setBrid(obj.getBrid());
//            gson.setSicid(obj.getSicid().getSicid());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonVCombinEmd> wrapToGsonVChangedSicidList(List<VChangedSicid> list) {
//        List<GsonVCombinEmd> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonVChangedSicid(s)));
//        return gsonList;
//    }
//
//    private static GsonDecData wrapToGsonRestoreMaket(GsonRestoreMaket obj) {
//        if (obj != null) {
//            GsonDecData gson = new GsonDecData();
//            gson.setId(obj.getId());
//            gson.setNsum(obj.getNsum());
//            gson.setNresh(obj.getN_resh());
//            gson.setSicId(obj.getSicid());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonDecData> wrapToGsonRestoreMaketList(List<GsonRestoreMaket> list) {
//        List<GsonDecData> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonRestoreMaket(s)));
//        return gsonList;
//    }
//}
