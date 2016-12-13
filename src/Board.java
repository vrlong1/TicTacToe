
public class Board {
	
	Turn turn = Turn.HUMAN;
	
	// Recursive method that continues until the game has a winner or draws
	public void runGame() {
		try {
			
			Game.getInstance().getPlayer(turn.getValue()).move();
			switch(turn) {
				case HUMAN:
					turn = Turn.COMPUTER;
					break;
				case COMPUTER:
					turn = Turn.HUMAN;
					break;
			}
			
			if (!hasWinner())
				runGame();
			else
				concludeGame();
			
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
		}
	}
	
	private void drawBoard() {	
	}
	
	private void concludeGame() {
		
	}
	
	private boolean hasWinner() {
		return false;
	}
	
	private enum Turn {
		 HUMAN(0), COMPUTER(1);
		 
		 private int value;
		 
		 private Turn(int index) {
		   this.value = index;
		 }
		 
		 public int getValue() {
		   return value;
		 };
	}
	
}
