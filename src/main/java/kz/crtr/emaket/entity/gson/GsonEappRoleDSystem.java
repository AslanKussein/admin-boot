package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author a.kussein;
 */
@Getter
@Setter
public class GsonEappRoleDSystem implements Serializable {

    private String id;
    private GsonDSystem dSystemId;
    private GsonEappRole eappRoleId;

    public GsonEappRoleDSystem() {
    }
}