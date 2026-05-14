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
public interface InputReader {

  /**
     * Reads lines from the input source
     * @return list of strings, one per line
     * @throws IOException if reading fails
     */
    List<String> readLines() throws IOException;    
}
