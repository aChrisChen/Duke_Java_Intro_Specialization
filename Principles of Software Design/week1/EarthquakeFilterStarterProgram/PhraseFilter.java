
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    
    public PhraseFilter (String Type, String Phrase) {
        type = Type;
        phrase = Phrase;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        String info = qe.getInfo();
        switch(type) {
            case "start":
                if (info.startsWith(phrase)){
                    return true;
                }
                break;
            case "any":
                if (info.indexOf(phrase) != -1){
                    return true;
                }
                break;
            case "end":
                if (info.endsWith(phrase)){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
    
    public String getName (){
        return "PhraseFilter,type="+type+",phrase="+phrase;
    }
}
