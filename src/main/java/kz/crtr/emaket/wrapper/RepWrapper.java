//package kz.crtr.emaket.wrapper;
//
//import kz.crtr.emaket.dto.GsonReportDTO;
//import kz.crtr.emaket.entity.tbl.domain.OrderForReport;
//import kz.crtr.emaket.entity.tbl.view.VOrderForReport;
//import kz.crtr.emaket.entity.gson.GsonDAction;
//import kz.crtr.emaket.gson.GsonOrderForReport;
//
//import java.util.List;
//
//import static kz.crtr.emaket.utils.Constant.DEF_PATT_VALUE_SIMICOLON;
//import static kz.crtr.emaket.utils.DateUtils.dateToString;
//import static kz.crtr.emaket.utils.Util.getInscriptionByLang;
//import static kz.crtr.emaket.utils.Util.newArrayList;
//import static kz.crtr.emaket.utils.Util.nvl;
//
//public class RepWrapper {
//
//    private static GsonOrderForReport wrapToGsonOrderForReport(VOrderForReport obj) {
//        if (obj != null) {
//            GsonOrderForReport gson = new GsonOrderForReport();
//            gson.setIdReport(obj.getIdReport().getNameRus());
//            gson.setDReprotId(obj.getIdReport().getId());
//            gson.setUName(obj.getUName().getUName());
//            gson.setBegDate(nvl(dateToString(obj.getBegDate(), DEF_PATT_VALUE_SIMICOLON), ""));
//            gson.setEndDate(nvl(dateToString(obj.getEndDate(), DEF_PATT_VALUE_SIMICOLON), ""));
//            gson.setId(obj.getId());
//            gson.setParams(obj.getParams());
//            gson.setStatus(obj.getStatus());
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonOrderForReport> wrapToGsonOrderForReportList(List<VOrderForReport> list) {
//        List<GsonOrderForReport> gsonList = newArrayList();
//        list.forEach(o -> gsonList.add(wrapToGsonOrderForReport(o)));
//        return gsonList;
//    }
//
//    private static GsonDAction wrapToGsonReportGFSSView(GsonReportDTO obj) {
//        if (obj != null) {
//            GsonDAction gson = new GsonDAction();
//            gson.setId(obj.getId());
//            gson.setParentId(obj.getParentId());
//            gson.setActionType(obj.getActionType());
//            gson.setMaskId(obj.getMaskId());
//            gson.setRepId(obj.getRepId());
//            gson.setCmd(obj.getCmd());
//            gson.setAsync(obj.getAsync());
//            gson.setValue(getInscriptionByLang(obj.getNameRus(), obj.getNameKaz()));
//            gson.setMaskValue(getInscriptionByLang(obj.getMaskNameRus(), obj.getMaskNameKaz()));
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonDAction> wrapToGsonReportGFSSViewList(List<GsonReportDTO> list) {
//        List<GsonDAction> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonReportGFSSView(s)));
//        return gsonList;
//    }
//}