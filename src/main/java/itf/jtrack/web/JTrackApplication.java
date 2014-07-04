package itf.jtrack.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("items")
@Named(value="jtrack")
@ApplicationScoped
public class JTrackApplication extends Application {
	
	private String buildId;

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
		System.out.println("[JTrackApplication] "+text);
		
	}

}
