package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class GsonTData<E> implements Serializable {

    private String id;
    private String descr;
    private BigDecimal cost;
    private Integer count;
    private String name;
    private String imgBdy;
    private String imageName;
}