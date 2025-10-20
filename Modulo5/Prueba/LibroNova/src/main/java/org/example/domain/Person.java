package org.example.domain;

public class Person {
    private int id;
    private String name;
    private String contact;
    private String password;
    private Role role;

    public Person() {}

    public Person(int id, String name, String contact, String password, Role role) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.password = password;
        this.role = role;
    }

    public Person(int id, String name, String contact, Role role) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.role = role;
    }

    public Person(String name, String contact, Role role) {
        this.name = name;
        this.contact = contact;
        this.role = role;
    }

    public Person(int id, String name, String contact, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.password = password;
        this.role = new Role(2); // Default role as member
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", role=" + role.getName() +
                '}';
    }
}
