package ru.practikum.kristinabogatova.generator;

import java.util.UUID;

public class UserDataGenerator {

    public static String getEmail() {
        return "test" + UUID.randomUUID().toString().substring(0,5) + "@mail.com";
    }

    public static String getPassword() {
        return "123456";
    }

    public static String getName() {
        return "Kristina";
    }
}