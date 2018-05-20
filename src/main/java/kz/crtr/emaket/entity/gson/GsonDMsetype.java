package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author a.kussein
 */
@Getter
@Setter
public class GsonDMsetype implements Serializable {

    private Long id;
    private String name;
    private List<GsonDMse> dMseList;

    public GsonDMsetype() {
    }
}