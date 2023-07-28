package br.com.mba.engenharia.de.software.service.usuarios;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserService {
    int updateStatus(Integer id);
    List<Usuario> findByIdAndToken(String token, Integer id);
    Optional<Usuario> findByTokenUsernameAndSenha(String username, String senha, String status, String token);
    List<Usuario> findAll();
    List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status);
    int save(Usuario usuario);
}