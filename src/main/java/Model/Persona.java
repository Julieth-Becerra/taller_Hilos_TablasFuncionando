package Model;

import javafx.beans.property.SimpleStringProperty;

public class Persona {

    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty id = new SimpleStringProperty();
    public SimpleStringProperty turno = new SimpleStringProperty();

    public String getNombre() {
        return nombre.get();
    }

    public String getId() {
        return id.get();
    }

    public String getTurno() {
        return turno.get();
    }

}
