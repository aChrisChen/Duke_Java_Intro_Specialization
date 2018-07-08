
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        String ldna = dna.toUpperCase();
        String lstartCodon = startCodon.toUpperCase();
        String lstopCodon = stopCodon.toUpperCase();
        
        int start = ldna.indexOf(lstartCodon);
        if (start == -1) return "";
        int stop = ldna.indexOf(lstopCodon, start + 3);
        if (stop == -1) return "";
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        } else {
            return "";
        }
    }
    
    public void testing() {
                String startCodon = "atg";
                String stopCodon = "taa";
        
		String a = "atgccctaa";
		String ap = "atgccctaa";
		//String a = "ATGCCCTAG";
		//String ap = "ATGCCCTAG";
		a.toUpperCase();
		ap.toUpperCase();
		String result = findSimpleGene(a, startCodon, stopCodon);
		System.out.println("=================");
		if (ap.equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
}
