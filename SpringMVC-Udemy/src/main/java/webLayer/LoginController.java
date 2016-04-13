package webLayer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domainLayer.User;
import serviceLayer.UsersService;

@Controller
public class LoginController {

	private static String LOGIN_PAGE = "loginForm";
	private static String CREATE_NEW_ACCOUNT_PAGE = "newAccountForm";
	private static String LOG_OUT_PAGE = "logOut";
	private static String ACCESS_DENIED_PAGE = "accessDenied";

	@Autowired
	private UsersService usersService;

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value = "/login")
	public String showLoginPage() {
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/logOut")
	public String showLogOutPage() {
		return LOG_OUT_PAGE;
	}

	@RequestMapping(value = "/accessDenied")
	public String showAccessDeniedPage() {
		return ACCESS_DENIED_PAGE;
	}

	@RequestMapping(value = "/newAccount")
	public String showNewAccountPage(Model model) {
			
		List<String> authorities = new ArrayList<>();
		authorities.add("ROLE_USER");
		authorities.add("ROLE_ADMIN");
		model.addAttribute("authorities", authorities);
		
		model.addAttribute("user", new User());
		return CREATE_NEW_ACCOUNT_PAGE;

	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			List<String> authorities = new ArrayList<>();
			authorities.add("ROLE_USER");
			authorities.add("ROLE_ADMIN");
			model.addAttribute("authorities", authorities);
			return CREATE_NEW_ACCOUNT_PAGE;
		} else {

			user.setEnabled(true);

			if (usersService.exists(user.getUsername())) {
				result.rejectValue("username", "DuplicateKey.user.username");
				return CREATE_NEW_ACCOUNT_PAGE;
			}

			usersService.saveUser(user);

			return Utils.showMessagePage(model, "Account Created");
		}

	}

}
