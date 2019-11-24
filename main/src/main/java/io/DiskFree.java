package io;

import java.io.File;

import com.darwinsys.util.ScaledNumberFormat;

public class DiskFree {

	public static void main(String[] args) {
		File[] roots = File.listRoots();
		ScaledNumberFormat fmt= new ScaledNumberFormat();
		for (File f : roots) {
			System.out.println(
				fmt.format(f.getFreeSpace()) + " " + 	
				fmt.format(f.getTotalSpace()));
		}
	}

}
