package kz.crtr.app.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author a.kussein;
 */
@Getter
@Setter
public class GsonDActionDSystem implements Serializable {
    private BigDecimal id;
    private GsonDAction dActionId;
    private GsonDSystem dSystemId;

    public GsonDActionDSystem() {
    }
}