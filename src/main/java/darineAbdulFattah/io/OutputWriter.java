/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package darineAbdulFattah.io;
import java.io.IOException;
import java.util.List;
 
/**
 * Interface for reading input lines from various sources
 */

/**
 *
 * @author USER
 */
public interface OutputWriter {
    /**
     * Writes lines to the output destination
     * @param lines list of strings to write
     * @throws IOException if writing fails
     */
    void writeLines(List<String> lines) throws IOException;
    
}
