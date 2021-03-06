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

        // Comment out to stop appending to file
        sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));

    }

    public int occurrences(String sourceFile) throws Exception {
        final String s = new String(Files.readAllBytes(Paths.get(sourceFile)));
        int n = 0; // total occurrences of keywords counter
        int c = 0; // track total number of checks performed

        Language.sort(); // sort the array of Reserved Words

        String checkString = s.replaceAll("\\W+", " ");

            for (String eachWord:ReservedWords) {
                int index = checkString.indexOf(eachWord);
                while (index >= 0) {
                    c++;
                    n++;
                    index = checkString.indexOf(" " + eachWord + " ", index+eachWord.length());
                }
            }

// Old Code - Cumbersome
//        String[] checkString = s.split("\\W+"); // Split string into array of words
//
//        for (String eachWord: ReservedWords) {
//            for (String myString : checkString) {
//                c++;
//                if (eachWord.equals(myString)) {
//                    // Uncomment to see each word found
//                    // System.out.println(eachWord);
//                    n++;
//                }
//            }
//        }

        System.out.println("Number of string comparisons: " + c);

        return n;
    }

    public void append(String sourceFile, String message) throws IOException {
        Files.write(Paths.get(sourceFile), message.getBytes(),
                StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}
// Added abstract as an extra reservedWord
//Keyword occurrences: 36
//Keyword occurrences: 37