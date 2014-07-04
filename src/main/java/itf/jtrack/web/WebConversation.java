package itf.jtrack.web;

import itf.jtrack.managers.BugManager;
import itf.jtrack.managers.ProductManager;
import itf.jtrack.managers.UserManager;
import itf.jtrack.model.Bug;
import itf.jtrack.model.Product;
import itf.jtrack.model.TrackState;
import itf.jtrack.model.User;

import java.io.Serializable;
import java.util.ArrayList;
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
	private int id;
	private User user=new User();
	private Bug bug=new Bug();
	private String pattern="";
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
		pattern="";
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

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public List<Bug> search() {
		try {
			Long bugid=Long.parseLong(pattern.trim());
			searchResults=new ArrayList<Bug>();
			searchResults.add(bugman.find(bugid));
			return null;
		}
		catch(NumberFormatException x) {
			log("no bugid: "+x);
		}
		searchResults=bugman.search(pattern);
		return null;
	}

	public List<Bug> getSearchResults() {
		return searchResults;
	}
}
