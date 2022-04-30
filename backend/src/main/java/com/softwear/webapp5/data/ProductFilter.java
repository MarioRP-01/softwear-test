package com.softwear.webapp5.data;

public enum ProductFilter {
    OneByName,
    ;

    public static ProductFilter stringToProductFilter(String filter) {

        return switch (filter) {
            case "one-by-name" -> OneByName;
            default -> null;
        };
    }
}
