package itf.jtrack.managers;

import java.util.Date;
import java.util.List;

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
	
	public List<Bug> getRecentlyChangedBugs() {
		return em.createQuery("select b from bugs b order by b.lastchange desc", Bug.class).setMaxResults(10).getResultList();
	}
	
	public Bug find(long bugid) {
		return em.find(Bug.class, bugid);
	}
	
	private void log(String text) {
		System.out.println("[BugManager]: "+text);
	}
	
	
}
