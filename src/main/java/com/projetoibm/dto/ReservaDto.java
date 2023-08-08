package com.projetoibm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ReservaDto implements Serializable {

    private Integer id;
    private String nomeHospede;
    private Date dataInicio;
    private Date dataFim;
    private String quantidadePessoas;
    private String status;

    public ReservaDto() {
    }

    public ReservaDto(Integer id, String nomeHospede, Date dataInicio, Date dataFim, String quantidadePessoas, String status) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }
}
