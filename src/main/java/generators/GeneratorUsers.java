package generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.User;

import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;

public class GeneratorUsers {
    private static final Logger logger = LogManager.getLogger(GeneratorUsers.class);

    public static List<User> createUser(){
        Faker faker = new Faker();
        List<User> listUser = new ArrayList<>();

        String RegisterUsers = "";

        for(int i=0; i<=3; i++){
            String userName = faker.name().fullName();
            listUser.add(new User(String.valueOf(i), userName, faker.regexify("[a-zA-Z0-9!@#$%^&*()]{10}"),"user"));

            RegisterUsers += "USER REGISTERED IN THE SYSTEM , ID:" + i + ", NAME: "+ userName;
        }

        logger.info(RegisterUsers);

        return listUser;
    }

}

