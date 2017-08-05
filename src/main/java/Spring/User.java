package Spring;

public class User 
{
    private String password;
    private String username;

    public User()
    {

    }

    public User(String username,String password)
    {
        this.password = password;
        this.username = username;
    }

    public String getUser()
    {
        return password;
    }

    public void setUser(String user)
    {
        this.password = user;
    }

    public String getName()
    {
        return username;
    }

    public void setName(String name)
    {
        this.username = name;
    }
    
    public Boolean login()
    {
    	return true;
    }
    
    public Boolean signUp()
    {
    	return true;
    }
}