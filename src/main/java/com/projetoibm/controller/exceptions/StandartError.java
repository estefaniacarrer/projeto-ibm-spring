package com.projetoibm.controller.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandartError implements Serializable {

    private Long timestamp;
    private Integer status;
    private String error;

    public StandartError() {
    }

    public StandartError(Long timestamp, Integer status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }
}
