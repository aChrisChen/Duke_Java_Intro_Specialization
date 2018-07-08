
/**
 * Write a description of Debugging1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Debugging1 {
    public void findAbc(String input){
       System.out.println("************");
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println(index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
       }
    }
    
    public void test(){
        System.out.println("============");
        String a = "abc";
        int w = 1;
        int p = 2;
        System.out.println((w+1)+","+(p+1));
        System.out.println(a.substring(0,3));
        System.out.println(a.substring(0,5));
        
    }
}
