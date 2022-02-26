package com.example.taller_hilos;

import javafx.application.Platform;

public class Label extends javafx.scene.control.Label implements Runnable {

    private Thread turns= null;

    public Label() {
    }

    public void start (){
        if (turns == null){
            turns = new Thread(this);
            turns.start();
        }
    }


    @Override
    public void run() {
        while (true) {
            for (int i = 1; i < 5000; i++) {
               final int cont = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setText("A" + cont);
                    }
                });
                try {
                    Thread.sleep((int) (Math.random() * (8000 - 2000 + 1) + 2000));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
