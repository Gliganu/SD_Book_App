package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daoLayer.UsersDAO;
import domainLayer.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" }) 
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDAOTests {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private DataSource dataSource;
	
	
	User user1 = new User("admin", "test", "Andreea Muresan", 19357621, "Ceahlau 14", "ROLE_ADMIN", true);
	User user2 = new User("admin1", "test", "Andreea Muresan", 19357621, "Ceahlau 14", "ROLE_ADMIN", true);
	User user3 = new User("admin2", "test", "Andreea Muresan", 19357621, "Ceahlau 14", "ROLE_ADMIN", true);
	User user4 = new User("bogdan", "test", "Bogdan Gliga", 19958692, "Giulesti 10", "ROLE_USER", true);
	User user5 = new User("bogdan1", "test", "Bogdan Gliga", 19958692, "Giulesti 10", "ROLE_USER", true);
	User user6 = new User("bogdan2", "test", "Bogdan Gliga", 19958692, "Giulesti 10", "ROLE_USER", true);
	User user7 = new User("bogdan3", "test", "Bogdan Gliga", 19958692, "Giulesti 10", "ROLE_USER", true);


	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	public void testCreateRetrive(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		usersDAO.saveUser(user3);
		usersDAO.saveUser(user4);
		usersDAO.saveUser(user5);
		usersDAO.saveUser(user6);
		usersDAO.saveUser(user7);
		
		List<User> userList2= usersDAO.getAllUsers();
		assertEquals("Four users should have been created and retrieved", 7, userList2.size());
		 
	}
	
	@Test
	public void testExists(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		assertTrue("User should exist", usersDAO.exists(user1.getUsername()));
		assertTrue("User should exist", usersDAO.exists(user2.getUsername()));

	}
	
	@Test
	public void testUpdate(){
		usersDAO.saveUser(user1);
		
		user1.setName("nameee");
		
		usersDAO.updateUser(user1);
		
		User retrieved = usersDAO.getUser(user1.getUsername());
		
		assertTrue(retrieved.getName().equals("nameee"));
	}

	@Test
	public void testDelete(){
		usersDAO.saveUser(user1);
		
		usersDAO.deleteUser(user1);
		List<User> offerList=usersDAO.getAllUsers();
		assertEquals("There should be no users",0,offerList.size());
		
	}
}