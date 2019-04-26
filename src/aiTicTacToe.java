import java.util.*;

public class aiTicTacToe {
	public final int NEGATIVE_INFINITY = Integer.MIN_VALUE;
	public final int INFINITY = Integer.MAX_VALUE;
	public int DEPTH = 2; 
    
	public int getStateOfPostionByCoordinate(int x, int y, int z, List<positionTicTacToe> board) {
		int index = x * 16 + y * 4 + z;
		return board.get(index).state;
	}

	public int getOneWinningLineScore (int playerCount, int opponentCount) {
		if(opponentCount > 0) {
			return 0;
		}
		if(playerCount == 0) {
			return 0;
		} else if(playerCount == 1) {
			return 1;
		} else if(playerCount == 2) {
			return 3;
		} else if(playerCount == 3) {
			return 7;
		} else if(playerCount == 4) {
			return Integer.MAX_VALUE;
		}
		return -1; // error
	}

	public int[] getPlayerOpponentCount(int[] stateArray, int player) {
		int playerCount = 0;
		int opponentCount = 0;
		for(int state : stateArray) {
			if(state == player) {
				playerCount++;
			} else if(state != 0) {
				opponentCount++;
			}
		}
		return new int[] {playerCount, opponentCount};
	}

	// error occurs if return -1
	// player1 -- player == 1; player2 -- player == 2
	public int getCurBoardScore(List<positionTicTacToe> board, int player) {
		int totalScore = 0;

		//48 straight winning lines
		//z axis winning lines -- 16
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int playerCount = 0;
				int opponentCount = 0;
				for(int k = 0; k < 4; k++) {
					int state = getStateOfPostionByCoordinate(i, j, k, board);
					if(state == player) {
						playerCount++;
					} else if(state != 0) {
						opponentCount++;
					}
				}
				int oneLineScore = getOneWinningLineScore(playerCount, opponentCount);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}

