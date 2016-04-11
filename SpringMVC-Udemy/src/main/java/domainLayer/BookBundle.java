package domainLayer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BookBundle {

	@XmlElement( name = "book" )
	private List<Book> bookList;

	public BookBundle() {
		
	}
	
	public BookBundle(List<Book> bookList) {
		this.bookList = bookList;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}
