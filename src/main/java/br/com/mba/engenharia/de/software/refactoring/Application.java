package br.com.mba.engenharia.de.software.refactoring;

import br.com.mba.engenharia.de.software.controller.*;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {"br.com.mba.engenharia.de.software.repository.contas", "br.com.mba.engenharia.de.software.repository.usuario",
                                       "br.com.mba.engenharia.de.software.repository.rendas","br.com.mba.engenharia.de.software.repository.gastos",
                                       "br.com.mba.engenharia.de.software.repository.veiculos","br.com.mba.engenharia.de.software.repository.imoveis"})
@ComponentScan(basePackageClasses = {Renda.class, Conta.class, Usuario.class, Veiculos.class, Imoveis.class,
                                     LoginController.class, UsuarioController.class, ContaController.class, RendasController.class,
        GastosController.class, Gastos.class, OrigensController.class, DashboradController.class, VeiculosController.class,
        ImoveisController.class})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}





