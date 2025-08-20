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

    private <T> T createRepository(EntityManager entityManager, Class<T> repositoryClass) {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);
        return factory.getRepository(repositoryClass);
    }

    @Bean
    public ContaRepository contasRepository(EntityManager entityManager) {
        return createRepository(entityManager, ContaRepository.class);
    }

    @Bean
    public GastosRepository gastosRepository(EntityManager entityManager) {
        return createRepository(entityManager, GastosRepository.class);
    }

    @Bean
    public ImoveisRepository imoveisRepository(EntityManager entityManager) {
        return createRepository(entityManager, ImoveisRepository.class);
    }

    @Bean
    public RendasRepository rendasRepository(EntityManager entityManager) {
        return createRepository(entityManager, RendasRepository.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository(EntityManager entityManager) {
        return createRepository(entityManager, UsuarioRepository.class);
    }

    @Bean
    public VeiculosRepository veiculosRepository(EntityManager entityManager) {
        return createRepository(entityManager, VeiculosRepository.class);
    }
}
