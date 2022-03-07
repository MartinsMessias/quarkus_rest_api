package br.com.client.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ClientResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/client")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}