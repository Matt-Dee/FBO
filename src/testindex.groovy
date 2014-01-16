@Grapes([
@Grab(group = 'org.elasticsearch', module = 'elasticsearch-lang-groovy', version = '1.6.0-SNAPSHOT'),
@Grab(group = 'org.elasticsearch', module = 'elasticsearch', version = '0.90.7')
])


import org.elasticsearch.groovy.node.GNode
import org.elasticsearch.groovy.node.GNodeBuilder
import static org.elasticsearch.groovy.node.GNodeBuilder.*


/**
 * User: mdonnelly
 * Date: 1/16/14
 * Time: 9:29 AM
 */


GNodeBuilder nodeBuilder = nodeBuilder();
nodeBuilder.settings {
    node {
        client = true
    }
    cluster {
        name = "mdonnelly-es"
    }
}

GNode node = nodeBuilder.node()



def indexR = client.index {
    index "test"
    type "type1"
    id "1"
    source {
        test = "value"
        complex {
            value1 = "value1"
            value2 = "value2"
        }
    }
}

node.stop().close()
