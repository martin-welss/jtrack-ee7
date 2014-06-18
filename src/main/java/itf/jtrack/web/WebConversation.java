package itf.jtrack.web;

import itf.jtrack.managers.BugManager;
import itf.jtrack.managers.ProductManager;
import itf.jtrack.managers.UserManager;
import itf.jtrack.model.Bug;
import itf.jtrack.model.Product;
import itf.jtrack.model.TrackState;
import itf.jtrack.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="webconversation")
@ConversationScoped
public class WebConversation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int conversation_counter=1;
	private static String buildId=null;
	private int id;
	private User user=new User();
	private Bug bug=new Bug();
	private Bug criteria=new Bug();
	private Product product=new Product();
	@Inject private Conversation conversation; 
	@Inject private BugManager bugman;
	@Inject private UserManager userman;
	@Inject private ProductManager prodman;
	private String successMessage;
	private List<Bug> searchResults;
	
	public WebConversation() {
		id=conversation_counter++;
		log("created");
	}
	
	@PostConstruct
	public void initialize() {
		conversation.begin();
		log("postConstruct()");
		resetSearch();
	}
	
	public void resetSearch() {
		criteria=new Bug();
		criteria.setAssignee(new User());
		criteria.setReporter(new User());
		criteria.setProduct(new Product());
		searchResults=null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBuildId() {
		if(buildId==null) {
			log("reading buildId");
	        try {
	        	InputStream str = getClass().getResourceAsStream("/META-INF/build_id.txt");
	        	BufferedReader br=new BufferedReader(new InputStreamReader(str));
	            buildId= br.readLine();
	        }
	        catch(Exception x) {
	            log("oops."+x);
		        buildId="no build_id";
	        }
		}
		return buildId;
	}

	public String newBug() {
		successMessage="";
		bug=new Bug();
		bug.setReporter(new User());
		bug.setAssignee(new User());
		bug.setProduct(new Product());
		return "editbug";
	}
	
	public String editBug(long bugid) {
		successMessage="";
		bug=bugman.find(bugid);
		return "editbug";
	}
	
	public String saveBug() {
		bug=bugman.save(bug);
		successMessage="Bug saved.";
		return "index";
	}
	
	public TrackState[] getTrackStates() {
		return TrackState.values();
	}
	
	private void log(String text) {
		System.out.println("[WebConversation#"+id+"] "+text);
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
    
	public List<User> getUsers() {
		return userman.findAll();
	}
	
	public String editUser(long userid) {
		successMessage="";
		user=userman.find(userid);
		return "useradmin";
	}
	
	public String newUser() {
		user=new User();
		return "useradmin";
	}
	
	public String saveUser() {
		userman.save(user);
		successMessage="User saved.";
		return "index";
	}

	public List<Product> getProducts() {
		return prodman.findAll();
	}
	
	public String editProduct(long productid) {
		successMessage="";
		product=prodman.find(productid);
		return "productadmin";
	}
	
	public String newProduct() {
		product=new Product();
		return "productadmin";
	}
	
	public String saveProduct() {
		prodman.save(product);
		successMessage="Product saved.";
		return "index";
	}

	public List<Bug> getRecentlyChangedBugs() {
		return bugman.getRecentlyChangedBugs();
	}

	public Bug getCriteria() {
		return criteria;
	}

	public void setCriteria(Bug criteria) {
		this.criteria = criteria;
	}
	
	public List<Bug> search() {
		searchResults=bugman.search(criteria);
		return null;
	}

	public List<Bug> getSearchResults() {
		return searchResults;
	}
}
