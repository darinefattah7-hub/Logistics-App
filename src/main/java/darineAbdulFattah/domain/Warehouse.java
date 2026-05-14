package darineAbdulFattah.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Warehouse {
    
    private ProductList inventory;
    private ProductList shipment;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Warehouse(String id, String name) {
        this.id = id;
        this.name = name;
        this.inventory = new ProductList();
        this.shipment = new ProductList();
    }
    
    public void shipProduct(String productName) {
        Iterator<Product> iterator = inventory.iterator();
        
        while (iterator.hasNext()) {
            Product product = iterator.next();
            
            if (product.hasName(productName)) {
                iterator.remove();
                shipment.add(product);
                return;
            }
        }
    }
    
    public void sortInventory() {
        Collections.sort(inventory);
    }
    
    public void displayShipped() {
        for (Product product : shipment) {
            System.out.println(product);
        }
    }
    
    public List<Product> inventoryItems() {
        return Collections.unmodifiableList(inventory);
    }
    
    public List<Product> shipmentItems() {
        return Collections.unmodifiableList(shipment);
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public Product findProduct(String productName) {
        return inventory.findByName(productName);
    }

    public Product removeProduct(String productName) {
        return inventory.removeByName(productName);
    }

    public void addShippedProduct(Product product) {
        shipment.add(product);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}
