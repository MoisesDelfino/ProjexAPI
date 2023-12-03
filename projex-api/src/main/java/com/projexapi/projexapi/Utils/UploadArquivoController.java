package com.projexapi.projexapi.Utils;

import com.azure.core.http.rest.Response;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobStorageException;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


@RestController
@RequestMapping("/api/arquivos")
public class UploadArquivoController {

    @Autowired
    private ProjetoService projetoService;

    @Value("${azure.storage.connection-string}")
    private String azureConnectionString;

    @Value("${azure.storage.container-name}")
    private String azureContainerName;

    @PostMapping("/upload")
    public ResponseEntity<Message> uploadArquivo(@RequestParam("file") MultipartFile file) {
        try {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureConnectionString).buildClient();

            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);
            String fileName = generateUniqueFileName(file.getBytes(), fileExtension);

            BlobClient blobClient = blobServiceClient.getBlobContainerClient(azureContainerName).getBlobClient(fileName);

            if (blobClient.exists()) {
                return ResponseEntity.badRequest().body(new Message("O arquivo já existe."));
            }

            blobClient.upload(file.getInputStream(), file.getSize());

            return ResponseEntity.ok(new Message("Arquivo enviado com sucesso. Hash: " + fileName));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Ocorreu um erro ao fazer upload do arquivo."));
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Ocorreu um erro ao gerar a hash do arquivo."));
        }
    }

    private String generateUniqueFileName(byte[] fileBytes, String fileExtension) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5"); // Ou use "SHA-256" para uma hash mais segura
        byte[] hashBytes = digest.digest(fileBytes);
        String hash = byteArrayToHexString(hashBytes);

        return hash + "_" + System.currentTimeMillis() + "." + fileExtension;
    }

    private String byteArrayToHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private String getFileExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex >= 0 && extensionIndex < fileName.length() - 1) {
            return fileName.substring(extensionIndex + 1);
        }
        return "";
    }

@GetMapping("/download/{nomeArquivo}")
public ResponseEntity<Resource> downloadArquivo(@PathVariable("nomeArquivo") String nomeArquivo) {
    try {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureConnectionString).buildClient();
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(azureContainerName).getBlobClient(nomeArquivo);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", nomeArquivo);

        InputStreamResource resource = new InputStreamResource(inputStream);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    } catch (BlobStorageException e) {
        if (e.getStatusCode() == 404) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

@DeleteMapping("/delete/{fileName}")
public ResponseEntity<Message> deletarArquivo(@PathVariable("fileName") String fileName){
    try {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureConnectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(azureContainerName);
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        if (blobClient.exists()) {
            blobClient.delete();
            return ResponseEntity.ok(new Message("Arquivo deletado com sucesso."));
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (BlobStorageException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Ocorreu um erro ao deletar o arquivo."));
    }
}
}


