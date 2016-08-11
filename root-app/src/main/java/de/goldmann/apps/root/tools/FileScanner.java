package de.goldmann.apps.root.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {
    public static final String DATA_FOLDER = "/static/data/";

    private FileScanner() {
    }

    /**
     * Produce lines from a file.
     *
     * @param file
     *            to produce lines from.
     * @param skip
     *            how many lines to skip.
     * @return lines.
     */
    public static List<String> produceLines(final File file, final int skip) {
        try {
            return produceLines(new FileInputStream(file), skip);
        }
        catch (final FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Produce lines from an input stream.
     *
     * @param inputStream
     *            to produce lines from.
     * @param skip
     *            how many lines to skip.
     * @return lines.
     */
    public static List<String> produceLines(final InputStream inputStream, final int skip) {
        final List<String> lines = new ArrayList<String>();

        final Scanner scanner = new Scanner(inputStream, "UTF-8");

        int skipped = 0;
        while (scanner.hasNextLine()) {
            final String nextLine = scanner.nextLine();
            if (skip > skipped) {
                skipped++;
            }
            else {
                lines.add(nextLine);
            }
        }
        scanner.close();

        return lines;
    }
}
