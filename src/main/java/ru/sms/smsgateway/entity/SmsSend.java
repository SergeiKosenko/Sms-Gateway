package ru.sms.smsgateway.entity;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sms.smsgateway.controllers.SmsRestController;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsSend {

    private String user;
    private String pass;
    private String action;
    private String sender;
    private String message;
    private String target;

    SmsRestController smsRestController = new SmsRestController();

    public  String addSms(String phone, String pin) throws IOException {

        String url = "https://a2p-sms-https.beeline.ru/proto/http/rest";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        JsonObject object = new JsonObject();
        object.addProperty("user", "login");        // Логин и пароль будем брать
        object.addProperty("pass", "password");     // из конфига от сервера билайн
        object.addProperty("action", "post_sms");   // СМС или ПУШ
        object.addProperty("sender", "my.site.ru"); // Имя отправителя регистрируем у билайн
        object.addProperty("message", pin);
        object.addProperty("target", phone);

        System.out.println(object); //Выводим в консоль Json
        System.out.println();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(object.toString());
        wr.flush();
        wr.close();
        return "Здесь ответ Json с сервера билайн ()";
    }
}
