package me.codeenzyme;

import com.blade.Blade;

public class App {

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
        }).start();
    }

}
