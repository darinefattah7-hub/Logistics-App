/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
 

/**
 *
 * @author USER
 */
public class FileInputReader implements InputReader {
    private String filename;
    
    /**
     * Constructor
     * @param filename the path to the file to read
     */
    public FileInputReader(String filename) {
        this.filename = filename;
    }
    
    @Override
    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {  // Skip empty lines
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error: File not found - " + filename);
            System.err.println("Error message: " + ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            System.err.println("Error: Cannot read file - " + filename);
            System.err.println("Error message: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("Unknown error while reading file");
            System.err.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException("Unexpected error reading file", ex);
        }
        
        return lines;
    }
}