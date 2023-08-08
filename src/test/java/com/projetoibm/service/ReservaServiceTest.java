package com.projetoibm.service;

import com.projetoibm.model.Reserva;
import com.projetoibm.repository.ReservaRepository;
import com.projetoibm.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;


    @Test
    public void testInsertReserva() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reserva = new Reserva(1, "Alice", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), "4", "CONFIRMADA");

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaInserida = reservaService.insert(reserva);
        assertNotNull(reservaInserida);
        assertEquals(reserva.getNomeHospede(), reservaInserida.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaInserida.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaInserida.getDataFim());
        assertEquals(reserva.getQuantidadePessoas(), reservaInserida.getQuantidadePessoas());
        assertNull(reservaInserida.getId());

        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    public void testeFindById(){
        Integer id = 1;
        Reserva reserva = new Reserva();
        reserva.setId(id);
        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        Reserva result = reservaService.findById(id);
        assertEquals(reserva, result);
        verify(reservaRepository, times(1)).findById(id);
    }

    @Test
    public void testFindReservaByIdNaoExistente() {
        Integer Id = 99;
        when(reservaRepository.findById(Id)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> reservaService.findById(Id));
        verify(reservaRepository, times(1)).findById(Id);
    }

    @Test
    public void testCancelarReserva() {
        Integer id = 1;
        Reserva reserva = new Reserva();
        reserva.setId(id);

        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        reservaService.cancelarReserva(id);

        assertEquals("CANCELADA", reserva.getStatus());
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void testUpdateReserva() {
        Integer Id = 1;
        Date dataInicio = new Date();
        Date dataFim = new Date();

        Reserva reservaAtualizada = new Reserva(Id, "Alice", dataInicio, dataFim, "2", "CONFIRMADA");
        Reserva reservaExistente = new Reserva(Id, "Arthur", dataInicio, dataFim, "3", "PENDENTE");

        when(reservaRepository.findById(Id)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaAtualizada);

        Reserva resultado = reservaService.update(reservaAtualizada);

        assertNotNull(resultado);
        assertEquals(Id, resultado.getId());
        assertEquals(reservaAtualizada.getNomeHospede(), resultado.getNomeHospede());
        assertEquals(reservaAtualizada.getDataInicio(), resultado.getDataInicio());
        assertEquals(reservaAtualizada.getDataFim(), resultado.getDataFim());
        assertEquals(reservaAtualizada.getQuantidadePessoas(), resultado.getQuantidadePessoas());
        assertEquals(reservaAtualizada.getStatus(), resultado.getStatus());

        verify(reservaRepository, times(1)).findById(Id);
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

}
