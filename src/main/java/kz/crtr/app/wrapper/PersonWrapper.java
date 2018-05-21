//package kz.crtr.app.wrapper;
//
//import kz.crtr.app.dto.GsonVEappApplicationDTO;
//import kz.crtr.app.entity.tbl.domain.LastVPerson;
//import kz.crtr.app.entity.tbl.domain.Person;
//import kz.crtr.app.entity.tbl.domain.ZCalc;
//import kz.crtr.app.entity.gson.GsonTData;
//import kz.crtr.app.gson.*;
//
//import java.util.List;
//
//import static kz.crtr.app.utils.Constant.DEF_DD_MM_YYYY_DOT;
//import static kz.crtr.app.utils.Constant.DEF_YYYY_MM_DD;
//import static kz.crtr.app.utils.DateUtils.dateToString;
//import static kz.crtr.app.utils.Util.*;
//
//public class PersonWrapper {
//
//    private static GsonLastVPerson wrapToGsonLastVPerson(LastVPerson obj) {
//        if (obj != null) {
//            GsonLastVPerson gson = new GsonLastVPerson();
//            gson.setId(obj.getId());
//            gson.setChangedate(dateToString(obj.getChangedate()));
//            gson.setMiddlename(obj.getMiddlename());
//            gson.setUName(obj.getUName());
//            gson.setBirthdate(dateToString(obj.getBirthdate()));
//            gson.setLastname(obj.getLastname());
//            gson.setFirstname(obj.getFirstname());
//            gson.setRn(obj.getIin());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonLastVPerson> wrapToGsonLastVPersonList(List<LastVPerson> list) {
//        List<GsonLastVPerson> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonLastVPerson(s)));
//        return gsonList;
//    }
//
//    public static GsonTData wrapToGsonZCalc(ZCalc obj) {
//        if (obj != null) {
//            GsonTData gson = new GsonTData();
//            gson.setId(obj.getId());
//            gson.setSicid(obj.getSicid().getSicid());
//            gson.setBrid(obj.getBrid());
//            gson.setPc(obj.getPc());
//            gson.setDateObr(dateToString(obj.getDInp()));
//            gson.setDateAppoint(dateToString(obj.getDNaz()));
//            gson.setNsum(obj.getNsum());
//
//            gson.setExcludingSmd(obj.getNosal());
//            if (obj.getSalbeg() != null && obj.getSalend() != null) {
//                gson.setBeginSalary(dateToString(obj.getSalbeg()));
//                gson.setEndSalary(dateToString(obj.getSalend()));
//            }
//
//            gson.setBestSmz(obj.getBestsal());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static GsonPerson wrapGblPersonNewToGsonPerson(Person person) {
//        if (person != null) {
//            GsonPerson gson = new GsonPerson();
//            gson.setSex(person.getSex());
//            gson.setRn(objectToString(person.getRn()));
//            gson.setLastname(objectToString(person.getLastname()));
//            gson.setBirthdate(dateToString(person.getBirthdate(), DEF_DD_MM_YYYY_DOT));
//            gson.setSic(person.getSic());
//            gson.setMiddlename(objectToString(person.getMiddlename()));
//            gson.setFirstname(objectToString(person.getFirstname()));
//            gson.setSicid(person.getSicid());
//            gson.setFullName(getUserFullName(person.getLastname(), person.getFirstname(), person.getMiddlename()));
//            gson.setSic(person.getSic());
//            if (person.getDoctype() != null) {
//                gson.setDoctype(person.getDoctype().getName());
//            }
//            gson.setDocdate(dateToString(person.getDocdate(), DEF_DD_MM_YYYY_DOT));
//            gson.setDocnum(person.getDocnum());
//            gson.setBirthplace(person.getBirthplace());
//            gson.setDocplace(person.getDocplace());
//            gson.setAddress(person.getAddress());
//            return gson;
//        }
//        return null;
//    }
//
//    public static GsonPerson wrapToGsonPerson(Person obj) {
//        if (obj != null) {
//            GsonPerson gson = new GsonPerson();
//            gson.setSicid(obj.getSicid());
//            gson.setRnn(obj.getRnn());
//            gson.setSex(obj.getSex());
//            gson.setRn(objectToString(obj.getRn()));
//            gson.setRmiddlename(obj.getRmiddlename());
//            gson.setLastname(objectToString(obj.getLastname()));
//            gson.setFullName(getUserFullName(obj.getLastname(), obj.getFirstname(), obj.getMiddlename()));
//            gson.setWorkplace(obj.getWorkplace());
//            gson.setBranchid(obj.getBranchid());
//            gson.setSiapId(obj.getSiapId());
//            gson.setResident(obj.getResident());
//            gson.setDocser(obj.getDocser());
//            gson.setEmpid(obj.getEmpid());
//            gson.setArea(obj.getArea());
//            gson.setRfirstname(obj.getRfirstname());
//            gson.setRegdate(dateToString(obj.getRegdate()));
//            gson.setRbranchid(obj.getRbranchid());
//            gson.setBirthdate(dateToString(obj.getBirthdate(), DEF_DD_MM_YYYY_DOT));
//            gson.setStatusgen(obj.getStatusgen());
//            gson.setActdate(dateToString(obj.getActdate()));
//            gson.setHomephone(obj.getHomephone());
//            gson.setActid(obj.getActid());
//            gson.setWorkphone(obj.getWorkphone());
//            gson.setDocplace(objectToString(obj.getDocplace()));
//            gson.setDocnum(objectToString(obj.getDocnum()));
//            gson.setDocdate(dateToString(obj.getDocdate(), DEF_YYYY_MM_DD));
//            gson.setSic(obj.getSic());
//            gson.setMiddlename(objectToString(obj.getMiddlename()));
//            gson.setRlastname(obj.getRlastname());
//            gson.setOrdnum(integerToLong(obj.getOrdnum()));
//            gson.setStatus(obj.getStatus());
//            gson.setFirstname(objectToString(obj.getFirstname()));
//            gson.setRegdateOld(dateToString(obj.getRegdateOld()));
//            gson.setSicid(obj.getSicid());
//            gson.setStgenold(obj.getStgenold());
//            gson.setCategory(obj.getCategory());
//            gson.setBirthplace(obj.getBirthplace());
//            gson.setAddress(objectToString(obj.getAddress()));
//            if (obj.getDoctype() != null) {
//                gson.setDoctype(obj.getDoctype().getName());
//            }
//            gson.setFilldate(dateToString(obj.getFilldate()));
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static GsonVEappApplication wrapToGsonVEappApplications(GsonVEappApplicationDTO obj) {
//        if (obj != null) {
//            GsonVEappApplication gson = new GsonVEappApplication();
//            gson.setId(obj.getId());
//            gson.setDReg(dateToString(obj.getDReg()));
//            gson.setDInp(dateToString(obj.getDInp()));
//            if (obj.getNumb() != null) {
//                gson.setNumb(obj.getNumb());
//            }
//            gson.setAppBrid(obj.getAppBrid());
//            gson.setDNaz(dateToString(obj.getDNaz()));
//            gson.setJoblessDate(dateToString(obj.getJoblessDate()));
//            gson.setIdTip(obj.getIdTip().getId());
//            gson.setSicid(wrapToGsonPerson(obj.getSicid()));
//            gson.setMSolSicid(wrapToGsonPerson(obj.getMSolsicid()));
//            gson.setAppNum(obj.getAppNum());
//            gson.setIdSour(obj.getIdSour());
//            gson.setPc(obj.getPc());
//            gson.setIdOsn(obj.getIdOsn());
//            gson.setBrid(obj.getBrid());
//            gson.setSt(obj.getSt());
//            gson.setNsum(obj.getNsum());
//            gson.setIdSour(obj.getIdSour());
//            gson.setIdSourType(obj.getIdSourType());
//            gson.setReasonName(getInscriptionByLang(obj.getOsnName(), obj.getOsnNameKz(), obj.getOsnNameLn()));
//            gson.setTypeName(getInscriptionByLang(obj.getTypeName(), obj.getTypeNameKz(), obj.getTypeNameLn()));
//            gson.setSourceName(getInscriptionByLang(obj.getSourceName(), obj.getSourceNameKz(), obj.getSourceNameLn()));
//            gson.setNameTip(getInscriptionByLang(obj.getIdTip().getName(), obj.getIdTip().getNameKz(), obj.getIdTip().getNameLn()));
//            gson.setOrd(obj.getOrd());
//
//            return gson;
//        }
//
//        return null;
//    }
//
//    private static GsonVEappDocprev wrapToGsonVEappDocprev(GsonPrevDeleted obj) {
//        if (obj != null) {
//            GsonVEappDocprev gson = new GsonVEappDocprev();
//            gson.setSicid(obj.getSicid());
//            gson.setId(obj.getId());
//            gson.setIdSour(obj.getIdSour());
//            gson.setIdOsn(obj.getIdOsn());
//            gson.setIdTip(obj.getIdTip());
//            gson.setOsnName(getInscriptionByLang(obj.getOsnName(), obj.getOsnNameKz(), obj.getOsnNameLn()));
//            gson.setTipName(getInscriptionByLang(obj.getTipName(), obj.getTipNameKz(), obj.getTipNameLn()));
//            gson.setSourName(getInscriptionByLang(obj.getSourName(), obj.getSourNameKz(), obj.getSourNameLn()));
//            return gson;
//        }
//
//        return null;
//    }
//
//    public static List<GsonVEappDocprev> wrapToGsonVEappDocprevList(List<GsonPrevDeleted> list) {
//        List<GsonVEappDocprev> gsonList = newArrayList();
//        list.forEach(s -> gsonList.add(wrapToGsonVEappDocprev(s)));
//        return gsonList;
//    }
//}
