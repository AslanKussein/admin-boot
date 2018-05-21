package kz.crtr.app.gson;

import kz.crtr.app.entity.gson.GsonUserDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 20.08.2016.
 */
@Getter
@Setter
public class GsonUsersProfile<T> implements Serializable {

    private String uName;
    private String uPassword;
    private String uDescription;
    private GsonUserDetail userDetail;
    private String uPosition;
    private T eappUserRoleList;
}