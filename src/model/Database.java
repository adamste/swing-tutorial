package model;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
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

    public void save() throws SQLException {
        String checkSql="Select count(*) as count from people where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql="insert into people (id, name, age, employment_status, tax_id, " +
                "us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";

        String updateSql="update people set name=?, age=?, employment_status=?, tax_id=?, " +
                "us_citizen=?, gender=?, occupation=? where id=?";
        PreparedStatement updateStmt=con.prepareStatement(updateSql);

        PreparedStatement insertStatement=con.prepareStatement(insertSql);
        for (Person person:people){
            int id=person.getId();

            String name=person.getName();
            String occupation=person.getOccupation();
            AgeCategory ageCategory=person.getAgeCategory();
            EmploymentCategory employmentCategory=person.getEmpCat();
            String tax=person.getTaxId();
            boolean isUS=person.isUsCitizen();
            Gender gender=person.getGenderCommand();

            checkStmt.setInt(1,id);

            ResultSet checkResult=checkStmt.executeQuery();
            checkResult.next();

            int count=checkResult.getInt(1);

            if(count==0){
                System.out.println("Inserting person with id: "+id);

                int col=1;
                insertStatement.setInt(col++,id);
                insertStatement.setString(col++,name);
                insertStatement.setString(col++,ageCategory.name());

                insertStatement.setString(col++,employmentCategory.name());
                insertStatement.setString(col++,tax);
                insertStatement.setBoolean(col++,isUS);
                insertStatement.setString(col++,gender.name());

                insertStatement.setString(col++,occupation);

                insertStatement.executeUpdate();
            }else {
                System.out.println("Update person with id: "+id);

                int col=1;
                updateStmt.setString(col++,name);
                updateStmt.setString(col++,ageCategory.name());

                updateStmt.setString(col++,employmentCategory.name());
                updateStmt.setString(col++,tax);
                updateStmt.setBoolean(col++,isUS);
                updateStmt.setString(col++,gender.name());

                updateStmt.setString(col++,occupation);

                updateStmt.setInt(col++,id);

                updateStmt.executeUpdate();
            }

            System.out.println("Count for person with ID "+id+" is "+count);
        }
        insertStatement.close();
        checkStmt.close();
        updateStmt.close();
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
