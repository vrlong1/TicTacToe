import javafx.util.Pair;

public class ComputerPlayer extends Player {
	public String[] challengeStatements = {
			"You ready to play?",
			"Don't cry when you lose!",
			"I'm a reigning champion when it comes to Tic Taco Toe!",
			"Hmm, I sense a challenger! Show me your Tic Tac Toe skills."
	};
	public String[] battleStatements = {
			"Interesting choice...but can you handle this move?",
			"Aha! I know what my next move is.",
			"I commend your move choice. But as reigning champion...take this!"
	};
	public String[] defeatStatements = {
			"I...lost?",
			"I concede. You're too good at this.",
			"Bravo! You were spectacular!"
	};
	
	@Override
	public void move() {
		/*Goals:
		 * block human player when human is about to win
		 * generate random available cell to mark
		 */
		Pair<Integer, Integer> block_coordinate = blockCheck();
		if (block_coordinate != null) {
			Game.getInstance().markCell(block_coordinate.getKey(), block_coordinate.getValue());
			System.out.println(generateBattleMessage());
			return;
		}
		//Marks the first available cell
		for (int x = 0; x < 3; x++) 
			for (int y = 0; y <3; y++) 
				if (Game.getInstance().getCellInfo(x, y) == null) {
					Game.getInstance().markCell(x, y);
					System.out.println(generateBattleMessage());
					return;
				}
	}
	
	private Pair<Integer, Integer> blockCheck() {
		// Top left to top right --
		Pair<Integer, Integer> block_coordinate;
		if ((block_coordinate = getLastCellInLine(2, 0, 0, 1)).equals(new Pair<Integer, Integer>(0, 2)))
			return block_coordinate;
		
		// Top left to bottom left |
		if ((block_coordinate = getLastCellInLine(0, 0, 1, 0)).equals(new Pair<Integer, Integer>(2, 0)))
			return block_coordinate;
		
		// Top left to bottom right \
		if ((block_coordinate = getLastCellInLine(0, 0, 1, 1)).equals(new Pair<Integer, Integer>(2, 2)))
			return block_coordinate;
		
		// Middle left to middle right --
		if ((block_coordinate = getLastCellInLine(1, 0, 0, 1)).equals(new Pair<Integer, Integer>(1, 2)))
			return block_coordinate;
		
		// Bottom left to top right /
		if ((block_coordinate = getLastCellInLine(2, 0, -1, 1)).equals(new Pair<Integer, Integer>(0, 2)))
			return block_coordinate;
		
		// Bottom left to bottom right
		if ((block_coordinate = getLastCellInLine(2, 0, 0, 1)).equals(new Pair<Integer, Integer>(2, 2)))
			return block_coordinate;
		
		// Bottom right to top right
		if ((block_coordinate = getLastCellInLine(2, 2, -1, 0)).equals(new Pair<Integer, Integer>(0, 2)))
			return block_coordinate;
		
		return null;
	}
	
	/*
	 * @param x - X coordinate on the board
	 * @param y - Y coordinate on the board
	 * @param dx - Change in X direction
	 * @param dy - Change in Y direction
	 * Checks in a line to see if human player has a winning move. If the first box is not human, return an impossible coordinate so we know human 
	 * isn't close to a win condition.
	 */
	public Pair<Integer, Integer> getLastCellInLine(int x, int y, int dx, int dy) {
		if (Game.getInstance().getCellInfo(x, y) != Game.Turn.HUMAN)
			return new Pair<Integer, Integer>(-1, -1);
		for (int counter = 0; counter < 2; counter++) {
			x += dx;
			y += dy;
			Game.Turn move = Game.getInstance().getCellInfo(x, y);
			if (move == null)
				return new Pair<Integer, Integer>(x, y);
			if (move != Game.Turn.HUMAN)
				return new Pair<Integer, Integer>(-1, -1);
		}
		return new Pair<Integer, Integer>(-1, -1);
	}
	
	//Generate messages for challenge, battle, defeat
	String generateChallengeMessage() {
		String chosenMessage = challengeStatements[(int) (Math.random() * challengeStatements.length)];
		return chosenMessage;
	}
	String generateBattleMessage() {
		String chosenMessage = battleStatements[(int) (Math.random() * battleStatements.length)];
		return chosenMessage;
	}
	String generateDefeatMessage() {
		String chosenMessage = defeatStatements[(int) (Math.random() * defeatStatements.length)];
		return chosenMessage;
	}

}
