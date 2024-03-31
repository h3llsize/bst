package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.entity.UserRequest;
import com.bstbackend.bstbackend.entity.UserRequestItem;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    private String emailLogin;

    private String emailPassword;

    public void sendToEmail(String text) {
        new Thread(() -> {
            try {
                Email email = new SimpleEmail();
                email.setHostName("smtp.yandex.com");
                email.setSmtpPort(587);
                email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
                email.setSSLOnConnect(true);
                email.setSubject("Входящая заявка с сайта");
                email.setFrom("zakaz@bst-sl.ru");
                email.setMsg(text);
                email.addTo("555@bst-sl.ru");
                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendUserRequest(UserRequest userRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Почта : " + userRequest.getEmail() + "\n");
        stringBuilder.append("Номер телефона : " + userRequest.getPhoneNumber() + "\n");
        stringBuilder.append("Имя : " + userRequest.getName() + "\n");
        stringBuilder.append("Описание : " + userRequest.getDescription() + "\n");

        if(userRequest.getItems() != null) {
            stringBuilder.append("\n");
            for (UserRequestItem item : userRequest.getItems()) {
                stringBuilder.append(item.getCatalogProduct().getName() + " " + item.getAmount());
            }
        }

        sendToEmail(stringBuilder.toString());
    }
}
