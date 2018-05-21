package kz.crtr.app.gson;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dit18;
 * @since on 12.07.2016.
 */
@Getter
@Setter
public class GsonDatatableData<T> {

    private int pos;
    private int total_count;
    private Object data;
    private int test;
    private T tBytes;
}