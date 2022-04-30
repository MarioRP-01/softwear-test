package com.softwear.webapp5.data;

public enum TransactionFilter {
    
    STATICS;

    public static TransactionFilter stringToProductFilter(String filter) {

        return switch (filter) {
            case "statics" -> STATICS;
            default -> null;
        };
    }
}
