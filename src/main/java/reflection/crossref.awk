$2 == "field" {
	if (fields[$1] == "")
		fields[$1] = $3;
	else
		fields[$1] = fields[$1] ", " $3
	next
	}
$2 == "method" {
	if (methods[$1] == "")
		methods[$1] = $3;
	else
		methods[$1] = methods[$1] ", " $3
	next
	}
	{
	print "INVALID INPUT", $0
	}
END	{
	# print "Methods cross-reference"
	for (m in methods) {
		print "<DT>" m "<DD>" methods[m] >> "xref.methods"
		}
	# print "Public Fields cross-reference"
	for (f in fields) {
		print "<DT>" f "<DD>" fields[f] >> "xref.fields"
		}
	}
