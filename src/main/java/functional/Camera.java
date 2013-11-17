package functional;

public class Camera {
	enum Make { Canon, Nikon, Sony, LG, Sanyo };
	enum Type { DSLR, ILC, P_S };
	enum SensorSize { Huge, FullFrame, APSC, Small, Tiny };
	
	Make make;
	String model;
	Type type;
	int yearIntroduced;
	double msrpWhenIntroduced; // in USD
	double mPix;
	SensorSize sensorsize;
}
