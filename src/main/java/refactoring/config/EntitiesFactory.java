package refactoring.config;

import refactoring.repository.contas.ContaRepository;
import refactoring.repository.gastos.GastosRepository;
import refactoring.repository.imoveis.ImoveisRepository;
import refactoring.repository.rendas.RendasRepository;
import refactoring.repository.usuario.UsuarioRepository;
import refactoring.repository.veiculos.VeiculosRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EntitiesFactory<T> {

    private final Map<Class<?>, Object> repositoryMap = new HashMap<>();

    @Autowired
    public EntitiesFactory(EntityManager entityManager) {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);

        // Registra todos os repositórios
        repositoryMap.put(ContaRepository.class, factory.getRepository(ContaRepository.class));
        repositoryMap.put(GastosRepository.class, factory.getRepository(GastosRepository.class));
        repositoryMap.put(ImoveisRepository.class, factory.getRepository(ImoveisRepository.class));
        repositoryMap.put(RendasRepository.class, factory.getRepository(RendasRepository.class));
        repositoryMap.put(UsuarioRepository.class, factory.getRepository(UsuarioRepository.class));
        repositoryMap.put(VeiculosRepository.class, factory.getRepository(VeiculosRepository.class));
    }

    @SuppressWarnings("unchecked")
    public T getRepository(Class<T> clazz) {
        return (T) repositoryMap.get(clazz);
    }

    @Bean
    public ContaRepository contasRepository() {
        return (ContaRepository) repositoryMap.get(ContaRepository.class);
    }

    @Bean
    public GastosRepository gastosRepository() {
        return (GastosRepository) repositoryMap.get(GastosRepository.class);
    }

    @Bean
    public ImoveisRepository imoveisRepository() {
        return (ImoveisRepository) repositoryMap.get(ImoveisRepository.class);
    }

    @Bean
    public RendasRepository rendasRepository() {
        return (RendasRepository) repositoryMap.get(RendasRepository.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository() {
        return (UsuarioRepository) repositoryMap.get(UsuarioRepository.class);
    }

    @Bean
    public VeiculosRepository veiculosRepository() {
       return  (VeiculosRepository) repositoryMap.get(VeiculosRepository.class);
    }
}
