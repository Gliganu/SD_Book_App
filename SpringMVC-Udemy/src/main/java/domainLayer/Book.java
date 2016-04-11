package domainLayer;

import java.io.BufferedWriter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
//@XmlRootElement( name = "book" )
public class Book {

	@Id
	@NotEmpty
	@Size(min=13,max=13)
	@Pattern(regexp="^(0|[1-9][0-9]*)$")
//	@XmlElement(name = "id")
	private String id;

	
	@NotEmpty
//	@XmlElement(name = "title")
	private String title;

	@NotEmpty
//	@XmlElement(name = "genre")
	private String genre;

	@NotEmpty
//	@XmlElement(name = "author")
	private String author;

	@NotNull
	@Min(0)
//	@XmlElement(name = "quantity")
	private int quantity;

	@NotNull
	@Min(0)
//	@XmlElement(name = "price")
	private int price;

	public Book() {

	}

	public Book(String id, String title, String genre, String author, int quantity, int price) {
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		return builder.append("Id: ").append(id).append(" Title: ").append(title).append(" Genre: ").append(genre).append(" Author: ").append(author).
				append(" Quantity: ").append(quantity).append(" Price: ").append(price).toString();
		
	}
}
