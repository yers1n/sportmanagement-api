package sportmanagement.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    protected String name;
    protected int age;

    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120");
        }
        this.age = age;
    }
}