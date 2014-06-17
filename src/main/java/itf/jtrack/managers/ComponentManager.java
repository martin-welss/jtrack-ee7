package itf.jtrack.managers;

import itf.jtrack.model.Component;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named(value="componentmanager")
public class ComponentManager {

	@PersistenceContext EntityManager em;
	
	public Component save(Component comp) {
		log("saving component: "+comp);
		return em.merge(comp);
	}

	@SuppressWarnings("unchecked")
	public List<Component> findAll() {
		return em.createQuery("select c from components c").getResultList();
	}
	
	public Component find(long componentid) {
		return em.find(Component.class, componentid);
	}
	
	
	
	private void log(String text) {
		System.out.println("[ComponentManager]: "+text);
	}
	
	
}
