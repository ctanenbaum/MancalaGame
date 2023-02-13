package mancala;

public class Pit extends Hole {
	private int pitNumber; // so can know which one they are picking
	private int pitIndex;

	public Pit(String owner, int i, int pitIndex) {
		super(owner, 4);
		this.pitNumber = i;
		this.pitIndex = pitIndex;
	}

	public void pick() {
		super.setCount(0);

	}

	public int getPitNumber() {
		return pitNumber;
	}

	public void setPitIndex(int index) {
		this.pitIndex = index;
	}

	public int getPitIndex() {
		return pitIndex;
	}

	/**
	 * @return
	 */
	public int getPitAcross() {
		int pitAcross = 12 - pitIndex;
		
		/*
		 * if (pitNumber == 1) { pitAcross = 6; } else if (pitNumber == 2) { pitAcross =
		 * 5; } else if (pitNumber == 3) { pitAcross = 4; } else if (pitNumber == 4) {
		 * pitAcross = 3; } else if (pitNumber == 5) { pitAcross = 2; } else { pitAcross
		 * = 1; }
		 */
		return pitAcross;
	}

}
