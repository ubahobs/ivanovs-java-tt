package com.tdl.catfact.services;

import com.tdl.catfact.enums.HttpMethod;
import com.tdl.catfact.util.ApiClient;
import io.restassured.response.Response;
import org.bson.Document;
import org.testng.Assert;

public class FactService {
    ApiClient client;
    Response response;

    public FactService (String baseUrl) {
        client = new ApiClient(baseUrl, "fact");
    }

    public FactService getFact () {
        this.response = client.sendRequest(HttpMethod.GET);
        this.response.then().assertThat().statusCode(200);

        return this;
    }

    public FactService compareFacts (Document dbFact) {
        String fact = response.jsonPath().getString("fact");
        int factWordCount = fact.split("\\s+").length;
        int factCharCount = fact.length();

        int dbWordCount = dbFact.getInteger("wordCount");
        int dbCharCount = dbFact.getInteger("charCount");

//        Assert.assertTrue(factWordCount > dbWordCount, "Word count mismatch");
//        Assert.assertTrue(factCharCount > dbCharCount, "Char count mismatch");

        return this;
    }
}
