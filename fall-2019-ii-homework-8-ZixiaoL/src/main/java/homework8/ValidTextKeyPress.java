package homework8;

import java.util.List;
import java.util.ArrayList;

public enum ValidTextKeyPress {
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9);

    //corresponding T9 characters
    private final List<Character> t9;

    private ValidTextKeyPress(int number) {
	this.t9 = new ArrayList<>(0);
        switch(number) {
            case 2:
                t9.add('a');
                t9.add('b');
                t9.add('c');
                t9.add('A');
                t9.add('B');
                t9.add('C');
                break;
            case 3:
                t9.add('d');
                t9.add('e');
                t9.add('f');
                t9.add('D');
                t9.add('E');
                t9.add('F');
                break;
            case 4:
                t9.add('g');
                t9.add('h');
                t9.add('i');
                t9.add('G');
                t9.add('H');
                t9.add('I');
                break;
            case 5:
                t9.add('j');
                t9.add('k');
                t9.add('l');
                t9.add('J');
                t9.add('K');
                t9.add('L');
                break;
            case 6:
                t9.add('m');
                t9.add('n');
                t9.add('o');
                t9.add('M');
                t9.add('N');
                t9.add('O');
                break;
            case 7:
                t9.add('p');
                t9.add('q');
                t9.add('r');
                t9.add('s');
                t9.add('P');
                t9.add('Q');
                t9.add('R');
                t9.add('S');
                break;
            case 8:
                t9.add('t');
                t9.add('u');
                t9.add('v');
                t9.add('T');
                t9.add('U');
                t9.add('V');
                break;
            case 9:
                t9.add('w');
                t9.add('x');
                t9.add('y');
                t9.add('z');
                t9.add('W');
                t9.add('X');
                t9.add('Y');
                t9.add('Z');
                break;
            default:
                break;
        }
    }

    public List<Character> getT9() {
        return t9;
    }

}
