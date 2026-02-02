package br.com.procardio.api.dto;

import java.time.LocalDateTime;

import br.com.procardio.api.model.Consulta;

public record ConsultaResponseDTO (

    Long id,
    Long pacientId,
    Long medicoId,
    String medicoNome,
    String pacienteNome,
    String medicoEspecialidade,
    LocalDateTime dataHora

){
    public ConsultaResponseDTO(Consulta consulta){
    this(
        consulta.getId(),
        consulta.getPaciente().getId(),
        consulta.getMedico().getId(),
        consulta.getMedico().getNome(),
        consulta.getPaciente().getNome(),
        consulta.getMedico().getEspecialidade().toString(),
        consulta.getDataHora()
    );

}


}




