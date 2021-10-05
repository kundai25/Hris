
import employee.Employee;
import employee.Manager;

import java.io.*;
import java.util.Scanner;


public class Hris {
    String password;
    Scanner scanner;
    Employee[] employees;
    int numOfEmployees;

    Hris() {
        password = "admin";
        scanner = new Scanner(System.in);
        employees = new Employee[100];
        numOfEmployees = 0;

    }
    void run() throws IOException {
        if (login()) {
           readFile();
            menu();
        } else {
            System.out.println("System terminated");

        }
    }

    void printLogin() {
        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*      to be named in real app            *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.print("Password :");
    }

    boolean login() {
        while (true) {
            cleanScreen();
            printLogin();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if (input.equals(password)) {
                return true;
            } else if (input.equals("bye")) {
                return false;
            }
        }
    }

    void cleanScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }


    }


    void printMainMenu() {
        System.out.println("**********************************************************");
        System.out.println("*                                                        *");
        System.out.println("*           some optionals                               *");
        System.out.println("*                                                        *");
        System.out.println("*           1 . EMPLOYEES                                *");
        System.out.println("*           2 . ADD                                      *");
        System.out.println("*           3 . REMOVE                                   *");
        System.out.println("*           4 . SEARCH                                   *");
        System.out.println("*           5 . ASSIGN                                   *");
        System.out.println("*           6 . EXIT                                     *");
        System.out.println("*                                                        *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
        System.out.print("select option : ");
    }


    boolean menu() throws IOException {
        while (true) {
            cleanScreen();
            printMainMenu();
            String input;
            input = scanner.next();
            if ("1".equals(input)) {
                showAll();
                break;
            } else if ("2".equals(input)) {
                add();
                break;
            } else if ("3".equals(input)) {
                removeAll();
                break;
            } else if ("4".equals(input)) {
                search();
                break;
            } else if ("5".equals(input)) {
                assign();
                break;
            } else if ("6".equals(input)) {
                writeFile();
                System.out.print("EXIT!");
                return true;

            }
            scanner.next();
        }
        return menu();
    }


    void add() {
        String input;
        Employee employee;
        System.out.print("E/M");
        input = scanner.next();


        switch (input) {
            case "E":
                employee = new Employee();
                break;
            case "M":
                employee = new Manager();
                break;

            default:
                System.out.print("wrong title!");
                return;
        }

        System.out.println("enter your name");
        input = scanner.next();
        employee.setName(input);
        //Integer.parseInt("age");

        System.out.println("enter your age");
        input = scanner.next();
        employee.setAge(Integer.parseInt(input));

        employees[numOfEmployees] = employee;
        numOfEmployees = numOfEmployees + 1;

    }

    void showAll() {
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            System.out.println(employees[i].toString());

        }
        scanner.next();
    }

    void removeAll() {
        System.out.print("enter item to remove");
        int input;
        input = scanner.nextInt();
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            if (employees[i].getId() == input) {
                employees[i] = employees[numOfEmployees - 1];
                numOfEmployees = numOfEmployees - 1;
                System.out.print("successfully removed");
                scanner.next();
                return;
            }
        }
        System.out.print("failed to remove target id");
        scanner.next();
    }

    void search() {
        while (true) {
            cleanScreen();
            printfindPannel();
            String input = scanner.next();
            if ("1".equals(input)) {
                findById();
                break;
            } else if ("2".equals(input)) {
                findByName();
                break;
            } else if ("3".equals(input)) {
                findByAge();
                break;
            } else if ("r".equals(input)) {
                return;
            }
        }
        scanner.next();

    }

    void printfindPannel() {
        System.out.println("**********************************************************");
        System.out.println("*                                                        *");
        System.out.println("*                  search opt                            *");
        System.out.println("*                                                        *");
        System.out.println("*           1 . BY ID                                    *");
        System.out.println("*           2 . BY NAME                                  *");
        System.out.println("*           3 . BY AGE                                   *");
        System.out.println("*           r . RETURN                                   *");
        System.out.println("*                                                        *");
        System.out.println("*                                                        *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
        System.out.print("enter the option : ");

    }

    void findById() {
        System.out.print("enter the id : ");
        int input;
        input = scanner.nextInt();
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            if (employees[i].getId() == input) {
                System.out.println(employees[i].toString());
            }
        }
        scanner.next();
    }

    void findByName() {
        System.out.print("enter the name : ");
        String input;
        input = scanner.next();
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            if (employees[i].getName().equals(input)) {
                System.out.println(employees[i].toString());
            }
        }
    }

    void findByAge() {
        System.out.print("enter the age : ");
        int input;
        input = scanner.nextInt();
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            if (employees[i].getAge() == input) {
                System.out.print(employees[i].toString());
            }
        }

    }

    void assign() {
        int idE, idM;
        System.out.print("Enter the employee id  : ");
        idE = scanner.nextInt();

        Employee sub = getByid(idE);

        System.out.print("Enter the manager id  : ");
        idM = scanner.nextInt();
        Employee manager = getByid(idM);

        if (manager.getClass() != Manager.class) {
            System.out.println(idM + "not a manager");
            scanner.next();
            return;
        }


        ((Manager) manager).addSub(sub);
    }

    Employee getByid(int id) {
        for (int i = 0; i < numOfEmployees; i = i + 1) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    void writeFile() throws IOException {
        FileWriter fw = new FileWriter("database.txt");

        fw.write(Employee.getNextId() + "\n");

        for (int i = 0; i<numOfEmployees; i = i + 1){
            fw.write(employees[i].toString());
            fw.write("\n");
        }
        fw.close();
    }

    void readFile() throws IOException {
        FileReader fr = new FileReader( "database.txt");
        BufferedReader br = new BufferedReader(fr);

        String input;
        input = br.readLine();
        Employee.setNextId(Integer.parseInt(input));

        while(input != null){
            input = br.readLine();
            if(input == null)break;
            String[] data = input.split(" ");
            Employee employee;
           if(data[0].equals("E")){
               employee = new Employee(Integer.parseInt(data[1]));
           }else if(data[0].equals("M")){
                employee = new Manager(Integer.parseInt(data[1]));
            }else{
               System.out.print( "Error!");
               return;
           }
           employee.setName(data[2]);
           employee.setAge(Integer.parseInt(data[3]));


           employees[numOfEmployees] = employee;
           numOfEmployees = numOfEmployees + 1;
        }
        br.close();

        fr = new FileReader( "database.txt");
        br = new BufferedReader(fr);
        br.readLine();
        while (true){
            input = br.readLine();
            if(input == null) break;
            String[] data = input.split(" ");
            if(data[0].equals("M")){
                Manager manager= (Manager) getByid(Integer.parseInt(data[1]));
                for(int i = 6; i < data.length; i = i +1){
                    Employee employee = getByid(Integer.parseInt(data[i]));
                    manager.addSub(employee);
                }
            }
        }
        br.close();
    }

}


