import java.util.Scanner;


public class HumanPlayer extends Player {
	
	Scanner reader = new Scanner(System.in);

	@Override
	public void move() {
		System.out.print("Please input a valid move: ");
		String move = reader.next().toUpperCase();
		
		if (isValidMove(move)) {
			int row = move.charAt(0) - 65;
			int col = move.charAt(1) - 49;
			Game.getInstance().markCell(row, col);
		} else {
			move();
		}
	}

}
