package kz.crtr.emaket.domain;

import java.util.List;
import lombok.Data;

@Data
public class PageCommon {
    int number;
    int size;
    long totalElements;
    int totalPages;
    List<Object> content;
}