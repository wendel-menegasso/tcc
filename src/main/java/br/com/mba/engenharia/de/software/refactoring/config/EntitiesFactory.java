package br.com.mba.engenharia.de.software.refactoring.config;

import br.com.mba.engenharia.de.software.refactoring.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.imoveis.ImoveisRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.usuario.UsuarioRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.veiculos.VeiculosRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

@Configuration
public class EntitiesFactory {

    @Bean
    public ContaRepository contasRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(ContaRepository.class);
    }

    @Bean
    public GastosRepository gastosRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(GastosRepository.class);
    }

    @Bean
    public ImoveisRepository imoveisRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(ImoveisRepository.class);
    }

    @Bean
    public RendasRepository rendasRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(RendasRepository.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(UsuarioRepository.class);
    }

    @Bean
    public VeiculosRepository veiculosRepository(EntityManager entityManager){
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        return jpaRepositoryFactory.getRepository(VeiculosRepository.class);
    }
}
