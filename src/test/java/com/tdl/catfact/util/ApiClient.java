package com.tdl.catfact.util;

import com.tdl.catfact.enums.HttpMethod;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.InvalidArgumentException;

import static com.tdl.catfact.enums.HttpMethod.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

/**
 * ApiClient is a utility class for sending HTTP requests to a specified API
 * using the RestAssured library. It supports GET, POST, PUT, DELETE, and PATCH methods.
 */
public class ApiClient {
    private final RequestSpecification spec;

    /**
     * Initializes the ApiClient with a base URL and a base path.
     *
     * @param baseUrl  The base URL of the API (e.g., "https://api.example.com").
     * @param endpoint The base path of the endpoint (e.g., "/v1/resource").
     */
    public ApiClient (String baseUrl, String endpoint) {
        this.spec = given().
                baseUri(baseUrl).
                basePath(endpoint).
                contentType(JSON).
                accept(JSON).
                header("Content-Type", "application/json");
    }

    /**
     * Sends an HTTP request with the specified method.
     *
     * @param httpMethod The HTTP method to use (GET, POST, PUT, DELETE, PATCH).
     * @return The Response object representing the server's response.
     * @throws InvalidArgumentException if the HTTP method is not implemented.
     */
    public Response sendRequest (HttpMethod httpMethod) {

        if (httpMethod == GET) return spec.get();
        if (httpMethod == POST) return spec.post();
        if (httpMethod == DELETE) return spec.delete();
        if (httpMethod == PUT) return spec.put();
        if (httpMethod == PATCH) return spec.patch();

        throw new InvalidArgumentException("'" + httpMethod + "' is not implemented");
    }

    /**
     * Sends an HTTP request with the specified method and a request body.
     *
     * @param httpMethod The HTTP method to use (GET, POST, PUT, DELETE, PATCH).
     * @param body       The request body to include in the request.
     * @return The Response object representing the server's response.
     * @throws InvalidArgumentException if the HTTP method is not implemented.
     */
    public Response sendRequest (HttpMethod httpMethod, final String body) {

        if (httpMethod == GET) return spec.body(body).get();
        if (httpMethod == POST) return spec.body(body).post();
        if (httpMethod == DELETE) return spec.body(body).delete();
        if (httpMethod == PUT) return spec.body(body).put();
        if (httpMethod == PATCH) return spec.body(body).patch();

        throw new InvalidArgumentException("'" + httpMethod + "' is not implemented");
    }
}
