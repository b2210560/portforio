package com.example.wsp_spring.model;
import java.io.FileReader;
import java.util.Properties;


import jakarta.mail.MessagingException;


import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.util.Properties;

import java.io.IOException;
import java.io.FileNotFoundException;
import jakarta.mail.MessagingException;

import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.Transport;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class mailService {

    @Autowired
    MailSender mailSender;

    public String mailSend(String mainText,String titleText) {

        try {
            SimpleMailMessage simpleMsg = new SimpleMailMessage();

            // 送信元アドレスをセット
            simpleMsg.setFrom("b2210560@photon.ac.jp");
            // 送信先アドレスをセット
            simpleMsg.setTo("info@b2210560okichi.shop");
            // 表題をセット
            simpleMsg.setSubject(titleText);
            // 本文をセット
            simpleMsg.setText(mainText);

            // メール送信
            mailSender.send(simpleMsg);
                // 成功時のメッセージ
                return "送信しました";
            } catch (MailException e) {
                // 失敗時のメッセージ
                return "送信できませんでした" + e;
        }

    }
}