/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USER
 */
public class ConsoleInputReader implements InputReader {
    
    /**
     * Reads lines from console until user enters an empty line or "END"
     */
    @Override
    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
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
    
    /**
     * Reads a single line from console
     * @param prompt the prompt to display
     * @return the line read
     * @throws IOException if reading fails
     */
    public String readLine(String prompt) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print(prompt);
            return reader.readLine().trim();
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