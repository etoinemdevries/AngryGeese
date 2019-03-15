package com.teamsenseo.angrygeese.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Utils for reading and writing files
 *
 * @author Robert
 */
public final class FileUtils {
    private final File f;

    public FileUtils(final File f) {
        this.f = f;
    }

    /**
     * Reads from the specified file
     */
    public final String read() {
        FileReader reader = null;

        try {
            reader = new FileReader(this.f);
            final StringBuilder builder = new StringBuilder();

            for (int r; (r = reader.read()) != -1; )
                builder.append((char) r);

            reader.close();
            return builder.toString();
        } catch (final Exception e) {
            System.out.println("Failed to read from file: " + e.getMessage());
            e.printStackTrace();
        }

        if (reader != null) {
            try {
                reader.close();
            } catch (final Exception e) {
                System.out.println("Failed to close file reader: " + e.getMessage());
            }
        }

        return "";
    }

    /**
     * Writes to the specified file
     */
    public final boolean write(final String contents) {
        return write(contents, false);
    }

    /**
     * Writes to the specified file
     */
    public final boolean write(final String contents, final boolean append) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(this.f, append);
            writer.write(contents);

            writer.flush();
            writer.close();
            return true;
        } catch (final Exception e) {
            System.out.println("Failed to write to file: " + e.getMessage());
            e.printStackTrace();
        }

        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (final Exception e) {
                System.out.println("Failed to close file writer: " + e.getMessage());
            }
        }

        return false;
    }

    /**
     * Gets the file used in this util
     */
    public final File getFile() {
        return this.f;
    }

    /**
     * Reads from a file
     */
    public static final String read(final File f) {
        return new FileUtils(f).read();
    }

    /**
     * Writes to a file
     */
    public static final boolean write(final File f, final String contents) {
        return new FileUtils(f).write(contents);
    }

    /**
     * Writes to a file
     */
    public static final boolean write(final File f, final String contents, final boolean append) {
        return new FileUtils(f).write(contents, append);
    }
}
