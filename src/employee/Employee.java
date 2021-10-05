package employee;

public class Employee {
    int id;
    String name;
    int age;

    static int nextId = 1;

     static public int getNextId() {
        return nextId;
    }

    static public void setNextId(int nid){
          nextId = nid;
    }

    public Employee() {
        id = nextId;
        nextId = nextId + 1;
    }
    public Employee(int i){
        id = i;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    public int getId(){
        return id;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int newAge){
        age = newAge;
    }

    public String toString(){
        System.out.println(" ");
        return "E " + id + " " + name + " " + age;
    }
}
