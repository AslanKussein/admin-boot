package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author amanzhol-ak;
 * @since on 09.01.2017.
 */
@Getter
@Setter
public class GsonEappUserRole implements Serializable {

    private String id;
    private GsonEappRole eappRoleId;
    private GsonUsers uName;

    public GsonEappUserRole() {
    }
}