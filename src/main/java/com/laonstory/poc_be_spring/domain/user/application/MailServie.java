package com.laonstory.poc_be_spring.domain.user.application;


import com.laonstory.poc_be_spring.domain.user.dto.request.MailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MailServie {

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "austin@underpin.kr";



    private final char[] passwordTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&',
            '*', '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};


    public String mailSend(String userEmail, MailDTO mailDTO) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);//누구에게 보내냐
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mailDTO.getSubject());
        message.setText(mailDTO.getTexts().get(0) +  mailDTO.getTexts().get(1) +mailDTO.getCode());
        mailSender.send(message);
        return "Y";

    }


    public String madeCode() {


        Random random = new Random(System.currentTimeMillis());

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            buffer.append(passwordTable[random.nextInt(passwordTable.length - 1)]);
        }

        return buffer.toString();
    }


}
