package kz.crtr.emaket.domain;

import lombok.Data;

@Data
public class PasswordControl {
    private String password;
    private String passwordConf;
    private String passwordNew;
}