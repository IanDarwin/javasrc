package oo.interfaces;

public abstract class BuildingLight extends BuildingAsset {
	// generic info on lighting: flourescent/incandescent, ...
	BuildingLight(int room) {
		super(room);
	}

	@Override
	public String toString() {
		return "Light" + " " + room;
	}
}
