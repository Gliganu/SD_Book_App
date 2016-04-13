package daoLayer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domainLayer.Book;
import domainLayer.OperationResult;
import domainLayer.SearchQuery;
import domainLayer.User;

public interface BooksDAO {

	List<Book> getBooks(SearchQuery searchQuery);

	Book findBook(String id);

	OperationResult deleteBook(String id);

	OperationResult createBook(Book book);
	
	OperationResult createBookAtIndex(Book book, int index);

	OperationResult updateBook(Book book);

	boolean checkIdExists(String id);

	void deleteAllBooks();

}