package edu.gcccd.csis;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

import static org.junit.Assert.*;

public class SelfAwareTest {

    Path myFilePath = Paths.get("myTestFile.txt");

    private String test1 = "A list of keywords is abstract,assert,boolean,break,byte,case,catch,char" +
            ",class,const,default,do,double,else,enum,extends,false,final,finally,float,for,goto,if,implements" +
            ",import,instanceof,int,interface,long,native,new,null,package,private,protected,public" +
            ",return,short,static,strictfp,super,switch,synchronized,this,throw,throws,transient,true" +
            ",try,void,volatile,while,continue";

    private String test2 = "A list of keywords is abstract,assert,boolean,break,byte,case,catch,char" +
            ",class,const,default,do,double,else,enum,extends,false,final,finally,float,for,goto,if,implements" +
            ",import,instanceof,int,interface,long,native,new,null,package,private,protected,public" +
            ",return,short,static,strictfp,super,switch,synchronized,this,throw,throws,transient,true" +
            ",try,void,volatile,while,continue,fgsdfgs,dfg,sdfgd,gggg)g934593098r09t03824593f9039,one extra" +
            " abstract/,.,.][p[p[{}90";

    private String test3 = "334908,4f-0-ff4f())^&$#%@#$$56yy;''kk46h;;h;;int==-dfg--";


    @After
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(String.valueOf(myFilePath)));
        } catch (IOException e) {
            System.out.println("Failed due to: " + e.toString());
        }
    }

    //@Test
    public void occurrencesTest1() throws Exception {

        try {
            Files.write(Paths.get(String.valueOf(myFilePath)), test1.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Failed due to: " + e.toString());
        }

        int numFound = new SelfAware().occurrences(String.valueOf(myFilePath));
        assertEquals(53, numFound );
    }

    @Test
    public void occurrencesTest2() throws Exception {

        try {
            Files.write(Paths.get(String.valueOf(myFilePath)), test2.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Failed due to: " + e.toString());
        }

        int numFound = new SelfAware().occurrences(String.valueOf(myFilePath));
        assertEquals(54, numFound );
    }

    //@Test
    public void occurrencesTest3() throws Exception {

        try {
            Files.write(Paths.get(String.valueOf(myFilePath)), test3.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Failed due to: " + e.toString());
        }

        int numFound = new SelfAware().occurrences(String.valueOf(myFilePath));
        assertEquals(1, numFound );
    }
}