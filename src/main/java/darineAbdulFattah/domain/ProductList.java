package darineAbdulFattah.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductList extends ArrayList<Product> {

    public ProductList() {
        super();
    }

    @Override
    public boolean add(Product newProduct) {
        for (Product existingProduct : this) {
            if (existingProduct.getName().equalsIgnoreCase(newProduct.getName())) {
                existingProduct.getWeight().addMeasurement(newProduct.getWeight());
                return true;
            }
        }
        return super.add(newProduct);
    }

    public Product findByName(String name) {
        for (Product p : this) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public Product removeByName(String name) {
        Iterator<Product> it = this.iterator();

        while (it.hasNext()) {
            Product p = it.next();
            if (p.getName().equalsIgnoreCase(name)) {
                it.remove();
                return p;
            }
        }
        return null;
    }
}