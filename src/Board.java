
public class Board {
	
	private int[][] coordinatesToCheck = {{2, 0, -1, 1}, {1, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 0}, 
										  {0, 0, 0, 1}, {0, 0, 1, 0}, {2, 0, 0, 1}, {0, 2, 1, 0}};
	
	public void drawBoard() {
		
		Game game = Game.getInstance();
		
		String output = "";
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				Game.Turn player = game.getCellInfo(x, y);
				String move = (player == Game.Turn.HUMAN ? "X" : player == Game.Turn.COMPUTER ? "O" : " ");
				output += " " + move + " ";
				if (y < 2)
					output += "|"; 
			}
			
			if (x < 2)
				output += "\r\n---|---|---\r\n";
		}
		
		System.out.println(output);
	}
	
	public GameOverType getWinner() {
		GameOverType game_status = GameOverType.NOT_OVER;
		
		// Loop through selected coordinates and directions to check for a winner
		for (int[] x : coordinatesToCheck) {
			game_status = checkWin(x[0], x[1], x[2], x[3]);
			if (game_status == GameOverType.HUMAN || game_status == GameOverType.COMPUTER)
				return game_status;
		}
		
		//Determine if board is filled
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (Game.getInstance().getCellInfo(x, y) == null) 
					return GameOverType.NOT_OVER;
				
		return GameOverType.DRAW;
	}
	
	/*
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 * @param dx - Change in X coordinate
	 * @param dy - Change in Y coordinate
	 * Checks for a winner starting from original coordinates and checking the next cell through dx and dy. Returns a GameOverType to keep track of the
	 * current win status. 
	 */
	private GameOverType checkWin(int x, int y, int dx, int dy) {
		Game.Turn player = Game.getInstance().getCellInfo(x, y);
		if (player == null)
			return GameOverType.NOT_OVER;
		for (int counter = 0; counter < 2; counter++) {
			x += dx;
			y += dy;
			if (player != Game.getInstance().getCellInfo(x, y))
				return GameOverType.NOT_OVER;
		}
		return player == Game.Turn.COMPUTER ? GameOverType.COMPUTER : GameOverType.HUMAN;
	}
	
	public enum GameOverType {
		HUMAN(0), COMPUTER(1), DRAW(2), NOT_OVER(3);
		
		private int value;
		private GameOverType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
}
