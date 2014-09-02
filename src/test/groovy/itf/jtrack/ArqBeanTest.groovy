package itf.jtrack

import itf.jtrack.managers.BugManager;
import itf.jtrack.managers.ProductManager;
import itf.jtrack.managers.UserManager;
import itf.jtrack.model.Bug;
import itf.jtrack.model.Product;
import itf.jtrack.model.TrackState;
import itf.jtrack.model.User;

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
	
	@Inject
	BugManager bugman;
	
	 @Deployment
	 def static JavaArchive "create deployment"() {
		 return ShrinkWrap.create(JavaArchive.class)
				 .addClass(Product.class)
				 .addClass(ProductManager.class)
				 .addClass(Bug.class)
				 .addClass(BugManager.class)
				 .addClass(User.class)
				 .addClass(UserManager.class)
				 .addClass(TrackState.class)
				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				 .addAsManifestResource("persistence.xml");
	 }
	
	 def "test products"() {
		 println "starting product test"
	 	
		 when:
		 	def prodlist=prodman.findAll();
			 println "prodlist.size: ${prodlist.size()}"
			 
		 then:
			assert prodlist.size() > 0

		 println "finish test"
	 }
	 
	 def "test writing all trackstates"() {
		 	 
		 when:
		 	println "tstate: $tstate"
			def bug=bugman.find(1)		 
		 	bug.state = tstate
	 		def outbug=bugman.save(bug)
			 
	     then:
		 	outbug.state == tstate
			 
		 where:
			tstate << TrackState
			// tstate << [ TrackState.NEW, TrackState.ASSIGNED, ... ]
	 }
}
