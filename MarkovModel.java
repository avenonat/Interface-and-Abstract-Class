import java.util.*;
/**
 * Write a description of class MarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MarkovModel extends AbstractMarkovModel
{
    private int x;

    /**
     * Constructor for objects of class MarkovModel
     */
    public MarkovModel(int num)
    {
        // initialise instance variables
        x = num;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
       
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-x);
        //System.out.println(index);
        String key = myText.substring(index, index + x);
        //System.out.println(key);
        sb.append(key);
        for(int k=0; k < numChars - x; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size()); 
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
            //System.out.println(key);
        }
        
        return sb.toString();
    }
    public String toString() {
        String temp = Integer.toString(x);
        return "MarkovMpdel order of " + temp; 
    }
}
