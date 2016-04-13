package serviceLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domainLayer.Book;

public class ServiceUtils {

	
	public static List<Book> getRandomBooks(int number){
		
		List<Book> bookList = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			bookList.add(new Book(getRandomISBN(),"Title " + i, "Genre " + i, "Author " + i, i, i * 10));
		}
		
		return bookList;
		
	}
	
	public static List<Book> getInitBooks(){
	
		Book book1 = new Book(getRandomISBN(),"Amadis de Gaula", "Romance", "Garci Rodriguez De Montalvo ", 10, 59); 
		Book book2 = new Book(getRandomISBN(),"Gargantua si Pantagruel ", "Fiction", "Francois Rabelais", 12, 359); 
		Book book3 = new Book(getRandomISBN(),"Lusiada ", "SF", "Luis Vaz De Camoes ", 130, 54); 
		Book book4 = new Book(getRandomISBN(),"Tirant Blanc", "Western", "Joanot Martorell ", 140, 34); 
		Book book5 = new Book(getRandomISBN(),"Magarul de aur", "Fiction", "Lucius Apuleius", 0, 6); 
		Book book6 = new Book(getRandomISBN(),"Aventurosul Simplicissimus", "Fiction", "Lucius Apuleius", 0, 432); 
		Book book7 = new Book(getRandomISBN(),"O propunere modesta", "Romance", "Jonathan Swift ", 0, 32); 
		Book book8 = new Book(getRandomISBN(),"O propunere mare", "Romance", "Jonathan Swift ", 0, 32); 
		Book book9 = new Book(getRandomISBN(),"O propunere si mai mare", "Romance", "Jonathan Swift ", 0, 32); 
		Book book10 = new Book(getRandomISBN(),"Fanny Hill ", "SF", "John Cleland", 13, 456); 
		Book book11 = new Book(getRandomISBN(),"Don Quijote Feminin ", "Romance", "Garci Rodriguez De Montalvo", 16, 66); 
		Book book12 = new Book(getRandomISBN(),"Rasselas", "SF", "Samuel Johnson", 15, 345); 
		
		List<Book> bookList = new ArrayList<>();
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		bookList.add(book6);
		bookList.add(book7);
		bookList.add(book8);
		bookList.add(book9);
		bookList.add(book10);
		bookList.add(book11);
		bookList.add(book12);
		
		
		return bookList;
		
	}
	
	public static String getRandomISBN(){
		
		StringBuffer buffer = new StringBuffer();
		
		Random random = new Random();
		
		for(int i=0;i<13;i++){
			buffer.append(random.nextInt(10));
		}
		
		return buffer.toString();
		
	}
	
	public static String getRandomCNP(){
		
		StringBuffer buffer = new StringBuffer();
		
		Random random = new Random();
		
		buffer.append(random.nextInt(2)+1);
		buffer.append("9");
		buffer.append(random.nextInt(50)+50);
		
		for(int i=0;i<9;i++){
			buffer.append(random.nextInt(10));
		}
		
		return buffer.toString();
		
	}
	
}
