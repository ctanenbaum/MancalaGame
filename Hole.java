package mancala;

public class Hole {
	private int count;
	private String owner;

	public Hole(String owner, int count) {
		this.count = count;    //can put exception if not 4 or 0
		this.owner = owner;	}
	public int getCount() {
		return count;
	}

	//public void setOwner(String owner) { // connect owner sent in to first player and second player

	//}

	public String getOwner() {
		return owner;
	}
	

	public void add() {
		this.count++;
	}
	public void setCount(int count) {
		this.count = count;
		
	}

	

}
