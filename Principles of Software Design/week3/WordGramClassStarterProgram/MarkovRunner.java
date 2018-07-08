
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        
        String st = markov.getRandomText(size); 
        printOut(st);       
    } 

    public void runMarkov() { 
        System.out.println("===========================");
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markov = new MarkovWord(5); 
        runModel(markov, st, 50, 844); 
    } 
    
    public void runEfficientMarkov() { 
        System.out.println("===========================");
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markov = new EfficientMarkovWord(2); 
        //st ="this is a test yes this is really a test yes a test this is wow";
        runModel(markov, st, 50, 65); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }
    
    public void compareMethods () {
        System.out.println("===========================");
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWord markov = new MarkovWord(2); 
        EfficientMarkovWord effMarkov = new EfficientMarkovWord(2);
        
        double begin2 = System.nanoTime();
        runModel(effMarkov, st, 500, 42);
        double end2 = System.nanoTime();
        double markovEfficientTime = end2 - begin2;
        
        double begin1 = System.nanoTime();
        runModel(markov, st, 500, 42);
        double end1 = System.nanoTime();
        double markovModelTime = end1 - begin1;
        
        System.out.println("Time for MarkovModel: " + markovModelTime + "\n"
	    + "Time for EfficientMarkovModel: " + markovEfficientTime);
    }

}
