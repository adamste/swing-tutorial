import model.*;

import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running DB test");
        Database db=new Database();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.addPerson(new Person("Adam", "Java Programmer", AgeCategory.child,  EmploymentCategory.selfemployed, "123", true, Gender.male));
        db.addPerson(new Person("Bartek", "PHP Programmer", AgeCategory.senior,  EmploymentCategory.employed, null, false, Gender.male));
        db.addPerson(new Person("Lukas", "Logistic specialist", AgeCategory.senior,  EmploymentCategory.employed, null, false, Gender.male));
        try {
            db.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            db.load();
        } catch (SQLException e) {
            System.err.println("Cannot load from DB.");
            e.printStackTrace();
        }

        db.disconnect();
    }
}
