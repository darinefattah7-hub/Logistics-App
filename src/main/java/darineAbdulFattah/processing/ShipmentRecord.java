/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.processing;

/**
 *
 * @author USER
 */
public class ShipmentRecord {
    public enum Status {
        SUCCESSFUL,
        PARTIAL,
        FAILED
    }
    
    private String sourceWarehouse;
    private String destinationWarehouse;
    private String productName;
    private String requestedQuantity;
    private String actualQuantity;
    private Status status;
    private String failureReason;
    
    public ShipmentRecord(String sourceWarehouse, String destinationWarehouse, 
                         String productName, String requestedQuantity) {
        this.sourceWarehouse = sourceWarehouse;
        this.destinationWarehouse = destinationWarehouse;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }
    
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public String getSourceWarehouse() {
        return sourceWarehouse;
    }
    
    public String getDestinationWarehouse() {
        return destinationWarehouse;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getRequestedQuantity() {
        return requestedQuantity;
    }
    
    public String getActualQuantity() {
        return actualQuantity;
    }
    
    public String getFailureReason() {
        return failureReason;
    }
    
    @Override
    public String toString() {
        switch (status) {
            case SUCCESSFUL:
                return String.format("Ship %s of %s from %s to %s", 
                    requestedQuantity, productName, sourceWarehouse, destinationWarehouse);
            
            case PARTIAL:
                return String.format("Ship %s of %s from %s to %s (only %s available)", 
                    requestedQuantity, productName, sourceWarehouse, destinationWarehouse, actualQuantity);
            
            case FAILED:
                return String.format("Ship %s of %s from %s to %s", 
                    requestedQuantity, productName, sourceWarehouse, destinationWarehouse);
            
            default:
                return "";
        }
    }
    
}
