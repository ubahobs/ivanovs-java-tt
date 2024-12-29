package com.tdl.catfact;

import com.tdl.catfact.services.FactService;

public class Service {
    public static FactService getFactService(String baseUri) {
        return new FactService(baseUri);
    }
}
