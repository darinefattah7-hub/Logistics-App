/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.io;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USER
 */
public class ConsoleOutputWriter implements OutputWriter {
    
    @Override
    public void writeLines(List<String> lines) throws IOException {
        try {
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            System.err.println("Unknown error while writing to console");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException("Unexpected error writing to console", ex);
        }
    }
}