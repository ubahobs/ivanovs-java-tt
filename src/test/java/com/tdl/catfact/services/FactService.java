package com.tdl.catfact.services;

import com.tdl.catfact.enums.HttpMethod;
import com.tdl.catfact.util.ApiClient;
import com.tdl.util.AllureUtil;
import io.restassured.response.Response;
import io.qameta.allure.*;
import org.bson.Document;

public class FactService {
    ApiClient client;
    Response response;

    public FactService (String baseUrl) {
        client = new ApiClient(baseUrl, "fact");
    }

    @Step("Retrieving fact")
    public FactService getFact () {
        response = client.sendRequest(HttpMethod.GET);
        response.then().assertThat().statusCode(200);

        AllureUtil.logStepIntoReport(String.format("Response status code:: %d", response.statusCode()));
        AllureUtil.logStepIntoReport(String.format("Response body: %s", response.body().toString()));

        return this;
    }

    @Step("Comparing fact with the data in database")
    public FactService compareFacts (Document dbFact) {
        String fact = response.jsonPath().getString("fact");
        int factWordCount = fact.split("\\s+").length;
        int factCharCount = fact.length();

        int dbWordCount = dbFact.getInteger("wordCount");
        int dbCharCount = dbFact.getInteger("charCount");

        String wordResult = factWordCount > dbWordCount ? "<": ">";
        String charResult = factCharCount > dbCharCount ? "<": ">";

        AllureUtil.logStepIntoReport(String.format("Database Word Count: %d %s API Word Count: %d", dbWordCount, wordResult, factWordCount));
        AllureUtil.logStepIntoReport(String.format("Database Char Count: %d %s API Char Count: %d", dbCharCount, charResult, factCharCount));

        return this;
    }
}
