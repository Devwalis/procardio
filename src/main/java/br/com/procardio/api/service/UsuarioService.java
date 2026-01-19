package br.com.procardio.api.service;




import org.springframework.beans.factory.annotation.Autowired;

import br.com.procardio.api.dto.UsuarioDTO;
import br.com.procardio.api.model.Usuario;
import br.com.procardio.api.repository.UsuarioRepository;

public class UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {
       Usuario usuario = new Usuario();
       usuario.setNome(usuarioDTO.nome());
       usuario.setEmail(usuarioDTO.email());
       usuario.setSenha(usuarioDTO.senha());
       return usuarioRepository.save(usuario);
   }




}
