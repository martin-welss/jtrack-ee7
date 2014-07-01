package itf.jtrack.managers;

import itf.jtrack.model.Bug;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@Named(value="bugmanager")
@Path("bug")
public class BugManager {

	@PersistenceContext EntityManager em;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Bug save(Bug bug) {
		log("saving bug: "+bug);
		if(bug.getBugid()==null) {
			bug.setCreated(new Date());
		}
		bug.setLastchange(new Date());
		return em.merge(bug);
	}
	
	@GET
	@Produces("application/json")	
	public List<Bug> getRecentlyChangedBugs() {
		return em.createQuery("select b from bugs b order by b.lastchange desc", Bug.class).setMaxResults(10).getResultList();
	}
	
	@GET
	@Produces("application/json")	
	public Bug find(long bugid) {
		return em.find(Bug.class, bugid);
	}
	
	private void log(String text) {
		System.out.println("[BugManager]: "+text);
	}

	@SuppressWarnings("unchecked")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public List<Bug> search(String text) {
		text="%"+text.toLowerCase().trim()+"%";
		Query query=em.createQuery("select b from bugs b where b.title like :text1 or b.description like :text2", Bug.class);
		query.setParameter("text1", text);
		query.setParameter("text2", text);
		return query.getResultList();
	}
	
	
}
