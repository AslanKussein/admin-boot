package kz.crtr.emaket.domain;

import lombok.Data;

@Data
public class AppSearch {
    private String appNumber;
    private String iin;

    public AppSearch() {
    }
}