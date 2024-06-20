/<!-- EXPECTO/ {
	dumping=1
	print
}
/<!-- \/EXPECTO/ {
	myline = $0
	dumping=0
	while (getline < "excludes.xml") {
		print
	}
	print myline
}

	{
	if (!dumping)
		print
	}

