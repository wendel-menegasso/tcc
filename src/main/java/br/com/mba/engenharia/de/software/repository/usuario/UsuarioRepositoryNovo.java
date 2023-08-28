package br.com.mba.engenharia.de.software.repository.usuario;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UsuarioRepositoryNovo extends org.springframework.data.repository.Repository<Usuario, Long> {
    @Transactional
    @Modifying
    @Query("update Usuario u set u.status = '1' " +
            "where u.token like ?1 and u.username like ?2 and u.senha like ?3 and u.status like ?4")
    Integer findByTokenUsernameSenhaAndStatusAndUpdateStatus(String token, String username, String senha, String status);
    @Query("select count(u) from Usuario u")
    int count();
    @Query("select u from Usuario u where u.username like ?1 and u.senha like ?2 and u.status like ?3")
    List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status);
    @Transactional
    @Modifying
    @Query("update Usuario u set u.status = '1' where u.id = ?1")
    int updateStatus(Integer id);
    @Query("select u from Usuario u where u.token = ?1 and u.id = ?2")
    List<Usuario> findByIdAndToken(String token, Integer id);
    @Query("select u from Usuario u where u.token like ?1 and u.username like ?2 and u.status like ?3 and u.senha like ?4")
    Usuario findByTokenUsernameAndSenha(String token, String username, String status, String senha);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
}