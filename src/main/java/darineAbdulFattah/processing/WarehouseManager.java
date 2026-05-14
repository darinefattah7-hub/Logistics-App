/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.processing;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 import darineAbdulFattah.domain.Product;
import darineAbdulFattah.domain.Warehouse;

/**
 *
 * @author USER
 */
public class WarehouseManager {
    private Map<String, Warehouse> warehouses;
    
    public WarehouseManager() {
        this.warehouses = new HashMap<>();
    }
    
    /**
     * Adds a warehouse to the system
     * @param warehouse the warehouse to add
     */
    public void addWarehouse(Warehouse warehouse) {
        warehouses.put(warehouse.getId(), warehouse);
    }
    
    /**
     * Creates a new warehouse with the given ID and name
     * @param id warehouse ID
     * @param name warehouse name
     * @return the created warehouse
     */
    public Warehouse createWarehouse(String id, String name) {
        if (warehouses.containsKey(id)) {
            return warehouses.get(id);
        }
        Warehouse warehouse = new Warehouse(id, name);
        warehouses.put(id, warehouse);
        return warehouse;
    }
    
    /**
     * Gets a warehouse by ID
     * @param id the warehouse ID
     * @return the warehouse, or null if not found
     */
    public Warehouse getWarehouse(String id) {
        return warehouses.get(id);
    }
    
    /**
     * Gets a warehouse by name (case-insensitive)
     * @param name the warehouse name
     * @return the warehouse, or null if not found
     */
    public Warehouse getWarehouseByName(String name) {
        for (Warehouse warehouse : warehouses.values()) {
            if (warehouse.getName().equalsIgnoreCase(name)) {
                return warehouse;
            }
        }
        return null;
    }
    
    /**
     * Checks if a warehouse exists by ID
     * @param id the warehouse ID
     * @return true if warehouse exists
     */
    public boolean warehouseExists(String id) {
        return warehouses.containsKey(id);
    }
    
    /**
     * Gets all warehouses
     * @return map of warehouse ID to warehouse
     */
    public Collection<Warehouse> getAllWarehouses() {
        return Collections.unmodifiableCollection(warehouses.values());
    }
    
    /**
     * Adds a product to a warehouse
     * @param warehouseId the warehouse ID
     * @param product the product to add
     * @return true if successful, false if warehouse not found
     */
    public boolean addProductToWarehouse(String warehouseId, Product product) {
        Warehouse warehouse = warehouses.get(warehouseId);
        if (warehouse != null) {
            warehouse.addProduct(product);
            return true;
        }
        return false;
    }
    
}
