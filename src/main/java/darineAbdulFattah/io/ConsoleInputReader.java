package darineAbdulFattah.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInputReader implements InputReader {
    private final BufferedReader reader;

    public ConsoleInputReader() {
        this(new BufferedReader(new InputStreamReader(System.in)));
    }

    public ConsoleInputReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();

        try {
            System.out.println("Enter lines (type 'END' or empty line to finish):");

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.equalsIgnoreCase("END")) {
                    break;
                }

                lines.add(line);
            }
        } catch (IOException ex) {
            System.err.println("Error: Cannot read from console");
            System.err.println("Error message: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("Unknown error while reading console input");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException("Unexpected error reading console", ex);
        }

        return lines;
    }

    public String readLine(String prompt) throws IOException {
        try {
            System.out.print(prompt);
            String line = reader.readLine();
            return line == null ? "" : line.trim();
        } catch (IOException ex) {
            System.err.println("Error: Cannot read from console");
            System.err.println("Error message: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("Unknown error while reading console input");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException("Unexpected error reading console", ex);
        }
    }
}
