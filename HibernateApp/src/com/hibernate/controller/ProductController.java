package com.hibernate.controller;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.hibernate.exception.InvalidIdException;
import com.hibernate.model.Product;
import com.hibernate.service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductController {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory =(SessionFactory)
		Persistence.createEntityManagerFactory("myecomapp");
		
		EntityManager entityManager = sessionFactory.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
//		System.out.println(sessionFactory);
		Scanner sc = new Scanner(System.in);
		
		ProductService productService = new ProductService(entityManager,entityTransaction);
	while(true) {
		System.out.println("------------Product Menu---------------");
		System.out.println("1. Enter product in DB");
		System.out.println("2.Fetch All products");
		System.out.println("3.Delete  Products");
		System.out.println("Update Product Details");
		System.out.println("0.Exit");
		int input = sc.nextInt();
		if(input == 0) {
			System.out.println("Exiting.. thank you");
			break;
		}
		switch(input) {
		case 1:
			Product product = productService.takeInput(sc);
			productService.insert(product);
			System.out.println("Product Added to Db..");
			break;
		
		case 3:
			try {
				product = productService.getById(sc);
				productService.delete(product);
				System.out.println("product deleted from db..");
			}catch(InvalidIdException e) {
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Invalid Input,Try Again!");
			break;
		}
		System.out.println("----------------------------------------");
	}
	sc.close();
	}

	
	
}
