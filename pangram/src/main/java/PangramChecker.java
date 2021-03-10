import java.util.ArrayList;

public class PangramChecker {

    public boolean isPangram(String input) {
        //filter out strings less than 26 characters
        if (input.length()<26) {
            return false;
        }
        else {
            String alphabetString = "abcdefghijklmnopqrstuvwxyz";
            ArrayList<Character> alphabet = new ArrayList<Character>();
            for (char c : alphabetString.toCharArray()) {
                alphabet.add(c);
            }

            //parse out string to an array
            //loop through array, comparing each character to an alphabet array.
            for (char i : input.toCharArray()){
                //Remove used characters until the end of the sentence is reached.
                char c = Character.valueOf(i);
                c = Character.toLowerCase(c);
                alphabet.remove(Character.valueOf(c));
            }
            //if alphabet array.length is > 0, it is not a pangram, return false;
            if (alphabet.size() == 0) {
                return true;
            }
            else {
                return false;
            }
        }






    }

}
