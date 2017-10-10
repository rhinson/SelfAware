package edu.gcccd.csis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SelfAware implements Language {

    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") +
                File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";

        System.out.println(code);

        SelfAware sa = new SelfAware();

        // Uncomment to see results while testing without appending to file
//        System.out.println("Keyword occurrences: " + sa.occurrences(code));

        // Comment out to stop appending to file
        sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));

    }

    public int occurrences(String sourceFile) throws Exception {

        final String s = new String(Files.readAllBytes(Paths.get(sourceFile)));
        System.out.println(s);

        Language.sort(); // sort the array of Reserved Words
        int n = 0; // total occurrences of keywords counter

        String[] checkString = s.split("\\W+"); // Split string into array of words

        for (String eachWord: ReservedWords) {
            int y = 0; // track instances of each word
            for (String myString : checkString) {
                if (eachWord.equals(myString)) {
//                    System.out.println(eachWord);
                    n++;
                    y++;
                }
            }
            // Uncomment to see count of each word found
//            if ( y > 0 ) {
//                System.out.println("Keyword: " + eachWord + ": " + y);
//            }

        }
        return n;

        // Original method of finding matches
        // It found results within a word rather than the whole word
//        for (String eachWord: ReservedWords) {
//            int y = 0;
//            int k = -1; // progressed through k
//            while (-1 != (k = s.indexOf(eachWord, k + 1))) {
//                System.out.println(eachWord);
//                n++;
//                y++;
//            }
//            // Uncomment to show number of each word found
//            if ( y > 0 ) {
//                System.out.println("Keyword: " + eachWord + ": " + y);
//            }
//        }
//        return n;


    }

    public void append(String sourceFile, String message) throws IOException {
        Files.write(Paths.get(sourceFile), message.getBytes(),
                StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}

// I've added abstract to get an extra keyword
//Keyword occurrences: 38
//Keyword occurrences: 39