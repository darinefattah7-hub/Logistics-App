/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package oop1.darineabdulfattah_miniproject;
import darineAbdulfattah.domain.*;
import darineAbdulFattah.io.*;
import darineAbdulFattah.processing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
/**
 *
 * @author USER
 */
public class LogisticsApp {
 
    
  private WarehouseManager warehouseManager;
    private ShipmentProcessor shipmentProcessor;
    private ReportGenerator reportGenerator;
    private BufferedReader consoleReader;
    

    public LogisticsApp() {
        this.warehouseManager = new WarehouseManager();
        this.shipmentProcessor = new ShipmentProcessor(warehouseManager);
        this.reportGenerator = new ReportGenerator();
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        LogisticsApp app = new LogisticsApp();
        app.run();
    }
    
    public void run() {
        try {
            System.out.println("=== Warehouse Logistics System ===\n");
            
            // Initial data loading
            loadInitialData();
            
            // Interactive menu
            showMenu();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadInitialData() throws IOException {
        System.out.println("How would you like to load initial data?");
        System.out.println("1. Load from files");
        System.out.println("2. Enter manually");
        System.out.print("Choice: ");
        
        String choice = consoleReader.readLine().trim();
        
        if (choice.equals("1")) {
            loadFromFiles();
        } else {
            System.out.println("Manual entry mode not implemented in initial load.");
            System.out.println("Proceeding with empty system...");
        }
    }
    
    private void loadFromFiles() throws IOException {
        // Load warehouses
        System.out.print("Enter warehouses file path (or press Enter for 'warehouses.txt'): ");
        String warehousesFile = consoleReader.readLine().trim();
        if (warehousesFile.isEmpty()) {
            warehousesFile = "warehouses.txt";
        }
        
        try {
            InputReader warehouseReader = new FileInputReader(warehousesFile);
            List<String> warehouseLines = warehouseReader.readLines();
            processWarehouseLines(warehouseLines);
            System.out.println("✓ Loaded " + warehouseLines.size() + " warehouses");
        } catch (IOException e) {
            System.out.println("⚠ Could not load warehouses file: " + e.getMessage());
        }
        
        // Load inventory
        System.out.print("Enter inventory file path (or press Enter for 'inventory.txt'): ");
        String inventoryFile = consoleReader.readLine().trim();
        if (inventoryFile.isEmpty()) {
            inventoryFile = "inventory.txt";
        }
        
        try {
            InputReader inventoryReader = new FileInputReader(inventoryFile);
            List<String> inventoryLines = inventoryReader.readLines();
            processInventoryLines(inventoryLines);
            System.out.println("✓ Loaded " + inventoryLines.size() + " inventory items");
        } catch (IOException e) {
            System.out.println("⚠ Could not load inventory file: " + e.getMessage());
        }
        
        // Load operations
        System.out.print("Enter operations file path (or press Enter for 'operations.txt'): ");
        String operationsFile = consoleReader.readLine().trim();
        if (operationsFile.isEmpty()) {
            operationsFile = "operations.txt";
        }
        
        try {
            InputReader operationsReader = new FileInputReader(operationsFile);
            List<String> operationLines = operationsReader.readLines();
            processOperationLines(operationLines);
            System.out.println("✓ Processed " + operationLines.size() + " operations");
        } catch (IOException e) {
            System.out.println("⚠ Could not load operations file: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private void processWarehouseLines(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                warehouseManager.createWarehouse(id, name);
            }
        }
    }
    
    private void processInventoryLines(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(",", 3);
            if (parts.length >= 3) {
                String warehouseId = parts[0].trim();
                String productName = parts[1].trim();
                String measurement = parts[2].trim();
                
                Product product = new Product(productName, measurement);
                warehouseManager.addProductToWarehouse(warehouseId, product);
            }
        }
    }
    
    private void processOperationLines(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 0) continue;
            
            String operation = parts[0].trim();
            
            if (operation.equalsIgnoreCase("Create Warehouse") && parts.length >= 3) {
                processCreateWarehouse(parts);
            } else if (operation.equalsIgnoreCase("Add Product") && parts.length >= 3) {
                processAddProduct(parts);
            } else if (operation.equalsIgnoreCase("Ship") && parts.length >= 5) {
                processShip(parts);
            }
        }
    }
    
    private void processCreateWarehouse(String[] parts) {
        String warehouseId = parts[1].trim();
        String warehouseName = parts[2].trim();
        
        Warehouse warehouse = warehouseManager.createWarehouse(warehouseId, warehouseName);
        
        // Add initial products (if any)
        for (int i = 3; i < parts.length; i += 2) {
            if (i + 1 < parts.length) {
                String productName = parts[i].trim();
                String measurement = parts[i + 1].trim();
                Product product = new Product(productName, measurement);
                warehouse.addProduct(product);
            }
        }
    }
    
    private void processAddProduct(String[] parts) {
        String warehouseName = parts[1].trim();
        Warehouse warehouse = warehouseManager.getWarehouseByName(warehouseName);
        
        if (warehouse != null) {
            // Add products (pairs of name and measurement)
            for (int i = 2; i < parts.length; i += 2) {
                if (i + 1 < parts.length) {
                    String productName = parts[i].trim();
                    String measurement = parts[i + 1].trim();
                    Product product = new Product(productName, measurement);
                    warehouse.addProduct(product);
                }
            }
        }
    }
    
    private void processShip(String[] parts) {
        String sourceName = parts[1].trim();
        String destName = parts[2].trim();
        String productName = parts[3].trim();
        String quantity = parts[4].trim();
        
        shipmentProcessor.processShipment(sourceName, destName, productName, quantity);
    }
    
    private void showMenu() throws IOException {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Create Warehouse");
            System.out.println("2. Add Product to Warehouse");
            System.out.println("3. Ship Product");
            System.out.println("4. View Warehouse Inventory");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            
            String choice = consoleReader.readLine().trim();
            
            try {
                switch (choice) {
                    case "1":
                        createWarehouseInteractive();
                        break;
                    case "2":
                        addProductInteractive();
                        break;
                    case "3":
                        shipProductInteractive();
                        break;
                    case "4":
                        viewInventory();
                        break;
                    case "5":
                        generateReports();
                        break;
                    case "6":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    
    private void createWarehouseInteractive() throws IOException {
        System.out.print("Enter warehouse ID: ");
        String id = consoleReader.readLine().trim();
        
        System.out.print("Enter warehouse name: ");
        String name = consoleReader.readLine().trim();
        
        warehouseManager.createWarehouse(id, name);
        System.out.println("✓ Warehouse created successfully!");
    }
    
    private void addProductInteractive() throws IOException {
        System.out.print("Enter warehouse name: ");
        String warehouseName = consoleReader.readLine().trim();
        
        Warehouse warehouse = warehouseManager.getWarehouseByName(warehouseName);
        if (warehouse == null) {
            System.out.println("✗ Warehouse not found!");
            return;
        }
        
        System.out.print("Enter product name: ");
        String productName = consoleReader.readLine().trim();
        
        System.out.print("Enter quantity (e.g., '10 kg'): ");
        String quantity = consoleReader.readLine().trim();
        
        Product product = new Product(productName, quantity);
        warehouse.addProduct(product);
        System.out.println("✓ Product added successfully!");
    }
    
    private void shipProductInteractive() throws IOException {
        System.out.print("Enter source warehouse name: ");
        String source = consoleReader.readLine().trim();
        
        System.out.print("Enter destination warehouse name: ");
        String dest = consoleReader.readLine().trim();
        
        System.out.print("Enter product name: ");
        String productName = consoleReader.readLine().trim();
        
        System.out.print("Enter quantity to ship (e.g., '8 kg'): ");
        String quantity = consoleReader.readLine().trim();
        
        ShipmentRecord record = shipmentProcessor.processShipment(source, dest, productName, quantity);
        
        System.out.println("\nShipment Result:");
        System.out.println("Status: " + record.getStatus());
        System.out.println(record.toString());
    }
    
    private void viewInventory() {
        System.out.println("\n=== Warehouse Inventories ===");
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses().values()) {
            System.out.println("\n" + warehouse.getName() + " (" + warehouse.getId() + "):");
            
            if (warehouse.getInventory().isEmpty()) {
                System.out.println("  (empty)");
            } else {
                for (Product product : warehouse.getInventory()) {
                    System.out.println("  - " + product.toString());
                }
            }
        }
    }
    
    private void generateReports() throws IOException {
        System.out.println("\n=== Generate Reports ===");
        System.out.println("1. Shipment Report");
        System.out.println("2. Warehouse List");
        System.out.println("3. Inventory List");
        System.out.println("4. All Reports");
        System.out.print("Choice: ");
        
        String choice = consoleReader.readLine().trim();
        
        System.out.println("Output to:");
        System.out.println("1. Console");
        System.out.println("2. File");
        System.out.print("Choice: ");
        
        String outputChoice = consoleReader.readLine().trim();
        
        OutputWriter writer;
        if (outputChoice.equals("2")) {
            System.out.print("Enter filename: ");
            String filename = consoleReader.readLine().trim();
            writer = new FileOutputWriter(filename);
        } else {
            writer = new ConsoleOutputWriter();
        }
        
        switch (choice) {
            case "1":
                reportGenerator.generateShipmentReport(shipmentProcessor, writer);
                System.out.println("✓ Shipment report generated");
                break;
            case "2":
                reportGenerator.generateWarehouseList(warehouseManager, writer);
                System.out.println("✓ Warehouse list generated");
                break;
            case "3":
                reportGenerator.generateInventoryList(warehouseManager, writer);
                System.out.println("✓ Inventory list generated");
                break;
            case "4":
                reportGenerator.generateShipmentReport(shipmentProcessor, 
                    new FileOutputWriter("Report.txt"));
                reportGenerator.generateWarehouseList(warehouseManager, 
                    new FileOutputWriter("Warehouses_out.txt"));
                reportGenerator.generateInventoryList(warehouseManager, 
                    new FileOutputWriter("Inventory_out.txt"));
                System.out.println("✓ All reports generated to files");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
       
    }

