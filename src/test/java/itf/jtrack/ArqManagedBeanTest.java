package itf.jtrack;

import itf.jtrack.managers.BugManager;
import itf.jtrack.managers.ProductManager;
import itf.jtrack.managers.UserManager;
import itf.jtrack.model.Bug;
import itf.jtrack.model.Product;
import itf.jtrack.model.TrackState;
import itf.jtrack.model.User;
import itf.jtrack.web.WebConversation;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArqManagedBeanTest {

	@Inject
	WebConversation wconv;
	
	 @Deployment
	 public static JavaArchive createDeployment() {
		 return ShrinkWrap.create(JavaArchive.class)
				 .addClass(Product.class)
				 .addClass(ProductManager.class)
				 .addClass(Bug.class)
				 .addClass(BugManager.class)
				 .addClass(User.class)
				 .addClass(UserManager.class)
				 .addClass(TrackState.class)
				 .addClass(WebConversation.class)
				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		 		 .addAsManifestResource("persistence.xml");
	 }
	
	
	@Test
	public void firstTest() {
		System.out.println("starting tests");
		wconv.resetSearch();
		Assert.assertTrue(wconv.getSearchResults()==null);
		System.out.println("finish tests");
	}
	
	
}
