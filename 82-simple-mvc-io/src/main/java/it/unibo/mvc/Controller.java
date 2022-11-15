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
    
    public void setCourrentfile(final File f) {
        this.dest = f;
    }

    public File getCourrentfile(){
        return this.dest;
    }

    public String getCourrentPath(){
        return this.dest.getPath();
    }

    public void save(final String s) {
        try (PrintStream ps = new PrintStream(dest, StandardCharsets.UTF_8)) {
            ps.println(s);
        } catch (IOException e1) {
            e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
        }
    }
}
