import java.io.*;
/**
 * Code to read/write MS-Windows MetaFile.
 * NOT WORKING YET   DO NOT USE
 */
public class WMF {
	short	mtType;		/* must be 1 */
	short	mtHeaderSize;	/* size in 16-bit words of header, == 9 */
	short	mtVersion;	/* 0x0300 == Windows 3.x */
	int	mtSize;		/* # words in file, == (dirsize of file)/2 */
	short	mtNumObjects;	/* # of concurrent graphic objects needed */
	int	mtMaxRecord;	/* width of largest GDI record, in ushortx */
	short	mtNumParameters; /* reserved, must be zero */

	/** Construct a WMF object by initializing the basic fields */
	public WMF()
	{
		mtType = 1;
		mtHeaderSize = 9;
		mtVersion = 0x300;
		mtSize = 0;
		mtNumObjects = 0;
		mtMaxRecord = 0;
		mtNumParameters = 0;
	}

	void writeHdr(DataOutputStream fd) {
		// write(fd, &(t->mtType), sizeof t->mtType);
		// write(fd, &(t->mtHeaderSize), sizeof t->mtHeaderSize);
		// write(fd, &(t->mtVersion), sizeof t->mtVersion);
		// write(fd, &(t->mtSize), sizeof t->mtSize);
		// write(fd, &(t->mtNumObjects), sizeof t->mtNumObjects);
		// write(fd, &(t->mtMaxRecord), sizeof t->mtMaxRecord);
		// write(fd, &(t->mtNumParameters), sizeof t->mtNumParameters);
	}

	/* TEST MAIN */
	public static void main(String[] unused) {
		DataOutputStream fd;
		try {
			fd = new DataOutputStream(new FileOutputStream("foo.wmf"));
			WMF t = new WMF();
			WMFMeta p = new WMFMeta(WMFMeta.GDI_EMPTY);
			t.writeHdr(fd);
			p.write(fd);
		} catch (IOException e) {
			System.out.println("IO Error " + e);
		}
	}
}
