/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.processing;
import darineAbdulFattah.domain.Warehouse;
import darineAbdulFattah.domain.Product;
import darineAbdulFattah.domain.Measurement;

import java.util.ArrayList;
import java.util.List;
/**
 *n
 * @author USER
 */
public class ShipmentProcessor {
     private WarehouseManager warehouseManager;
    private List<ShipmentRecord> shipmentHistory;
    private int successfulShipments;
    private int partialShipments;
    private int failedShipments;
    
    public ShipmentProcessor(WarehouseManager warehouseManager) {
        this.warehouseManager = warehouseManager;
        this.shipmentHistory = new ArrayList<>();
        this.successfulShipments = 0;
        this.partialShipments = 0;
        this.failedShipments = 0;
    }
    
    /**
     * Processes a shipment from source to destination warehouse
     * @param sourceName source warehouse name
     * @param destName destination warehouse name
     * @param productName product to ship
     * @param requestedQuantity requested quantity string (e.g., "8 kg")
     * @return the shipment record
     */
    public ShipmentRecord processShipment(String sourceName, String destName, 
                                         String productName, String requestedQuantity) {
        ShipmentRecord record = new ShipmentRecord(sourceName, destName, 
                                                   productName, requestedQuantity);
        
        // Get warehouses by name
        Warehouse source = warehouseManager.getWarehouseByName(sourceName);
        Warehouse destination = warehouseManager.getWarehouseByName(destName);
        
        // Check if warehouses exist
        if (source == null) {
            record.setStatus(ShipmentRecord.Status.FAILED);
            record.setFailureReason("Source warehouse does not exist");
            failedShipments++;
            shipmentHistory.add(record);
            return record;
        }
        
        if (destination == null) {
            record.setStatus(ShipmentRecord.Status.FAILED);
            record.setFailureReason("Destination warehouse does not exist");
            failedShipments++;
            shipmentHistory.add(record);
            return record;
        }
        
        // Find product in source inventory
        Product product = source.getInventory().findByName(productName);
        
        if (product == null) {
            record.setStatus(ShipmentRecord.Status.FAILED);
            record.setFailureReason("Product not found in source inventory");
            failedShipments++;
            shipmentHistory.add(record);
            return record;
        }
        
        // Parse requested quantity
        Measurement requested = new Measurement(requestedQuantity);
        Measurement available = product.getWeight();
        
        // Determine shipment status
        double requestedValue = requested.getValue();
        double availableValue = available.getValue();
        
        if (availableValue >= requestedValue) {
            // Successful shipment
            record.setStatus(ShipmentRecord.Status.SUCCESSFUL);
            record.setActualQuantity(available.toString());
            successfulShipments++;
        } else {
            // Partial shipment (available < requested)
            record.setStatus(ShipmentRecord.Status.PARTIAL);
            record.setActualQuantity(available.toString());
            partialShipments++;
        }
        
        // Execute the shipment: move product from source to destination
        // Remove from source inventory
        Product removedProduct = source.getInventory().removeByName(productName);
        
        // Add to source shipment list
        source.getShipment().add(removedProduct);
        
        // Add to destination inventory
        destination.getInventory().add(removedProduct);
        
        shipmentHistory.add(record);
        return record;
    }
    
    public List<ShipmentRecord> getShipmentHistory() {
        return shipmentHistory;
    }
    
    public int getSuccessfulShipments() {
        return successfulShipments;
    }
    
    public int getPartialShipments() {
        return partialShipments;
    }
    
    public int getFailedShipments() {
        return failedShipments;
    }
    
    /**
     * Gets shipments by status
     * @param status the status to filter by
     * @return list of shipments with that status
     */
    public List<ShipmentRecord> getShipmentsByStatus(ShipmentRecord.Status status) {
        List<ShipmentRecord> filtered = new ArrayList<>();
        for (ShipmentRecord record : shipmentHistory) {
            if (record.getStatus() == status) {
                filtered.add(record);
            }
        }
        return filtered;
    }
}
