package br.com.procardio.api.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
    

    public UsuarioNaoEncontrado(String email){
        super("Usuário com email " + email + " não encontrado.");
    }
}
