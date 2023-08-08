package com.projetoibm.service;

import com.projetoibm.dto.ReservaDto;
import com.projetoibm.model.Reserva;
import com.projetoibm.repository.ReservaRepository;
import com.projetoibm.service.exceptions.DataValidationException;
import com.projetoibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva insert(Reserva obj) {
        Date dataAtual = new Date();
        if (obj.getDataInicio().before(dataAtual)) {
            throw new DataValidationException("Data inválida! Informe uma data posterior à data atual.");
        }
        if (obj.getDataFim().equals(obj.getDataInicio()) || obj.getDataFim().before(obj.getDataInicio())) {
            throw new DataValidationException("Data inválida! Informe uma data posterior à data de início.");
        }

        obj.setId(null);
        obj= reservaRepository.save(obj);
        return obj;
    }

    public List<Reserva> findAll() {
        List<Reserva> list = reservaRepository.findAll();
        return list;
    }

    public Reserva findById(Integer id){
        Optional<Reserva> obj= reservaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!" + id + " , Tipo: " + Reserva.class.getName()));
    }

    public Reserva update(Reserva obj) {
        Reserva reservaAtualizada = findById(obj.getId());
        updateData(reservaAtualizada, obj);
        return reservaRepository.save(reservaAtualizada);
    }

    private void updateData(Reserva nova, Reserva antiga){
        nova.setNomeHospede(antiga.getNomeHospede());
        nova.setDataInicio(antiga.getDataInicio());
        nova.setDataFim(antiga.getDataFim());
        nova.setQuantidadePessoas(antiga.getQuantidadePessoas());
        nova.setStatus(antiga.getStatus());
    }

    public Reserva fromDto(ReservaDto objDto){
        return new Reserva(objDto.getId(), objDto.getNomeHospede(), objDto.getDataInicio(), objDto.getDataFim(), objDto.getQuantidadePessoas(), objDto.getStatus());
    }

    public void cancelarReserva(Integer id) {
        Reserva reserva = findById(id);
        reserva.setStatus("CANCELADA");
        reservaRepository.save(reserva);
    }
}
