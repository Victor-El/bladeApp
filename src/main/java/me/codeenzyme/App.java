package me.codeenzyme;

import com.blade.Blade;

public class App {

    public static void main(String[] args) {
        Blade.of().get("/", ctx -> {
            ctx.text("Welcome to root");
        }).start();
    }

}
