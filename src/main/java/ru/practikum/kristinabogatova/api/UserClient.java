package ru.practikum.kristinabogatova.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.practikum.kristinabogatova.model.UserRequest;
import ru.practikum.kristinabogatova.utils.Endpoints;

import static ru.practikum.kristinabogatova.utils.GlobalConst.BASE_URL;

public class UserClient {

    public UserClient() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Создать пользователя через API")
    public Response createUser(String email, String password, String name) {
        UserRequest body = new UserRequest(email, password, name);
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(Endpoints.API_REGISTER_PATH);
    }

    @Step("Удалить пользователя через API")
    public Response deleteUser(String accessToken) {
        if (accessToken == null) return null;
        return RestAssured.given()
                .header("Authorization", accessToken)
                .delete(Endpoints.API_USER_PATH);
    }
}