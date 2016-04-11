package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daoLayer.BooksDAO;
import daoLayer.UsersDAO;
import domainLayer.Book;
import domainLayer.SearchQuery;
import domainLayer.User;
import serviceLayer.ServiceUtils;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" }) 
@RunWith(SpringJUnit4ClassRunner.class)
public class BooksDAOTests {

	@Autowired
	private BooksDAO booksDAO;

//	@Before
	public void init() {
		booksDAO.deleteAllBooks();
	}

	@Test
	public void testCreateRetrive(){
		
		List<Book> randomBooks = ServiceUtils.getRandomBooks(10);
		
		for(Book book: randomBooks){
			booksDAO.createBook(book);
		}
		
		
		List<Book> bookList= booksDAO.getBooks(new SearchQuery());
		assertEquals("Ten users should have been created and retrieved", 10, bookList.size());
	}
	
	@Test
	public void testExists(){
//		usersDAO.saveUser(user1);
//		usersDAO.saveUser(user2);
//		
//		assertTrue("User should exist", usersDAO.exists(user1.getUsername()));
//		assertTrue("User should exist", usersDAO.exists(user2.getUsername()));

	}
	
	@Test
	public void testUpdate(){
//		usersDAO.saveUser(user1);
//		
//		user1.setName("nameee");
//		
//		usersDAO.updateUser(user1);
//		
//		User retrieved = usersDAO.getUser(user1.getUsername());
//		
//		assertTrue(retrieved.getName().equals("nameee"));
	}

	@Test
	public void testDelete(){
//		usersDAO.saveUser(user1);
//		
//		usersDAO.deleteUser(user1);
//		List<User> offerList=usersDAO.getAllUsers();
//		assertEquals("There should be no users",0,offerList.size());
		
	}
}