import java.util.*;
/**
 * Write a description of class EfficientMarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel
{
    private int x;
    private HashMap<String, ArrayList<String>> map;
    /**
     * Constructor for objects of class MarkovModel
     */
    public EfficientMarkovModel(int num)
    {
        // initialise instance variables
        map = new HashMap<String, ArrayList<String>>();
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
        for(int k=0; k < numChars - x; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            buildMap(key, follows);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            //System.out.println("check " + key);
            key = key.substring(1) + next;
            //System.out.println("check " + key);
            //System.out.println();
        }
        printHashMapInfo();
        return sb.toString();
    }
    
    public void buildMap(String key, ArrayList<String> follows) {
        if (!map.containsKey(key)) {
            map.put(key, follows);
        }
    }
        
    public String toString() {
        String temp = Integer.toString(x);
        return "EfficientMarkovModel order of " + temp; 
    }
    
    public void  printHashMapInfo() {
        int num = 0;
        String temp = null;
        System.out.println(map.keySet());
        System.out.println(map.keySet().size());
        Iterator<Map.Entry<String, ArrayList<String>>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = entries.next();
            if (entry.getValue().size() > num) {
                num = entry.getValue().size();
            }
        }
        Iterator<Map.Entry<String, ArrayList<String>>> ent = map.entrySet().iterator();
        while (ent.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = ent.next();
            if (entry.getValue().size() == num) {
                System.out.println(entry.getValue());
                System.out.println(entry.getKey());
            }
        }
        System.out.println("The max size: " + num);
    } 
}
