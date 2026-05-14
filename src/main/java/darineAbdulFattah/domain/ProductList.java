package darineAbdulFattah.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ProductList extends ArrayList<Product> {

    public ProductList() {
        super();
    }

    @Override
    public boolean add(Product newProduct) {
        for (Product existingProduct : this) {
            if (existingProduct.hasSameName(newProduct)) {
                existingProduct.addWeightFrom(newProduct);
                return true;
            }
        }
        return super.add(newProduct);
    }

    @Override
    public boolean addAll(Collection<? extends Product> products) {
        boolean changed = false;
        for (Product product : products) {
            changed |= add(product);
        }
        return changed;
    }

    public Product findByName(String name) {
        for (Product p : this) {
            if (p.hasName(name)) {
                return p;
            }
        }
        return null;
    }

    public Product removeByName(String name) {
        Iterator<Product> it = this.iterator();

        while (it.hasNext()) {
            Product p = it.next();
            if (p.hasName(name)) {
                it.remove();
                return p;
            }
        }
        return null;
    }
}
