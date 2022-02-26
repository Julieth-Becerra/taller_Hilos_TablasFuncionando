package com.example.taller_hilos;

import Model.Person;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Label extends javafx.scene.control.Label implements Runnable {

    //Define los atributos: El hilo, el string del label, el label hijo al que van ligado los turnos, el valor maximo del tiempo
    //que atiende a una persona y el valor maximo del tiempo que espera a una persona en milisegundos
    private Thread turns = null;
    private javafx.scene.control.Label child;
    private int max = 8000;
    private int max2 = 5000;
    private List<Person> list;

    public Label() {
        list = new ArrayList<>();
    }

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


    //Pone el tiempo maximo que se demora atendiendo a una persona (milisegundos)
    public void setMax(int max) {
        this.max = max;
    }

    //Pone el tiempo maximo que se demora esperando a una persona (milisegundos)
    public void setMax2(int max2) {
        this.max2 = max2;
    }

    public void addItem(Person person) {
        list.add(person);
    }

    public int getSize() {
        return list.size();
    }

    //Runnable de la ejecucion de los turnos, y modificacion de los labels
    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isStatus()) {
                final int cont = i;
                Platform.runLater(() -> {
                    child.setText(list.get(cont).getTurn());
                    changeStatus(0);
                    setText("Esperando");
                });
                try {
                    Thread.sleep((int) (Math.random() * (max2 - 2000 + 1) + 2000));
                    Platform.runLater(() -> {
                        setText("Atendiendo");
                        changeStatus(1);
                    });
                    Thread.sleep((int) (Math.random() * (max - 2000 + 1) + 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    list.get(i).changeStatus();
                }
            }
        }
        Platform.runLater(() -> {
            setText("Sin turno");
            child.setText("");
        });
        changeStatus(3);
        turns = null;

    }


    //Metodo encargado de cambiar el color del label en caso de que se encuentre ocupado o disponible
    private void changeStatus(int status) {
        if (status == 0) {
            child.setTextFill(Color.GREEN);
        } else if (status == 1) {
            child.setTextFill(Color.RED);
        } else {
            child.setTextFill(Color.BLACK);
        }
    }


}
