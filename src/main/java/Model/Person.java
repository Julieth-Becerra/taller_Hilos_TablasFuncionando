package Model;

public class Person {

    private String name;
    private String id;
    private String module;
    private boolean status;
    private String turn;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void changeStatus(){
        this.status = !this.status;
    }

    public void asignTurn(String turn){
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", module='" + module + '\'' +
                ", status=" + status +
                ", turn='" + turn + '\'' +
                '}';
    }
}
