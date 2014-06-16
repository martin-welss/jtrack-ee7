package itf.jtrack.web;

import itf.jtrack.managers.BugManager;
import itf.jtrack.managers.ComponentManager;
import itf.jtrack.managers.UserManager;
import itf.jtrack.model.Bug;
import itf.jtrack.model.Component;
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
	private Component component=new Component();
	@Inject private Conversation conversation; 
	@Inject private BugManager bugman;
	@Inject private UserManager userman;
	@Inject private ComponentManager compoman;
	private String successMessage;
	
	public WebConversation() {
		id=conversation_counter++;
		log("created");
	}
	
	@PostConstruct
	public void initialize() {
		conversation.begin();
		log("postConstruct()");
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

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
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
	
	
	
}
