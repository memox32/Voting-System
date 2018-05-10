package EpicVotingSystem;
import static java.lang.System.out;
import java.io.*;
import java.util.*;

/**
 * File Name : VotingControler.java
 * author : Guillermo Romero
 * Date : 19/09/2017
 * Description : Epic Voting System, allow staff of the company to vote for the candidate 
 * that will represent them in the monthly board of directors meeting
 */


public class VotingController
{
    //Create an Arraylist read & store staff, candidate and admin data from file
    private ArrayList<Staff> staffs = new ArrayList<Staff>();
    private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    
    
   //Type to access individual staff, candidate and admin from array list
    private Staff theStaff;
    private Candidate theCandidate;
    private Admin theAdmin;       
    
    
    //VotingController constructor
    public VotingController()
    {
        loadStaffData();
        loadCandidateData();
        loadAdminData();
    }

    //=================================================================
    
    //writes candidates data back to file
    public void saveCandidateData()
    {
        //Use this method to write data back to candidate text file
      
    	try {
    		BufferedWriter writer = new  BufferedWriter(new FileWriter("candidates.txt"));
            Iterator<Candidate> it = candidates.iterator();
            String candidateDetails;
            while(it.hasNext())
            {
            	theCandidate = (Candidate) it.next();
            	candidateDetails = theCandidate.getCandidateCode() + "," +theCandidate.getName() + "," + theCandidate.getDepartment() + "," + theCandidate.getVotes() +"\n";
            	writer.write(candidateDetails);// getDepartmet() is added
            }
            writer.close();
    		
    	}
    	catch (IOException e) 
    	{
    		System.out.println(e);
    	}	
    }//end method
        
