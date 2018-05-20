//package kz.crtr.emaket.wrapper;
//
//import kz.crtr.emaket.entity.tbl.domain.SCause;
//import kz.crtr.emaket.entity.tbl.domain.ZDoc;
//import kz.crtr.emaket.entity.tbl.domain.ZOsn;
//import kz.crtr.emaket.gson.*;
//
//import static kz.crtr.emaket.service.DocService.getMsolMoreDetail;
//import static kz.crtr.emaket.utils.DateUtils.dateToString;
//import static kz.crtr.emaket.utils.Util.*;
//
//public class DocWrapper {
//
//    public static GsonDeclaration wrapToGsonDeclaration(ZDoc obj, GsonUPart uPart) {
//
//        if (obj != null) {
//            GsonDeclaration gson = new GsonDeclaration();
//            gson.setId(obj.getId());
//            gson.setNum(obj.getNum());
//            gson.setDReg(dateToString(obj.getDReg()));
//            gson.setDInp(dateToString(obj.getDInp()));
//            gson.setBrid(obj.getBrid());
//            gson.setZOsn(wrapToGsonZOsn(obj.getIdOsn()));
//
//            GsonMsolMoreDetail moreDetail = getMsolMoreDetail(obj);
//
//            gson.setNumb(moreDetail.getNumb());
//            gson.setRn(obj.getPersonId().getRn());
//            gson.setName(getUserFullName(obj.getPersonId().getLastname(), obj.getPersonId().getFirstname(), obj.getPersonId().getMiddlename()));
//            gson.setBirthdate(dateToString(obj.getPersonId().getBirthdate()));
//            gson.setPc(moreDetail.getPc());
//            gson.setUsername(obj.getUsr());
//            gson.setDResh(moreDetail.getDResh());
//            gson.setNsum(moreDetail.getNSum());
//            gson.setDNaz(moreDetail.getDNaz());
//            gson.setTip(getInscriptionByLang(obj.getIdTip().getName(), obj.getIdTip().getNameKz(), obj.getIdTip().getNameLn()));
//
//            if (obj.getIdTip().getId().equals("GVN")) {
//                if (isNullOrEmpty(moreDetail.getNumb())) {
//                    if (uPart.getIsBscShr().equals(newBigDecimal("1"))) {
//                        gson.setIsBscN(1);
//                        gson.setIsBscTxt("<span style='color: green' class='trashBtn webix_icon fa-check-circle-o'></span>");
//                        gson.setIsBsc(uPart.getIsBscShrTxt());
//                    } else if (uPart.getIsBscShr().equals(newBigDecimal("2"))) {
//                        gson.setIsBscN(1);
//                        gson.setIsBscTxt("<span style='color: red' class='trashBtn webix_icon fa-ban'></span>");
//                        gson.setIsBsc(uPart.getIsBscShrTxt());
//                    } else {
//                        gson.setIsBscTxt("<span style='background: none'></span>");
//                        gson.setIsBsc("");
//                    }
//                } else {
//                    gson.setIsBscTxt("<span style='background: none'></span>");
//                    gson.setIsBsc("");
//                }
//            }
//
//            return gson;
//        }
//        return null;
//    }
//
//    private static GsonZOsn wrapToGsonZOsn(ZOsn obj) {
//        GsonZOsn gson = new GsonZOsn();
//        if (obj != null) {
//            gson.setIdGr(bigIntegerToLong(obj.getIdGr()));
//            gson.setIsVis(bigIntegerToLong(obj.getIsVis()));
//            gson.setIdGr2(bigIntegerToLong(obj.getIdGr2()));
//            gson.setSvrsubcode(obj.getSvrsubcode());
//            gson.setOrd(bigIntegerToLong(obj.getOrd()));
//            gson.setIdCause(wrapToGsonSCause(obj.getIdCause()));
//            gson.setEstday(bigIntegerToLong(obj.getEstday()));
//            gson.setId(obj.getId());
//            gson.setIdSgp(bigIntegerToLong(obj.getIdSgp()));
//            gson.setSvrcode(obj.getSvrcode());
//            gson.setFullName(getZOsnFullName(obj));
//            if (obj.getIdh() != null) {
//                gson.setIdh(obj.getIdh().getId());
//            }
//            gson.setValue(getInscriptionByLang(obj.getName(), obj.getNameKz(), obj.getNameLn()));
//        }
//        return gson;
//    }
//
//    private static GsonSCause wrapToGsonSCause(SCause obj) {
//        if (obj != null) {
//            GsonSCause gson = new GsonSCause();
//            gson.setId(obj.getId());
//            gson.setNameKz(obj.getNameKz());
//            gson.setName(obj.getName());
//            gson.setOrd(obj.getOrd());
//            return gson;
//        }
//
//        return null;
//    }
//
//    private static String getZOsnFullName(ZOsn obj) {
//        if (obj.getIdh() != null) {
//            return getInscriptionByLang(
//                    getDelimetrForDic(1, obj.getIdh().getName(), obj.getName()),
//                    getDelimetrForDic(1, obj.getIdh().getNameKz(), obj.getNameKz()),
//                    getDelimetrForDic(1, obj.getIdh().getNameLn(), obj.getIdh().getNameLn())
//            );
//        }
//        return obj.getName();
//    }
//}
