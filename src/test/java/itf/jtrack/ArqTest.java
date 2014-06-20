package itf.jtrack;

import java.util.List;

import javax.inject.Inject;

import itf.jtrack.managers.ProductManager;
import itf.jtrack.model.Product;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArqTest {

	@Inject
	ProductManager prodman;
	
	 @Deployment
	 public static JavaArchive createDeployment() {
		 return ShrinkWrap.create(JavaArchive.class)
				 .addClass(Product.class)
				 .addClass(ProductManager.class)
				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		 		 .addAsManifestResource("persistence.xml");
	 }
	
	
	@Test
	public void firstTest() {
		System.out.println("starting tests");
		List<Product> prodlist=prodman.findAll();
		Assert.assertTrue(prodlist.size()>0);
		System.out.println("finish tests");
	}
	
	
}
