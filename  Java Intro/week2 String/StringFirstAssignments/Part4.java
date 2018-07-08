import edu.duke.URLResource;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String readURL(){
        System.out.println("=====================");
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.lines()){
            String s_new = s.toLowerCase();
            int result = s_new.indexOf("youtube.com");
            if (result != -1){
                int start = s.indexOf("\"");
                int stop = s.lastIndexOf("\"");
                //System.out.println(s);
                //System.out.println(start);
                //System.out.println(stop);
                System.out.println(s.substring(start, stop + 1));
            }
        }
        return "finish";
    }
}
