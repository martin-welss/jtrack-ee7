package itf.jtrack.managers;

import java.util.List;

import itf.jtrack.model.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Named(value="usermanager")
@Path("user")
public class UserManager {

	@PersistenceContext EntityManager em;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public User save(User user) {
		log("saving user: "+user);
		return em.merge(user);
	}

	@GET
	@Produces("application/json")
	public List<User> findAll() {
		return em.createQuery("select u from users u", User.class).getResultList();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public User find(@PathParam("id") long userid) {
		return em.find(User.class, userid);
	}
	
	private void log(String text) {
		System.out.println("[UserManager]: "+text);
	}

	
}
