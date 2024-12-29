package com.tdl.util;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class AllureUtil {

    @Step("{message}")
    public static void logStepIntoReport(String message) {
        Allure.addAttachment("Step Log", message);
    }
}
