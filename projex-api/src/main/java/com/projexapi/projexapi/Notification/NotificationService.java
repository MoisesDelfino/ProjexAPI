package com.projexapi.projexapi.Notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    @Value("${fcm.api.key}")
    private String fcmApiKey;


    public void sendNotification(List<String> deviceTokens, String title, String body) {
        for (String token : deviceTokens){
            sendNotification(token,title,body);
        }
    }

    public void sendNotification(String deviceToken, String title, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "key=" + fcmApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "https://fcm.googleapis.com/fcm/send";

        RestTemplate restTemplate = new RestTemplate();

        // Construir o corpo da solicitação
        NotificationRequest request = new NotificationRequest();
        request.setTo(deviceToken);
        request.setNotification(new Notification(title, body));

        HttpEntity<NotificationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Verificar a resposta
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Notificação enviada com sucesso!");
        } else {
            System.out.println("Erro ao enviar notificação. Código de status: " + response.getStatusCodeValue());
        }
    }
}

