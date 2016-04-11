package serviceLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import daoLayer.UserDetailsImpl;
import daoLayer.UsersDAO;
import domainLayer.User;

@Service("usersService")
public class UsersService implements UserDetailsService {

	@Autowired
	private UsersDAO usersDao;
	

	
	public void saveUser(User user){
		usersDao.saveUser(user);
	}
	
	public void updateUser(User user){
		usersDao.updateUser(user);
	}

	public void deleteUser(String username){
		User user = getUser(username);
	
		usersDao.deleteUser(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}


	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	
	public User getUser(String username){
		return usersDao.getUser(username);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		User user = getUser(username);
		
		if(user == null){
			throw new UsernameNotFoundException(username);
		}
		
		return new UserDetailsImpl(user);
	}

}
