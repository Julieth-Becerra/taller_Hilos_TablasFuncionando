package com.example.taller_hilos;

import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Label extends javafx.scene.control.Label implements Runnable {

    //Define los atributos: El hilo, el string del label, el label hijo al que van ligado los turnos, el valor maximo del tiempo
    //que atiende a una persona y el valor maximo del tiempo que espera a una persona en milisegundos
    private Thread turns = null;
    private String type;
    private javafx.scene.control.Label child;
    private int max = 8000;
    private int max2 = 5000;


    //Inicializa el hilo y usa el runnable de la clase (this)
    public void start() {
        if (turns == null) {
            turns = new Thread(this);
            turns.start();
        }
    }

    //Se define el nodo hijo, al cual se le van a realizar las modificaciones
    public void setChild(javafx.scene.control.Label child) {
        this.child = child;
    }

    //Se define el string que es el que acopaña al numero de turno
    public void setType(String type) {
        this.type = type;
    }

    //Pone el tiempo maximo que se demora atendiendo a una persona (milisegundos)
    public void setMax(int max){
        this.max = max;
    }

    //Pone el tiempo maximo que se demora esperando a una persona (milisegundos)
    public void setMax2(int max2){
        this.max2 = max2;
    }

    //Runnable de la ejecucion de los turnos, y modificacion de los labels
    @Override
    public void run() {
        while (true) {
            for (int i = 1; i < 5000; i++) {
                final int cont = i;
                Platform.runLater(() -> {
                    setText("Atendiendo");
                    changeStatus(false);
                });
                try {
                    Thread.sleep((int) (Math.random() * (max - 2000 + 1) + 2000));
                    Platform.runLater(() -> {
                        child.setText(type + cont);
                        changeStatus(true);
                        setText("Esperando");
                    });
                    Thread.sleep((int) (Math.random() * (max2 - 2000 + 1) + 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Metodo encargado de cambiar el color del label en caso de que se encuentre ocupado o disponible
    private void changeStatus(boolean status) {
        if (status) {
            child.setTextFill(Color.GREEN);
        } else {
            child.setTextFill(Color.RED);
        }
    }


}
