package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author amanzhol-ak;
 * @since on 09.01.2017.
 */
@Getter
@Setter
public class GsonEappRoleDAction implements Serializable {

    private String id;
    private GsonDAction dActionId;
    private GsonEappRole eappRoleId;

    public GsonEappRoleDAction() {
    }
}