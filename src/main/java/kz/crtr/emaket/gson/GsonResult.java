package kz.crtr.emaket.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GsonResult implements Serializable {

    private Boolean result;
    private Object message;
}
