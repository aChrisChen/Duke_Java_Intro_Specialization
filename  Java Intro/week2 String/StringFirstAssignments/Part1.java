
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene (String s){
        int start = s.indexOf("atg");
        if (start == -1) return "";
        int end = s.indexOf("taa", start + 3);
        if (end == -1) return "";
        if ((end - start) % 3 == 0) {
            return s.substring(start, end + 3);
        } else {
            return "";
        }
    }
    
    public void testing() {
		String a = "atgccctaa";
		String ap = "atgccctaa";
		//String a = "ATGCCCTAG";
		//String ap = "ATGCCCTAG";
		String result = findSimpleGene(a);
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
