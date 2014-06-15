package itf.jtrack.managers;

import java.util.Date;

import itf.jtrack.model.Bug;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named(value="bugmanager")
public class BugManager {

	@PersistenceContext EntityManager em;
	
	public Bug save(Bug bug) {
		log("saving bug: "+bug);
		if(bug.getBugid()==null) {
			bug.setCreated(new Date());
		}
		bug.setLastchange(new Date());
		return em.merge(bug);
	}
	
	private void log(String text) {
		System.out.println("[BugManager]: "+text);
	}
	
	
}
