package kz.crtr.app.gson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author beljerin;
 * @since on 20.07.2016.
 */
@Getter
@Setter
public class GsonDetails<E> implements Serializable {

    private E isWorking;
    private E type;
    private E rfrcId;
    private List<E> list;
}