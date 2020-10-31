package com.example.email_notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    public void send(String mail){
        simpleMailMessage.setTo("codingroup001@gmail.com");
        simpleMailMessage.setSubject("TEST MAIL");
        simpleMailMessage.setText(mail);
        javaMailSender.send(simpleMailMessage);
    }

    @KafkaListener(topics = "USER",groupId = "email")
    public void sendEmailOnUserChange(Event event){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserResponse> entity =new HttpEntity<>(headers);

        UserResponse userResponse = restTemplate.exchange("http://localhost:5055/user/"+event.getUser(),
                HttpMethod.GET,
                entity,
                UserResponse.class)
                .getBody();

        simpleMailMessage.setTo(userResponse.getEmail());
        simpleMailMessage.setSubject(event.getName());
        simpleMailMessage.setText(event.getData());
        javaMailSender.send(simpleMailMessage);
    }


}
