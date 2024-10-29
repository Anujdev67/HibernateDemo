package com.hibernate.service;

import java.time.LocalDate;
import java.util.Scanner;
import com.hibernate.model.Customer;
import com.hibernate.model.Product;
import com.hibernate.model.ProductCustomer;
import com.hibernate.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

public class PurchaseService {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public PurchaseService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = entityManager.getTransaction();
    }

    public User loginUser(String username, String password) {
        try {
            User user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        } catch (NoResultException e) {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public void purchaseProduct(Scanner sc, User user) {
        try {
            //fetch customer details using user
            Customer customer = (Customer) entityManager.createQuery("SELECT c FROM Customer c WHERE c.user.id = :userId")
                    .setParameter("userId", user.getId())
                    .getSingleResult();

            // fetch_product detail
            System.out.println("Enter Product ID:");
            int productId = sc.nextInt();
            System.out.println("Enter Quantity:");
            int quantity = sc.nextInt();

            Product product = entityManager.find(Product.class, productId);
            if (product == null) {
                System.out.println("Invalid Product ID.");
                return;
            }

            //create and persist ProductCustomer relationship
            ProductCustomer productCustomer = new ProductCustomer();
            productCustomer.setProduct(product);
            productCustomer.setCustomer(customer);
            productCustomer.setQty(quantity);
            productCustomer.setPurchaseDate(LocalDate.now());

            entityTransaction.begin();
            entityManager.persist(productCustomer);
            entityTransaction.commit();

            System.out.println("Purchase completed successfully.");
        } catch (NoResultException e) {
            System.out.println("No matching customer found for the given user.");
        }
    }
}
