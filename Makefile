index-by-chapter:
	makeIndexByChapter $$jcb/ch*.*doc | asciidoctor - > index-bychapter.html
