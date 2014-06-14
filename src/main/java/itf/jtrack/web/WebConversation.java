package itf.jtrack.web;

import itf.jtrack.model.Component;
import itf.jtrack.model.Task;
import itf.jtrack.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value="webconversation")
@ConversationScoped
public class WebConversation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int conversation_counter=1;
	private static String buildId=null;
	private int id;
	private User user;
	private Task task;
	private Component component;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public WebConversation() {
		id=conversation_counter++;
		log("created");
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

	private void log(String text) {
		System.out.println("[WebConversation#"+id+"] "+text);
	}
        
}
