/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop1.darineabdulfattah_miniproject;

import darineAbdulFattah.domain.*;
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
    
    private static final String DEFAULT_WAREHOUSES_FILE = "samples/warehouses.txt";
    private static final String DEFAULT_INVENTORY_FILE = "samples/inventory.txt";
    private static final String DEFAULT_OPERATIONS_FILE = "samples/operations.txt";

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
            loadInitialData();
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
        
        String choice = readConsoleLine();
        
        if (choice.equals("1")) {
            loadFromFiles();
        } else {
            loadManually();
        }
    }
    
    private void loadManually() throws IOException {
        System.out.println("\n=== Manual Data Entry ===");

        ConsoleInputReader consoleInputReader = new ConsoleInputReader(consoleReader);

        System.out.println("Enter warehouse data (type 'END' to finish):");
        processWarehouseLines(consoleInputReader.readLines());

        System.out.println("Enter inventory data (type 'END' to finish):");
        processInventoryLines(consoleInputReader.readLines());

        System.out.println("Enter operations data (type 'END' to finish):");
        processOperationLines(consoleInputReader.readLines());
        
        System.out.println("Loaded " + warehouseManager.getAllWarehouses().size() + " warehouses manually");
    }
    
    private void loadFromFiles() throws IOException {
        System.out.println("\n=== Loading from Files ===");
        
        System.out.print("Enter warehouses file path (or press Enter for default): ");
        String warehousesFile = readConsoleLine();
        if (warehousesFile.isEmpty()) {
            warehousesFile = DEFAULT_WAREHOUSES_FILE;
        }
        
        try {
            InputReader warehouseReader = new FileInputReader(warehousesFile);
            List<String> warehouseLines = warehouseReader.readLines();
            processWarehouseLines(warehouseLines);
            System.out.println("Loaded " + warehouseLines.size() + " warehouses");
        } catch (IOException e) {
            System.out.println("Could not load warehouses file: " + e.getMessage());
            System.out.println("   Make sure the file exists at: " + warehousesFile);
        }
        
        System.out.print("Enter inventory file path (or press Enter for default): ");
        String inventoryFile = readConsoleLine();
        if (inventoryFile.isEmpty()) {
            inventoryFile = DEFAULT_INVENTORY_FILE;
        }
        
        try {
            InputReader inventoryReader = new FileInputReader(inventoryFile);
            List<String> inventoryLines = inventoryReader.readLines();
            processInventoryLines(inventoryLines);
            System.out.println("Loaded " + inventoryLines.size() + " inventory items");
        } catch (IOException e) {
            System.out.println("Could not load inventory file: " + e.getMessage());
        }
        
        System.out.print("Enter operations file path (or press Enter for default): ");
        String operationsFile = readConsoleLine();
        if (operationsFile.isEmpty()) {
            operationsFile = DEFAULT_OPERATIONS_FILE;
        }
        
        try {
            InputReader operationsReader = new FileInputReader(operationsFile);
            List<String> operationLines = operationsReader.readLines();
            processOperationLines(operationLines);
            System.out.println("Processed " + operationLines.size() + " operations");
        } catch (IOException e) {
            System.out.println("Could not load operations file: " + e.getMessage());
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
                String measurementStr = parts[2].trim();
                
                Measurement measurement = new Measurement(measurementStr);
                Product product = new Product(productName, measurement);
                warehouseManager.addProductToWarehouse(warehouseId, product);
            }
        }
    }
    
    private void processOperationLines(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 0) {
                continue;
            }
            
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
        
        for (int i = 3; i < parts.length; i += 2) {
            if (i + 1 < parts.length) {
                String productName = parts[i].trim();
                String measurementStr = parts[i + 1].trim();
                
                Measurement measurement = new Measurement(measurementStr);
                Product product = new Product(productName, measurement);
                warehouse.addProduct(product);
            }
        }
    }
    
    private void processAddProduct(String[] parts) {
        String warehouseName = parts[1].trim();
        Warehouse warehouse = warehouseManager.getWarehouseByName(warehouseName);
        
        if (warehouse != null) {
            for (int i = 2; i < parts.length; i += 2) {
                if (i + 1 < parts.length) {
                    String productName = parts[i].trim();
                    String measurementStr = parts[i + 1].trim();
                    
                    Measurement measurement = new Measurement(measurementStr);
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
            
            String choice = readConsoleLine();
            
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
        String id = readConsoleLine();
        
        System.out.print("Enter warehouse name: ");
        String name = readConsoleLine();
        
        warehouseManager.createWarehouse(id, name);
        System.out.println("Warehouse created successfully!");
    }
    
    private void addProductInteractive() throws IOException {
        System.out.print("Enter warehouse name: ");
        String warehouseName = readConsoleLine();
        
        Warehouse warehouse = warehouseManager.getWarehouseByName(warehouseName);
        if (warehouse == null) {
            System.out.println("Warehouse not found!");
            return;
        }
        
        System.out.print("Enter product name: ");
        String productName = readConsoleLine();
        
        System.out.print("Enter quantity (e.g., '10 kg'): ");
        String quantityStr = readConsoleLine();
        
        Measurement measurement = new Measurement(quantityStr);
        Product product = new Product(productName, measurement);
        warehouse.addProduct(product);
        System.out.println("Product added successfully!");
    }
    
    private void shipProductInteractive() throws IOException {
        System.out.print("Enter source warehouse name: ");
        String source = readConsoleLine();
        
        System.out.print("Enter destination warehouse name: ");
        String dest = readConsoleLine();
        
        System.out.print("Enter product name: ");
        String productName = readConsoleLine();
        
        System.out.print("Enter quantity to ship (e.g., '8 kg'): ");
        String quantity = readConsoleLine();
        
        ShipmentRecord record = shipmentProcessor.processShipment(source, dest, productName, quantity);
        
        System.out.println("\nShipment Result:");
        System.out.println(record.toString());
    }
    
    private void viewInventory() {
        System.out.println("\n=== Warehouse Inventories ===");
        
        for (Warehouse warehouse : warehouseManager.getAllWarehouses()) {
            System.out.println("\n" + warehouse.getName() + ":");
            
            if (warehouse.inventoryItems().isEmpty()) {
                System.out.println("  (empty)");
            } else {
                for (Product product : warehouse.inventoryItems()) {
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
        
        String choice = readConsoleLine();
        
        System.out.println("Output to:");
        System.out.println("1. Console");
        System.out.println("2. File");
        System.out.print("Choice: ");
        
        String outputChoice = readConsoleLine();
        
        OutputWriter writer;
        if (outputChoice.equals("2")) {
            System.out.print("Enter filename: ");
            String filename = readConsoleLine();
            writer = new FileOutputWriter(filename);
        } else {
            writer = new ConsoleOutputWriter();
        }
        
        switch (choice) {
            case "1":
                reportGenerator.generateShipmentReport(shipmentProcessor, writer);
                System.out.println("Shipment report generated");
                break;
            case "2":
                reportGenerator.generateWarehouseList(warehouseManager, writer);
                System.out.println("Warehouse list generated");
                break;
            case "3":
                reportGenerator.generateInventoryList(warehouseManager, writer);
                System.out.println("Inventory list generated");
                break;
            case "4":
                reportGenerator.generateShipmentReport(
                    shipmentProcessor,
                    new FileOutputWriter("output/Report.txt")
                );
                reportGenerator.generateWarehouseList(
                    warehouseManager,
                    new FileOutputWriter("output/Warehouses_out.txt")
                );
                reportGenerator.generateInventoryList(
                    warehouseManager,
                    new FileOutputWriter("output/Inventory_out.txt")
                );
                System.out.println("All reports generated to 'output' folder");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private String readConsoleLine() throws IOException {
        String line = consoleReader.readLine();
        return line == null ? "" : line.trim();
    }
}

    
    

