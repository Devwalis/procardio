package br.com.procardio.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(

    @NotNull(message = "O ID do paciente é obrigatório")
    Long pacientId,

    @NotNull(message = "O ID do médico é obrigatório")
    Long medicoId,

    @NotNull(message = "A data e hora da consulta são obrigatórias")
    @Future(message = "A data e hora da consulta devem ser futuras")
    LocalDateTime dataHora
) {
}
    
