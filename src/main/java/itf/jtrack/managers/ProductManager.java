package itf.jtrack.managers;

import itf.jtrack.model.Product;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@Named(value="productmanager")
@Path("product")
public class ProductManager {

	@PersistenceContext EntityManager em;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Product save(Product prod) {
		log("saving product: "+prod);
		return em.merge(prod);
	}

	@GET
	@Produces("application/json")
	public List<Product> findAll() {
		return em.createQuery("select p from products p", Product.class).getResultList();
	}
	
	@GET
	@Produces("application/json")
	public Product find(long productid) {
		return em.find(Product.class, productid);
	}
	
	
	
	private void log(String text) {
		System.out.println("[ProductManager]: "+text);
	}
	
	
}
