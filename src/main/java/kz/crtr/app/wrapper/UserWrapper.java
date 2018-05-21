package kz.crtr.app.wrapper;

import kz.crtr.app.entity.gson.*;
import kz.crtr.app.entity.tbl.Groupmembers;
import kz.crtr.app.entity.tbl.UserDetail;
import kz.crtr.app.entity.tbl.Users;
import kz.crtr.app.gson.GsonUsersProfile;

import java.util.List;

import static kz.crtr.app.utils.Util.getUserFullName;
import static kz.crtr.app.utils.Util.newArrayList;

public class UserWrapper {

    public static GsonUsers wrapToGsonUsers(Users obj) {
        if (obj != null) {
            GsonUsers gson = new GsonUsers();
            gson.setEmpId(obj.getEmpId());
            gson.setUserDetail(wrapToGsonUserDetail(obj.getUserDetail()));
            gson.setUName(obj.getUName());
            gson.setUDescription(obj.getUDescription());
            return gson;
        }

        return null;
    }

    public static GsonUsers wrapToGsonUsers(Users obj, List<Groupmembers> groupmembers) {
        if (obj != null) {
            GsonUsers gson = new GsonUsers();
            gson.setEmpId(obj.getEmpId());
            gson.setUserDetail(wrapToGsonUserDetail(obj.getUserDetail()));
            gson.setUName(obj.getUName());
            gson.setUDescription(obj.getUDescription());
            gson.setGroupmembers(wrapGroupmembersListToString(groupmembers));
            return gson;
        }

        return null;
    }

    private static List<String> wrapGroupmembersListToString(List<Groupmembers> groupmembers) {
        List<String> list = newArrayList();
        groupmembers.forEach(s -> list.add(wrapGroupmembersToString(s)));

        return list;
    }

    private static String wrapGroupmembersToString(Groupmembers groupmembers) {
        return groupmembers.getGName().getGName();
    }

    public static GsonUsersProfile wrapToGsonUsersInfo(Users obj) {
        if (obj != null) {
            GsonUsersProfile gson = new GsonUsersProfile();
            gson.setUName(obj.getUName());
            gson.setUDescription(obj.getUDescription());
            gson.setUserDetail(wrapToGsonUserDetail(obj.getUserDetail()));

            return gson;
        }
        return null;
    }

    private static GsonUserDetail wrapToGsonUserDetail(UserDetail obj) {
        if (obj != null) {
            GsonUserDetail gson = new GsonUserDetail();
            gson.setUName(obj.getEmpId().getUName());
            gson.setMiddlename(obj.getMiddlename());
            gson.setLastname(obj.getLastname());
            gson.setFirstname(obj.getFirstname());
            gson.setFullName(getUserFullName(obj.getLastname(), obj.getFirstname(), obj.getMiddlename()));
            gson.setIin(obj.getIin());
            return gson;
        }

        return null;
    }
}