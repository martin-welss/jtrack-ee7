package itf.jtrack.managers;

import itf.jtrack.model.Component;

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
	
	private void log(String text) {
		System.out.println("[ComponentManager]: "+text);
	}
	
	
}
