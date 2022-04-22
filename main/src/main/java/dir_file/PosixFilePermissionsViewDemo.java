package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class PosixFilePermissionsViewDemo {

	public static void main(String[] args) throws IOException {
		String fileName = "/tmp/id";
		Path filePath = Path.of(fileName);
		Files.deleteIfExists(filePath);    // Clean up from previous runs

		// Set up permissions and create file
		Set<PosixFilePermission> nPerms = Set.of(PosixFilePermission.OWNER_READ,
			PosixFilePermission.GROUP_READ);
		Files.createFile(filePath, 
			PosixFilePermissions.asFileAttribute(nPerms));

		// Use FileAttributes to view permissions
		// tag::main[]
		PosixFileAttributes attrs = 
			Files.getFileAttributeView(filePath, 
				PosixFileAttributeView.class)
			.readAttributes();
		System.out.format("File %s Owned by %s has perms %s%n",
			filePath,
			attrs.owner().getName(),
			PosixFilePermissions.toString(attrs.permissions()));
		// end::main[]
	}
}
