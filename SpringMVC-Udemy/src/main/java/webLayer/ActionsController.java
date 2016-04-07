package webLayer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domainLayer.Book;
import domainLayer.OperationResult;
import domainLayer.SearchQuery;
import domainLayer.Status;
import domainLayer.User;
import serviceLayer.BookService;
import serviceLayer.UsersService;

@Controller
public class ActionsController {

	private static String BOOKS_PAGE = "books";
	private static String UPDATE_BOOK_PAGE = "updateBook";
	private static String ALL_USERS_PAGE = "viewAllCustomers";
	private static String UPDATE_USER_INFO_PAGE = "updateUserInfo";

	private String HOME_PAGE = "home";

	@Autowired
	UsersService usersService;

	@Autowired
	BookService booksService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showBooks(Model model, Principal principal) {

		List<Book> books = booksService.getBooks(new SearchQuery());
		
		model.addAttribute("searchQuery",new SearchQuery());
		model.addAttribute("books", books);

		return BOOKS_PAGE;
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String showBooksPost(@Valid SearchQuery searchQuery,BindingResult result, Model model, Principal principal) {

		List<Book> books = booksService.getBooks(searchQuery);
		
		model.addAttribute("searchQuery",searchQuery);
		model.addAttribute("books", books);

		return BOOKS_PAGE;
	}


	@RequestMapping(value = "/sellBook", method = RequestMethod.GET)
	public String sellBook(@RequestParam(value = "id", required = true) int id,
	 Model model, Principal principal) {

		OperationResult result = booksService.sellBook(id);

		if (Status.FAILURE.equals(result.getStatus())) {
			model.addAttribute("errorMsg", result.getMessage());
		} else {
			model.addAttribute("successMsg", result.getMessage());
		}

		return showBooks(model, principal);
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(@RequestParam(value = "id", required = true) int id, Model model, Principal principal) {

		OperationResult result = booksService.deleteBook(id);

		if (Status.FAILURE.equals(result.getStatus())) {
			model.addAttribute("errorMsg", result.getMessage());
		} else {
			model.addAttribute("successMsg", result.getMessage());
		}

		return showBooks(model,principal);
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.GET)
	public String updateBook(@RequestParam(value = "id", required = true) int id, Model model, Principal principal) {

		Book book = booksService.getBookById(id);

		model.addAttribute("book", book);

		return UPDATE_BOOK_PAGE;
	}

	@RequestMapping(value = "/createBook", method = RequestMethod.GET)
	public String createBook(Model model, Principal principal) {

		Book book = new Book();

		model.addAttribute("book", book);

		return UPDATE_BOOK_PAGE;
	}

	@RequestMapping(value = "/createBook", method = RequestMethod.POST)
	public String createBookPost(@Valid Book book, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_BOOK_PAGE;
		} else {

			OperationResult opResult = booksService.createBook(book);
			model.addAttribute("successMsg", opResult.getMessage());

			return HOME_PAGE;
		}
	}

	@RequestMapping(value = "updateBook", method = RequestMethod.POST)
	public String updateBookPost(@Valid Book book, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_BOOK_PAGE;
		} else {

			OperationResult opResult = booksService.updateBook(book);
			model.addAttribute("successMsg", opResult.getMessage());

			return HOME_PAGE;
		}

	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public String updateUserInfoPost(@Valid User finalUser, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_USER_INFO_PAGE;
		} else {
			usersService.updateUser(finalUser);

			model.addAttribute("successMsg", "User " + finalUser.getUsername() + " updated successfully");

			return HOME_PAGE;
		}

	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
	public String updateUserInfo(@RequestParam(value = "username", required = true) String username, Model model) {

		User user = usersService.getUser(username);

		model.addAttribute("finalUser", user);

		return UPDATE_USER_INFO_PAGE;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String showDeleteCustomerPage(@RequestParam("username") String username, Model model, Principal principal) {

		if (username.equals(principal.getName())) {
			model.addAttribute("errorMsg", "Cannot delete your own account");
			return viewAllUsers(model,principal);
		}
		

		usersService.deleteUser(username);

		model.addAttribute("successMsg", "User " + username + " deleted successfully");

		return viewAllUsers(model,principal);
	}

	@RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
	public String viewAllUsers(Model model, Principal principal) {

		List<User> allUsers = usersService.getAllUsers();

		model.addAttribute("allUsers", allUsers);

		return ALL_USERS_PAGE;
	}

}
