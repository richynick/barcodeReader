package org.example;

import java.io.IOException;

import static org.example.BarCode.getDiscountedPrice;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

//        String barcode = "%20"; // Example barcode
//        int discountedPrice = getDiscountedPrice();
//        if (discountedPrice != -1) {
//            System.out.println("Discounted Price: " + discountedPrice);
//        } else {
//            System.out.println("Barcode not found.");
//        }
        getDiscountedPrice();
    }
}