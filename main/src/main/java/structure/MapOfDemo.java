import java.util.Map;
void main() {
	var map = Map.of("Poppi","Purple", 
		"Tom","Tawny", "Robin","Rouge");
	map.forEach((k,v) ->
		{ IO.println(k + " likes " + v);});
}
