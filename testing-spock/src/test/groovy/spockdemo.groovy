package spock;

import spock.lang.Specification;

/**
 * Simple demo of Spock
 */
class SpockDemo extends Specification {
    def 'spock true'() {
        expect:
            true
    }
}

