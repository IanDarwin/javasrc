import com.sun.java.swing.*;
import java.io.*;

/** PnmConv -- convert among bitmap formats
 */

class MyFileType implements FileType {
	/** The presentation name */
	protected String typeName;
	/** The file extension(s) */
	protected String typeExts[];
	/** The Icon for this type */
	protected Icon icon = null;
	/** True iff our pnmplus/netpbm can convert FROM this type */
	protected boolean canConvertFrom = false;
	/** True iff our pnmplus/netpbm can convert TO this type */
	protected boolean canConvertTo = false;

	/** Construct one type. Since the compiler says that
	 * "Array constants can only be used in initializers", we accept
	 * up to three string args, and make the array ourself.
	 */
	public MyFileType(String desc, 
		boolean from, boolean to,
		String ext1, String ext2, String ext3) {
		StringBuffer sb = new StringBuffer(desc);
		canConvertFrom = from;
		canConvertTo = to;
		if (ext1 == null)
			throw new IllegalArgumentException("Extentions list invalid");
		int nExt = 1;
		if (ext2 != null)
			++nExt;
		if (ext3 != null)
			++nExt;
		sb.append("(*.");
		// remaining code blechiferous. Build the array the hard way,
		// then walk it with a for loop to build the (*.foo,*.bar) string.
		typeExts = new String[nExt]; 
		typeExts[0] = "."+ext1;
		sb.append(ext1);
		if (nExt == 1) {
			sb.append(")");
			typeName = sb.toString();
			return;
		}
		typeExts[1] = "."+ext2;
		sb.append(",*.");
		sb.append(ext2);
		if (nExt == 2) {
			sb.append(")");
			typeName = sb.toString();
			return;
		}
		typeExts[2] = "."+ext3;
		sb.append(",*.");
		sb.append(ext3);
		sb.append(")");
		typeName = sb.toString();
	}

	/**
	 @return a human readable name for this type, e.g. "Foo text" 
	 */
	public String getPresentationName() {
		return typeName;
	}

	/**
	 @return true if this file matches this type
	 */
	public boolean testFile(File file) {
		String name = file.getName();
		for (int i=0; i<typeExts.length; i++)
			if (name.endsWith(typeExts[i]))
				return true;
		return false;
	}

	/** Return true if this type can be converted FROM */
	public boolean isFromType() {
		return canConvertFrom;
	}
	/** Return true if this type can be converted FROM */
	public boolean isToType() {
		return canConvertTo;
	}

	/**
	 * Get the icon for this type.
	 @return May return null. In this case the UI must provide an icon 
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 @return true if file choosers should descend into this type.
	 */
	public boolean isContainer() {
		return false;
	}
}

class AllGfxTypes extends MyFileType {
	/** Constructor */
	AllGfxTypes() {
		super("All convertible types", true, true, "*", null, null);
	}

	/**
	 @return true if this file matches this type
	 */
	public boolean testFile(File file) {
		return true;
	}
}
		

public class PnmTypes {
	public static MyFileType fileTypes[] = {
		new MyFileType("PNM Bitmap", true, true, "pbm", null, null),
		new MyFileType("PNM Graymap", true, true, "pgm", null, null),
		new MyFileType("PNM Colormap", true, true, "pnm", null, null),
		new MyFileType("Sun Raster", true, true, "ras", "rst", "rast"),
		new AllGfxTypes(),
	};
}
