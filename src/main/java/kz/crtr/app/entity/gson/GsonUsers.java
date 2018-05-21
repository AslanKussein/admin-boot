package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author a.kussein
 */
@Getter
@Setter
public class GsonUsers implements Serializable {

    private Long empId;
    private String uName;
    private String uPassword;
    private String uDescription;
    private String changedate;
    private String tmpPassBeginDate;
    private String tmpPassEndDate;
    private BigInteger block;
    private String passBeginDate;
    private String passEndDate;
    private GsonUserDetail userDetail;
    private List<GsonEappUserRole> eappUserRoleList;
    private int isReset;
    private Integer isDbTest;
    private List<String> groupmembers;
    private String ipAddress;
    private String host;
    private String computerName;
    private String branch;
}