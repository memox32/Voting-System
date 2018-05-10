package EpicVotingSystem;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import static java.lang.System.out;


/**
 * File Name : VotingInterface.java
 * author : Guillermo Romero
 * Date : 19/09/2017
 * Description : Epic Voting System, allow staff of the company to vote for the candidate 
 * that will represent them in the monthly board of directors meeting
 */

public class VotingInterface
{
	//field variables declared
    private VotingController vc;
    private Staff theStaff;
    @SuppressWarnings("unused")//the candidate is used in some point in the code, this is for suppress warning
	private Candidate theCandidate;
    private Admin theAdmin;
    private String date = "";// this is used to set the date in the timeStamp

    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
    
    
	//Here is the start of your program. 
    public static void main(String[] args)
    {
        VotingInterface vi = new VotingInterface();
        vi.start();
    }

	//This methods is complete and does not require change.
    public void start(){
    	
    	//the voting date must run at the beginning of the program
    	runDateLimit();
    	vc = new VotingController();
        commenceVoting();
        
    }

	 
	//=======================================================================		
    //validates administrator username & password. This method is complete and does not require additional code.	   
	public boolean validateAdmin(){
		 //for this method a scanner to read user input is preferred
		
    	@SuppressWarnings("resource")//this is to indicate that keyboard should not been closed
		 Scanner keyboard = new Scanner(System.in);
		 boolean quit = false; //Flag
		 String pass = "";
		 String username = "";
		
		 //while quit is not true it keeps looping and asking for the inputs 
		 while(!quit){	
			 
			 try {
				 			 
			 	System.out.println("Enter user name or \"q\" to quit: ");			 				 	
			 	username = keyboard.next().trim();//use of trim to delete any unnecessary white spaces
	     		theAdmin = vc.getAdmin(username);

			 	//assign a value to the getAdmin method from the Admin class
		     	
		     	if (username.equalsIgnoreCase("q")) {//option to go back to the main screen 
	        			quit = true;	        		
			 	}
		     	
		     	else {   
		     		out.println("Please enter your password: ");
	        		pass = keyboard.next().trim();

	        		//If username "or" password are not correct the exception error message prompts and the loop continues
	        		if (theAdmin.getName() == null || vc.getAdminPassword(pass) == null) {
	        			quit = false;// The flag is set to false so the loop continues	              		
	        		}else {
	        			quit = true;// else the loop finish and the program continue
	        		}
	        	}//end else
		     	
			 }//end try
			 
			//If username "or" password are not correct this exception catch the error
			 catch (NullPointerException e) { 
				 out.println("\"The username or password is not correct\"\n");          		
			 }			 
			 
		 }//end while	
		
		 //validation returns true if username "and" password are correct else it returns false 
    	 if(theAdmin.getName() != null && theAdmin.getPassword() != null) { 		 		
			 return true;
         } 
         else{
        	 return false;
         }
    	 
    }//end method
	 
	//=======================================================================
	//validates staff username & password. This method is complete and does not require additional code.	   		    
	
