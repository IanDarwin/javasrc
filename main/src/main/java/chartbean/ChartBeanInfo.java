package chartbean;

import java.beans.BeanDescriptor;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

/** BeanInfo for our Chart Demo */
public class ChartBeanInfo extends SimpleBeanInfo {

	/** Give a descriptor for the Bean itself */
	public BeanDescriptor getBeanDescriptor() {
		return new BeanDescriptor(Chart.class);
	}

	/** Set the Icon for display in the BeanBox */
	public java.awt.Image getIcon(int kind) {
		switch(kind) {
		case ICON_COLOR_16x16:
			return loadImage("ChartIcon16.gif");
		case ICON_COLOR_32x32:
			return loadImage("ChartIcon32.gif");
		case ICON_MONO_16x16:
			return loadImage("ChartIconBW16.gif");
		case ICON_MONO_32x32:
			return loadImage("ChartIconBW32.gif");
		default:
			System.out.println("ChartBean Unsupported Icon Format " + kind);
		}
		return null;
	}

	/** Set up the method name descriptions for actions */
	public MethodDescriptor[] getMethodDescriptors() {
		try {
			// DODEMO()
			Method m = Chart.class.getMethod("doDemo", new Class[] {});
			MethodDescriptor md1 = new MethodDescriptor(m);
			md1.setShortDescription("Run a simple demonstration");

			// SETDATA(ChartData[]);
			Class<?> argTypes[] = { Class.forName("ChartData[]") };
			m = Chart.class.getMethod("setData", argTypes);
			MethodDescriptor md2 = new MethodDescriptor(m);
			md2.setShortDescription("Provide array of ChartData to plot");

			return new MethodDescriptor[] { md1, md2 };

		} catch (Exception e) {		// if anything fails, fall back to default
			System.out.println(e);
			return super.getMethodDescriptors();
		}
	}
}
