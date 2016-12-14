import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class ComputerPlayer extends Player {
	private String[] challengeStatements = {
			"You ready to play?",
			"Don't cry when you lose!",
			"I'm a reigning champion when it comes to Tic Taco Toe!",
			"Hmm, I sense a challenger! Show me your Tic Tac Toe skills."
	};
	private String[] battleStatements = {
			"Interesting choice...but can you handle this move?",
			"Aha! I know what my next move is.",
			"I commend your move choice. But as reigning champion...take this!"
	};
	private String[] defeatStatements = {
			"I...lost?",
			"I concede. You're too good at this.",
			"Bravo! You were spectacular!"
	};
	
	private int[][] coordinatesAndDirectionToCheck = {
			/* Top left to top right */ {2, 0, 0, 1}, 
			/* Top left to bottom left */ {0, 0, 1, 0}, 
			/* Top left to bottom right */ {0, 0, 1, 1},
			/* Middle left to middle right */ {1, 0, 0, 1}, 
			/* Bottom left to top right */ {2, 0, -1, 1},
			/* Bottom left to bottom right */ {2, 0, 0, 1}, 
			/* Bottom right to top right */ {2, 2, -1, 0}};
	
	@Override
	public void move() {
		/*Goals:
		 * block human player when human is about to win
		 * generate random available cell to mark
		 */
		Pair<Integer, Integer> block_coordinate = blockCheck();
		if (block_coordinate != null) {
			System.out.println("BLOCKED!");
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
	
	// Runs through each coordinate and direction to check for a player getting close to a win. Returns a coordinate to block if the player is close to winning.
	private Pair<Integer, Integer> blockCheck() {
		// Top left to top right --
		Pair<Integer, Integer> block_coordinate = null;
		for (int[] x : coordinatesAndDirectionToCheck) {
			if ((block_coordinate = getLastCellInLine(x[0], x[1], x[2], x[3])) != null)
				return block_coordinate;
		}
		
		return block_coordinate;
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
		// Store a map that should only be at max size 3
		Map<Pair<Integer, Integer>, Game.Turn> moves = new HashMap<Pair<Integer, Integer>, Game.Turn>();
		
		// Empty cell coordinates to easily return later
		Pair<Integer, Integer> emptyCellCoordinate = null;
		
		// # of human cells in this row
		int human_cells = 0;
		
		for (int counter = 0; counter < 3; counter++, x += dx, y += dy) {
			
			Game.Turn move = Game.getInstance().getCellInfo(x, y);
			
			// Return if computer has a cell here. Impossible for player to win in this row.
			if (Game.getInstance().getCellInfo(x, y) == Game.Turn.COMPUTER)
				return null;
			
			// Otherwise, place the coordinate and its value into the map
			Pair<Integer, Integer> coordinate = new Pair<Integer, Integer>(x, y);
			moves.put(coordinate, Game.getInstance().getCellInfo(x, y));
			
			// If human cell, increment # of human cells 
			if (move == Game.Turn.HUMAN)
				human_cells++;
			
			// If the cell is empty, then log the empty cell's coordinates for returning
			if (move == null)
				emptyCellCoordinate = coordinate;
		}
		
		// If # of human cells isn't 2, then it's impossible for human to win so return null
		if (human_cells != 2)
			return null;
		
		return emptyCellCoordinate;
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
