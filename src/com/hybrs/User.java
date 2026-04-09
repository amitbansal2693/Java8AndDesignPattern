package com.hybrs;

public class User
{
    //default access and protected access are same within the same package but protected access is different when we are trying to access it from different package
    String defaultName= "pari";
    public String publicName= "Sona";
    protected String protectName ="chotu";
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
