package generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.example.Product;

import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;

public class GeneratorProducts {
    private static final Logger logger = LogManager.getLogger(GeneratorProducts.class);

    public static List<Product> createProduct(){
        Faker faker = new Faker();
        List<Product> listProduct = new ArrayList<>();

        //For save registers
        String RegisterProduct = "";

        for(int i=0; i<=10; i++){
            String productName = faker.commerce().productName();
            listProduct.add(new Product(String.valueOf(i), productName, Double.parseDouble(faker.commerce().price().replace(",", ".")), faker.commerce().material() + " " + faker.color().name(), faker.number().numberBetween(1, 20)));

            RegisterProduct += "PRODUCT REGISTERED IN THE SYSTEM , ID:" + i + ", NAME: "+ productName;
            }


        logger.info(RegisterProduct);

        return listProduct;
    }

}


