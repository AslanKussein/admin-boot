package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author a.kussein
 */
@Getter
@Setter
public class GsonDMse implements Serializable {
    private String id;
    private Long num;
    private String spec;
    private String address;
    private String email;
    private String phonenumber;
    private String gcvpId;
    private String rgId;
    private String name;
    private String lvl;
    private List<GsonUserDetail> userDetailList;
    private GsonDMsetype typeId;

    public GsonDMse() {
    }
}