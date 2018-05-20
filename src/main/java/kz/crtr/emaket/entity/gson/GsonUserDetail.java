package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author a.kussein
 */
@Getter
@Setter
public class GsonUserDetail implements Serializable {

    private String firstname;
    private String lastname;
    private String middlename;
    private String specialName;
    private String fullName;
    private String email;
    private String uName;
    private String iin;
    private GsonBranch branchId;
    private GsonDMse mseId;
    private GsonUsers users;
}