indices: index-bychapter.html index-byname.html

index-bychapter.html:
	makeIndexByChapter $$jcb/ch*.*doc | asciidoctor - > index-bychapter.html
index-byname.html:
	java \
	-cp ./target/classes:../darwinsys-api/target/darwinsys-api-1.5.0-SNAPSHOT.jar \
	netweb.MkIndex src/main/java

clean:
	rm -f normal.txt gaussian.txt
