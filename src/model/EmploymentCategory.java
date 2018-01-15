package model;

public enum EmploymentCategory {
    employed("employed"),
    selfemployed("self employed"),
    unemployed("un employed"),
    other("other");

    private String text;

    private EmploymentCategory(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
