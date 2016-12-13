
public class ComputerPlayer extends Player {

	@Override
	void move() {
		for (int x = 0; x < 3; x++) 
			for (int y = 0; y <3; y++) 
				if (Game.getInstance().getCellInfo(x, y) == null) {
					Game.getInstance().markCell(x, y);
					System.out.println("The computer has made its move!");
					return;
				}
	}

}
