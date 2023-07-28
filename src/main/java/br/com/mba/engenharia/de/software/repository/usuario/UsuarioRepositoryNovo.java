package br.com.mba.engenharia.de.software.repository.usuario;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UsuarioRepositoryNovo extends org.springframework.data.repository.Repository<Usuario, Long> {
    @Query("select u from Usuario u where u.username = '?1' and u.senha = '?2' and u.status = '?3'")
    List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status);
    @Transactional
    @Modifying
    @Query("update Usuario u set u.status = '1' where u.id = ?1")
    int updateStatus(Integer id);
    @Query("select u from Usuario u where u.token = ?1 and u.id = ?2")
    List<Usuario> findByIdAndToken(String token, Integer id);
    @Query("select u from Usuario u where u.username = ?1 and u.senha = ?2 and u.status = ?3 and u.token = ?4")
    Optional<Usuario> findByTokenUsernameAndSenha(String username, String senha, String status, String token);
    List<Usuario> findAll();
    int save(Usuario usuario);
}