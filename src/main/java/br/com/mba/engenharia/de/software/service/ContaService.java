package br.com.mba.engenharia.de.software.service;

import br.com.mba.engenharia.de.software.PersistenceUnitInfoImpl;
import br.com.mba.engenharia.de.software.exceptions.ListarContaException;
import br.com.mba.engenharia.de.software.negocio.contas.Conta;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.persistenceunit.SmartPersistenceUnitInfo;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class ContaService {

    @PersistenceContext
    jakarta.persistence.EntityManager entityManager;

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
        managedClassName.add("br.com.mba.engenharia.de.software.negocio.contas.Conta");
        SmartPersistenceUnitInfo pui = new PersistenceUnitInfoImpl("tcc", managedClassName, properties);
        pui.setPersistenceProviderPackageName("\"br.com.mba.engenharia.de.software.negocio.contas.Conta");
        this.entityManager = hibernatePersistenceProvider.getPersistenceProvider().createContainerEntityManagerFactory(pui, properties).createEntityManager();
        return hibernatePersistenceProvider.getPersistenceProvider().createContainerEntityManagerFactory(pui, properties);
    }

    public boolean salvarConta(Conta contas) {
        entityManagerFactory();
        entityManager.getTransaction().begin();
        List list = entityManager.createNativeQuery("select id from conta").getResultList();
        if (list.size() > 0) {
            int id = (int) list.get(list.size() - 1) + 1;
            contas.setId(id);
        } else {
            contas.setId(1);
        }
        try {
            entityManager.persist(contas);
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

    @Transactional
    public List<Conta> procuraRegistro(Conta contas){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Conta.class);
        Root<Conta> root = criteriaQuery.from(Conta.class);
        if (contas.getConta() != null && contas.getAgencia() != null && contas.getBanco() != null){
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("conta"),contas.getConta()))
                    .where(builder.equal(root.get("agencia"),contas.getAgencia()))
                    .where(builder.equal(root.get("banco"), contas.getBanco()));
        }
        else if (contas.getConta() != null && contas.getAgencia() != null){
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("conta"),contas.getConta()))
                    .where(builder.equal(root.get("agencia"),contas.getAgencia()));
        }
        else if (contas.getConta() != null && contas.getBanco() != null){
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("conta"),contas.getConta()))
                    .where(builder.equal(root.get("banco"), contas.getBanco()));
        }
        else if (contas.getAgencia() != null && contas.getBanco() != null){
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("agencia"),contas.getAgencia()))
                    .where(builder.equal(root.get("banco"), contas.getBanco()));
        } else if (contas.getConta() != null) {
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("conta"),contas.getConta()));
        } else if (contas.getAgencia() != null) {
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("agencia"),contas.getAgencia()));
        }
        else if (contas.getBanco() != null){
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("banco"), contas.getBanco()));
        }
        TypedQuery<Conta> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Conta> listarConta(Conta contas){
        return procuraRegistro(contas);
    }

    @Transactional
    public List<Conta> listarTodasContas(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Conta> cQuery = builder.createQuery(Conta.class);
        Root<Conta> root = cQuery.from(Conta.class);
        cQuery
                .select(root);

        TypedQuery<Conta> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

}


