package oo.interfaces;

public abstract class ComputerAsset extends Asset {
	// generic computer component stuff here: location, owner, 
	// warranty expiration date :-), etc.
	int deskNumber;
	ComputerAsset(int deskNumber) {
		this.deskNumber = deskNumber;
	}
}
