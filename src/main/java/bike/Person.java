package bike;

import java.util.Objects;

public class Person {

    private final String name;
    private int balance;

    public Person(String name) {
        this(name, Integer.MAX_VALUE); // Set to max value as default because we never add, only subtract
    }

    public Person(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;
        return balance == person.balance &&
          Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }

    @Override
    public String toString() {
        return "Person{" +
          "name='" + name + '\'' +
          ", balance=" + balance +
          '}';
    }
}
