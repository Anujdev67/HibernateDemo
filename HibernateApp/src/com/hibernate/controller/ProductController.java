package com.hibernate.controller;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.hibernate.exception.InvalidIdException;
import com.hibernate.model.Category;
import com.hibernate.model.Product;
import com.hibernate.model.Vendor;
import com.hibernate.service.ProductService;
import com.hibernate.utility.FactoryUtility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductController {
	
	public static void main(String[] args) {
		EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
//		SessionFactory sessionFactory =(SessionFactory)
//		Persistence.createEntityManagerFactory("myecomapp");
//		
//		EntityManager entityManager = sessionFactory.createEntityManager();
//		
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		System.out.println(sessionFactory);
		Scanner sc = new Scanner(System.in);
		
		ProductService productService = new ProductService(entityManager,entityManager.getTransaction());
	while(true) {
		System.out.println("------------Product Menu---------------");
		System.out.println("1. Enter product in DB");
		System.out.println("2.Fetch All products");
		System.out.println("3.Delete  Products");
		System.out.println("4.Update Product Details");
		System.out.println("5.Enter Vendor in Db");
		 System.out.println("6. Enter Category in Db"); 
		System.out.println("0.Exit");
		int input = sc.nextInt();
		if(input == 0) {
			System.out.println("Exiting.. thank you");
			break;
		}
		switch(input) {
		case 1:
			Product product = productService.takeInput(sc);
			if (product == null) {
			    System.out.println("Product creation failed due to invalid input.");
			    break;
			}

			productService.insert(product);
			System.out.println("Product Added to Db..");
			break;
		case 2:
			List<Product> list = productService.getAll();
			list.stream().forEach(System.out :: println);
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
		case 4:
			try {
				product = productService.getById(sc);
				System.out.println("Current Product Details");
				System.out.println(product);
				System.out.println("Enter Title("+ product.getTitle() + ") "+
				"Press Y to retain current value else type new Value: ");
				sc.nextLine();
				String title = sc.nextLine();
				if(!title.equals("Y"))
					product.setTitle(title);
				System.out.println("Enter Description(Press Y to retain current value else type new value)");
				String description = sc.nextLine();
				if(!description.equals("Y"))
					product.setDescription(description);
				System.out.println("Enter Price("+ product.getPrice() + ") "+
						"Press Y to retain current value else type new Value: ");
				String priceStr = sc.next();
				if(!priceStr.equals("Y"))
					product.setPrice(Double.parseDouble(priceStr));
				productService.insert(product);
				System.out.println("Product details Updated");
			}catch(InvalidIdException e) {
				System.out.println(e.getMessage());
			}
			break;

        case 5:  // New case for vendor input
            Vendor vendor = productService.takeVendorInput(sc);
            productService.saveVendor(vendor);
            System.out.println("Vendor Added to Db..");
            break;
        case 6:  // New case for vendor input
            Category category = productService.takeCategoryInput(sc);
            productService.saveCategory(category);
            System.out.println("Category Added to Db..");
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