    //loads candidates from file. This method is complete and working ok.
    public void loadCandidateData()
    {
        try
        {
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 int code = Integer.parseInt(candidateDetails[0]);
                 int votes = Integer.parseInt(candidateDetails[3]);
                 String name = candidateDetails[1];
                 String department = candidateDetails[2];//department is added to this method
                 theCandidate = new Candidate(code, name, department, votes);
                 candidates.add(theCandidate);
             }
             reader.close();
         }//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
         catch(NumberFormatException e){
            System.out.println("Error! Open the file and check for invalid data...");
            System.exit(0);
         }	
    }//end method 
   
    
    //=================================================================        
    //returns a candidate if found in the candidate ArrayList
    public Candidate getCandidate(int candidateCode)
    {
        //Use this method to return the candidate if found in the candidates ArrayList
        
        Iterator<Candidate> it = candidates.iterator();
        while(it.hasNext())
        {
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode()== candidateCode)
            {
                return theCandidate;
            }
        }
    	return null;
    }
     
    //=================================================================    
    //returns the collection of candidates
    public ArrayList<Candidate> getCandidates()
    {
        return candidates;
    }

     //******************************************************\\
    //========================================================\\


    
    //writes  admin data back to file
    public void saveAdminData(){
    	
        //Use this method to write data back to admin text file
    	try {
    		BufferedWriter writer = new  BufferedWriter(new FileWriter("admin.txt"));
            Iterator<Admin> it = admins.iterator();
            String adminDetails;
            while(it.hasNext())
            {
            	theAdmin = (Admin) it.next();
            	adminDetails = theAdmin.getId() + "," +theAdmin.getName() + "," + theAdmin.getUsername() + "," + theAdmin.getPassword() + "\n";
            	writer.write(adminDetails);//admin username and password added
            }
            writer.close();
    		
    	}//end try
    	catch (IOException e) 
    	{
    		System.out.println(e);
    	}	
    }//end method
        
    
    //========================================================
    //loads Admins from file
    public void loadAdminData()
    {
        try
        {
             String fileName = "admin.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String adminData;

             while((adminData = reader.readLine())!= null)
             {
                 String[] adminDetails = adminData.split(",");
                 int id = Integer.parseInt(adminDetails[0]);
                 String name = adminDetails[1];
                 String username = adminDetails[2];
                 String password = adminDetails[3];
                 
                 theAdmin = new Admin(id, name, username , password);
                 admins.add(theAdmin);
             }
             reader.close();
         }//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
         catch(NumberFormatException e){
            System.out.println("Error! Open the file and check for invalid data...");
         System.exit(0);
         }	
    }//end method
   
    //========================================================    
    //returns an admin if found in the admin ArrayList "search by ID"
    public Admin getAdminID(int id)
    {
       //Use this method to return the admin if found in the admins ArrayList       
        Iterator<Admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getId() == id) 
            { 
            	return theAdmin;
            }
        }
    	return null;
    }//end method

  //========================================================    
    //returns an admin if found in the admin ArrayList
    public Admin getAdmin(String adminUsername)
    {
        //Use this method to return the admin if found in the admins ArrayList       
        Iterator<Admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getUsername().equals(adminUsername)) 
            { 
            	return theAdmin;
            }
        }
    	return null;
    }//end method
    
    //========================================================

    //returns an Admin password if found in the Admin ArrayList
    public Admin getAdminPassword(String adminPassword)
    {
       //Use this method to return the admin password if found in the admins ArrayList     
        Iterator<Admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getPassword().equals(adminPassword))
            {
                return theAdmin;
            }
        }
    	return null;
    }

    
    //returns the collection of candidates
    public ArrayList<Admin> getAdmins()
    {
        return admins;
    }

    //=================================================================
    //writes staffs data back to file
    public void saveStaffData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("staff.txt"));
            Iterator<Staff> it = staffs.iterator();
            String staffDetails;
           
            while(it.hasNext())
            {
                theStaff = (Staff) it.next();
                staffDetails = theStaff.getId() + "," +theStaff.getName() + "," + theStaff.getPassword() + "," + theStaff.hasVoted() + "," + theStaff.getTimeStamp() +"\n";
                writer.write(staffDetails);//getTimeStamp() added
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
        
    //=================================================================
    //loads staff names from file.
    public void loadStaffData()
    {
        //Use this method to read data from staff text file
    	// and store in arraylist .
   
    	try
        {
             String fileName = "staff.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String staffData;

             while((staffData = reader.readLine())!= null)
             {
                 String[] staffDetails = staffData.split(",");
                 int code = Integer.parseInt(staffDetails[0]);
                 String name = staffDetails[1];
                 String password = staffDetails[2];
                 int vote = Integer.parseInt(staffDetails[3]);
                 String timeStamp = staffDetails[4]; 
                 
                 theStaff = new Staff(code, name, password, vote,timeStamp );
                 staffs.add(theStaff);
             }
             reader.close();
         }//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    	catch(NumberFormatException e)
        {
            System.out.println("Error! Open the file and check for invalid data...");
            System.exit(0);
        }	
    }//end method
    
    //=================================================================        
    //returns a staff if found in the staffs ArrayList   
    public Staff getStaff(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId()== id)
            {
                return theStaff;
            }
        }
        return null;
    }
    
    //=================================================================
    //get staff that has not voted
    public void getStaffNoVoted()
    {    
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext()) {
            theStaff = (Staff) it.next();
            	//when a staff has not voted instead of the date a null is set
            	if (theStaff.getTimeStamp().contains("null")) {	
            		out.printf("%d \t%9s%n" ,theStaff.getId(), theStaff.getName());
            	}
        }
        
    }//end method
	    
    //=================================================================
    //get staff that has not voted   
    public void getStaffVoted()
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            		
            		//if while iterating through the array a time stamp contains ":" 
            		//it returns that staff.
            		//(this is used when the time is written in the time stamp, for example 20:00:40)          		
            		if (theStaff.getTimeStamp().contains(":")) {            			
            		out.printf("%d \t%9s \t%2s%n" ,theStaff.getId(), theStaff.getName(),theStaff.getTimeStamp());
            		}
        }
    }//end method
    
    //=================================================================       
    //returns a staff password if found in the staffs ArrayList
    public Staff getStaffPassword(String staffPassword)
    {
        // Use this method to return the candidate if found in the candidates ArrayList       
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getPassword().equals(staffPassword) )
            {
                return theStaff;
            }
        }
    	return null;
    }
    
    
    //=================================================================
    //this is for counting how many staff have voted, this is information is displayed in the statistics
    public int getVotes() {

    	int voteCounter = 0;
    	
    	try
        {
             String fileName = "staff.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             
             String staffData;

             while((staffData = reader.readLine())!= null)
             {
                 String[] staffDetails = staffData.split(",");

                 int vote = Integer.parseInt(staffDetails[3]);                 
                 
                	 if (vote == 1) {
                		 		
                		 voteCounter++;
                 }
             }//end while
             reader.close();
         }//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading staff info from file");
         }
		return voteCounter;

    }//end method

    
    //returns total number of staffs in the collection
    public int getTotalVoters()
    {
        return staffs.size();
    }

    //when a staff votes this records different information to the staff and candidates files
    public void recordVote()
    {
        theStaff.setVoted();
        theCandidate.addVote();
        theStaff.setTimeStamp();//time stamp added
        saveStaffData();//save to file
        saveCandidateData();//save to file
    }

    
    //=================================================================
    //this method displays the number of staff that have voted, this is used in the 
    //printVoteResults() method in the VotingInterface class to show part of the statistics
    public void displayVotes() {
    	
    	double percentage =   (getVotes() * 100.00) / getTotalVoters();
        System.out.printf("Number of staff voted : %d (%.2f%%)%n", getVotes(),percentage);//escaped percent sign to show % after f value  	
    }
    
    //=================================================================
    /*this is used in the printVoteResults() method in the VotingInterface class
     * to show part of the statistics, the design of the output is handled in the
     * printVoteResults() method 
     */
    
    public void getCandidateStatistic() {
    	
    	try
        {
    	     String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             
             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 // Arrays.sort(candidateDetails);
                 int code = Integer.parseInt(candidateDetails[0]);
                 int votes = Integer.parseInt(candidateDetails[3]);
                 String name = candidateDetails[1];
                 String department = candidateDetails[2];
                 
                 //percentage of the votes of each candidate vs the overall votes
                 double percentage = (votes * 100) / getVotes();

                 System.out.printf("%2d \t%9s \t%9s \t%d \t(%.2f%%)%n" ,code, name, department, votes, percentage);    
                 }
             reader.close();
         }//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }//end method
    
}//end public class