/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.domain;
import java.util.Objects;

/**
 *
 * @author USER
 */



public class Measurement implements Adjustable {
     
  private double value;
  private  String unit;
  
  
  public Measurement(double value, String unit){
      if(value < 0){
          this.value=0;
      } 
      else { this.value=value;}
      this.unit=unit;
      
      }
  
  public Measurement (String measurementStr){
     String[] parts = measurementStr.split(" ");
        
        double parsedValue = Double.parseDouble(parts[0]);
        if (parsedValue < 0) {
            this.value = 0;
        } else {
            this.value = parsedValue;
        }
        
        if (parts.length == 2) {
            this.unit = parts[1];
        } else {
            StringBuilder unitBuilder = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                if (i > 1) unitBuilder.append(" ");
                unitBuilder.append(parts[i]);
            }
            this.unit = unitBuilder.toString();
        }
    
  }
 
    @Override
    public String toString() {
        return value + "" + unit;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Measurement other = (Measurement) obj;

     
        double roundedThis = Math.round(this.value * 100.0) / 100.0;
        double roundedOther = Math.round(other.value * 100.0) / 100.0;

        
        return roundedThis == roundedOther && this.unit.equalsIgnoreCase(other.unit);
    }

    @Override
    public int hashCode() {
        double rounded = Math.round(value * 100.0) / 100.0;
        return Objects.hash(rounded, unit.toLowerCase());
    }

    // Add another Measurement's quantity to this one
    
    //re
    public void addMeasurement(Measurement other) {
        if (!this.unit.equalsIgnoreCase(other.unit)) {
            throw new IllegalArgumentException(
                "Cannot add measurements with different units: " 
                + this.unit + " vs " + other.unit
            );
        }
        this.value += other.value;
    }

      public  double getValue() {
    return value;
}

public String getUnit() {
    return unit;
    
}

}