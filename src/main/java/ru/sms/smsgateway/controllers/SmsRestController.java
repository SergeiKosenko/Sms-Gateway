package ru.sms.smsgateway.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sms.smsgateway.entity.SmsSend;

import java.io.IOException;

@Data
@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsRestController {

    @PostMapping
    public void smsSend(String phone, String pin) throws IOException {

        SmsSend smsSend = new SmsSend();
        smsSend.addSms(phone, pin);
    }
}
