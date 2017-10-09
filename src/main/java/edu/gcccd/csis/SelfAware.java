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

//        System.out.println("Keyword occurrences: " + sa.occurrences(code));

        sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));

    }

    public int occurrences(String sourceFile) throws Exception {

        final String s = new String(Files.readAllBytes(Paths.get(sourceFile)));
        System.out.println(s);

        Language.sort(); // sort the array of Reserved Words
        int n = 0; // occurrences counter

        for (String eachWord: ReservedWords) {
            int y = 0;
            int k = -1; // progressed through k
            while (-1 != (k = s.indexOf(eachWord, k + 1))) {
                n++;
                y++;
            }
            // Uncomment to show number of each word found
//            if ( y > 0 ) {
//                System.out.println("Keyword: " + eachWord + ": " + y);
//            }
        }
        return n;
    }

    public void append(String sourceFile, String message) throws IOException {
        Files.write(Paths.get(sourceFile), message.getBytes(),
                StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}

//Keyword occurrences: 38
//Keyword occurrences: 38