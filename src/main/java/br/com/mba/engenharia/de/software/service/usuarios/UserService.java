package br.com.mba.engenharia.de.software.service.usuarios;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.repository.usuario.UsuarioRepositoryNovo;

import java.util.List;

public interface UserService {
    int updateStatus(Integer id);
    List<Usuario> findByIdAndToken(String token, Integer id);
    Usuario findByTokenUsernameAndSenha(String token, String username, String status, String senha);
    List<Usuario> findAll();
    List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status);
    Usuario save(Usuario usuario);
    void setRepository(UsuarioRepositoryNovo usuarioRepositoryNovo);
    int count();
    Integer findByTokenUsernameSenhaAndStatusAndUpdateStatus(String token, String username, String senha, String status);
}