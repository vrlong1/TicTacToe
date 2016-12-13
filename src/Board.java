
public class Board {
	
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
		
		return null;
	}
	
	public enum GameOverType {
		HUMAN(0), COMPUTER(1), DRAW(2);
		
		private int value;
		private GameOverType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
}