	public boolean validateStaff(){
		 
    	@SuppressWarnings("resource")//this is to indicate that keyboard should not been closed
		 Scanner keyboard = new Scanner(System.in);//the scanner should not be closed in this method
		 boolean quit = false;//flag set to false
		 String pass = "";
		 String id = "";
		 int counter = 0;

		 //while quit is not true it keeps looping and asking for the inputs 		 
		 while(!quit){	
			 
			 try {
				 
				 //if the user has failed for 3 times the system go back to the main screen
				 if (counter == 3) {
						out.println("Maximun number of attemps rached, coming back to"
								+ " the main screen...\n ");
						quit = true;//flag set to true after 3 failed attemps
					}
				 // you can identify staff from arraylist by using 
			     // getStaff() as shown below
			     // theStaff = vc.getStaff(Integer.parseInt(getInput()));			    	
				 else {
				 out.println("Enter ID: ");//prompt to ask for user ID			 	
 
				 id = keyboard.next();//a string is preferred for better error handling
			     theStaff = vc.getStaff(Integer.parseInt(id));// the input is passed to the getStaff setter   	
			
			     
				 //the username is not validated yet to provide better security	     			     	   
			     out.println("Please enter your password: ");
		    	 pass = keyboard.next().trim();

		    	//If username "or" password are not correct the exception error message prompts and the loop continues		        	
			     if (vc.getStaff(Integer.parseInt(id)) == null || vc.getStaffPassword(pass)== null) {
		          		out.println("\"The username or password is not correct\"\n");
		          		quit = false;
		          		counter++;// the counter is increased with every loop (wrong username or password)
			     }
			     else {
			    	 quit = true;
			     }
			     	        	
				 }//else end
			 }//try end
			 catch (NumberFormatException e) {
				 out.println("\"This is not a number\"\n"); 
				 counter++;// the counter is increased with every loop (wrong username or password)
				 }
			 
		 }//end while
		 
		//validation returns true if username and password are correct else it returns false     	 
		 if(vc.getStaff(Integer.parseInt(id)) != null && vc.getStaffPassword(pass) != null){ 
		 			
			 return true;			 
         }
         else{
        	 return false;        	 
         }

    }//end method
	 
	 
	//=======================================================================	   
	//screen input reader. This method is complete and working ok.
    private String getInput(){
    	
        String theInput = "";

        try
        {
            theInput = in.readLine().trim();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return theInput;
        
    }
	 
     
   //=======================================================================
    
    
     /*validates the voting period returning a boolean value 
     * whether is true or false if it's allowed to vote or not
     */
    public boolean validateDateLimit(){
    	
    	//create two objects from the class Timestamp and set the time to the current date with a new date object
    	Timestamp time1 = new Timestamp (new Date().getTime());       	      	    	
    	Timestamp time2 = new Timestamp (new Date().getTime());
//    	String date = "2017-10-28 00:00:00.000";
    	//String date = timeLimit();
    	
    	//set Timestamp to the value of the date chosen from the user in the runDateLimit() method
    	time2 = Timestamp.valueOf(date); 
    	if (time1.after(time2)){//if the current time is after the allowed voting time return false else true	
    		return false;
    	}
    	else {
    		return true;} 	
    	
    }//end method
   
    
    //=======================================================================
    
    //this method displays the admin menu
    public void adminMenu() {
    	
    	boolean quit = false;
    	
    	out.printf("\nWelcome %s!: \n",theAdmin.getName());//it gets the name from the previous admin validation
    	
		while (!quit) {
		
		
			out.println("\nSelect \"v\" to view voting results, to modify/view system files \nselect \"a\" "
	    			+ "for admin, \"s\" for staff, \"c\" for candidate,\n"
					+ "select \"d\" to change voting ending period, "
					+ " \"q\" to go back to the main menu or \"t\" to terminate the system:");

			//get user input
			String option = getInput();			
			
			/*this are the options that are displayed to the admin, 
			 * also the manageAdmin() statements are now included here
			 */
			switch (option) {
				case "v":
	        		printVoteResults();
	        		break;
				case "s":
					modifyStaff();
	    			break;
				case "c":
					modifyCandidate();
	        		break;
				case "a":
	        		modifyAdmin();
	        		break;
				case "d":
					dateLimitMenu();
	        		break;
				case "q": //the case q and case t substitute the manageAdmin() method 
					commenceVoting();			
					quit = true;
	        		break;
				case "t": 
					out.println("Terminating the system, thank you...");
					System.exit(0);
					break;
	        	
				default:
					out.println("Incorrect input, please try again");
			}//end switch 
		}	    			
		
    }//end method
    
    
    //=======================================================================
    
    public void commenceVoting(){
        
        
        // Use this method to display menu to vote or login as admin.
        // get user input by using getInput() methods.
        // call  manageVote() or validateAdmin() then manageAdmin()  
        // based on user input
    	
    	
    	/* do while true, important to do the loop in case the user input an incorrect key,
    	 * in this case it keeps looping until the condition is false
    	 * this is just for showing another way to loop without using flags
    	 * nonetheless this might be discouraged because it might not been clear,
    	 * when the user input a wrong key the result is true and the error message is showed,
    	 * when the user input a wring key the result is false and the loop is finished.
    	 */    	
    	
    	String keyboard;
    	
    	do { 
    		
        	try {
	    	System.out.println("********************Welcome to the Epic Voting System********************");
	        System.out.println("\nEnter \"v\" to vote as staff, \"a\" to login in as system adminirstaror, "
	        		+ "\"h\" for help info or \"q\" to finish the system: ");
	        
	        keyboard = getInput();
	        
	      //validates if the voting period is still open, if it's not open a message displays 
	      // and the system is closed otherwise proceeds with the staff validation
	        if (keyboard.equalsIgnoreCase("v")) {
	        	if (!validateDateLimit()) {
	        		out.println("The voting period has finished, you can't vote now.");
	        		out.println("Closing the system.....");	        		
	        		System.exit(0);
	        		}
	        	else {
	        		if (validateStaff()) {	
	        			manageVote();	        			
	        		}
	        	}
	        }//end if
	        else if(keyboard.equalsIgnoreCase("a")) { 
	        	
	        	if (validateAdmin()) {
	        		adminMenu();
	        	}
	        }
	        else if (keyboard.equalsIgnoreCase("h")) {
	        	helpFile();
	        }
	        else if (keyboard.equalsIgnoreCase("q")) {
	        	out.println("Closing the system...");
	        	System.exit(0);
	        }
	        else {//error message shown when the user input a wrong key
	        	System.out.println("\n\"Your input is not correct\"\n");
	        }
        	}//end try
	        catch (NullPointerException e) { 
	        	//this catch a letter q input when the admin want to log in and select "q" 
	        	//to go back to the main menu
	        }
        	catch (NumberFormatException e) { 
	        	//this catch a incorrect input "letter instead of numbers" when the staff want to log in and select "q" 
	        	//to go back to the main menu
	        }
	    
    	}while(true);

    }//end method
        
     
    //=======================================================================
    
    //validate if the staff has already voted or not
    public void manageVote(){
    	
    	//the request for input input was moved to the validateStaff() method
    	
    	
	       
	        // Use this method to enable staff to login.
	    	
	    	// you can identify staff from arraylist by using
	        // getStaff() as shown below
	        // theStaff = vc.getStaff(Integer.parseInt(getInput()));
	    	
	    	// After a user login with a valid ID
	        // call getStaffVote() if user has not voted , otherwise display
	        // a message to inform the user that they can't vote again.
	    	
    	//if the staff has voted the message is displayed 
    	//else if the staff has not voted the getStaffVote is called
	    if (theStaff.hasVoted() == 1) {
	    	out.println("You have already voted, you can't vote again!\n");
	    }
	    else if (theStaff.hasVoted() == 0) {
	   		getStaffVote();
	    }
	    	
    }//end method
   
    
    //=======================================================================
    
	/*this method validates an int value from the user's input
	 *this is used in the modify classes to avoid that the user enters negative IDs and Codes 
	 */
    public int validateInt() {
    	
    	/*it's important that the scanner is not closed in this method
    	 *otherwise you will get an infinite IOException: Stream closed error
    	 */
    	@SuppressWarnings("resource")//this is to indicate that keyboard should not been closed
    	Scanner keyboard = new Scanner(System.in);
    	
    	boolean validate = false;
    	int theInput = 0;

    	//while the flag is false keeps the iteration
        while(!validate  ) {
        	
        	try{
        	    	
	            theInput = keyboard.nextInt();
	            //if the number is negative the error message 
	            //is displayed and the flag is set to false else the validation continues in the modify methods
	            if (theInput <0) { 
	            	out.println("\"The number must be greater than 1\"");
	        		out.println("\nEnter number again: ");
	                
	            	validate = false;
	            }
	            else {
	            validate = true;
	            }
        	}
        	//catch inputs that are not valid and request for a valid input again
	        catch (InputMismatchException e) {
	        	out.println("This is not a number or is not a valid number");
        		out.println("\nEnter number again: ");
	         	keyboard.nextLine();	         	
	        }        	
        }
        return theInput;
    }//end method
    
      
    
    //=======================================================================
    
    //this method contains the options to manage the candidate information
    public void modifyCandidate(){
    	try
        {
    		 //candidates file reader and writer initialized
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             File theTempFile = new File("candidatesTemp.txt");/////check
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(theFile, true));//append is set to true
             BufferedWriter tempWriter = new BufferedWriter(new FileWriter(theTempFile));//a temp file is created

             
             out.println("Enter \"v\" to veiw, \"a\" to add and \"d\" to delete candidate information "
             		+ "\nor \"q\" to go back to the admin menu: ");             
             String option = getInput().trim();
             
             if (option.equalsIgnoreCase("q")) {
            	 adminMenu();
             }
             //display candidates list
             else if (option.equalsIgnoreCase("v")){
         	
	        	 String candidateData;
	        	 out.println("\nCode       Name\n");
	        	 while((candidateData = reader.readLine())!= null)//while there is line with text do...
	             {
	                 String[] candidateDetails = candidateData.split(",");
	                 int code = Integer.parseInt(candidateDetails[0]);//only the name and code are displayed
	                 String name = candidateDetails[1];
	     
	                 out.printf("%2d \t%9s%n" ,code, name);    
	             }        	
             }//end else if "v"
             
             //display series of prompts to add a new candidate
             else if  (option.equalsIgnoreCase("a")){
            	 
            	 boolean quit = false;
            	 
            	 do {//do while quit is false
            		 
		        	String candidateDetails;

		        	out.println("\nEnter cadidate code: ");
		            int code = validateInt();

		            Candidate candidateCode = vc.getCandidate(code);	
				     
			         //if the candidate code already exist the system display the below error message
			         //and ask admin to re-enter, else the flow continue
		        	 if (candidateCode != null) {
				    	 out.print("This candidate code already exist...\n");
				    	 quit = false;
		        	 }	
		        	 else {        
			        
			            out.println("\nEnter candidate name: ");
			            String name = getInput().trim();
			                 
			            out.println("\nEnter candidate department: ");
			            String department = getInput().trim();
			                 
			            out.println("\nEnter number of votes: ");
			            int votes = validateInt();
			                 
			            out.println("\nThe new candidate information has been added successfully\n");
			                 	
			                 	                	
			            candidateDetails = code + "," + name + "," + department + ","  + votes +"\n";
			            // the information is appended so the existing information is not overwritten
		                writer.append(candidateDetails);
		                writer.close();	                 	 
		                quit = true;
		                
		        	 }//end else

            	 }while(!quit);
        	
             }//end else if "a"
             
             
             //display series of prompts to delete a candidate           
             else if (option.equalsIgnoreCase("d")) {
            	 
            	 boolean quit = false;
            	 
            	 while (!quit) {
            		 
            	 
		        	 out.println("\nEnter the code of the candidate to delete: ");
		        	 int code = validateInt();
		        	 String currentLine;// = reader.readLine(); //change name?????
		        	 
		        	 Candidate candidateCode = vc.getCandidate(code);	
				     
		        	 if (candidateCode == null) {
				    	 out.print("This staff ID is not valid...\n");
				    	 quit = false;
		        	 }	
				     else {
			    		        	 
			        	 //it reads line by line until it find a line without information
			        	 while ((currentLine = reader.readLine()) != null) {
			        		 
			        		 String[] candidateDetails = currentLine.split(",");
			        		 //this boolean variable compare the code that the user input vs the codes in the file
			        		 
			        		 //the id variable is converted to String to handle file text better
			        		 boolean codes = candidateDetails[0].equals(Integer.toString(code));
			                 
			        		 /*the reader copies line by line from the candidate file to a temp file and when the 
			        		  * system finds the code that you want to delete (!codes) it doesn't copy this to 
			        		  * the temp file.
			        		  * it uses the System getProperty method with the line.separator property, 
			        		  * this separate lines in text files, it's used as a new line character in Windows
			        		  * (OS dependent line separator)
			        		  */
			        		 if (!codes)   /////:3
			        		 	tempWriter.write(currentLine + System.getProperty("line.separator"));
			        	 }//end while
		        	 		        	 
		        	 
		        	 reader.close();    		 
		        	 tempWriter.flush();//the use of flush is to ensure that all the buffered data is written in the file
	        		 tempWriter.close();
	        		 writer.close(); 
	        		 
	        		 out.println("\"The candidate has been deleted successfully\"");	        		 
	        		 quit = true;//flag is set to true to exit the loop
	        		 
	        		 //if the file cannot be deleted the error message is displayed
	        		 //and it also deletes the old file
	        		 if (!theFile.delete()) {
        		        System.out.println("Could not delete file");
        		        return;
	        		 }
	        		 //if the temp file cannot be renamed the error message is displayed
	        		 //and it also renames the temp file with the old file name(candidates.txt)
	        		 if (!theTempFile.renameTo(theFile)) {
        		        System.out.println("Could not rename file");
	        		 }  
	        		 
				     }//end else
		        	 
            	 }//end while
	        		         	 
        	}//end else if "d"
            	             
         }//end try
    	
    	/*catch if the files have a problem	to be opened, 
         *this catch is for the try at the beginning of the method  
         */
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate info from file");
         }       
    	
    }//end method
    
    
    //=======================================================================
    
    //this method validates the has voted or has not voted input when the new staff is being added 
    public int validateStaffInt() {
    	
    	@SuppressWarnings("resource")//this is to indicate that keyboard should not been closed
    	Scanner keyboard = new Scanner(System.in);
    	
    	boolean validate = false;
    	int theInput = 0;

        while(!validate  ) {
        	
        	try{
        	
        		out.println("\nEnter 1 if staff has voted and 0 if staff hasn't voted: ");
                	
	            theInput = keyboard.nextInt();
	            
	            /*with this we make sure the admin doesn't input a number >1 or <0
	             * if the input is not correct the system displays the error message 
	             * and ask for the input again 
	             */
	            if (theInput >=2 || theInput <0) { 
	            	out.println("\"The number must be 0 or 1\"");
	            	validate = false;
	            }
	            else {
	            	validate = true;
	            }
        	}//end try
        	
        	//catch any invalid character and ask the user to try again
	        catch (InputMismatchException e) {
	        	out.println("This is not a number or is not a valid number, please try again...");		         			         	
	         	keyboard.nextLine();	         	
	        }        	
        }//end while    
        
        return theInput;//a valid user input is returned
    
    }//end method
  
    
    //=======================================================================
    
    //this method contains the options to manage the staff information
    public void modifyStaff(){
    	
    	try {
       	
    		 //staff file reader and writer initialized    		    
    		 String fileName = "staff.txt";
             File theFile = new File(fileName);
             File theTempFile = new File("staffTemp.txt");
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(theFile, true));
             BufferedWriter tempWriter = new BufferedWriter(new FileWriter(theTempFile));

             
             out.println("Enter \"v\"to view staff list, \"vv\" to veiw staff that have voted"
             		+ "\n\"nv\" for staff that haven't voted, \"a\" to add, \"d\" "
             		+ "to delete staff information \nor \"q\" to come back to the admin menu: ");
             
             String option = getInput().trim();
             if (option.equalsIgnoreCase("q")) {
            	 adminMenu();
             }
             else if (option.equalsIgnoreCase("v")){
         	
	        	 String staffData;
	
	        	 while((staffData = reader.readLine())!= null)
	             {
	                 String[] staffDetails = staffData.split(",");
	                 int id = Integer.parseInt(staffDetails[0]);
	                 String name = staffDetails[1];
	     
	                 out.printf("%2d \t%9s%n" ,id, name);    
	             }        	
             }
             //display staff that have voted
             else if (option.equalsIgnoreCase("vv")){
            	 out.println("The following staff have voted:\n");
            	 out.println("  ID       Name              Vote date");
            	 vc.getStaffVoted();
            	 }
             //display staff that have not voted
             else if (option.equalsIgnoreCase("nv")){
            	 
            	 out.println("The following staff haven't voted:\n");
            	 out.println("  ID       Name      ");
            	 vc.getStaffNoVoted();
             }
    
             else if  (option.equalsIgnoreCase("a")){
            	 
            	 boolean quit = false;
            	 
            	 do {
            		 
			         String staffDetails;
			         
			         //call validateInt() method to validate user ID input                 
			         out.println("\nEnter staff ID: ");                 
			         int id = validateInt();
			         
			         Staff staffID = vc.getStaff(id);	
				     
			         //if the staff id already exist the system display the below error message
			         //and ask admin to re-enter, else the flow continue
		        	 if (staffID != null) {
				    	 out.print("This staff ID already exist...\n");
				    	 quit = false;
		        	 }	
		        	 else {        
			         out.println("\nEnter staff name: ");
			         String name = getInput().trim();
			                 	
			         out.println("\nEnter staff password: ");
			         String password = getInput().trim();
			               
			         //validate if has voted calling the validateStaffInt() method
			         int hasVoted = validateStaffInt();
			         
			         
			         //this prompts a message asking for the time stamp the formats showed below must be used
			         String timeStamp = "";
			         
			         /*if the staff has not voted the system automatically set time stamp to null,
			          *this is important to prevent malfunction in the system when retrieving information
			          *like the staff that have voted and haven't
			          */
			         if (hasVoted == 0) {		        	 
			        	 timeStamp = "null";
			         }
			         /*this is in case the new user is a staff that has already voted but was probably
			          * deleted by accident and needs to be saved into the file again, the time is set to
			          * the default value 00:00:00, this is important for other methods so the system assign
			          * this value automatically
			          */
			         else {			        	 
			         out.println("\nEnter staff \"time stamp\" if staff has voted (use yyyy-mm-dd format)\n"
			        		 +"or write \"null\" if staff hasn't voted: ");
			          timeStamp = getInput().trim() + " 00:00:00";
			         }
			         			         
			         out.println("The new staff information has been added successfully\n");
			                 	
			         staffDetails = id + "," +name + "," + password + "," + hasVoted + "," + timeStamp +"\n"; 
		             writer.append(staffDetails);
		             writer.close();	                 	 
		             quit = true;
		        	 }
            	 }while(!quit);
        	
             }//end else if "a"
             
             else if (option.equalsIgnoreCase("d")) {
            	 
            	 boolean quit = false;
            	 
            	 while(!quit) {
            		             		  		        	 
            		 out.println("\nEnter the ID of the staff to delete: ");
		        	 int id = validateInt();
		        	 
		        	 String currentLine;		        	 		        	 
		        	 Staff staffCode = vc.getStaff(id);	
				     
		        	 if (staffCode == null) {
				    	 out.print("This staff ID is not valid...\n");
				    	 quit = false;
		        	 }	
				     else {
			        	 while ((currentLine = reader.readLine()) != null) {
			        	
			        		 String[] staffDetails = currentLine.split(",");
			        		 //the id variable is converted to String to handle file text better
			        		 boolean idNum = staffDetails[0].equals(Integer.toString(id));
			        		 
	
			                 /*if a staff ID is different from the one that the user input, 
			                  * this is copied to a temp file, only the ID that the user input 
			                  * is not copied to the temp file 		                 
			                  */
			                  if (!idNum)   /////:3
			        		 	tempWriter.write(currentLine + System.getProperty("line.separator"));
			        	 }		        	 
		        	 
		        	 reader.close();    		 
		        	 tempWriter.flush();
	        		 tempWriter.close();
	        		 writer.close(); 
	        		 
	        		 out.println("The staff has been deleted successfully");
	        		 quit = true;
	        		 if (!theFile.delete()) {
        		        System.out.println("Could not delete file");
        		        return;
	        		 }
	        		 if (!theTempFile.renameTo(theFile)) {
        		        System.out.println("Could not rename file");
	        		 }        		 
				     
				     }//end else
		        	             				        	             	 
                 }//end while
             
             }//end else if "d"
             
    	}//end try
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading staff info from file");
         }        	 
        
    }//end method

    
    //=======================================================================
    //this method contains the options to manage the admin information    
    public void modifyAdmin(){
    	
    	
    	try {
   		 	 //admin file reader and writer initialized    	    
             String fileName = "admin.txt";
             File theFile = new File(fileName);
             File theTempFile = new File("adminTemp.txt");
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             // using append mode so it doesn't overwrite the file
             BufferedWriter writer = new BufferedWriter(new FileWriter(theFile, true));
             BufferedWriter tempWriter = new BufferedWriter(new FileWriter(theTempFile));

             
             out.println("Enter \"v\" to veiw, \"a\" to add and \"d\" to delete candidate information "
             		+ "\nor \"q\" to go back to the admin menu: ");             
             String option = getInput().trim();
             if (option.equalsIgnoreCase("q")){
            	 adminMenu();
             }
             else if (option.equalsIgnoreCase("v")){
         	
	        	 String adminData;
	
	        	 while((adminData = reader.readLine())!= null)
	             {
	                 String[] adminDetails = adminData.split(",");
	                 int id = Integer.parseInt(adminDetails[0]);
	                 String name = adminDetails[1];
	     
	                 out.printf("%2d \t%9s%n" ,id, name);    
	             }        	
             }
             else if  (option.equalsIgnoreCase("a")){
	    
            	 boolean quit = false;
             	
            	 do {
	            	 
            		 	 String adminDetails;
		                 	
		                 out.println("\nEnter admin id: ");
		                 int id = validateInt();
		                 
		                 Admin adminID = vc.getAdminID(id);	
					     
				         //if the staff id already exist the system display the below error message
				         //and ask admin to re-enter, else the flow continue
			        	 if (adminID != null) {
					    	 out.print("This admin ID already exist...\n");
					    	 quit = false;
			        	 }	
			        	 else {        
				        
			                 out.println("\nEnter admin name: ");
			                 String name = getInput().trim();
			                 	
			                 out.println("\nEnter admin username: ");
			                 String username = getInput().trim();
			                 
			                 out.println("\nEnter admin password: ");
			                 String password = getInput().trim();
			                 
			                 out.println("The new admin information has been added successfully\n");
			                 	
			                 	                	
			                 adminDetails = id + "," +name + "," + username + "," + password +"\n";
		          
			                 writer.append(adminDetails);
		                 	 writer.close();
		                 	 quit = true;
		            	 
			        	 }//end else
			        	 
	        	 }while(!quit);
    
             }//end else if "a"
                         
             else if (option.equalsIgnoreCase("d")) {

            	 boolean quit = false;
            	 
            	 while (!quit){
            		 
            	 
            		 out.println("\nEnter the id of the admin to delete: ");
		        	 
 		        	 int id = validateInt();  
		        	 String currentLine; 		        	 
		        	 Admin adminID = vc.getAdminID(id);	

		        	 
		        	 if (adminID == null) {
				    	 out.print("This admin ID is not valid...\n");
				    	 quit = false;
		        	 }	
				     else {
			        	 
			        	 while ((currentLine = reader.readLine()) != null) {
			       
			        		 String[] adminDetails = currentLine.split(","); 	                 
			        		 boolean idNum = adminDetails[0].equals(Integer.toString(id));
			                 	 		                 
			        		 if (!idNum)   
			        		 	tempWriter.write(currentLine + System.getProperty("line.separator"));
			        	 }
			        	 
		        	 
		        	 reader.close();    		 
		        	 tempWriter.flush();
	        		 tempWriter.close();
	        		 writer.close(); 

	        		 out.println("The admin has been deleted successfully");
	        		 quit = true;
	        		 
	        		 if (!theFile.delete()) {
        		        System.out.println("Could not delete file");
        		        return;
	        		 if (!theTempFile.renameTo(theFile)) {
        		        System.out.println("Could not rename file");
	        		 }        		 	        		         	 
	        		 	        		
            
				     }//end else
		        	 
            	 }//end while
            	 
			 }//end else if "d"
             
         }//end try
    	
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading admin info from file");
         }        	       
    	
    }//end method

    //=======================================================================    
    public void helpFile() {

    	try {
	        String fileName = "help file.txt";
	        File theFile = new File(fileName);
	        BufferedReader reader = new BufferedReader(new FileReader(theFile));
	        
	        String helpFile;
	
	        while((helpFile = reader.readLine())!= null)
	        {
	        	//System.getProperty("line.separator") is used again, in this case for reading 
	        	//any line in a  
	            String[] helpFileDetails = helpFile.split(System.getProperty("line.separator"));
	            String data = helpFileDetails[0];
	            out.println(data);   
	        }
	        reader.close();
    	}
    	catch(IOException e)
    	{
    		System.out.println("Error! There was a problem with loading date info from file");
    	}

    }//end method
    
    
    //=======================================================================
    //reads the voting date period limit
    public void runDateLimit() {
    
    	try
        {
    		//probar con scanner
             String fileName = "voting date.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             
             String dateData;

             while((dateData = reader.readLine())!= null)
             {
                 String[] dateeDetails = dateData.split(",");
                 String dates = dateeDetails[0];
                 this.date = dates;
             }
             reader.close();
        }
    	catch(IOException e)
        {
            System.out.println("Error! There was a problem with loading date info from file");
        }

	}//end method
    
    
    public void dateLimitMenu() {
    	 
    	try
        {
             String fileName = "voting date.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));
             
             out.println("Enter \"v\" to veiw, \"a\" to add/update vote date information "
              		+ "or q to come back to the admin menu:");             
             
             String option = getInput().trim();
              
              if (option.equalsIgnoreCase("q")) {
             	 adminMenu();
              }
              
              else if (option.equalsIgnoreCase("v")){

            	  String dateData;

                  while((dateData = reader.readLine())!= null){
                           
                	  String[] dateeDetails = dateData.split(",");                                                      
                	  String date = dateeDetails[0];                      
                	  out.println(date);
                  }
                  reader.close();                     
                              	  
              }//end else if "v"
              
              else if (option.equalsIgnoreCase("a")) {
    
            	  try {
            		
            		 //using default mode so it overwrites the file
                     BufferedWriter writer = new BufferedWriter(new FileWriter(theFile));
          	        		 	
 	                 out.println("\nEnter the date: ");
 	                 int date = Integer.parseInt(getInput()+ ""+ " 00:00:00.000");//adding the time format to the user input
 	                 
 	                 out.println("The new date information has been mofified successfully\n");
 	                 	
 	                 writer.write(date);
                  	 writer.close();
          		
 		         	}
 		         	catch (IOException e) {
 		               System.out.println("Error! There was a problem with writing date limit info to file");
 		         	}
            	  	catch (NumberFormatException e) {
		               System.out.println("This is not a number, please try again");
		         	}
            	  
              }//end else if "a"
             
        }//end first try
    	
    	catch(IOException e)
        {
            System.out.println("Error! There was a problem with loading candidate names from file");
        }
    	
    }//end method

    
    //=======================================================================
    //this displays the candidate's name and code to the user
    public void displayVotingScreen (){
    	 
       
        // Use this methods to  welcome & display staff name after a successfull login 
        // then display candidate names and instructions on how to place a vote.		

    	out.printf("%nHello %s %n%n", theStaff.getName());
	    	 out.println("=======================");
	    	 out.println(" Code   Candidate");
	    	 out.println("=======================");
	    	 
	    	 try {
	    		 
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
	                 String department = candidateDetails[2];
	                 theCandidate = new Candidate(code, name, department, votes);
		             out.printf("%3s\t%s%n", code, name);
		         }
		         reader.close(); 	 
	    	 }
	    	 catch (IOException e) {
	    		 out.println("Error loading the file");
	    	 }
	}//end method
    
		
    //=======================================================================
    //manages a staff vote 
    private void getStaffVote(){
	
   
    // Use this methods to  capture and confirm a vote for staff
	// then display candidate names and indtructions on how to place a vote.
    	 
    	boolean quit = false;	 
    
    	displayVotingScreen();
    	
    	while (!quit) {
    	
    		try {
    			
		    	System.out.println("\nEnter the code of one of the candidates or press q to return: ");
		    	String code = getInput().trim();
		 
		    	if (code.equalsIgnoreCase("q")) {
		    		quit = true;
		    		out.println("Thank you, coming back to the main screen...\n");   		
		    	}
		    	else {
			    
		    		Candidate candidateCode = vc.getCandidate(Integer.parseInt(code));
			    	out.printf("You have voted for %s, press \"y\" to coutinue or \"q\" to go to selection screen again: "
			    			, candidateCode.getName());

			    	String confirm = getInput();
			    	if (confirm.equalsIgnoreCase("y")) {
				    	vc.recordVote();
				    	out.println("\nThank you, your vote has been registered.\n");
				    	quit = true;
			    	}
		    	}//end else
    		}//end try
        	
    		//exception handling for incorrect numbers or invalid characters
    		catch (NullPointerException e) {
        		out.println("\"Incorrect candidate number\"");
        	}
    		catch (NumberFormatException e) {
        		out.println("\"This is not a number\"");
        	}
    	}//end while     	
    	
    }//end method
	
	
    //=======================================================================
    
    //prints out the voting results after a successful admin login
    public void printVoteResults(){

       
        // Use this methods to  display voting statistics & results of votes  after 
    	// a successful admin login the call manageAdmin() method to determine what to do next.
    	 
    	out.println("Staff Voting Statistics\n");
    	   	 
    	out.printf("Numbers on voting list: %d%n", vc.getTotalVoters());     	 
    	vc.displayVotes();     	 
    	     	 
    	out.println("\n\t  Voting results\n");
    	 
    	out.println("Code      Name          Department    Votes       (%)\n");         	 
    	vc.getCandidateStatistic();
    	 
    }//end method
    
}//end public class
