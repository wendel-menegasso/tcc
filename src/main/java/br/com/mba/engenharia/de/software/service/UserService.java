package br.com.mba.engenharia.de.software.service;

import br.com.mba.engenharia.de.software.PersistenceUnitInfoImpl;
import br.com.mba.engenharia.de.software.exceptions.ListarContaException;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.persistenceunit.SmartPersistenceUnitInfo;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@Service
public class UserService{

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter hibernatePersistenceProvider = new HibernateJpaVendorAdapter();
        Properties properties = new Properties();
        properties.put("spring.jpa.database-platform", "org.hibernate.dialect.MySQL57Dialect");
        properties.put("spring.jpa.generate-ddl", true);
        properties.put("spring.datasource.url", "jdbc:mysql://root:fsa41306@localhost:3306/tcc?useTimezone=true&serverTimezone=UTC");
        properties.put("spring.datasource.username", "root");
        properties.put("spring.datasource.password", "fsa41306");
        properties.put("spring.jpa.hibernate.ddl-auto", "create");
        properties.put("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver");
        properties.put("spring.main.allow-bean-definition-overriding", true);
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://root:fsa41306@localhost:3306/tcc?useTimezone=true&serverTimezone=UTC");

        List<String> managedClassName = new ArrayList<>();
        managedClassName.add("br.com.mba.engenharia.de.software.negocio.usuarios.Usuario");
        SmartPersistenceUnitInfo pui = new PersistenceUnitInfoImpl("tcc", managedClassName, properties);
        pui.setPersistenceProviderPackageName("\"br.com.mba.engenharia.de.software.negocio.usuarios.Usuario");
        this.entityManager = hibernatePersistenceProvider.getPersistenceProvider().createContainerEntityManagerFactory(pui, properties).createEntityManager();
        return hibernatePersistenceProvider.getPersistenceProvider().createContainerEntityManagerFactory(pui, properties);
    }

    @Transactional
    public boolean salvarUsuario(Usuario user) {
        try {
            if (listarUsuario(user).size()==0){
                int id = ultimoId() + 1;
                user.setId(id);
                entityManagerFactory();
                entityManager.getTransaction().begin();
                entityManager.persist(user);
            }
            else{
                entityManager.close();
                logger.trace(String.format("Usuario duplicado"));
                throw new ListarContaException("Usuario duplicado!");
            }
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            entityManager.close();
            logger.trace(String.format("Erro %s", exception));
            return false;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public List<Usuario> listarUsuario(Usuario users) {
        return procuraRegistro(users);
    }


    @Transactional
    public boolean habilitarUsuario(Usuario user){
        entityManagerFactory();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        Usuario usuario = new Usuario();
        usuario.setUsername(user.getUsername());
        usuario.setToken(user.getToken());
        usuario.setSenha(user.getSenha());
        cQuery
                .select(root)
                .where(builder
                        .like(root.<String>get("username"), user.getUsername()));
        cQuery
                .where(builder
                        .like(root.<String>get("senha"), user.getSenha()));
        cQuery
                .where(builder
                        .like(root.<String>get("token"), user.getToken()));

        TypedQuery<Usuario> query = entityManager.createQuery(cQuery);
        Usuario usuario2 = query.getResultList().get(0);
        CriteriaBuilder builder2 = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> cQuery2 = builder2.createCriteriaUpdate(Usuario.class);
        Root<Usuario> root2 = cQuery2.from(Usuario.class);
        cQuery2
                .set("token", "1")
                .where(builder2.equal(root2.get("id"), usuario2.getId()));
        Query query2 = entityManager.createQuery(cQuery2);

        int result = query2.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        if (result == 1){
            return true;
        }
        else if (result == 0){
            return false;
        }
        return false;
    }

    @Transactional
    public List<Usuario> listarTodosUsuarios(){
        entityManagerFactory();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        cQuery
                .select(root);

        TypedQuery<Usuario> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

    @Transactional
    public List<Usuario> procuraRegistro(Usuario user){
        entityManagerFactory();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery
                .select(root)
                .where(builder.and(builder.like(root.get("username"), user.getUsername()), builder.like(root.get("senha"), user.getSenha())));
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Transactional
    public int ultimoId(){
        entityManagerFactory();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery
                .select(root);
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        int size = query.getResultList().size();
        return query.getResultList().get(size - 1).getId();
    }

}



