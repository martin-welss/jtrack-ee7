package itf.hello.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Hello {
	
	public Hello() {
		System.out.println("creating hello");
	}
    
	public String getHelloText() {
        return "Hello World!";
    }
	
	public String getBuild_id() {
    	System.out.println("reading build_id");
        try {
        	InputStream str = getClass().getResourceAsStream("/META-INF/build_id.txt");
        	BufferedReader br=new BufferedReader(new InputStreamReader(str));
            return br.readLine();
        }
        catch(Exception x) {
            System.out.println("oops."+x);
        }
        return "no build_id";
    }
	
}
