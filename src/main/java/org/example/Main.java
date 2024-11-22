package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import InvoicePDF.InvoiceCreator;
import com.github.javafaker.Faker;
import generators.GeneratorProducts;
import generators.GeneratorUsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static org.example.ShoppingCartDict.productsInCar;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void editInventory(User user, List<Product> products){

        Scanner scanner = new Scanner(System.in);

        if(user.getRol().equals("admin")){

            System.out.println("**********************************");
            System.out.println("Products in inventory");

            System.out.println("**********************************");

            for(Product product : products) {
                System.out.println("Id: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description : " + product.getDescription());
                System.out.println("Price:" + product.getPrice());
                System.out.println("Quantity:" + product.getQuantity());
                System.out.println("**********************************");
            }

            System.out.println("1. Edit Product");
            System.out.println("2. Delete Product");
            System.out.println("***************************");
            String option = String.valueOf(scanner.nextInt());
            scanner.nextLine();

            if (option.equals("1")){
                System.out.println("***********************");
                System.out.println("Id the product for edit: ");
                String idForEdit = scanner.nextLine();

                if (Integer.valueOf(idForEdit) >= 0 || Integer.valueOf(idForEdit) <= products.size()){
                    for (int i=0; i<=products.size(); i++){ //wrong for
                        if (products.get(i).getId().equals(idForEdit)){
                            System.out.println("***************************");
                            System.out.println("Editing product information");
                            System.out.println("1. Edit Name");
                            System.out.println("2. Edit Description");
                            System.out.println("3. Edit Price");
                            System.out.println("4. Edit Stock quantity");

                            String editOption = String.valueOf(scanner.nextInt());
                            scanner.nextLine();

                            if (editOption.equals("1")) {
                                System.out.println("***************************");
                                System.out.println("Editing name");
                                System.out.println("New name: ");
                                String newName = scanner.nextLine();
                                scanner.nextLine();

                                try{
                                    products.get(Integer.parseInt(editOption)).setName(newName);
                                    logger.info("PRODUCT NAME EDITED. ID_PRODUCT :" + products.get(i).getId() + ",EDITED BY ADMIN, ID: "+ user.getId());
                                }catch (Exception e) {
                                    logger.error("ERROR IN EDITING PRODUCT NAME. ID_PRODUCT : "+ products.get(i).getId() +",EDITING BY ADMIN, ID: "+ user.getId() +" - " + e);
                                }


                            } else if (editOption.equals("2")) {
                                System.out.println("***************************");
                                System.out.println("Editing Description");
                                System.out.println("New Description: ");
                                String newDescription = scanner.nextLine();
                                scanner.nextLine();

                                try{
                                    products.get(Integer.parseInt(editOption)).setDescription(newDescription);
                                    logger.info("PRODUCT DESCRIPTION EDITED. ID_PRODUCT :" + products.get(i).getId() + ",EDITED BY ADMIN, ID: "+ user.getId());
                                }catch (Exception e) {
                                    logger.error("ERROR IN EDITING PRODUCT DESCRIPTION. ID_PRODUCT : "+ products.get(i).getId() +",EDITING BY ADMIN, ID: "+ user.getId() +" - " + e);
                                }


                            } else if (editOption.equals("3")) {
                                System.out.println("***************************");
                                System.out.println("Editing Price");
                                System.out.println("New Price: ");
                                double newPrice = scanner.nextDouble();
                                scanner.nextLine();

                                try{
                                    products.get(Integer.parseInt(editOption)).setPrice(newPrice);
                                    logger.info("PRODUCT PRICE EDITED. ID_PRODUCT :" + products.get(i).getId() + ",EDITED BY ADMIN, ID: "+ user.getId());
                                }catch (Exception e) {
                                    logger.error("ERROR IN EDITING PRODUCT PRICE. ID_PRODUCT : "+ products.get(i).getId() +",EDITING BY ADMIN, ID: "+ user.getId() +" - " + e);
                                }


                            } else if (editOption.equals("4")) {
                                System.out.println("***************************");
                                System.out.println("Editing Quantity");
                                System.out.println("***************************");

                                System.out.println("1. For existing quantity");
                                System.out.println("2. Fot change all stock");
                                String quantityOption =  scanner.nextLine();

                                int newQuantity;

                                if (quantityOption.equals("1")){

                                    System.out.println("New Quantity to add: ");
                                    newQuantity = scanner.nextInt();

                                    int willAdd = products.get(Integer.parseInt(editOption)).getQuantity() + newQuantity;

                                    try{
                                        products.get(Integer.parseInt(editOption)).setQuantity(willAdd);
                                        logger.info("ADDED QUANTITY TO INVENTORY. ADDED : "+ willAdd + " UNITS " + ", ID_PRODUCT :" + products.get(i).getId() + ",EDITED BY ADMIN, ID: "+ user.getId());
                                    }catch (Exception e) {
                                        logger.error("ERROR IN ADDING QUANTITY TO INVENTORY. ID_PRODUCT : "+ products.get(i).getId() +",EDITING BY ADMIN, ID: "+ user.getId() +" - " + e);
                                    }


                                } else if (quantityOption.equals("2")) {

                                    System.out.println("New Quantity: ");
                                    newQuantity = scanner.nextInt();
                                    int previousStock = products.get(Integer.parseInt(editOption)).getQuantity();

                                    try{
                                        products.get(Integer.parseInt(editOption)).setQuantity(newQuantity);
                                        logger.info("ALL STOCK CHANGE. BEFORE : "+ previousStock + ", NEW STOCK : " + newQuantity +", ID_PRODUCT :" + products.get(i).getId() + ",EDITED BY ADMIN, ID: "+ user.getId());
                                    }catch (Exception e) {
                                        logger.error("ERROR IN ADDING QUANTITY TO INVENTORY. ID_PRODUCT : "+ products.get(i).getId() +",EDITING BY ADMIN, ID: "+ user.getId() +" - " + e);
                                    }


                                }else{logger.info("ERROR IN EDITING PRODUCT INFORMATION , ADMIN IN SESSION : "+ user.getId() + ", NAME: " + user.getName());}

                            }
                        }
                    }
                } else{System.out.println("Id out");}

            } else if(option.equals("2")){
                //code for delete product
                System.out.println("**********************************");
                System.out.println("Deleting product");
                System.out.println("Id the product to delete: ");
                String idToDelete = String.valueOf(scanner.nextInt());

                if(Integer.parseInt(idToDelete) >= 0 || Integer.parseInt(idToDelete) <= products.size()){
                    for(int i=0; i<products.size(); i++){
                        if(products.get(i).getId().equals(idToDelete)){products.remove(i);}
                        logger.info("PRODUCT DELETED, ID :"+ idToDelete + ", DELETING BY ADMIN" + user.getId() + ", NAME: " + user.getName());
                    }

                }else{logger.info("Wrong option. The id does not exist");}

            }else{logger.info("Wrong option");}

        } else{logger.info("ACCESS DENIED");}
    }

    public static void managementUsers(User user, List<User> userOnSystem) {
        Scanner scanner = new Scanner(System.in);

        if (user.getRol().equals("admin")) {

            System.out.println("Management users system");

            for (User users : userOnSystem) {
                System.out.println("***************************");
                System.out.println("Id   : " + users.getId());
                System.out.println("Name : " + users.getName());
                System.out.println("Password  : " + users.getPassword());
                System.out.println("Rol  : " + users.getRol());
                System.out.println("***************************");
            }

            System.out.println("1. Edit User");
            System.out.println("2. Delete User");
            System.out.println("***************************");
            String option = String.valueOf(scanner.nextInt());
            scanner.nextLine();

            if (option.equals("1")) {

                System.out.println("Id User for edit: ");
                String idUser = String.valueOf(scanner.nextInt());

                for (int i = 0; i < userOnSystem.size(); i++) {
                    if (userOnSystem.get(i).getId().equals(idUser)) {
                        System.out.println("***************************");
                        System.out.println("Editing user information");
                        System.out.println("1. Edit Name");
                        System.out.println("2. Edit password");
                        System.out.println("3. Edit Rol");

                        String editOption = String.valueOf(scanner.nextInt());
                        scanner.nextLine();


                        if (editOption.equals("1")) {
                            System.out.println("***************************");
                            System.out.println("Editing name");
                            System.out.println("New name: ");
                            String newName = scanner.nextLine();
                            scanner.nextLine();

                            userOnSystem.get(i).setName(newName);

                        } else if (editOption.equals("2")) {
                            System.out.println("***************************");
                            System.out.println("Editing password");
                            System.out.println("New password: ");
                            String newPassword = scanner.nextLine();
                            scanner.nextLine();

                            userOnSystem.get(i).setPassword(newPassword);

                        } else if (editOption.equals("3")) {
                            System.out.println("***************************");
                            System.out.println("Editing rol");
                            System.out.println("New rol: ");
                            String newRol = scanner.nextLine();
                            scanner.nextLine();

                            userOnSystem.get(i).setRol(newRol);

                        }
                    }
                }

            } else if (option.equals("2")) {
                System.out.println("Id User for delete: ");
                String idUserForDelete = String.valueOf(scanner.nextInt());

                if(Integer.valueOf(idUserForDelete) >= 0 || Integer.valueOf(idUserForDelete) <= userOnSystem.size()){
                    for(int i=0; i<userOnSystem.size(); i++){
                        if(userOnSystem.get(i).getId().equals(idUserForDelete)){userOnSystem.remove(i);}
                    }

                }else{System.out.println("Wrong option. The id does not exist");}

            } else {System.out.println("Wrong option!");}

        } else {System.out.println("Do not has permissions");}

    }

    public static void buyProducts(Customer customer, List<Product> products){
        Scanner scanner = new Scanner(System.in);

        String option;

        System.out.println("**********************************");
        System.out.println("Welcome");

        do {
            System.out.println("**********************************");

            for(Product product : products) {
                System.out.println("Id: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description : " + product.getDescription());
                System.out.println("Price:" + product.getPrice());
                System.out.println("**********************************");
            }

            System.out.println("Id of product to buy: ");
            String idProduct = String.valueOf(scanner.nextInt());
            scanner.nextLine();

            if (Integer.parseInt(idProduct) >= 0 || Integer.parseInt(idProduct) <= products.size()){
                for (Product product : products){
                    if(product.getId().equals(idProduct)){
                        ShoppingCartDict.addProduct(product);
                        //System.out.println("Product added");
                    }
                }
            }
            if(Integer.parseInt(idProduct) < 0 || Integer.parseInt(idProduct) > products.size()){
                System.out.println("Wrong option");
            }

            System.out.println("x for exit or any letters to exit");
            option = scanner.nextLine();

        }while(!option.equals("x"));
    }

        public static void main (String[]args){

            Scanner scanner = new Scanner(System.in);
            Faker faker = new Faker();

            List<User> userOnSystem = new ArrayList<>();
            List<Product> productsOnSystem = new ArrayList<>();

            //ADMIN USER
            User Admin = new User("999", faker.name().fullName(), faker.regexify("[a-zA-Z0-9!@#$%^&*()]{10}"),"admin");
            //Simulate system entry by Admin
            logger.info("USER ENTRY , ID: " + Admin.getId() + ", NAME: "+ Admin.getName());

            //CUSTOMER USER
            Customer customer = new Customer("Paco", "Rodriguez", "Street 4", "3102583620");

            userOnSystem = GeneratorUsers.createUser(); //Users on system
            productsOnSystem = GeneratorProducts.createProduct(); //Products on system

            String exit;

            do{
                System.out.println("****************************");
                System.out.println("1. for management users");
                System.out.println("2. for management products");
                System.out.println("3. for Buy products");
                String mainOption =  scanner.nextLine();

                if (mainOption.equals("1")){

                    managementUsers(Admin, userOnSystem);

                } else if (mainOption.equals("2")) {
                    editInventory(Admin, productsOnSystem);

                } else if (mainOption.equals("3")) {
                    buyProducts(customer, productsOnSystem);
                    //Process purchase
                    double totalPrice = 0;

                    //calculate total price of shopping Cart
                    for(HashMap<String, String> product: productsInCar()){
                        totalPrice += Double.parseDouble(product.get("TotalPrice"));
                    }

                    InvoiceCreator.createInvoice(customer, ShoppingCartDict.productsInCar(), String.valueOf(totalPrice));
                    logger.info("Purchase made by : "+ customer.getName());

                }else{System.out.println("Wrong option");}

                System.out.println("x for exit or any letters to watch users");
                exit = scanner.nextLine();

            }while(!exit.equals("x"));

        }
    }