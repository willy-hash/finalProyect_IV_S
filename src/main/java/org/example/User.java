package org.example;

public class User {
    private String id;
    private  String name;
    private String password;
    private String rol;

    public User() {
    }

    public User(String id, String name, String password, String rol) {
        this.id = id;
        this.name = name;
        this.rol = rol;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
