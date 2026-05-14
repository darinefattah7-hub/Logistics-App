package darineAbdulFattah.processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import darineAbdulFattah.domain.Warehouse;
import darineAbdulFattah.domain.Product;
import darineAbdulFattah.io.OutputWriter;

public class ReportGenerator {

    public void generateShipmentReport(ShipmentProcessor processor, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        lines.add("Successful Shipments: " + processor.getSuccessfulShipments());
        lines.add("Partial Shipments: " + processor.getPartialShipments());
        lines.add("Failed Shipments: " + processor.getFailedShipments());
        lines.add("");
        lines.add("Shipment History:");
        
        List<ShipmentRecord> successful = processor.getShipmentsByStatus(
            ShipmentRecord.Status.SUCCESSFUL
        );

        if (!successful.isEmpty()) {
            lines.add(" Successful:");
            for (ShipmentRecord record : successful) {
                lines.add("  o " + record.toString());
            }
        }
        
        List<ShipmentRecord> partial = processor.getShipmentsByStatus(
            ShipmentRecord.Status.PARTIAL
        );

        if (!partial.isEmpty()) {
            lines.add(" Partial:");
            for (ShipmentRecord record : partial) {
                lines.add("  o " + record.toString());
            }
        }
        
        List<ShipmentRecord> failed = processor.getShipmentsByStatus(
            ShipmentRecord.Status.FAILED
        );

        if (!failed.isEmpty()) {
            lines.add(" Failed:");
            for (ShipmentRecord record : failed) {
                lines.add("  o " + record.toString());
            }
        }
        
        writer.writeLines(lines);
    }
    
    public void generateWarehouseList(WarehouseManager warehouseManager, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses()) {
            lines.add(warehouse.toString());
        }
        
        writer.writeLines(lines);
    }
    
    public void generateInventoryList(WarehouseManager warehouseManager, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses()) {
            for (Product product : warehouse.inventoryItems()) {
                lines.add(warehouse.getId() + "," + product.toString());
            }
        }
        
        writer.writeLines(lines);
    }    
}
