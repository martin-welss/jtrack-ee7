package itf.jtrack

import spock.lang.Specification
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import java.util.Random
import static groovyx.net.http.ContentType.*

class RestfulSpec extends Specification {

	def "read userlist restful"() {
		println "starting test"
		
		setup:
			def jtrack = new RESTClient( 'http://localhost:8080/jtrack-ee7/items/')
		
		when:
			def response=jtrack.get(path: 'user')
		
		then:
			assert response.status == 200
			assert response.contentType == JSON.toString()
			assert ( response.data instanceof List )
			println response.data
							
		println "finished test"
	}

	
	def "add user restful"() {
		println "starting test"
		
		setup:
			def jtrack = new RESTClient( 'http://localhost:8080/jtrack-ee7/items/')
			def random=new Random()
		
		when:
			def uname="hanno"+random.nextInt(10000);
			def user="""{"name": "$uname", "email":"hanno@test.com"}"""
			def response = jtrack.post(
				path : 'user',
				body : user,
				requestContentType : JSON )
		
		then:
			assert response.status == 200
			assert response.contentType == JSON.toString()
			assert response.data.name == uname
			assert response.data.userid > 0
										
		println "finished test"
	}


}
