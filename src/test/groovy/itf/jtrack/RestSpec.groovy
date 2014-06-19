package itf.jtrack

import spock.lang.Specification

class RestSpec extends Specification {
	def "maximum of two numbers"() {
		println "starting test"
		expect:
		// exercise math method for a few different inputs
		Math.max(1, 3) == 3
		Math.max(7, 4) == 7
		Math.max(0, 0) == 0
		println "finished test"
	}

}
