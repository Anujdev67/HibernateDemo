package com.hibernate.controller;

import java.util.Scanner;
import com.hibernate.model.User;
import com.hibernate.service.PurchaseService;
import com.hibernate.utility.FactoryUtility;
import jakarta.persistence.EntityManager;

public class PurchaseController {
    public static void main(String[] args) {
        EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
        Scanner sc = new Scanner(System.in);
        PurchaseService purchaseService = new PurchaseService(entityManager);

        // login as Customer
        System.out.println("Enter your username:"); //sansu@bogus.com
        String username = sc.nextLine();
        System.out.println("Enter your password:"); //123456
        String password = sc.nextLine();

        User user = purchaseService.loginUser(username, password);

        if (user != null) {
            purchaseService.purchaseProduct(sc, user);
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }

        sc.close();
    }
}
