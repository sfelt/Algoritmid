import java.util.*;

public class TreeNode {
    private String name;
    private TreeNode firstChild;
    private TreeNode nextSibling;

    TreeNode(String n, TreeNode d, TreeNode r) {
        this.name = n;
        this.firstChild = d;
        this.nextSibling = r;
    }

    public static TreeNode parsePrefix(String s) {
        // sisendi kontroll - sümbolid
        if (!s.matches("[A-Za-z0-9+\\-*/,()]+")) {
            throw new RuntimeException("Sisendstring sisaldab sobimatuid märke.");
        }

        // sisendi kontroll -  kas algab nimega
        if (s.charAt(0) == '(' || s.charAt(0) == ',') {
            throw new RuntimeException("Sisendstring ei alga tipu nimega ");
        }

        // leia alampuu algus
        int subtreeStartIndex = s.indexOf('(');
        if (subtreeStartIndex == -1) {
            // topeltkontroll: kui alampuud ei ole, siis veendu, et tipp ei sisalda ',' või ')''
            if (s.indexOf(')') != -1 || s.indexOf(',') != -1) {
                throw new RuntimeException("Ei suuda leida puustruktuuri esimest elementi");
            }

            // kui tõesti alampuud ei ole, siis tagasta tipp ainult täidetud nimega 
            return new TreeNode(s, null, null);
        }

        // leia töödeldava tipu nimi
        String name = s.substring(0, subtreeStartIndex);
        // kontrolli, et tipu nimes ei sisaldu ',' keskel ei ole  ')' ja et viimaseks elemendiks on ')'
        if (name.indexOf(')') != -1 || name.indexOf(',') != -1 || s.charAt(s.length() - 1) != ')') {
            throw new RuntimeException("Nimi sisaldab lõpetavat sulgu või koma või ei lõppe suluga.");
        }

        TreeNode result = new TreeNode(name, null, null);


        String subnodeString = s.substring(subtreeStartIndex + 1, s.length() - 1);

        // tükelda alamtipud stringide listiks
        List<String> subnodes = splitSubnodes(subnodeString);

        // töötle alamtipud rekursiivselt ja lisa käesolevale tipule
        for (String subnodeStr : subnodes) {
            TreeNode childNode = parsePrefix(subnodeStr);
            if (result.firstChild == null) {
                result.firstChild = childNode;
            } else {
                // kui töödeldava tipu esimene alamtipp ei ole null  - lisa uus alamtipp alamtipude ahelasse
                TreeNode parentSibling = result.firstChild;
                while (parentSibling.nextSibling != null) {
                    parentSibling = parentSibling.nextSibling;
                }
                parentSibling.nextSibling = childNode;
            }
        }

        return result;

    }

    /**
     * Töötle sisendstringi ja tükelda nodede listiks
     *
     * @param input  sisend string "Left Parenthetic Representation" kujul, tipud komadega eraldatud'
     * @return tippude list
     */
    private static List<String> splitSubnodes(String input) {
        List<String> subnodes = new ArrayList<>();
        int subnodeStartIndex = 0;
        int braceCounter = 0;

        // Algoritm:
        // käi läbi kõik märgid
        // arvuta alustavad ja lõpetavad sulud, et veenduda sulgude tasakaalus 
           for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                // suurenda sululoendurit
                braceCounter++;
            } else if (c == ')') {
                // kas lõpetav sulg on õigel kohal, vähenda sulgude loendurit 
                braceCounter--;
                if (braceCounter < 0) {
                    throw new RuntimeException("Viga! Puudub ')'");
                }
            } else if (c == ',' && braceCounter == 0) {
                // kui vanem sisaldab koma, siis tükelda järgmine alamtipp
                subnodes.add(input.substring(subnodeStartIndex, i));
                subnodeStartIndex = i + 1;
                if (subnodeStartIndex >= input.length()) {
                    throw new RuntimeException("Invalid format: too many ','");
                }
            }
        }

        // tükelda viimane alamtipp
        subnodes.add(input.substring(subnodeStartIndex));

        return subnodes;
    }

    public String rightParentheticRepresentation() {
        StringBuffer b = new StringBuffer();

        if (this.firstChild != null) {
            // trüki lapsed rekursiivselt, kui on olemas
            b.append('(');
            b.append(this.firstChild.rightParentheticRepresentation());
            b.append(')');
        }
        b.append(this.name);

        if (this.nextSibling != null) {
            // Trüki järgmised naabertipud 
            b.append(',');
            b.append(this.nextSibling.rightParentheticRepresentation());
        }

        return b.toString();
    }

    public static void main(String[] param) {
        String s = "A(B1,C,D)";
        TreeNode t = TreeNode.parsePrefix(s);
        String v = t.rightParentheticRepresentation();
        System.out.println(s + " ==> " + v); // A(B1,C,D) ==> (B1,C,D)A
    }
}
