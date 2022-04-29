package com.softwear.webapp5.data;

public enum ProductFilterToId {

    SIZE;

    public static ProductFilterToId stringToProductFilter(String filter) {

        return switch (filter) {
            case "size" -> SIZE;
            default -> null;
        };
    }
}


