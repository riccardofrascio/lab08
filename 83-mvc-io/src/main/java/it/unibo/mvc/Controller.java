package it.unibo.mvc;

import java.util.List;

/**
 * 
 */
public interface Controller {

    /**.
     * Set the next string to print in stdout
     * @param s next string to print
     */
    void setNextString(String s);

    /**
     * @return next string to print in stdout
     */
    String getNextString();

    /**
     * 
     * @return history of printed strings as a list
     */
    List<String> getHistoryString();

    /**
     * 
     * @throws IllegalStateException
     */
    void printString();
}
