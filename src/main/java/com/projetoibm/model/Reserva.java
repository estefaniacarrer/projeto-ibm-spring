package com.projetoibm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeHospede;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;
    private String quantidadePessoas;
    private String status = "CONFIRMADA";

    public Reserva() {
    }

    public Reserva(Integer id, String nomeHospede, Date dataInicio, Date dataFim, String quantidadePessoas, String status) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }

}
