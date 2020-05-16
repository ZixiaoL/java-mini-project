package homework8;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TextingDictionary {
    //each input and its press sequence
    private final Map<String, List<ValidTextKeyPress>> dictionary = new HashMap<>(0);

    public void insert(String word) throws IllegalArgumentException {
        if(word == null || word.length() == 0) {
            throw new IllegalArgumentException("Input not valid.");
        }
        //its press sequence
        List<ValidTextKeyPress> dictionaryValue = new ArrayList<>(0);
        //find the sequece of the input
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //check if input valid
            if(!(c-'a'>=0 && c-'a'<26 || c-'A'>=0 && c-'A'<26)) {
                throw new IllegalArgumentException("Input not valid.");
            }
            //get its corresponding number
            for(ValidTextKeyPress validTextKeyPress : ValidTextKeyPress.values()) {
                if(validTextKeyPress.getT9().contains(c)) {
                    dictionaryValue.add(validTextKeyPress);
                    break;
                }
            }
        }
        //store the result
        dictionary.put(word, dictionaryValue);
    }

    public List<String> search(List<ValidTextKeyPress> prefixes) throws IllegalArgumentException {
        if(prefixes == null || prefixes.size() == 0) {
            throw new IllegalArgumentException("Input not valid.");
        }
        //those words matches the prefix
        List<String> result = new ArrayList<>(0);
        //Compare each words' press sequece with the prefixes
        for(String s : dictionary.keySet()) {
            //count the same characters, if any characters unmatch break the loop
            int i = 0;
            for(ValidTextKeyPress v : prefixes) {
                if(v == dictionary.get(s).get(i)) {
                    i++;
                }
                else {
                    break;
                }
            }
            if(i == prefixes.size()) {
                //all prefixes match
                result.add(s);
            }
        }
        return result;
    }

}
