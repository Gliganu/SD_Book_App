package serviceLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daoLayer.BooksDAO;
import daoLayer.UsersDAO;
import domainLayer.Book;
import domainLayer.OperationResult;
import domainLayer.SearchQuery;
import domainLayer.Status;

@Service("booksService")
public class BookService {

	@Autowired
	private BooksDAO booksDAO;

	public List<Book> getBooks(SearchQuery searchQuery) {

		return booksDAO.getBooks(searchQuery);

	}

	public Book findBook(String id) {

		return booksDAO.findBook(id);

	}

	public OperationResult sellBook(String id) {

		Book book = findBook(id);

		if (book.getQuantity() == 0) {
			return new OperationResult(Status.FAILURE, "Can't sell " + book.getTitle() + ". Out of stock");
		}

		book.setQuantity(book.getQuantity() - 1);
			
		updateBook(book);
		
		return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " was sold.");

	}

	public OperationResult deleteBook(String id) {

		return booksDAO.deleteBook(id);

	}
	
	public OperationResult createBook(Book book){
		return booksDAO.createBook(book);	}

	
	public OperationResult updateBook(Book book) {

		return booksDAO.updateBook(book);
				
	}
	
	public boolean checkIdExists(String id){
		return booksDAO.checkIdExists(id);
	}
	
	public ArrayList<Book> getOutOfStockBooks(){
		
		List<Book> bookList = getBooks(new SearchQuery());
		
		List<Book> collect = bookList.stream().filter(book->book.getQuantity() == 0).collect(Collectors.toList());
		
		return (ArrayList<Book>) collect;
		
	}


}
