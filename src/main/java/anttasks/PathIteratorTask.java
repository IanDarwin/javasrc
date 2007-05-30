package anttasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

public class PathIteratorTask extends Task {

	private Path paths;

	@Override
	public void execute() throws BuildException {
		super.execute();
		System.out.println("Path List:");
		for (String el : paths.list()) {
			System.out.println("Path element is " + el);
		}		
	}
	
	public void addPath(Path path) {
		this.paths = path;
	}
}
