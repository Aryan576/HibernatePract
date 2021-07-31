package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Users;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

		/*
		 * System.out.println("Hello World!");
		 * 
		 * Configuration configuration= new
		 * Configuration().configure("hibernate.cfg.xml");
		 * System.out.println(configuration); SessionFactory sessionFactory =
		 * configuration.buildSessionFactory(); System.out.println(sessionFactory);
		 * Session session= sessionFactory.openSession(); System.out.println(session);
		 */
    	
    	SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();

		Users user = new Users();

		user.setFirstname("ram2");
		user.setEmail("ram1@gmail.com");
		user.setLastname("Shreeram");
		
		Transaction tx = session.beginTransaction();
		session.save(user);// insert into
		tx.commit();
		session.close();
    }
}
