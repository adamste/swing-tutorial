package model;

import java.io.Serializable;

public class Person implements Serializable{
    //brakuje mi tu serialVersionUUID
    private static int count=1;
    private int id;
    private String name;
    private String occupation;
    private AgeCategory  ageCategory;
    private EmploymentCategory empCat;
    private String taxId;
    private boolean usCitizen;
    private Gender genderCommand;

    public Person(String name, String occupation, AgeCategory ageCategory,
                  EmploymentCategory employmentCategory, String taxId,
                  Boolean usCitizen, Gender gender){
        this.name=name;
        this.occupation=occupation;
        this.ageCategory=ageCategory;
        this.empCat=employmentCategory;
        this.taxId=taxId;
        this.usCitizen=usCitizen;
        this.genderCommand=gender;
        this.id=count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public EmploymentCategory getEmpCat() {
        return empCat;
    }

    public void setEmpCat(EmploymentCategory empCat) {
        this.empCat = empCat;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public Gender getGenderCommand() {
        return genderCommand;
    }

    public void setGenderCommand(Gender genderCommand) {
        this.genderCommand = genderCommand;
    }
}