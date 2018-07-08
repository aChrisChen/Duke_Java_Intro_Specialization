
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        //for(int k=0; k < 3; k++){
        //    String st= markov.getRandomText(size);
        //    printOut(st);
        //}
        //String st= markov.getRandomText(size);
        //printOut(st);
    }
    
    public void testEfficientMethods(){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 1000;
	int seed = 531;
        
        EfficientMarkovModel mTwoEff = new EfficientMarkovModel(5);
	runModel(mTwoEff, st, size, seed);
    }
    
    public void compareMethods() {
	FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 1000;
	int seed = 615;
		
	MarkovModel mTwo = new MarkovModel(2);
	double begin = System.nanoTime();
        runModel(mTwo, st, size, seed);
        double end = System.nanoTime();
        double markovModelTime = (end-begin);
        
        EfficientMarkovModel mTwoEff = new EfficientMarkovModel(2);
	begin = System.nanoTime();
	runModel(mTwoEff, st, size, seed);
	end = System.nanoTime();
	double markovEfficientTime = (end-begin);
	System.out.println("Time for MarkovModel: " + markovModelTime + "\n"
	    + "Time for EfficientMarkovModel: " + markovEfficientTime);
    }
    
    public void testHashMap () {
        System.out.println("====================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace("\n", " ");
        int size = 50;
        int seed = 42;
        
        IMarkovModel ef = new EfficientMarkovModel (2);
        runModel(ef, st, size, seed);
    }
    
    public void runMarkov() {
        System.out.println("====================");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        IMarkovModel mz = new MarkovZero();
        runModel(mz, st, size, 32);
    
        IMarkovModel mOne = new MarkovOne();
        runModel(mOne, st, size, 32);
        
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 32);
        
        IMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size, 32);

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
    
}
