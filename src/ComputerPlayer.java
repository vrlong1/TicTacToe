
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
	public String[] victoryStatements = {
			"I win!",
			"Nice try, but I win!"
	};
	
	@Override
	void move() {
		/*Goals:
		 * block human player when human is about to win
		 * aim to win when it sees its about to win
		 */
		//blockCheck();
		
		// Random generating move
		int random_row = 0;
		int random_col = 0;
		
		do {
			random_row = (int)(Math.random() * 3);
			random_col = (int)(Math.random() * 3);
			if (Game.getInstance().getCellInfo(random_row, random_col) == null){
				Game.getInstance().markCell(random_row, random_col);
				System.out.println(generateBattleMessage());
				return;
			}
		} while(Game.getInstance().getCellInfo(random_row, random_col) != null);
	}
	
	
	void blockCheck() {
		return;
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
	String generateVictoryMessage() {
		String chosenMessage = victoryStatements[(int) (Math.random() * victoryStatements.length)];
		return chosenMessage;
	}

}
