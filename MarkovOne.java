import java.util.Random;
import java.util.*;
/**
 * Write a description of class MarkovOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MarkovOne extends AbstractMarkovModel
{
    public MarkovOne() {
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size()); 
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovMpdel order of 1"; 
    }
}