package domainLayer;

public class Book {

	private int id;
	private String title;
	private String genre;
	private String author;
	private int quantity;
	private int price;
	
	private static int idGenerator = 1;

	public Book(){
		
	}
	
	public Book(String title, String genre, String author, int quantity, int price) {
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
		this.id = idGenerator;
		idGenerator++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	
	
	
	
	
}
