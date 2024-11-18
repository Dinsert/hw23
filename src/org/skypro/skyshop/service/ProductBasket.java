package org.skypro.skyshop.service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.skypro.skyshop.model.Product;

public class ProductBasket {

    private final Map<String,Product> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getNameProduct(),product);
    }

    public int getTotalBasketValue() {
        int result = 0;
        for (Product product : products.values()) {
            if (nonNull(product)) {
                result += product.getPrice();
            }
        }
        return result;
    }

    public void printBasketContents() {
        int count = 0;
        boolean isEmptyBasket = true;
        for (Product product : products.values()) {
            if (nonNull(product)) {
                isEmptyBasket = false;
                System.out.println(product);
                if (product.isSpecial()) {
                    count++;
                }
            }
        }
        if (!isEmptyBasket) {
            System.out.println("Итого: " + getTotalBasketValue());
            System.out.println("Специальных товаров: " + count);
        } else {
            System.out.println("В корзине пусто");
        }
    }

    public boolean checkProductContainsInBasket(String productName) {
        return products.containsKey(productName);
    }

    public List<Product> removeProductByBasket(String productName) {
        List<Product> result = new ArrayList<>(products.values());
        Iterator<Product> productIterator = products.values().iterator();
        while (productIterator.hasNext()) {
            if (productIterator.next().getNameProduct().equals(productName)) {
                productIterator.remove();
            }
        }
        result.removeAll(products.values());
        if (result.isEmpty()) {
            System.out.println("Список пуст");
        }
        return result;
    }

    public void clearBasket() {
        products.clear();
    }

}
