package dk.cphbusiness.dat.cupcakeproject.model.entities;

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
}
