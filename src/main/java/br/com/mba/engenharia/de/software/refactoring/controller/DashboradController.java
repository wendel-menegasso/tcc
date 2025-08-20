package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.output.GenerateDashboard;
import br.com.mba.engenharia.de.software.refactoring.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.refactoring.repository.rendas.RendasRepository;
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

@RestController
@CrossOrigin(origins = "*")
public class DashboradController {

    @Autowired
    RendasRepository rendasRepository;

    @Autowired
    GastosRepository gastosRepository;

    @PostMapping
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
        return rendasRepository.findAll(Integer.parseInt(usuario)).stream()
                .mapToDouble(renda -> Double.parseDouble(renda.getValor()))
                .sum();

    }

    private double pegaDadosDosGastos(String usuario){
        return gastosRepository.findAll(Integer.parseInt(usuario))
                .stream()
                .mapToDouble(gastos->Double.parseDouble(gastos.getValor()))
                .sum();
    }


}

