package homework8;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class main {

    public static void main(String args[]) {
        TextingDictionary d = new TextingDictionary();
        d.insert("Brian");
        d.insert("brain");
        d.insert("Braid");
        //d.insert("safase@");

        List<ValidTextKeyPress> l = new ArrayList<>(0);
        l.add(ValidTextKeyPress.Two);
        l.add(ValidTextKeyPress.Seven);
        l.add(ValidTextKeyPress.Two);
        List<String> s = d.search(l);

        System.out.println(s);
    }

}
