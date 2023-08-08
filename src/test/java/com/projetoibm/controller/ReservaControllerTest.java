package com.projetoibm.controller;

import com.projetoibm.dto.ReservaDto;
import com.projetoibm.model.Reserva;
import com.projetoibm.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private ReservaDto reservaDto;

    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        reservaDto = new ReservaDto(1, "Alice", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), "4", "CONFIRMADA");
    }

    @Test
    public void testInsertReserva() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reservaInserida = new Reserva(1, "Alice", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), "4", "CONFIRMADA");

        when(reservaService.insert(any(Reserva.class))).thenReturn(reservaInserida);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeHospede\":\"Alice\",\"dataInicio\":\"2023-08-10\",\"dataFim\":\"2023-08-15\",\"quantidadePessoas\":4,\"status\":\"CONFIRMADA\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/reservas/1"));
    }

    @Test
    public void testeFindById() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer id = 1;
        Reserva reserva = new Reserva(id, "Alice", sdf.parse("2023-08-10"), sdf.parse("2023-08-12"), "4", "CONFIRMADA");
        when(reservaService.findById(id)).thenReturn(reserva);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantidadePessoas").value("4"));
    }

    @Test
    public void testCancelarReserva() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/{id}/cancelar", id))
                .andExpect(status().isNoContent());

        verify(reservaService, times(1)).cancelarReserva(id);
    }


}
