package employee;

public class Manager extends Employee {
    int numOfsub;
    Employee[] subordinates;


    public Manager(){
        super();
        numOfsub = 0;
        subordinates = new Employee[20];
    }
    public Manager(int i){
       super(i);
        numOfsub = 0;
        subordinates = new Employee[20];
    }

    public void addSub(Employee employee){
        subordinates[numOfsub] = employee;
        numOfsub = numOfsub + 1;
    }

    //@Override
    public String toString() {
       String s = "M " + id + " " + name + " " + age;
        s = s + " subs : ";
        for(int i = 0; i<numOfsub; i =i +1){
            System.out.println(" ");
            s = s + subordinates[i].id + " ";
        }
        return s;
    }
}
