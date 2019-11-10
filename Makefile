indices: force index-bychapter.html index-byname.html

# deps are unreliable, use make -B or this:
force:
	rm -f index-bychapter.html index-byname.html

index-bychapter.html:
	makeIndexByChapter $$jcb/ch*.*doc | asciidoctor - > index-bychapter.html
index-byname.html:
	java \
	-cp ./target/classes:../darwinsys-api/target/darwinsys-api-1.5.2-SNAPSHOT.jar \
	netweb.MkIndex src/main/java

clean:
	rm -f normal.txt gaussian.txt
