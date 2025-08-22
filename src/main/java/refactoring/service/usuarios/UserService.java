package refactoring.service.usuarios;

import refactoring.entity.usuarios.Usuario;
import refactoring.repository.usuario.UsuarioRepository;

import java.util.List;

public interface UserService {
    int updateStatus(Integer id);
    List<Usuario> findByIdAndToken(String token, Integer id);
    Usuario findByTokenUsernameAndSenha(String token, String username, String status, String senha);
    List<Usuario> findAll();
    List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status);
    Usuario save(Usuario usuario);
    void setRepository(UsuarioRepository usuarioRepositoryNovo);
    int count();
    Integer findByTokenUsernameSenhaAndStatusAndUpdateStatus(String token, String username, String senha, String status);
}