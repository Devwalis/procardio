package br.com.procardio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.procardio.api.model.Consulta;
import br.com.procardio.api.dto.ConsultaDTO;
import br.com.procardio.api.dto.ConsultaResponseDTO;
import br.com.procardio.api.model.Usuario;
import br.com.procardio.api.service.ConsultaService;
import br.com.procardio.api.service.MedicoService;
import br.com.procardio.api.service.UsuarioService;
import br.com.procardio.api.model.Medico;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {


    @Autowired
    private ConsultaService consultaService;
   

    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private MedicoService medicoService;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PACIENTE')")
    public ResponseEntity<ConsultaResponseDTO> criarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO){
        //buscar paciente

        Usuario paciente = usuarioService.buscarUsuarioPorId(consultaDTO.pacienteId());
        if(paciente == null){
            return ResponseEntity.badRequest().body(null);
        }

    //busca medico
    Medico medico = medicoService.buscarMedicoPorId(consultaDTO.medicoId());
    if(medico == null){
        return ResponseEntity.badRequest().body(null);
    }
    Consulta consulta = new Consulta();
    consulta.setPaciente(paciente);
    consulta.setMedico(medico);
    consulta.setDataHora(consultaDTO.dataHora());

    Consulta consultaSalva = consultaService.salvarConsulta(consulta);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consultaSalva));


}


     
        

        
       
    }

    

