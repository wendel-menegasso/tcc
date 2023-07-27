package br.com.mba.engenharia.de.software;

import br.com.mba.engenharia.de.software.controller.ContaController;
import br.com.mba.engenharia.de.software.controller.UsuarioController;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.usuario.UsuarioRepositoryNovo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackageClasses = {ContaRepository.class, UsuarioRepositoryNovo.class})
@ComponentScan(basePackageClasses = {ContaController.class, UsuarioController.class})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EntityManagerFactory entityManagerFactoryUsuarios() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
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

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.mba.engenharia.de.software.entity.usuarios");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return (EntityManagerFactory) factory.getObject();
    }

    @Bean
    public EntityManagerFactory entityManagerFactoryContas() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
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

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.mba.engenharia.de.software.entity.contas");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return (EntityManagerFactory) factory.getObject();
    }


}





