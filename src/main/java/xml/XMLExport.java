		// Set up the XML tree.
		XmlDocument doc = new XmlDocument();
		Element root = doc.createElement("jabadex");
		doc.appendChild(root);

		// For each person...
		while (userList.hasMoreElements()) {
			Person u = (Person)userList.nextElement();
			// Create <person>...</person>
			Element person = doc.createElement("person");
			root.appendChild(person);

			String[] f = u.getLabels();
			for (int i=0; i<u.getNF(); i++) {
				String element = f[i];
				if (element == null || element.length() == 0)
					continue;
				String value = u.f[i].getValue();
				if (value == null || value.length() == 0)
					continue;
				// Create e.g., <firstname>...</firstname>
				Element e = doc.createElement(
					element.replace(' ', '_'));
				person.appendChild(e);
				Text v = doc.createTextNode(value);
				e.appendChild(v);
			}
		}

		// Write the tree to the file.
		doc.write(df);
