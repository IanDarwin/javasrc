indices: force index-bychapter.html index-byname.html

# deps are unreliable, use "make -B" on some make variants, or this:
force:
	rm -f index-bychapter.html index-byname.html

index-bychapter.html:
	makeIndexByChapter $$jcb/ch*.*doc | asciidoctor - > index-bychapter.html
index-byname.html:
	java \
	-cp main/target/classes:$$HOME/lib/darwinsys-api.jar \
	netweb.MkIndex */src/main/java

dist:
	git archive HEAD --output  javasrc.tgz --format tgz

clean:
	rm -f normal.txt gaussian.txt
