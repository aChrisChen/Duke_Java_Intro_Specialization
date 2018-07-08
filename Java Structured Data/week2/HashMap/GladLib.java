import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String, ArrayList<String>> map;
    String[] words = {"adjective", "noun", "color","country","name",
                         "animal", "timeframe", "verb", "fruit"};
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        map = new HashMap<String, ArrayList<String>> ();
        for (String word : words) {
            ArrayList<String> list = readIt(source+"/"+word+".txt");
            map.put(word, list);
        }                 
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        for (String word : words) {
            if (label.equals(word)) {
                return randomFrom(map.get(word));
            }
        }
        return "**UNKNOWN**";
    }
	
    private String processWord(String w){
        int first = w.indexOf("<");
	int last = w.indexOf(">",first);
	if (first == -1 || last == -1){
		return w;
	}
	String prefix = w.substring(0,first);
	String suffix = w.substring(last+1);
	String sub = getSubstitute(w.substring(first+1,last));
	return prefix+sub+suffix;
    }
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){ // \\s is a single space
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void totalWordsInMap () {
	   int count = 0;
	   for (String word : words){
	       count += map.get(word).size();
	   }
	   System.out.println("In total, we have "+count+" different words.");
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}
	


}
