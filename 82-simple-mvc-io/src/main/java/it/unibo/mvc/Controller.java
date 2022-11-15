package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";
    private File dest = new File(HOME + File.separator + DEFAULT_FILE);

    /**
    * used to set the courrent file where to save f 
    * 
    */
    public void setCourrentfile(final File f) {
        this.dest = f;
    }
    
    /**
    * used to get the courrent file where text is saved 
    * 
    */
    public File getCourrentfile(){
        return this.dest;
    }

    /**
    * used to get the courrent file path where text is saved
    * 
    */
    public String getCourrentPath(){
        return this.dest.getPath();
    }

    /**
    * used to save the text wrote in the JTextarea
    * 
    */
    public void save(final String s) {
        try (PrintStream ps = new PrintStream(dest, StandardCharsets.UTF_8)) {
            ps.println(s);
        } catch (IOException e1) {
            e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
        }
    }
}
