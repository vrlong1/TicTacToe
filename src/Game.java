


public class Game {
	
	private Player[] players = new Player[2];
	private Turn[][] moves = {new Turn[3], new Turn[3], new Turn[3]};
	private Turn turn = Turn.HUMAN;
	private Board board;
	private static Game game;
	
	public Game() {
		board = new Board();
	}
	
	public static void main(String[] args) {
		
		// Create game and setup
		game = new Game();
		game.setup();
		game.runGame();
		
	}
	
	private void runGame() {
		ComputerPlayer computer_player = (ComputerPlayer) players[1];
		System.out.println(computer_player.generateChallengeMessage());
		board.drawBoard();
		Board.GameOverType winner = null;
		while ((winner = board.getWinner()) == Board.GameOverType.NOT_OVER) {
			try {
				
				// Execute current player's turn
				getPlayer(turn.getValue()).move();
				
				// Draws the newly updated board
				board.drawBoard();
				
				// Sets turn to the next player's turn
				switch(turn) {
					case HUMAN:
						turn = Game.Turn.COMPUTER;
						break;
					case COMPUTER:
						turn = Game.Turn.HUMAN;
						break;
				}
				
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				
			}
		}
		System.out.println("The winner is " + winner);
		if (winner == Board.GameOverType.HUMAN)
			System.out.println(computer_player.generateDefeatMessage());
	}
	
	public static Game getInstance() {
		return game;
	}
	
	public void setup() {
		players[0] = new HumanPlayer();
		players[1] = new ComputerPlayer();
	}
	
	public Player getPlayer(int index) {
		return players[index];
	}
	
	public void markCell(int row, int column) {
		moves[row][column] = turn;
	}
	
	public Turn getCellInfo(int row, int column) {
		return moves[row][column];
	}
	
	public enum Turn {
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
