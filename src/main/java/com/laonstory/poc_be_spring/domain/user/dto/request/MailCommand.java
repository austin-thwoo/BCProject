package com.laonstory.poc_be_spring.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MailCommand {
    private String userEmail;
    private String title;
    private String message;

    public void setMessage(String message){
        this.message=message;
    }
}
