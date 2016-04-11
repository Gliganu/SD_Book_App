package daoLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domainLayer.Book;
import domainLayer.User;
import domainLayer.UserBundle;

@Component("usersDAO")
public class UsersDAOXml implements UsersDAO {

	@Autowired
	XMLConverter xmlConverter;

	private final String USERS_XML_PATH = "C:\\Users\\GligaBogdan\\Desktop\\xml\\users.xml";

	@Override
	public void saveUser(User user) {

		UserBundle userBundle = getUserBundle();

		List<User> userList = userBundle.getUserList();
		
		if(userList == null){
			userBundle.setUserList(new ArrayList<>());
			userList = userBundle.getUserList();
		}
		userList.add(user);

		writeUserBundle(userBundle);

	}

	@Override
	public void updateUser(User user) {

		deleteUserByUsername(user.getUsername());
		saveUser(user);

	}

	private void deleteUserByUsername(String username) {
		
		UserBundle userBundle = getUserBundle();
		List<User> userList = userBundle.getUserList();
		
		User user = getUser(username);

		userList.remove(user);

		writeUserBundle(userBundle);

	}

	@Override
	public List<User> getAllUsers() {

		UserBundle userBundle = getUserBundle();
		
		List<User> userList = userBundle.getUserList();
		
		return userList == null ? new ArrayList<>() : userList;

	}

	@Override
	public User getUser(String username) {

		UserBundle userBundle = getUserBundle();
		List<User> userList = userBundle.getUserList();

		List<User> matchList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
		
		if(matchList.isEmpty()){
			return null;
		}
		
		return matchList.get(0);

	}

	@Override
	public void deleteUser(User user) {

		UserBundle userBundle = getUserBundle();

		List<User> userList = userBundle.getUserList();

		userList.remove(user);

		writeUserBundle(userBundle);


	}

	@Override
	public boolean exists(String username) {
		UserBundle userBundle = getUserBundle();
		List<User> userList = userBundle.getUserList();

		List<User> matchList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
		
		return matchList.size() != 0;
		
	}

	private UserBundle getUserBundle() {
		try {
			return (UserBundle) xmlConverter.convertFromXMLToObject(USERS_XML_PATH);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private boolean writeUserBundle(UserBundle userBundle) {

		try {
			xmlConverter.convertFromObjectToXML(userBundle, USERS_XML_PATH);
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void deleteAllUsers() {
		UserBundle userBundle = getUserBundle();
		userBundle.setUserList(new ArrayList<>());
		writeUserBundle(userBundle);
		
		
	}

}
