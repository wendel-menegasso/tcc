package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import br.com.mba.engenharia.de.software.service.rendas.RendasManager;
import br.com.mba.engenharia.de.software.service.rendas.RendasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RendasController{
    private static final Logger logger = LoggerFactory.getLogger(Renda.class);

    @Autowired
    RendasRepository repository;

    RendasService rendasService;

    @Bean
    public RendasService rendasService(){
        RendasManager rendasManager = new RendasManager(repository);
        return rendasManager;
    }

    public RendasController() {
        this.rendasService = rendasService();
    }
}
