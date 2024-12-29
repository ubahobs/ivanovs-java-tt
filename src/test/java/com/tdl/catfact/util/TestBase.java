package com.tdl.catfact.util;

import com.tdl.util.ConfigUtils;
import org.bson.Document;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class TestBase {

    protected List<Document> testData;

    @BeforeClass
    public void setup () {
        baseURI = ConfigUtils.getConfigProperty("catfact.base.url");

        MongoUtil.connectToDB();
        MongoUtil.insertTestData();
        testData = MongoUtil.getTestData();
    }

    @AfterClass
    public void tearDown () {
        MongoUtil.cleanUp();
    }
}
