package com.simplehomeinsurance.claims_management_system.dao;

import org.hibernate.query.Query;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.User;

@Repository
public class UserDAO {
	private EntityManager entityManager;
	
	public UserDAO(EntityManager em) {
		entityManager = em;
	}

	public User getUser(int userId) {		
		Session currentSession = entityManager.unwrap(Session.class);		
		return currentSession.get(User.class, userId);
	}
	
	public User getUserbyUsername(String username) {		
		Session currentSession = entityManager.unwrap(Session.class);		
		Query<User> query = currentSession.createQuery("from User where username = '"
															+ username +"'", User.class);	
		return query.getSingleResult();
	}	
}
