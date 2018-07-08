import edu.duke.URLResource;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    public String readURL(){
        System.out.println("=====================");
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : ur.lines()){
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
           	int beg = item.lastIndexOf("\"",pos);
           	int end = item.indexOf("\"", pos+1);
           	System.out.println("*****new*****");
           	System.out.println(beg);
           	System.out.println(end);
           	System.out.println(item.substring(beg+1,end));
               }
            }
        return "finish";
    }
}
