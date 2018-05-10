package EpicVotingSystem;
/**
 * File Name : Candidate.java
 * author : Guillermo Romero
 * Date : 19/09/2017
 * Description : Epic Voting System, allow staff of the company to vote for the candidate 
 * that will represent them in the monthly board of directors meeting
 */
public class Candidate
{
	//declaration of instance variables
    int candidateCode = 999;//999 is not an eligible candidate
    String name = null;
    String department;
    int votes = 0; //total votes

    //constructor for candidates
    public Candidate(int candidateCode, String name, String department, int votes)
    {
        this.candidateCode = candidateCode;
        this.name = name;
        this.department = department;
        this.votes = votes;
    }
    //method to get candidate code
    public int getCandidateCode ()
    {
        return candidateCode;
    }
    //method to get name
    public String getName()
    {
        return  name;
    }
    //method to get departmanet   
    public String getDepartment()
    {
        return  department;
    }
    //method to get name
    public int getVotes()
    {
        return  votes;
    }
    //method to add vote
    public void addVote()
    {
        votes++;
    }
}//end public class