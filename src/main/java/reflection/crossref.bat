del xref.*

echo "<TITLE>Non-private Field Reference</TITLE>"	>  fields.htm
echo "<H1>Non-private Field Reference</H1>"		>> fields.htm
echo "<DL>"						>> fields.htm
echo "<TITLE>Non-private Method Reference</TITLE>"	>  methods.htm
echo "<H1>Non-private Method Reference</H1>"		>> methods.htm
echo "<P>Excludes ubiquitous methods: toString, notify, wait.">>methods.htm
echo "<DL>"						>> methods.htm

java CrossRef %1% | sort -fu | awk -f crossref.awk

grep "INVALID INPUT" xref.*

sort -f xref.methods >> methods.htm

sort -f xref.fields  >> fields.htm

del xref.*

ls -l fields.htm methods.htm

echo If that worked, you may want to run crossrefinst.bat

