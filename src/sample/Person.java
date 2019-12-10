package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Person {

    private String icon;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;

    private  StringProperty choiceValue = new SimpleStringProperty();

    Person() {

    }

    public Person(String choiceValue) {
        this.choiceValue.set(choiceValue);
    }

    Person(String icon, String fName, String lName, String email) {
        this.icon = icon;
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.choiceValue.set(UUID.randomUUID().toString());
    }


    public String getChoiceValue() {
        return choiceValue.get();
    }

    public StringProperty choiceValueProperty() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue.set(choiceValue);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                '}';
    }
}
