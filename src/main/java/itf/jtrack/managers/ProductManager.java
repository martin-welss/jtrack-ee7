package itf.jtrack.managers;

import itf.jtrack.model.Product;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named(value="productmanager")
public class ProductManager {

	@PersistenceContext EntityManager em;
	
	public Product save(Product prod) {
		log("saving product: "+prod);
		return em.merge(prod);
	}

	public List<Product> findAll() {
		return em.createQuery("select p from products p", Product.class).getResultList();
	}
	
	public Product find(long productid) {
		return em.find(Product.class, productid);
	}
	
	
	
	private void log(String text) {
		System.out.println("[ProductManager]: "+text);
	}
	
	
}
