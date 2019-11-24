package structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/** "external sort" on multiple fields: descending by count, ascending by name
 */
public class SortMultipleFieldsExternal {

	/** "descending by count, ascending by name" */
	final static String SORT_ARGS_UNIX = "-k1rn,-k2";

	/**
	 * @param unused
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] unused) throws IOException, InterruptedException {
		List<SortableData> dataList = new ArrayList<SortableData>();
		dataList.add(new SortableData(4, "fish"));
		dataList.add(new SortableData(2, "meat"));
		dataList.add(new SortableData(4, "grain"));
		dataList.add(new SortableData(3, "chocolate"));

		File tmpFile = File.createTempFile("sortdemo", "txt");
		tmpFile.deleteOnExit();
		PrintWriter out = new PrintWriter(tmpFile);

		for (int i=0; i<dataList.size(); i++)
			out.println(dataList.get(i));
		out.close();

		String tmpFilePath = tmpFile.getAbsolutePath();
		String[] args = {"sort", SORT_ARGS_UNIX, "-o", tmpFilePath, tmpFilePath };
		System.out.println("Sorting phase starts...");
		Process p = Runtime.getRuntime().exec(args);
		int status = p.waitFor();
		if (status != 0) {
			System.err.printf("Sort failed, status %d, cannot continue", status);
			return;
		}
		System.out.printf("Sorting phase completed, status = %s%n", status);

		BufferedReader in = new BufferedReader(new FileReader(tmpFile));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}
}
