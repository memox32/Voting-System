package EpicVotingSystem;


/**
 * File Name : Staff.java
 * author : Guillermo Romero
 * Date : 19/09/2017
 * Description : Epic Voting System, allow staff of the company to vote for the candidate 
 * that will represent them in the monthly board of directors meeting
 */
public class Admin
{
	//declaration of instance variables
    private int id;
    private String name;
    private String username;
    private String password;
    
    
    //constructor for admins
    public Admin(int id, String name, String username, String password)
    {
            this.id = id;
            this.name = name;
            this.username = username;
            this.password = password; //important to save correctly
    }
    
    

    //method to set ID
    public void setId(int id)
    {
       this.id = id;
    }
    //method to get ID
    public int getId()
    {
       return id;
    }
    //method to set name
    public void setName(String name)
    {
            this.name = name;
    }
    //method to get name
    public String getName()
    {
            return name;
    }
    //method to set username
    public void setUsername(String username)
    {
            this.username = username;
    }
    //method to get username
    public String getUsername()
    {
            return username;
    }

    //method to set password
    public void setPassword(String password)
    {
       this.password = password;
    }
    //method to get password
    public String getPassword()
    {
       return password;
    }
   
    //method to validate Admin
    public boolean validateAdmin(String username, String password)
    {
       if(username.equalsIgnoreCase(username)&&(password.equals(password))){
           return true;
       }
       else{
           return false;
       }
    }
}//end public class
