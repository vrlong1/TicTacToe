
public abstract class Player {
	
	abstract void move();
	
	boolean isValidMove(String move) {
		if (move.length() != 2)
			return false;
		char row = move.charAt(0);
		int col = move.charAt(1) - 49;
		
		//Check if row is invalid
		if (row != 'A' && row != 'B' && row != 'C')
			return false;
		
		//Check if col is invalid
		if (col < 0 || col > 2)
			return false;
		
		int row_index = row - 65;
		if (Game.getInstance().getCellInfo(row_index, col) != null)
			return false;
		
		return true;
	}

}
