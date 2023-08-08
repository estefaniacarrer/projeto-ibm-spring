package com.projetoibm.service;

import com.projetoibm.model.Reserva;
import com.projetoibm.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ReservaRepository reservaRepository;

    public void instanciaBancoDeDados() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reserva1 = new Reserva(null, "Alice Silva", sdf.parse("2023-08-26"), sdf.parse("2023-08-28"), "3", "CONFIRMADA");
        Reserva reserva2 = new Reserva(null, "Roberto Oliveira", sdf.parse("2023-09-10"), sdf.parse("2023-09-15"), "2", "PENDENTE");
        Reserva reserva3 = new Reserva(null, "Vinicius de Assis", sdf.parse("2023-09-20"), sdf.parse("2023-09-22"), "1", "PENDENTE");
        Reserva reserva4 = new Reserva(null, "Nathalia Nassif", sdf.parse("2023-12-10"), sdf.parse("2023-12-13"), "4", "CONFIRMADA");

        reservaRepository.saveAll(Arrays.asList(reserva1, reserva2, reserva3, reserva4));
    }
}
