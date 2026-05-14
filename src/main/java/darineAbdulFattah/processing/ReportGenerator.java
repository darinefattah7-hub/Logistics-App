/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import darineAbdulFattah.domain.Warehouse;
import darineAbdulFattah.domain.Product;
import darineAbdulFattah.io.OutputWriter;

/**
 *
 * @author USER
 */
public class ReportGenerator {


/**
     * Generates a shipment report
     * @param processor the shipment processor containing shipment data
     * @param writer the output writer to use
     * @throws IOException if writing fails
     */
    public void generateShipmentReport(ShipmentProcessor processor, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        // Summary statistics
        lines.add("Successful Shipments: " + processor.getSuccessfulShipments());
        lines.add("Partial Shipments: " + processor.getPartialShipments());
        lines.add("Failed Shipments: " + processor.getFailedShipments());
        lines.add("");
        lines.add("Shipment History:");
        
        // Successful shipments
        List<ShipmentRecord> successful = processor.getShipmentsByStatus(
            ShipmentRecord.Status.SUCCESSFUL);
        if (!successful.isEmpty()) {
            lines.add(" Successful:");
            for (ShipmentRecord record : successful) {
                lines.add("  o " + record.toString());
            }
        }
        
        // Partial shipments
        List<ShipmentRecord> partial = processor.getShipmentsByStatus(
            ShipmentRecord.Status.PARTIAL);
        if (!partial.isEmpty()) {
            lines.add(" Partial:");
            for (ShipmentRecord record : partial) {
                lines.add("  o " + record.toString());
            }
        }
        
        // Failed shipments
        List<ShipmentRecord> failed = processor.getShipmentsByStatus(
            ShipmentRecord.Status.FAILED);
        if (!failed.isEmpty()) {
            lines.add(" Failed:");
            for (ShipmentRecord record : failed) {
                lines.add("  o " + record.toString());
            }
        }
        
        writer.writeLines(lines);
    }
    
    /**
     * Generates warehouse list output
     * @param warehouseManager the warehouse manager
     * @param writer the output writer to use
     * @throws IOException if writing fails
     */
    public void generateWarehouseList(WarehouseManager warehouseManager, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses().values()) {
            lines.add(warehouse.toString());
        }
        
        writer.writeLines(lines);
    }
    
    /**
     * Generates inventory output
     * @param warehouseManager the warehouse manager
     * @param writer the output writer to use
     * @throws IOException if writing fails
     */
    public void generateInventoryList(WarehouseManager warehouseManager, OutputWriter writer) 
            throws IOException {
        List<String> lines = new ArrayList<>();
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses().values()) {
            for (Product product : warehouse.getInventory()) {
                lines.add(warehouse.getId() + "," + product.toString());
            }
        }
        
        writer.writeLines(lines);
    }    
}
