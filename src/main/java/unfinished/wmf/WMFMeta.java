import java.io.*;

public class WMFMeta {
		public static final int GDI_EMPTY = 0;
		public static final int GDI_MoveTo = 0x0214;
		public static final int GDI_DrawText = 0x062f;

		private int	rdSize;		/* # of ushort's of record, incl 2 for rdsize */
		private int rdFunction = GDI_EMPTY;
		short	rdParm[];	/* VARIABLE LENGTH */

		public WMFMeta(int i) {
			rdFunction = i;
		}
		public void setFunction(int i) {
			rdFunction = i;
		}
		public int getFunction() {
			return rdFunction;
		}
		void write(DataOutputStream fd) {
			// write(fd, &(t->rdSize), sizeof t->rdSize);
			// write(fd, &(t->rdFunction), sizeof t->rdFunction);
		}

	}
