/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package darineAbdulFattah.domain;

import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author USER
 */
public class Warehouse {
    
      private ProductList inventory;
    private ProductList shipment;
    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    private String name;
    
    public Warehouse() {
        this.inventory = new ProductList();
        this.shipment = new ProductList();
    }
    
    public Warehouse(String id, String name) {
    this.id = id;
    this.name = name;
    this.inventory = new ProductList();
    this.shipment = new ProductList();
}
    
    public void shipProduct(String productName) {
        // Search for product in inventory
        Iterator<Product> iterator = inventory.iterator();
        
        while (iterator.hasNext()) {
            Product product = iterator.next();
            
            if (product.getName().equalsIgnoreCase(productName)) {
                // Remove from inventory
                iterator.remove();
                
                // Add to shipment
                shipment.add(product);
                
                return; // Product found and shipped
            }
        }
        
        // Product not found - do nothing
    }
    
    public void sortInventory() {
        Collections.sort(inventory);
    }
    
    public void displayShipped() {
        // Use for-each iteration (not index-based)
        for (Product product : shipment) {
            System.out.println(product);
        }
    }
    
    // Package-private accessors for warehouse management
    public ProductList getInventory() {
        return inventory;
    }
    
   public ProductList getShipment() {
        return shipment;
    }
   public void addProduct(Product product) {
    inventory.add(product);
}
    
}
