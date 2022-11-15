package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {


    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * 
     * @param configFile complete name of the configuartion file
     * @param views the views to attach
     */
    public DrawNumberApp(final String configFile, final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }

        Configuration.Builder configurationBuilder = new Configuration.Builder();
        try(var read = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(configFile)))) {
            String line;
            while ((line = read.readLine()) != null) {
                var words = line.split(":");
                if (words.length == 2) {
                    final int value = Integer.parseInt(words[1].trim());
                    if (words[0].contains("max")) {
                        configurationBuilder.setMax(value);
                    } else if (words[0].contains("min")) {
                        configurationBuilder.setMin(value);
                    } else if (words[0].contains("attempts")) {
                        configurationBuilder.setAttempts(value);
                    }
                } else {
                    displayError("I cannot understand \"" + line + '"');
                }
            }
        } catch (IOException e) {
            displayError(e.getMessage());
        }
        final var conf = configurationBuilder.build(); 
        if(conf.isConsistent()) {
            this.model = new DrawNumberImpl(conf);
        } else {
            displayError("Error: cannot read file configuration, using default configuration");
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    /**
     * 
     * @param err String that will be print in each view method displayError
     */
    private void displayError(final String err) {
        for (final DrawNumberView view: views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml",new DrawNumberViewImpl(), new PrintStreamView(System.out));
    }

}
