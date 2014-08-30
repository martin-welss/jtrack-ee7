package itf.jtrack

import itf.jtrack.managers.ProductManager;
import itf.jtrack.model.Product;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.spock.ArquillianSputnik;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import spock.lang.Specification;


@RunWith(ArquillianSputnik.class)
class ArqBeanTest extends Specification {

	@Inject
	ProductManager prodman;
	
	 @Deployment
	 def static JavaArchive "create deployment"() {
		 return ShrinkWrap.create(JavaArchive.class)
				 .addClass(Product.class)
				 .addClass(ProductManager.class)
				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				 .addAsManifestResource("persistence.xml");
	 }
	
	 def "test products"() {
		 println "starting test"
	 	
		 when:
		 	def prodlist=prodman.findAll();
			 println "prodlist.size: ${prodlist.size()}"
			 
		 then:
			assert prodlist.size() > 0

		 println "finish test"
	 }
}
