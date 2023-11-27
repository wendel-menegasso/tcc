package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.output.GenerateDashboard;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class DashboradController {

    @GetMapping("/graficos")
    public ResponseEntity<FileSystemResource> downloadArquivo() throws IOException {
        // Localização do arquivo que você deseja transferir
        GenerateDashboard generateDashboard = new GenerateDashboard();
        String filePath = generateDashboard.generatePlot();

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
}

