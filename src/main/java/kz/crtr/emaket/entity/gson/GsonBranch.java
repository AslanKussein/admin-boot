package kz.crtr.emaket.entity.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Bekzhan
 */
@Getter
@Setter
public class GsonBranch implements Serializable {

    private String id;
    private String parentId;
    private String value;
    private String lev;
}