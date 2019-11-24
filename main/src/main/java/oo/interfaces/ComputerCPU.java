package oo.interfaces;

public class ComputerCPU extends ComputerAsset {
	// information about CPU (P5, P6, ...), RAM, etc.
	ComputerCPU(int deskNumber) {
		super(deskNumber);
	}

	@Override
	public String toString() {
		return "CPU " + deskNumber;
	}
}
