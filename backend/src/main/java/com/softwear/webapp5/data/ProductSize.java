package com.softwear.webapp5.data;

public enum ProductSize {
    XS,
    S,
    M,
    L,
    XL;

    public static ProductSize stringToProductSize(String size) {
        size = size.toUpperCase();
        return switch (size) {
            case "XS" -> XS;
            case "S" -> S;
            case "M" -> M;
            case "L" -> L;
            case "XL" -> XL;
            default -> null;
        };
    }
}
