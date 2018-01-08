package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private ArrayList<Person> people;

    public Database() {
        people = new ArrayList<Person>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public ArrayList<Person> getPeople() {
        return people;
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
}
