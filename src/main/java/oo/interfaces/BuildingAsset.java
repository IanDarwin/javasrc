package oo.interfaces;

/** The top level of everything that is permanently part of the building */
public abstract class BuildingAsset extends Asset {
	protected int room;

	public BuildingAsset(int room) {
		this.room = room;
	}
}
