package EpicVotingSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File Name : Staff.java
 * author : Guillermo Romero
 * Date : 19/09/2017
 * Description : Epic Voting System, allow staff of the company to vote for the candidate 
 * that will represent them in the monthly board of directors meeting
 */
public class Staff
{
	//declaration of instance variables
    private int id;
    private String name;
    private int voted; //has the staff voted
    private String password;
    private String timeStamp;
    
    
  //constructor for staff
    public Staff(int id, String name, String password, int voted, String timeStamp)
    {
            this.id = id;
            this.name = name;
            this.password = password;
            this.voted = voted;
            this.timeStamp = timeStamp; //important to save correctly
    }
    //method to set time stamp
    public void setTimeStamp() {
    	//this.timeStamp = timeStamp;
    	this.timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }
    //method to get time stamp
    public String getTimeStamp() {
    	
    	return timeStamp;
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
    
    //method to set ID
    public void setPassword(String password)
    {
       this.password = password;
    }
    //method to get ID
    public String getPassword()
    {
       return password;
    }
    //method to mark that staff has voted
    public void setVoted()
    {
            this.voted = 1;
    }
    //method to check staff that has voted
    public int hasVoted()
    {
            return voted;
    }
    //method to validate Staff
    public boolean validateStaff(String username, String password)
    {
       if(username.equalsIgnoreCase(username)&&(password.equals(password))){
           return true;
       }
       else{
           return false;
       }
    }
}//end public class
