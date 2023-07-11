package br.com.mba.engenharia.de.software.negocio.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface UsuarioRepository <Usuario, Integer extends Serializable>
        extends JpaRepository<Usuario, Integer> {
    @Transactional
    @Modifying
    @Query("update Usuario u set u.token = 1 where u.id = ?1")
    int updateTokenById(Integer id);
    Usuario findByUsernameAndSenhaAndToken(String username, String senha, String token);
}