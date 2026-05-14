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
        if (value == Math.rint(value)) {
            return String.format("%.0f %s", value, unit);
        }
        return String.format("%.2f %s", value, unit);
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

    
      public void addMeasurement(Measurement other) {
        this.value += other.value;
    }

    @Override
    public double adjust(double factor) {
        this.value *= factor;
        return this.value;
    }

    double value() {
        return value;
    }

    String unit() {
        return unit;
    }

    Measurement copy() {
        return new Measurement(value, unit);
    }

    boolean hasSameUnit(Measurement other) {
        return unit.equalsIgnoreCase(other.unit);
    }

}

