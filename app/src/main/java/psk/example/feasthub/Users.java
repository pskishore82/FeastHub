package psk.example.feasthub;

public class Users {

    String userId, name, profile;

    public Users(String userId, String name, String profile)
    {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
    }


    public Users()
    {
    }

    public String getUserId()
    {
        return userId;
    }

    public  void setUserId(String userId)
    {
        this.userId= userId;
    }

    public String getName()
    {
        return name;
    }

    public  void setName(String userId)
    {
        this.name= name;
    }
    public String getProfile()
    {
        return profile;
    }

    public  void setProfile(String userId)
    {
        this.profile= profile;
    }
}
