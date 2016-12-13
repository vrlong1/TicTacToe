
public class Game {
	
	private Player[] players = new Player[2];
	private static Game game;
	
	public static void main(String[] args) {
		
		// Create game and setup
		game = new Game();
		game.setup();
		
		// Create board and run game
		Board board = new Board();
		board.runGame();
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

}
