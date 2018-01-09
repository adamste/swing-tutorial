package model;

import java.io.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Database {
    private List<Person> people;
    private Connection con;
    public Database() {
        people = new LinkedList<>();
    }

    public void connect() throws Exception {
        if(con!=null) return;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }
        String connectionUrl="jdbc:mysql://localhost:3306/root";
        con= DriverManager.getConnection(connectionUrl, "root", "root");

        System.out.println("Connected: "+con);
    }

    public void disconnect(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Can't close connection");
            }
        }
    }
    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = people.toArray(new Person[people.size()]);

        oos.writeObject(persons);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Person[] persons = (Person[]) ois.readObject();
        people.clear();
        people.addAll(Arrays.asList(persons));

        ois.close();
    }

    public void removePerson(int index) {
        people.remove(index);
    }
}
