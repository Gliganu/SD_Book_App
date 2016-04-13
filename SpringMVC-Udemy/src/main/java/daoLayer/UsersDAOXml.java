package daoLayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import domainLayer.Book;
import domainLayer.User;
import domainLayer.UserBundle;

@Component("usersDAO")
public class UsersDAOXml implements UsersDAO {


	private final String USERS_XML_PATHS_XML_PATH = "\\users.xml";

	@Value("${xml.rootDir}")
	private String[] rootDir;

	@Override
	public void saveUser(User user) {

		UserBundle userBundle = getUserBundle();

		List<User> userList = userBundle.getUserList();

		if (userList == null) {
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

		List<User> matchList = userList.stream().filter(user -> user.getUsername().equals(username))
				.collect(Collectors.toList());

		if (matchList.isEmpty()) {
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

		List<User> matchList = userList.stream().filter(user -> user.getUsername().equals(username))
				.collect(Collectors.toList());

		return matchList.size() != 0;

	}

	private UserBundle getUserBundle() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserBundle.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (UserBundle) jaxbUnmarshaller.unmarshal(new File(rootDir[0]+USERS_XML_PATHS_XML_PATH));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private boolean writeUserBundle(UserBundle userBundle) {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(UserBundle.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the employees list in file
			jaxbMarshaller.marshal(userBundle, new File(rootDir[0]+USERS_XML_PATHS_XML_PATH));

			return true;

		} catch (JAXBException e) {
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
