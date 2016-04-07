package serviceLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import domainLayer.Book;
import domainLayer.OperationResult;
import domainLayer.SearchQuery;
import domainLayer.Status;

@Service("booksService")
public class BookService {

	private List<Book> bookList;

	public List<Book> getBooks(SearchQuery searchQuery) {

		if (bookList == null) {
			bookList = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				bookList.add(new Book("Title " + i, "Genre " + i, "Author " + i, i, i * 10));
			}
		}
		
		String searchedAuthor = searchQuery.getAuthor() == null ? "" : searchQuery.getAuthor();
		String searchedTitle = searchQuery.getTitle() == null ? "" : searchQuery.getTitle();
		String searchedGenre = searchQuery.getGenre() == null ? "" : searchQuery.getGenre() ;
		
		List<Book> filteredBooks = bookList.stream().filter(book->book.getAuthor().contains(searchedAuthor) && 
						book.getTitle().contains(searchedTitle) && book.getGenre().contains(searchedGenre)).collect(Collectors.toList());;

		return filteredBooks;

	}

	public Book findBook(List<Book> bookList, int id) {

		for (Book book : bookList) {
			if (book.getId() == id) {
				return book;
			}
		}

		return null;

	}

	public OperationResult sellBook(int id) {

		Book book = findBook(bookList, id);

		if (book.getQuantity() == 0) {
			return new OperationResult(Status.FAILURE, "Can't sell " + book.getTitle() + ". Out of stock");
		}

		book.setQuantity(book.getQuantity() - 1);

		return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " was sold.");

	}

	public OperationResult deleteBook(int id) {

		Book book = findBook(bookList, id);
		
		boolean removeStatus = bookList.remove(book);
		
		if(removeStatus){
			return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " was deleted.");
		}
		
		return new OperationResult(Status.FAILURE, "Unknown error occurred.");

	}

	public Book getBookById(int id) {
		
		return findBook(bookList, id);
	}
	
	public OperationResult createBook(Book book){
		bookList.add(book);
		
		return new OperationResult(Status.SUCCESS,"Book "+book.getTitle()+" created successfully");
	}

	public OperationResult updateBook(Book book) {

		deleteBook(book.getId());
		createBook(book);
		
		return new OperationResult(Status.SUCCESS,"Book "+book.getTitle()+" updated successfully");
	}


}
