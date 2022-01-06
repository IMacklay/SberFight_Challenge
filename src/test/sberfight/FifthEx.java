package test.sberfight;

import java.util.*;

public class FifthEx {
    public static void main(String[] args) {

        int thisYear = 2021;
        int birthYear = 2020;
        String name = "a";

        List<String> phrase = Arrays.asList("0","a");

        System.out.println(getResult(thisYear,birthYear,name,phrase));
    }

    public static boolean getResult(int calendar, int dateOfBirth, String name, List<String> phrases) {

        int phrAge = Integer.parseInt(phrases.get(0));
        int evlAge = calendar - dateOfBirth;

        boolean ageValid = false;
        if ( (phrAge == evlAge || phrAge == evlAge-1) && phrAge>=0) ageValid = true;

        boolean nameValid = diff(name,phrases.get(1));

        System.out.println("Возраст в фразе: "+phrAge+" Возраст вычесл: "+evlAge+" Пропускаем по возрасту: "+ageValid);

        return ageValid & nameValid;
    }

    private static boolean diff(String str1, String str2){
        if ( Math.abs(str1.length()-str2.length())>1 || str1.length()==0 )
            return false;

        String s1 = str1.toUpperCase(Locale.ROOT),
               s2 = str2.toUpperCase(Locale.ROOT);

        if (s1.length() < s2.length()){
            String s = s1;
            s1 = s2;
            s2 = s;
        }


        if (s1.equals(s2)) return true;

        int idxDiff = -1;
        //Поиск индекса отличающегося символа
        for (int i=0;i<s2.length();i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                idxDiff = i;
                break;
            }
        }
        if (idxDiff == -1) idxDiff = s1.length()-1;

        System.out.println("S1: "+s1+"    S2: "+s2);

        System.out.println(s2.substring(0,idxDiff)+"."+s2.substring(idxDiff,s2.length()));

        if (s2.length()==s1.length())
            System.out.println(s2.substring(0,idxDiff)+"."+s2.substring(idxDiff+1,s2.length()));

        return s1.matches(s2.substring(0,idxDiff)+"."+s2.substring(idxDiff,s2.length())) ||
                s1.matches(s2.substring(0,idxDiff)+"."+s2.substring(idxDiff+1,s2.length()));

    }
}
