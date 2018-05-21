package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author amanzhol-ak;
 * @since on 09.01.2017.
 */
@Getter
@Setter
public class GsonEappRole implements Serializable {

    private Long id;
    private String lvl;
    private String nameRus;
    private String nameKaz;
    private List<GsonEappRoleDSystem> eappRoleDSystemList;
    private List<GsonEappUserRole> eappUserRoleList;
    private List<GsonEappRoleDAction> eappRoleDActionList;

}