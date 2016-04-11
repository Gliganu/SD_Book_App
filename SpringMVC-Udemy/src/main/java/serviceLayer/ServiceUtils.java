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
	
	public static String getRandomISBN(){
		
		StringBuffer buffer = new StringBuffer();
		
		Random random = new Random();
		
		for(int i=0;i<13;i++){
			buffer.append(random.nextInt(10));
		}
		
		return buffer.toString();
		
	}
	
}
