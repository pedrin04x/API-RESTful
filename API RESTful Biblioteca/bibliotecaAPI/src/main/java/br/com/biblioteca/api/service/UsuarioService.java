package br.com.biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.api.dto.request.UsuarioRequestDTO;
import br.com.biblioteca.api.dto.response.UsuarioResponseDTO;
import br.com.biblioteca.api.model.Usuario;
import br.com.biblioteca.api.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;


    //Método para salvar dados do usuário na base de dados
   public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setCpf(usuarioRequestDTO.getCpf());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(savedUsuario.getUserId(), savedUsuario.getNome(), savedUsuario.getCpf(), savedUsuario.getSenha());
    }

    //Método para listar todos os usuários
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getUserId(), usuario.getNome(), usuario.getCpf(), usuario.getSenha()))
                .collect(Collectors.toList());

   }

   //Método para listar usuário por Id
   public Optional<UsuarioResponseDTO> findById(Long userId) {
    return usuarioRepository.findById(userId)
            .map(usuario -> new UsuarioResponseDTO(usuario.getUserId(), usuario.getNome(), usuario.getCpf(), usuario.getSenha()));
}
    //Delete 👍
   public void deleteById(Long userId) {
    usuarioRepository.deleteById(userId);
}

}


