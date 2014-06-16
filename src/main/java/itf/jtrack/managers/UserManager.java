package itf.jtrack.managers;

import java.util.List;

import itf.jtrack.model.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named(value="usermanager")
public class UserManager {

	@PersistenceContext EntityManager em;
	
	public User save(User user) {
		log("saving user: "+user);
		return em.merge(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return em.createQuery("select u from users u").getResultList();
	}
	
	private void log(String text) {
		System.out.println("[UserManager]: "+text);
	}

	
}
