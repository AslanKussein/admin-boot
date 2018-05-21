package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author amanzhol-ak;
 * @since on 09.01.2017.
 */
@Getter
@Setter
public class GsonDSystem implements Serializable {

    private BigDecimal id;
    private String schema;
    private String code;
    private String nameRus;
    private String nameKaz;
    private List<GsonEappRoleDSystem> eappRoleDSystemList;
    private List<GsonDActionDSystem> dActionDSystemList;

    public GsonDSystem() {
    }
}