		//y axis winning lines
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int playerCount = 0;
				int opponentCount = 0;
				for(int k = 0; k < 4; k++) {
					int state = getStateOfPostionByCoordinate(i, k, j, board);
					if(state == player) {
						playerCount++;
					} else if(state != 0) {
						opponentCount++;
					}
				}
				int oneLineScore = getOneWinningLineScore(playerCount, opponentCount);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}

		//x axis winning lines
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int playerCount = 0;
				int opponentCount = 0;
				for(int k = 0; k < 4; k++) {
					int state = getStateOfPostionByCoordinate(k, i, j, board);
					if(state == player) {
						playerCount++;
					} else if(state != 0) {
						opponentCount++;
					}
				}
				int oneLineScore = getOneWinningLineScore(playerCount, opponentCount);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}
		//48 straight winning lines ENDS


		//12 main diagonal winning lines STARTS
		//xz plane-4
		if(true) {
			for(int i = 0; i < 4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(0, i, 0, board);
				stateArray[1] = getStateOfPostionByCoordinate(1, i, 1, board);
				stateArray[2] = getStateOfPostionByCoordinate(2, i, 2, board);
				stateArray[3] = getStateOfPostionByCoordinate(3, i, 3, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}

		//yz plane-4
		if(true) {
			for(int i = 0; i < 4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(i, 0, 0, board);
				stateArray[1] = getStateOfPostionByCoordinate(i, 1, 1, board);
				stateArray[2] = getStateOfPostionByCoordinate(i, 2, 2, board);
				stateArray[3] = getStateOfPostionByCoordinate(i, 3, 3, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}

		//xy plane-4
		if(true) {
			for(int i = 0; i < 4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(0, 0, i, board);
				stateArray[1] = getStateOfPostionByCoordinate(1, 1, i, board);
				stateArray[2] = getStateOfPostionByCoordinate(2, 2, i, board);
				stateArray[3] = getStateOfPostionByCoordinate(3, 3, i, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}
		// 12 main diagonal winning lines ENDS


		//12 anti diagonal winning lines STARTS
		//xz plane-4
		if(true) {
			for(int i = 0; i < 4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(0, i, 3, board);
				stateArray[1] = getStateOfPostionByCoordinate(1, i, 2, board);
				stateArray[2] = getStateOfPostionByCoordinate(2, i, 1, board);
				stateArray[3] = getStateOfPostionByCoordinate(3, i, 0, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}

		//yz plane-4
		if(true) {
			for(int i = 0; i < 4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(i, 0, 3, board);
				stateArray[1] = getStateOfPostionByCoordinate(i, 1, 2, board);
				stateArray[2] = getStateOfPostionByCoordinate(i, 2, 1, board);
				stateArray[3] = getStateOfPostionByCoordinate(i, 3, 0, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}

		}

		//xy plane-4
		if(true) {
			for(int i = 0; i<4; i++) {
				int[] stateArray = new int[4];
				stateArray[0] = getStateOfPostionByCoordinate(0, 3, i, board);
				stateArray[1] = getStateOfPostionByCoordinate(1, 2, i, board);
				stateArray[2] = getStateOfPostionByCoordinate(2, 1, i, board);
				stateArray[3] = getStateOfPostionByCoordinate(3, 0, i, board);
				int countArray[] = getPlayerOpponentCount(stateArray, player);
				int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
				if(oneLineScore == -1) {
					return -1;
				} else if(oneLineScore == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				totalScore += oneLineScore;
			}
		}
		//12 anti diagonal winning lines ENDS


		//4 additional diagonal winning lines STARTS
		// 1
		if(true) {
			int[] stateArray = new int[4];
			stateArray[0] = getStateOfPostionByCoordinate(0, 0, 0, board);
			stateArray[1] = getStateOfPostionByCoordinate(1, 1, 1, board);
			stateArray[2] = getStateOfPostionByCoordinate(2, 2, 2, board);
			stateArray[3] = getStateOfPostionByCoordinate(3, 3, 3, board);
			int countArray[] = getPlayerOpponentCount(stateArray, player);
			int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
			if(oneLineScore == -1) {
				return -1;
			} else if(oneLineScore == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			totalScore += oneLineScore;
		}

		// 2
		if(true) {
			int[] stateArray = new int[4];
			stateArray[0] = getStateOfPostionByCoordinate(0, 0, 3, board);
			stateArray[1] = getStateOfPostionByCoordinate(1, 1, 2, board);
			stateArray[2] = getStateOfPostionByCoordinate(2, 2, 1, board);
			stateArray[3] = getStateOfPostionByCoordinate(3, 3, 0, board);
			int countArray[] = getPlayerOpponentCount(stateArray, player);
			int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
			if(oneLineScore == -1) {
				return -1;
			} else if(oneLineScore == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			totalScore += oneLineScore;
		}

		// 3
		if(true) {
			int[] stateArray = new int[4];
			stateArray[0] = getStateOfPostionByCoordinate(3, 0, 0, board);
			stateArray[1] = getStateOfPostionByCoordinate(2, 1, 1, board);
			stateArray[2] = getStateOfPostionByCoordinate(1, 2, 2, board);
			stateArray[3] = getStateOfPostionByCoordinate(0, 3, 3, board);
			int countArray[] = getPlayerOpponentCount(stateArray, player);
			int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
			if(oneLineScore == -1) {
				return -1;
			} else if(oneLineScore == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			totalScore += oneLineScore;
		}

		// 4
		if(true) {
			int[] stateArray = new int[4];
			stateArray[0] = getStateOfPostionByCoordinate(0, 3, 0, board);
			stateArray[1] = getStateOfPostionByCoordinate(1, 2, 1, board);
			stateArray[2] = getStateOfPostionByCoordinate(2, 1, 2, board);
			stateArray[3] = getStateOfPostionByCoordinate(3, 0, 3, board);
			int countArray[] = getPlayerOpponentCount(stateArray, player);
			int oneLineScore = getOneWinningLineScore(countArray[0], countArray[1]);
			if(oneLineScore == -1) {
				return -1;
			} else if(oneLineScore == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			totalScore += oneLineScore;
		}
		//4 additional diagonal winning lines ENDS

		return totalScore;
	}

	// Yufeng ENDS...............


	public int player; //1 for player 1 and 2 for player 2
	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return board.get(index).state;
	}
	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		long start = System.currentTimeMillis();
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		int alpha = NEGATIVE_INFINITY;
		int beta = INFINITY;
		List<positionTicTacToe> predictedMoves = guessMoves(board);
		
		int hurValueMax = NEGATIVE_INFINITY;

		for (positionTicTacToe move: predictedMoves ){
			int index = move.x * 16 + move.y * 4 + move.z;
			board.get(index).state = player;
			List<positionTicTacToe> copied = deepCopyATicTacToeBoard(board);
//			int curValue = miniMaxEach(copied, 3, false );
			int curValue = alphaBeta(copied, DEPTH, alpha, beta, false);
			curValue = curValue > NEGATIVE_INFINITY ? curValue : NEGATIVE_INFINITY;
			if (curValue >= hurValueMax){
				hurValueMax = curValue;
				myNextMove = move;
			}
			alpha = Math.max(alpha, hurValueMax);
			if (alpha >= beta){
				board.get(index).state = 0;
				break;
			}
			board.get(index).state = 0;
		}
		long end = System.currentTimeMillis();
		System.out.println("this step takes: "+ (end - start)/1000.0 + "s");
		return myNextMove;
	}

	private List<positionTicTacToe> deepCopyATicTacToeBoard(List<positionTicTacToe> board)
	{
		//deep copy of game boards
		List<positionTicTacToe> copiedBoard = new ArrayList<positionTicTacToe>();
		for(int i=0;i<board.size();i++)
		{
			copiedBoard.add(new positionTicTacToe(board.get(i).x,board.get(i).y,board.get(i).z,board.get(i).state));
		}
		return copiedBoard;
	}

	// produce a sequence of possible results from already known board
	public List<positionTicTacToe> guessMoves(List<positionTicTacToe> board){
		List<positionTicTacToe> moves = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					positionTicTacToe curPosition = new positionTicTacToe(i, j, k);
					if (getStateOfPositionFromBoard(curPosition, board) == 0)
						moves.add(curPosition);
				}
			}
		}
		return moves;
	}
	public void printBoardTicTacToe(List<positionTicTacToe> targetBoard)
	{
		//print each position on the board, uncomment this for debugging if necessary
		/*
		System.out.println("board:");
		System.out.println("board slots: "+board.size());
		for (int i=0;i<board.size();i++)
		{
			board.get(i).printPosition();
		}
		*/

		//print in "graphical" display
		for (int i=0;i<4;i++)
		{
			System.out.println("level(z) "+i);
			for(int j=0;j<4;j++)
			{
				System.out.print("["); // boundary
				for(int k=0;k<4;k++)
				{
					if (getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==1)
					{
						System.out.print("X"); //print cross "X" for position marked by player 1
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==2)
					{
						System.out.print("O"); //print cross "O" for position marked by player 2
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==0)
					{
						System.out.print("_"); //print "_" if the position is not marked
					}
					if(k==3)
					{
						System.out.print("]"); // boundary
						System.out.println();
					}


				}

			}
			System.out.println();
		}
	}

	public int miniMaxEach(List<positionTicTacToe> board, int depth, boolean maximizer){
		List<positionTicTacToe> children = guessMoves(board);
		List<positionTicTacToe> tempBoard =  deepCopyATicTacToeBoard(board);
		int hurValueMax = maximizer ? NEGATIVE_INFINITY : INFINITY ;
		int opponent = player == 1 ? 2 : 1;
		int thinker = maximizer ? player : opponent;


		if (depth == 0 || children.size() == 0) {
			int curScore = getCurBoardScore(tempBoard, player);
			int opponent_score= getCurBoardScore(tempBoard, opponent);
			if (opponent_score == INFINITY)
				curScore = NEGATIVE_INFINITY;
			else if (curScore == INFINITY)
				curScore = INFINITY;
			else curScore -= opponent_score;
			return curScore;
		}
		for (positionTicTacToe child : children){
			int index = child.x * 16 + child.y * 4 + child.z;
			tempBoard.get(index).state = thinker;


			if (maximizer){
			    int hurValue = miniMaxEach(tempBoard, depth - 1, false);
				hurValueMax = Math.max(hurValueMax, hurValue);
//				String playerN = (maximizer? "player " : "opponent ") + "hurValue: " + hurValue + "position:" + child.x + "," + child.y + "," + child.z;
//				System.out.println(playerN);
			}else {
			    int hurValue = miniMaxEach(tempBoard, depth - 1, true);
				hurValueMax = Math.min(hurValueMax, hurValue);
//				String playerN = (maximizer? "player " : "opponent ") + "hurValue: " + hurValue + "position:" + child.x + "," + child.y + "," + child.z;
//				System.out.println(playerN);
			}
			tempBoard.get(index).state = 0;
		}

		return hurValueMax;
	}

	public int alphaBeta(List<positionTicTacToe> board, int depth, int alpha, int beta, boolean maximizer){
		List<positionTicTacToe> children = guessMoves(board);
		List<positionTicTacToe> tempBoard =  deepCopyATicTacToeBoard(board);
		int hurValueMax = maximizer ? NEGATIVE_INFINITY : INFINITY ;
		int opponent = player == 1 ? 2 : 1;
		int thinker = maximizer ? player : opponent;


		if (depth == 0 || children.size() == 0) {
			int curScore = getCurBoardScore(tempBoard, player);
			int opponent_score= getCurBoardScore(tempBoard, opponent);
			if (opponent_score == INFINITY)
				curScore = NEGATIVE_INFINITY;
			else if (curScore == INFINITY)
				curScore = INFINITY;
			else curScore -= opponent_score;
			return curScore;
		}
		for (positionTicTacToe child : children){
			int index = child.x * 16 + child.y * 4 + child.z;
			tempBoard.get(index).state = thinker;


			if (maximizer){
			    int hurValue = alphaBeta(tempBoard, depth - 1, alpha, beta, false);
				hurValueMax = Math.max(hurValueMax, hurValue);
				alpha = Math.max(alpha, hurValue);
				if (alpha >= beta){
					tempBoard.get(index).state = 0;
					break;
				}

//				String playerN = (maximizer? "player " : "opponent ") + "hurValue: " + hurValue + "position:" + child.x + "," + child.y + "," + child.z;
//				System.out.println(playerN);
			}else {
			    int hurValue = alphaBeta(tempBoard, depth - 1, alpha, beta, true);
				hurValueMax = Math.min(hurValueMax, hurValue);
				beta = Math.min(beta, hurValueMax);
				if (alpha >= beta){
					tempBoard.get(index).state = 0;
					break;
				}
//				String playerN = (maximizer? "player " : "opponent ") + "hurValue: " + hurValue + "position:" + child.x + "," + child.y + "," + child.z;
//				System.out.println(playerN);
			}
			tempBoard.get(index).state = 0;
		}

		return hurValueMax;
	}

	public positionTicTacToe randomAlgorithm(List<positionTicTacToe> board, int player){
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		do
		{
			Random rand = new Random();
			int x = rand.nextInt(4);
			int y = rand.nextInt(4);
			int z = rand.nextInt(4);
			myNextMove = new positionTicTacToe(x,y,z);
		}while(getStateOfPositionFromBoard(myNextMove,board)!=0);
		return myNextMove;
	}
	private List<List<positionTicTacToe>> initializeWinningLines()
	{
		//create a list of winning line so that the game will "brute-force" check if a player satisfied any 	winning condition(s).
		List<List<positionTicTacToe>> winningLines = new ArrayList<List<positionTicTacToe>>();
		
		//48 straight winning lines
		//z axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,j,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//y axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,j,-1));
				winningLines.add(oneWinCondtion);
			}
		//x axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,j,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 main diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,0,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,0,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,3,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 anti diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,3,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,3,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,3,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,0,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//4 additional diagonal winning lines
		List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,3,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,0,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(3,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(0,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,3,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,0,3,-1));
		winningLines.add(oneWinCondtion);	
		
		return winningLines;
		
	}
	public aiTicTacToe(int setPlayer)
	{
		player = setPlayer;
	}
}
