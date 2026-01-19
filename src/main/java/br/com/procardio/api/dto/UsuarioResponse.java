package br.com.procardio.api.dto;

import br.com.procardio.api.model.Usuario;

public record UsuarioResponse(
    Long id,
    String nome,
    String email

) {

    public UsuarioResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
} 
    



