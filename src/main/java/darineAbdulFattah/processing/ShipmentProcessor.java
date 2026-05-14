package darineAbdulFattah.processing;

import darineAbdulFattah.domain.Warehouse;
import darineAbdulFattah.domain.Product;
import darineAbdulFattah.domain.Measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    public ShipmentRecord processShipment(String sourceName, String destName, 
                                          String productName, String requestedQuantity) {
        ShipmentRecord record = new ShipmentRecord(
            sourceName,
            destName,
            productName,
            requestedQuantity
        );
        
        Warehouse source = warehouseManager.getWarehouseByName(sourceName);
        Warehouse destination = warehouseManager.getWarehouseByName(destName);
        
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
        
        Product product = source.findProduct(productName);
        
        if (product == null) {
            record.setStatus(ShipmentRecord.Status.FAILED);
            record.setFailureReason("Product not found in source inventory");
            failedShipments++;
            shipmentHistory.add(record);
            return record;
        }
        
        Measurement requested = new Measurement(requestedQuantity);

        if (product.hasAtLeast(requested)) {
            record.setStatus(ShipmentRecord.Status.SUCCESSFUL);
            record.setActualQuantity(product.quantityString());
            successfulShipments++;
        } else if (product.hasLessThan(requested)) {
            record.setStatus(ShipmentRecord.Status.PARTIAL);
            record.setActualQuantity(product.quantityString());
            partialShipments++;
        } else {
            record.setStatus(ShipmentRecord.Status.FAILED);
            record.setFailureReason("Requested unit does not match available unit");
            failedShipments++;
            shipmentHistory.add(record);
            return record;
        }
        
        Product removedProduct = source.removeProduct(productName);
        source.addShippedProduct(removedProduct);
        destination.addProduct(removedProduct);
        
        shipmentHistory.add(record);
        return record;
    }
    
    public List<ShipmentRecord> getShipmentHistory() {
        return Collections.unmodifiableList(shipmentHistory);
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
