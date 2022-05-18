package functional;

public record Camera(
	CameraMake make,
	String model,
	CameraType type,
	int yearIntroduced,
	double price, // price, in USD, when first introduced.
	CameraSensor sensorsize,
	double mPix) {
	
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
}
