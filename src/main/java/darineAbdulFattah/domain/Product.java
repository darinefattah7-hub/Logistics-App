package darineAbdulFattah.domain;

public class Product implements Comparable<Product> {
    private static int idCounter = 1;
    private final int id; 
    private String name;
    private Measurement weight;
    
    public Product(String name, Measurement weight) {
        this.id = idCounter++;
        this.name = name;
        this.weight = weight.copy(); 
    }

    @Override
    public String toString() {
        return name + "," + weight.toString();
    }
    
    @Override
    public int compareTo(Product other) {
        int weightComparison = Double.compare(this.weight.value(), other.weight.value());
        
        if (weightComparison != 0) {
            return weightComparison;
        }
        
        return this.name.compareToIgnoreCase(other.name);
    }
    
    boolean hasName(String productName) {
        return name.equalsIgnoreCase(productName);
    }

    boolean hasSameName(Product other) {
        return hasName(other.name);
    }

    void addWeightFrom(Product other) {
        weight.addMeasurement(other.weight);
    }

    public boolean hasAtLeast(Measurement requested) {
        return weight.hasSameUnit(requested) && weight.value() >= requested.value();
    }

    public boolean hasLessThan(Measurement requested) {
        return weight.hasSameUnit(requested) && weight.value() < requested.value();
    }

    public String quantityString() {
        return weight.toString();
    }
}
