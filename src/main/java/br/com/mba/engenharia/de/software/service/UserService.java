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

    public boolean salvarUsuario(Usuario user) {
        entityManagerFactory();
        entityManager.getTransaction().begin();
        List list = entityManager.createNativeQuery("select id from usuarios").getResultList();
        if (list.size() > 0) {
            int id = (int) list.get(list.size() - 1) + 1;
            user.setId(id);
        } else {
            user.setId(1);
        }
        try {
            if (listarUsuario(user).size()==0){
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


    public boolean habilitarUsuario(Usuario user){
        entityManagerFactory();
        entityManager.getTransaction().begin();
        Usuario usuario = null;
        usuario = findByUsernameAndSenhaAndToken(user.getUsername(), user.getSenha(), user.getToken());
        updateTokenById(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Transactional
    public Usuario findByUsernameAndSenhaAndToken(String username, String senha, String token) {
        entityManagerFactory();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        Usuario user = new Usuario();
        user.setUsername(username);
        user.setToken(token);
        user.setSenha(senha);
        cQuery
                .select(root)
                .where(builder
                        .like(root.<String>get("username"), username))
                .where(builder
                        .like(root.<String>get("senha"), senha))
                .where(builder
                        .like(root.<String>get("token"), token));

        TypedQuery<Usuario> query = entityManager.createQuery(cQuery);
        return query.getResultList().get(0);
    }



    @Transactional
    public int updateTokenById(Usuario usuario){
        entityManagerFactory();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> cQuery = builder.createCriteriaUpdate(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        cQuery
                .set("token", "1")
                .where(builder.equal(root.get("id"), usuario.getId()));
        Query query = entityManager.createQuery(cQuery);
        return query.executeUpdate();
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
                .where(builder.equal(root.get("username"),user.getUsername()))
                .where(builder.equal(root.get("senha"),user.getSenha()));
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}



