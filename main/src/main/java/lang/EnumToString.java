package lang;

public class EnumToString {

	enum NodeType { RED, BLACK }

	String getType() {
		// You have to call toString() or name() here to get a String
		return NodeType.RED.toString(); 
	}
}
