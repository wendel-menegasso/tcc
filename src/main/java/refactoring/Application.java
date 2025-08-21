package refactoring;

import org.springframework.context.annotation.Import;
import refactoring.config.EntitiesFactory;
import refactoring.controller.*;
import refactoring.entity.contas.Conta;
import refactoring.entity.despesas.Gastos;
import refactoring.entity.imoveis.Imoveis;
import refactoring.entity.rendas.Renda;
import refactoring.entity.usuarios.Usuario;
import refactoring.entity.veiculos.Veiculos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import refactoring.service.GenericService;

@SpringBootApplication
@Import(EntitiesFactory.class)
@EnableJpaRepositories(basePackages = {"br.com.mba.engenharia.de.software.refactoring.repository.contas", "br.com.mba.engenharia.de.software.refactoring.repository.usuario",
                                       "br.com.mba.engenharia.de.software.refactoring.repository.rendas","br.com.mba.engenharia.de.software.refactoring.repository.gastos",
                                       "br.com.mba.engenharia.de.software.refactoring.repository.veiculos","br.com.mba.engenharia.de.software.refactoring.repository.imoveis"})
@ComponentScan(basePackageClasses = {Renda.class, Conta.class, Usuario.class, Veiculos.class, Imoveis.class,
                                     LoginController.class, UsuarioController.class, ContaController.class, RendasController.class,
        GastosController.class, Gastos.class, OrigensController.class, DashboradController.class, VeiculosController.class,
        ImoveisController.class, GenericService.class})
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}





