
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
        System.out.println("======================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024);
        markov.setTraining(st);
        
        String text = markov.getRandomText(500);
        printOut(text);
        //for(int k=0; k < 3; k++){
        //    String text = markov.getRandomText(500);
        //    printOut(text);
        //}
    }
    
    public void runMarkovOne() {
        System.out.println("======================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(365);
        markov.setTraining(st);
        
        String text = markov.getRandomText(500);
        printOut(text);
        
    }
    
    public void runMarkovFour() {
        System.out.println("======================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovFour markov = new MarkovFour();
        
        markov.setRandom(715);
        markov.setTraining(st);
        
        String text = markov.getRandomText(715);
        printOut(text);
        
    }
    
    public void runMarkovModel() {
        System.out.println("======================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovModel markov = new MarkovModel(7);
        
        markov.setRandom(953);
        markov.setTraining(st);
        
        String text = markov.getRandomText(500);
        printOut(text);
        
    }
    
    private void printOut(String s){
        //in order to realize the change line
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------start------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n---------------finish-------------------");
    }
    
}
