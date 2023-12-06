package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.GastosRespostaDTO;
import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.output.GenerateDashboard;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.service.gastos.GastosService;
import br.com.mba.engenharia.de.software.service.rendas.RendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DashboradController {

    @Autowired
    RendasRepository rendasRepository;

    @Autowired
    GastosRepository gastosRepository;

    @PostMapping("/graficos")
    public ResponseEntity<FileSystemResource> downloadArquivo(@RequestBody String usuario) throws IOException {
        // Localização do arquivo que você deseja transferir
        GenerateDashboard generateDashboard = new GenerateDashboard();

        double totalRendas = pegaDadosDasRendas(usuario);
        double totalGastos = pegaDadosDosGastos(usuario);

        String filePath = generateDashboard.generatePlot1(totalRendas, totalGastos);

        // Cria um objeto FileSystemResource com base no arquivo
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));

        // Configura os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileSystemResource.getFilename());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSystemResource.contentLength()));

        // Cria o ResponseEntity com o objeto FileSystemResource e os cabeçalhos
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileSystemResource);
    }

    private double pegaDadosDasRendas(String usuario){
        double totalRendas = 0;
        RendasController rendasController = new RendasController();
        RendasService rendasService = rendasController.rendasService();
        rendasService.setRendasRepository(rendasRepository);
        List<Renda> listaDeRendas = rendasService.findAll(Integer.parseInt(usuario));
        for (Renda renda : listaDeRendas){
            double rendaUnitaria = Double.parseDouble(renda.getValor());
            totalRendas = totalRendas + rendaUnitaria;
        }
        return totalRendas;
    }

    private double pegaDadosDosGastos(String usuario){
        double totalGastos = 0;
        GastosController gastosController = new GastosController();
        GastosService gastosService = gastosController.gastosService();
        gastosService.setGastosRepository(gastosRepository);
        List<Gastos> listaDeGastos = gastosService.findAll(Integer.parseInt(usuario));
        for (Gastos gasto : listaDeGastos){
            double rendaUnitaria = Double.parseDouble(gasto.getValor());
            totalGastos = totalGastos + rendaUnitaria;
        }
        return totalGastos;
    }


}

