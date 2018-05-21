package kz.crtr.app.domain;

import lombok.Data;

@Data
public class RequestResult {
    private Boolean result;
    private Object message;
}