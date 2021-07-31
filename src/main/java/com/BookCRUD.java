package com;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.BookBean;

public class BookCRUD {
	public static void main(String[] args) {
		int choice = -1;
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = null;
		String name, author;
		int price, bookId;
		Scanner scr = new Scanner(System.in);
		Transaction tx = null;
		while (true) {
			System.out.println(
					"\n0 for Exit\n1 For AddBook\n2 For List All Books\n3 For Delete Book By Id\n4 For Get Book Detail By Id");
			System.out.println("\n5 For Update book\n6 for SearchBook By Name\nEnter Choice");
			choice = scr.nextInt();

			switch (choice) {
			case 0:
				System.exit(0);
				break;
			case 1:
				System.out.println("\nInput: BookName , AuthorName and Price");
				name = scr.next();
				author = scr.next();
				price = scr.nextInt();

				BookBean book = new BookBean();
				book.setName(name);
				book.setAuthor(author);
				book.setPrice(price);

				session = sf.openSession();
				tx = session.beginTransaction();
				try {
					session.save(book);
					tx.commit();
				} catch (Exception e) {
					//
					tx.rollback();
				} finally {
					session.close();
				}
				break;
			case 2:
				session = sf.openSession();
				List<BookBean> books = session.createQuery("from BookBean", BookBean.class).getResultList();

				for (BookBean b : books) {
					System.out.println(b.getBookId() + " " + b.getName() + " " + b.getAuthor() + " " + b.getPrice());
				}

				session.close();
				break;
				
				
			case 3:
					System.out.println("Enter Book Id\n");
					bookId=scr.nextInt();
					session=sf.openSession();
					tx=session.beginTransaction();
					book=session.get(BookBean.class, bookId);
					session.delete(book);
					tx.commit();
					
					break;
			case 4:
				System.out.println("Enter BookId ");
				bookId = scr.nextInt();
				session = sf.openSession();
				book = session.get(BookBean.class, bookId);
				System.out.println(
						book.getBookId() + " " + book.getName() + " " + book.getAuthor() + " " + book.getPrice());
				break;
				
			case 5: 
				System.out.println("Update\n");
				System.out.println("Enter BookId you wan tto update\n");
				bookId=scr.nextInt();
				session=sf.openSession();
				book=session.get(BookBean.class, bookId);
				System.out.println("old Book Name"+book.getName());
				System.out.println("Enter The new Name of Book You wan to  update press 1 for yes");
				int ch=scr.nextInt();
				
				if(ch == 1)
				{
					System.out.println("Enter new Book name");
					String BookName=scr.next();
					book.setName(BookName);
				}
				
				System.out.println("old Book Author "+book.getAuthor());
				System.out.println("Enter New Author Name  update press 1 for yes");
				 ch=scr.nextInt();
				
				if(ch == 1)
				{
					System.out.println("Enter new Author name");
					String BookAuthor=scr.next();
					book.setAuthor(BookAuthor);
				}
				
				
					break;
					
			case 6:
					
				break;
			default:
				System.out.println("\nInvalid choice try again!!!");
				break;
			}
		}

	}

}
