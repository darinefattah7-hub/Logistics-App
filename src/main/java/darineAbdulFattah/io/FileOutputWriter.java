/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.io;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author USER
 */
public class FileOutputWriter implements OutputWriter {
    private String filename;
    
    /**
     * Constructor
     * @param filename the path to the file to write
     */
    public FileOutputWriter(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void writeLines(List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error: Cannot write to file - " + filename);
            System.err.println("Error message: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("Unknown error while writing file");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException("Unexpected error writing file", ex);
        }
    }
}
 