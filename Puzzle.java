import java.util.*;

//viited
//https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
//https://stackoverflow.com/questions/2710713/algorithm-to-generate-all-possible-permutations-of-a-list

public class Puzzle {
    public static void main(String[] args) {

//    	args = new String[]{"YKS", "KAKS", "KOLM"};
//    	args = new String[]{"SEND", "MORE", "MONEY"};
    	args = new String[]{"ABCDEFGHIJAB", "ABCDEFGHIJA", "ACEHJBDFGIAC"};

        // Kas sisendis täpselt kom stringi?
        if (args.length != 3) {
            throw new RuntimeException("Sisendis peab olema kolm stringi! ");
        }

        // Kas koosneb ainult suurtähtedest?
        for (int i = 0; i < 3; i++) {
            if (!args[i].matches("[A-Z]+")) {
                throw new RuntimeException("Sisendis lubatud ainut suurtähed!");
            }
        }

        //  Map: täht ja selle positsiooni
        Map<Character, Integer> positionMap = generatePositionMap(args);

        // Massiiv asenduste jaoks
        // Kasutusel tähtede asendamiseks numbritega vastavalt positionMap'ile
        int[] replaceArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Kasutame Set kollektsiooni, et vältida duplikaate
        Set<String> results = new HashSet<>();

        // Tsükkel üle kõikide kombinatsioonide
        do {
            if (checkCombination(replaceArr, args, positionMap)) {
                results.add(getMappingString(positionMap, replaceArr));
            }
        } while (nextLexShuffle(replaceArr));

        // Trüki tulemused
        if (results.size() == 0) {
            System.out.println("Lahendus puudub");
        } else {
            System.out.println("Lahendusi leitud: " + results.size());
            for (String result : results) {
                System.out.println(result);
            }
        }
    }

    /**
     * Koostab stringi, mis vastab leitud lahendusele
     *
     * @param positionMap tähtede asukohad
     * @param replaceArr  asendavate numbrite massiiv
     * @return string, mis sisaldab lahendust
     */
    private static String getMappingString(Map<Character, Integer> positionMap, int[] replaceArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Map.Entry<Character, Integer> entry : positionMap.entrySet()) {
            sb.append(entry.getKey())
                    .append('=')
                    .append(replaceArr[entry.getValue()])
                    .append(", ");
        }
        sb.delete(sb.length() - 2, sb.length())
                .append("}");
        return sb.toString();
    }

    /**
     * @param replaceArr  asendavate numbrite massiiv
     * @param input sisendmassiid - kaks liidetavat ja summa
     * @param positionMap tähed ja nende asukohad asendamiseks
     * @return true, kui lahendub
     */
    private static boolean checkCombination(int[] replaceArr, String[] input, Map<Character, Integer> positionMap) {
        // asenda tähed 
        String firstStr = replaceCharacters(input[0], positionMap, replaceArr);
        String secondStr = replaceCharacters(input[1], positionMap, replaceArr);
        String expectedStr = replaceCharacters(input[2], positionMap, replaceArr);

        // kontrolli, et esimeseks märgiks ei oleks 0
        if (firstStr.charAt(0) == '0' || secondStr.charAt(0) == '0' || expectedStr.charAt(0) == '0') {
            return false;
        }

        // konverteeri long tüüpi 
        long first = Long.parseLong(firstStr);
        long second = Long.parseLong(secondStr);
        long expected = Long.parseLong(expectedStr);

        long actual = first + second; 

        return expected == actual; // kas on võrdne?
    }

    /**
     * Asenda tähed numbritega vastavalt positionMap'ile ja replaceArr'ile
     *
     * @param input       string, mille sasendused teha
     * @param positionMap map, milles on tähtede asukohad ehk positsioonid
     * @param replaceArr  asendavate numbrite massiiv
     * @return string, milles on tähed asendatud numbritega 
     */
    private static String replaceCharacters(String input, Map<Character, Integer> positionMap, int[] replaceArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(replaceArr[positionMap.get(input.charAt(i))]);
        }
        return sb.toString();
    }

    /**
     * Loob map tüüpi kollektsiooi, milles igale täht on seotud tema asukohaga  
     *
     * @param sisendmassiiv
     * @return tähed seotud asukohtadega
     */
    private static Map<Character, Integer> generatePositionMap(String[] input) {

    	// sõnad kokku üheks 
        String fullword = input[0] + input[1] + input[2];
        // tähtede Set korduste vältimiseks
        Set<Character> chars = new HashSet<>();
        // iga täht Set'ti
        for (char c : fullword.toCharArray()) {
            chars.add(c);
        }
        // Erinevaid tähti ei tohi olla enam kui 10
        if (chars.size() > 10) {
            throw new RuntimeException("Sisendis on enam kui 10 erinevat tähemärki");
        }

        // Koostab tähemärkide  asukoha Map'i
        Map<Character, Integer> res = new HashMap<>();
        int pos = 0;

        for (Character c : chars) {
            res.put(c, pos++);
        }

        return res;
    }

    /**
     * Koosta järgmine tähestikuline permutatsioon
     *
     * @param replaceArr array, mille alusel genereerida järgmine tähestikuline permutatsioon
     * @return järgmine tähestikuline permutatsioon
     */
    private static boolean nextLexShuffle(int[] replaceArr) {
        int first = -1, second = -1;
        for (int i = replaceArr.length - 2; i >= 0; i--) {
            if (replaceArr[i] < replaceArr[i + 1]) {
                first = i;
                break;
            }
        }

        if (first == -1) {
            return false;
        }

        for (int i = replaceArr.length - 1; i >= first; i--) {
            if (replaceArr[i] > replaceArr[first]) {
                second = i;
                break;
            }
        }

        if (second == -1) {
            return false;
        }

        //vaheta
        shift(replaceArr, first, second);

        //pööra ümber
        reverse(replaceArr, first);
        return true;
    }


    /**
     * Pööra massivi elemendid alates etteantud positsioonist 
     *
     * @param arr - massiiv, mida pööratakse
     * @param from - index, millele järgnevast elemendist alates pööratakse
     */
    private static void reverse(int[] arr, int from) {
        for (int i = from + 1; i <= (((arr.length - from) / 2) + from); i++) {
            int j = arr.length - (i - from);
            shift(arr, i, j);
        }
    }

    /**
     * Vaheta esimene ja teine element massiivis
     *
     * @param arr    massiv, milles vahetada elemendid
     * @param first  esimese elemendi indeks
     * @param second teise elemendi indeks
     */
    private static void shift(int[] arr, int first, int second) {
        int hold = arr[first];
        arr[first] = arr[second];
        arr[second] = hold;
    }
}
