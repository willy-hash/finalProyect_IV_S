package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartDict {
    public static List<HashMap<String, String>> products = new ArrayList<>();

    public static void addProduct(Product product){
        productsToSell(product);
    }

    public static List<HashMap<String, String>> productsInCar(){
        return products;
    }

    public static void removeProduct(String idProduct){
        for(HashMap<String, String> product : products){
            if(product.get("Id").equals(idProduct)){
                products.remove(product);
                break;
            }
        }
    }

    public static void productsToSell(Product product){

        boolean finded = false;

        for (HashMap<String, String> productInShoppingCart : products) {

            if (productInShoppingCart.get("Id").equals(product.getId())) {

                double newPrice = Double.parseDouble(productInShoppingCart.get("TotalPrice"));
                int newQuantity = Integer.parseInt(productInShoppingCart.get("Quantity"));

                newPrice += product.getPrice();
                newQuantity += 1;

                productInShoppingCart.put("TotalPrice", String.valueOf(newPrice));
                productInShoppingCart.put("Quantity", String.valueOf(newQuantity));

                finded = true;
                break;
            }
        }

        if (!finded){
            products.add(
                    new HashMap<>(){{
                        put("Id", product.getId());
                        put("Name", product.getName());
                        put("Description", product.getDescription());
                        put("Price", String.valueOf(product.getPrice()));
                        put("TotalPrice", String.valueOf(product.getPrice()));
                        put("Quantity", String.valueOf(product.getQuantity()));
                    }}
            );
        }

    }

}

