package kz.crtr.app.entity.gson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author a.kussein
 */
@Getter
@Setter
public class GsonDAction implements Serializable {
    private Long id;
    private Long parentId;
    private Integer actionType;
    private String value;
    private Long maskId;
    private String maskValue;
    private Long repId;
    private String cmd;
    private String async;
}