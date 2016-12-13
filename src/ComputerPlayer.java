


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
	void move() {
		for (int x = 0; x < 3; x++) 
			for (int y = 0; y <3; y++) 
				if (Game.getInstance().getCellInfo(x, y) == null) {
					Game.getInstance().markCell(x, y);
					System.out.println(generateBattleMessage());
					return;
				}
	}
	
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
