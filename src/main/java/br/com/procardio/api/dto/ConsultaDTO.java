package br.com.procardio.api.dto;

import java.time.LocalDateTime;

public record ConsultaDTO(
    Long pacientId,
    Long medicoId,
    LocalDateTime dataHora
) {
}
    
