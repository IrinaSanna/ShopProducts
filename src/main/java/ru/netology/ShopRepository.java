package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        Product addProduct = findById(product.id);
        if (addProduct != null) {
            throw new AlreadyExistsException(
                    "Товар с таким id уже существует"
            );
        }
        products = addArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        Product productToBeRemoved = findById(id);
        if (productToBeRemoved == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}