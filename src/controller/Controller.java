package controller;

import gui.FormEvent;
import model.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    Database database = new Database();

    public ArrayList<Person> getPeople(){
        return database.getPeople();
    }

    public void addPerson(FormEvent ev) {
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCategory = ev.getAgeCategory();
        String empCat = ev.getEmpCat();
        boolean isUs = ev.isUsCitizen();
        String taxId = ev.getTaxId();
        String gender = ev.getGenderCommand();

        AgeCategory ageCat;
        switch (ageCategory) {
            case 0:
                ageCat = AgeCategory.child;
                break;
            case 1:
                ageCat = AgeCategory.adult;
                break;
            case 2:
                ageCat = AgeCategory.senior;
                break;
            default:
                ageCat = AgeCategory.senior;
        }

        EmploymentCategory employmentCategory;
        if (empCat.equals("employed")) {
            employmentCategory = EmploymentCategory.employed;
        } else if (empCat.equals("selfemployed")) {
            employmentCategory = EmploymentCategory.selfemployed;
        } else if (empCat.equals("unemployed")) {
            employmentCategory = EmploymentCategory.unemployed;
        } else if (empCat.equals("other")) {
            employmentCategory = EmploymentCategory.unemployed;
        } else {
            employmentCategory = EmploymentCategory.other;
        }

        Gender genderCat;
        if (gender.equals("male")) {
            genderCat = Gender.male;
        } else {
            genderCat = Gender.female;
        }

        Person person = new Person(name, occupation, ageCat, employmentCategory, taxId, isUs, genderCat);

        database.addPerson(person);
    }

    public void saveToFile(File file) throws IOException {
        database.saveToFile(file );
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        database.loadFromFile(file);
    }
}
