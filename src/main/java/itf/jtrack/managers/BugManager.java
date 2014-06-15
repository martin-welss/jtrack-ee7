package itf.jtrack.managers;

import itf.jtrack.model.Bug;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named(value="bugmanager")
public class BugManager {

	@PersistenceContext EntityManager em;
	
	public Bug saveTask(Bug bug) {
		log("saving bug.");
		return em.merge(bug);
	}
	
	private void log(String text) {
		System.out.println("[BugManager]: "+text);
	}
	
	
}
