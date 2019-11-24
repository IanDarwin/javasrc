package lang;

public class EnumToString {

	enum NodeType { RED, BLACK }

	String getType() {
		// You shouldn't have to call toString() here, but you do:
		return NodeType.RED.toString(); 
	}
}
