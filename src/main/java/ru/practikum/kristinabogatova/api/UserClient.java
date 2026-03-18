package ru.practikum.kristinabogatova.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class UserClient {

    private final String BASE_URL = "https://stellarburgers.education-services.ru/api";

    public UserClient() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response createUser(String email, String password, String name) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(body)
                .post("/auth/register");
    }
}