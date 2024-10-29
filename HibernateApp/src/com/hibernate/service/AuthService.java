package com.hibernate.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

import com.hibernate.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class AuthService {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	Map<String, String> map;     // anonymmous block professional
	{
		map = new HashMap<>();
		map.put("admin1", "password1");
		map.put("admin2", "password2");
	}
	public AuthService(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.entityTransaction = entityManager.getTransaction();
	}
	public String checkIfAdmin(Scanner sc) {
		boolean status = false;
		System.out.println("&&&&&&&&&&&&&&&&Bank Login&&&&&&&&&&&&&&&&");
		System.out.print("Enter Username:");
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		
		if(map.containsKey(username)) {
			String mapPassword = map.get(username);
			if(mapPassword.equals(password))
				status = true;
		}
		if(status == true) 
			return username;
		else
			return null;
		
	}
	public boolean checkIfUsernameUnique(String username) {
		entityTransaction.begin();
		String jpql = "Select u from User u where u.username=?1";
		TypedQuery <User> query = entityManager.createQuery(jpql,User.class);
		query.setParameter(1, username);
		List<User> list = query.getResultList();
		entityTransaction.commit();
		if(list != null && !list.isEmpty())
			return false;
		return true;
	}
}


/*  for reference jpql
 * select u.* from user u where u.username=?
 * select u from User u where u.username=?
 * */
