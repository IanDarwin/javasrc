package functional;

public class Camera {
	CameraMake make;
	String model;
	CameraType type;
	int yearIntroduced;
	double price; // price, in USD, when first introduced.
	double mPix;
	CameraSensor sensorsize;
	
	public Camera() {
		// empty
	}
	
	public Camera(CameraMake make, String model, CameraType type, 
			int yearIntroduced, double priceAtIntroduction, 
			CameraSensor sensorsize, double mPix) {
		super();
		this.make = make;
		this.model = model;
		this.type = type;
		this.yearIntroduced = yearIntroduced;
		this.price = priceAtIntroduction;
		this.mPix = mPix;
		this.sensorsize = sensorsize;
	}
	
	@Override
	public String toString() {
		return make + " " + model;
	}

	public CameraMake getMake() {
		return make;
	}

	public void setMake(CameraMake make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CameraType getType() {
		return type;
	}

	public void setType(CameraType type) {
		this.type = type;
	}
	
	// Convenience
	public boolean isIlc() {
		return type != null && type == CameraType.ILC;
	}
	public boolean isDslr() {
		return type != null && type == CameraType.DSLR;
	}
	public boolean isPointAndShoot() {
		return type != null && type == CameraType.P_S;
	}

	public int getYearIntroduced() {
		return yearIntroduced;
	}

	public void setYearIntroduced(int yearIntroduced) {
		this.yearIntroduced = yearIntroduced;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getmPix() {
		return mPix;
	}

	public void setmPix(double mPix) {
		this.mPix = mPix;
	}

	public CameraSensor getSensorsize() {
		return sensorsize;
	}

	public void setSensorsize(CameraSensor sensorsize) {
		this.sensorsize = sensorsize;
	}
	
}
