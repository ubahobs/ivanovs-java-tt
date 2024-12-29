package com.tdl.catfact.tests;

import com.tdl.catfact.Service;
import com.tdl.catfact.util.TestBase;
import org.bson.Document;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class CatfactApiTest extends TestBase {

    @DataProvider
    public Object[][] catFactDataProvider() {
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "catFactDataProvider")
    public void catfactApiTest (Document doc) {
        Service
            .getFactService(baseURI)
            .getFact()
            .compareFacts(doc);
    }
}
