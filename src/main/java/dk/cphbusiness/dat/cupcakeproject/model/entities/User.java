package dk.cphbusiness.dat.cupcakeproject.model.entities;

import java.util.Objects;

public class User
{
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private String address;
    private Account account;

    public User(String name, String email, String password, Role role)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.account = new Account(0);
    }
    public User(String name, String email, String phone, String password, Role role, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.address = address;
    }
    public User(String name, String email, String phone, String password, Role role, String address, Account account) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.address = address;
        this.account = account;
    }


    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getPassword()
    {
        return password;
    }

    public Role getRole()
    {
        return role;
    }

    public String getAddress()
    {
        return address;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getName().equals(user.getName()) && getEmail().equals(user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && getPassword().equals(user.getPassword()) && getRole() == user.getRole() && Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getName(), getEmail(), getPhone(), getPassword(), getRole(), getAddress());
    }
}
