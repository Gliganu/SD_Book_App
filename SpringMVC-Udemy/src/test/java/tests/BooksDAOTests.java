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

	@Before
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
		
		List<Book> randomBooks = ServiceUtils.getRandomBooks(2);
		Book book1 = randomBooks.get(0);
		Book book2 = randomBooks.get(1);
		
		booksDAO.createBook(book1);
		booksDAO.createBook(book2);
		
		assertTrue("Book should exist", booksDAO.checkIdExists(book1.getId()));
		assertTrue("Book should exist", booksDAO.checkIdExists(book2.getId()));

	}
	
	@Test
	public void testUpdate(){
		
		List<Book> randomBooks = ServiceUtils.getRandomBooks(2);
		Book book1 = randomBooks.get(0);
		
		booksDAO.createBook(book1);
		
		book1.setGenre("ggg");
		
		booksDAO.updateBook(book1);
		
		List<Book> retrieved = booksDAO.getBooks(new SearchQuery("","ggg",""));
		
		assertTrue(retrieved.get(0).getGenre().equals("ggg"));
	}

	@Test
	public void testDelete(){
		
		List<Book> randomBooks = ServiceUtils.getRandomBooks(2);
		Book book1 = randomBooks.get(0);
		
		booksDAO.createBook(book1);
		
		booksDAO.deleteBook(book1.getId());
		
		List<Book> bookList= booksDAO.getBooks(new SearchQuery("","",""));
		
		assertEquals("There should be no books",0,bookList.size());
		
	}
}