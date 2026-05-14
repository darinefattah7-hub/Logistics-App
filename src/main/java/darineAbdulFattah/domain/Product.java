/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.domain;

/**
 *
 * @author USER
 */
public class Product implements Comparable<Product> {
    private static int idCounter = 1;
    private final int id; 
    private String name;
    private Measurement weight;
    
    
     public Product(String name, Measurement weight) {
        this.id = idCounter++;
        this.name = name;
        this.weight = new Measurement(weight.getValue(), weight.getUnit()); 
    }
    @Override
    public String toString() {
        return name + "," + weight.toString();
    }
    
    @Override
    public int compareTo(Product other) {
        // First compare by weight value
        int weightComparison = Double.compare(this.weight.getValue(), 
                                             other.weight.getValue());
        
        if (weightComparison != 0) {
            return weightComparison;
        }
        
        // If weights equal, compare by name (case-insensitive)
        return this.name.compareToIgnoreCase(other.name);
    }
    
    // Package-private accessors for ProductList
    public String getName() {
        return name;
    }
    
    public Measurement getWeight() {
        return weight;
    }
    
}
