package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**.
 * Simple controller that implemets Controller
 *
 */
public final class SimpleController implements Controller {

    private List<String> historyString = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextString(final String s) {
        this.nextString = Objects.requireNonNull(s, "This method does not accept null values.");
    }

    @Override
    public String getNextString() {
        return nextString;
    }

    @Override
    public List<String> getHistoryString() {
        return historyString;
    }

    @Override
    public void printString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
        }
        historyString.add(this.nextString);
        System.out.println(this.nextString); // NOPMD: allowed in exercises
    }

}
