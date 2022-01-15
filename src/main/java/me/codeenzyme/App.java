package me.codeenzyme;

import com.blade.Blade;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class App {

    public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    public static final String PHONE_NUMBER = System.getenv("PHONE_NUMBER");
    public static final String TO_PHONE_NUMBER = System.getenv("TO_PHONE_NUMBER");

    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 9000;
        }

        Blade.of().listen(port).get("/", ctx -> {
            ctx.text("Welcome to root");
        }).get("/sms", ctx -> {
            ctx.request().body().toString();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(TO_PHONE_NUMBER),
                    new com.twilio.type.PhoneNumber(PHONE_NUMBER),
                    "Hello there!")
                    .create();

            System.out.println(message.getSid());
        }).start();
    }

}
