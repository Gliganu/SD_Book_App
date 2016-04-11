package daoLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domainLayer.Book;
import domainLayer.BookBundle;
import domainLayer.OperationResult;
import domainLayer.SearchQuery;
import domainLayer.Status;
import domainLayer.User;
import domainLayer.UserBundle;
import serviceLayer.ServiceUtils;

@Component("booksDAO")
public class BooksDAOXml implements BooksDAO {

	@Autowired
	XMLConverter xmlConverter;

	private final String BOOKS_XML_PATH = "C:\\Users\\GligaBogdan\\Desktop\\xml\\books.xml";

	@Override
	public List<Book> getBooks(SearchQuery searchQuery) {

		BookBundle bookBundle = getBookBundle();
		List<Book> bookList = bookBundle.getBookList();

		String searchedAuthor = searchQuery.getAuthor() == null ? "" : searchQuery.getAuthor();
		String searchedTitle = searchQuery.getTitle() == null ? "" : searchQuery.getTitle();
		String searchedGenre = searchQuery.getGenre() == null ? "" : searchQuery.getGenre();

		List<Book> filteredBooks = bookList
				.stream().filter(book -> book.getAuthor().contains(searchedAuthor)
						&& book.getTitle().contains(searchedTitle) && book.getGenre().contains(searchedGenre))
				.collect(Collectors.toList());
		;

		return filteredBooks;

	}
	
	@Override
	public void deleteAllBooks() {
		BookBundle bookBundle = getBookBundle();
		bookBundle.setBookList(new ArrayList<>());
		writeBookBundle(bookBundle);
		
		
	}
	
	@Override
	public OperationResult deleteBook(String id) {

		BookBundle bookBundle = getBookBundle();
		List<Book> bookList = bookBundle.getBookList();

		Book book = findBook(id);

		boolean removeStatus = bookList.remove(book);

		writeBookBundle(bookBundle);

		if (removeStatus) {
			return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " was deleted.");
		}

		return new OperationResult(Status.FAILURE, "Unknown error occurred.");

	}

	@Override
	public OperationResult createBook(Book book) {

		BookBundle bookBundle = getBookBundle();
		List<Book> bookList = bookBundle.getBookList();

		bookList.add(book);

		writeBookBundle(bookBundle);

		return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " created successfully");
	}
	
	@Override
	public OperationResult updateBook(Book book) {

		deleteBook(book.getId());
		createBook(book);

		return new OperationResult(Status.SUCCESS, "Book " + book.getTitle() + " updated successfully");
	}

	@Override
	public boolean checkIdExists(String id) {

		BookBundle bookBundle = getBookBundle();
		List<Book> bookList = bookBundle.getBookList();

		List<Book> filteredBooks = bookList.stream().filter(book -> book.getId().equals(id))
				.collect(Collectors.toList());

		return filteredBooks.size() != 0;
	}



	@Override
	public Book findBook(String id) {

		BookBundle bookBundle = getBookBundle();
		List<Book> bookList = bookBundle.getBookList();
		
		List<Book> collect = bookList.stream().filter(book -> book.getId() == id).collect(Collectors.toList());

		if (collect.isEmpty()) {
			return null;
		}

		return collect.get(0);
	}

	private BookBundle getBookBundle() {
		try {
			return (BookBundle) xmlConverter.convertFromXMLToObject(BOOKS_XML_PATH);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean writeBookBundle(BookBundle bookBundle) {

		try {
			xmlConverter.convertFromObjectToXML(bookBundle, BOOKS_XML_PATH);
			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}