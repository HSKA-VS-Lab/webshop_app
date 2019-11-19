package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiRole;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiUser;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Role;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {
	
	ConsumeApiRole apiRole = new ConsumeApiRole();
	ConsumeApiUser apiUser = new ConsumeApiUser();
	
	public void registerUser(String username, String name, String lastname, String password, Role role) {
		apiUser.addUser(name, lastname, username, password);
	}

	
	public User getUserByUsername(String username) {
		return apiUser.getUser(username);
	}

	public boolean deleteUserById(int id) {
		apiUser.deleteUser(id);
		return true;
	}

	public Role getRoleByLevel(int level) {
		Role[] roles = apiRole.getAllRoles();
		for (Role role : roles) {
			if (role.getLevel() == level) {
				return role;
			}
		}
		return null;
	}

	public boolean doesUserAlreadyExist(String username) {
		User user = apiUser.getUser(username);
		if ( user != null) {
			return false;
		}
		return true;
	}
	

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		return true;
	}

}
