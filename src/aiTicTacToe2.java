import java.util.*;
public class aiTicTacToe2 {
    public int one = 1;
    public int two = 3;
    public int three = 7;
    public int four = 15;
    public int arg = 2;


	private List<List<positionTicTacToe>>  winningLines = new ArrayList<>();
	public final int NEGATIVE_INFINITY = Integer.MIN_VALUE;
	public final int INFINITY = Integer.MAX_VALUE;
	public int player; //1 for player 1 and 2 for player 2
    public class Help{
    	public positionTicTacToe pos;
    	public List<positionTicTacToe> board;
    	public int value;
    	public int nextMax = -1;
		public Help(positionTicTacToe pos, List<positionTicTacToe> board) {
		    this.pos = pos;
		    this.value = getScore(board,pos);
		    this.board = board;
		    //pos.printPosition();
		    //System.out.println("value: "+ value);
        }
        public int getNextMax(){
            List<positionTicTacToe> copied = deepCopyATicTacToeBoard(board);
            copied.get(pos.x * 16 + pos.y * 4 + pos.z).state = player;
            int max = -1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        int location = i * 16 + j * 4 + k;
                        positionTicTacToe temp = copied.get(location);
                        if (temp.state == 0){
                            Help nextHelp = new Help(temp,copied);
                            if(max < nextHelp.value)
                                max = nextHelp.value;
                        }
                    }
                }
            }
            nextMax = max;
            return max;
        }
	}

	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> targetBoard)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return targetBoard.get(index).state;
	}

	public int getScore(List<positionTicTacToe> board, positionTicTacToe pos) {
        int score = 0;
        board.get(pos.x * 16 + pos.y * 4 + pos.z).state = 1;
        score += getScoreFor(1,board);
        board.get(pos.x * 16 + pos.y * 4 + pos.z).state = 2;
        score += getScoreFor(2,board);
        board.get(pos.x * 16 + pos.y * 4 + pos.z).state = 0;
        return score;
    }

    public int getScoreFor(int player,List<positionTicTacToe> board){
        int score = 0;
        List<positionTicTacToe> ptnlWinMoves = new ArrayList<>();
		for (List<positionTicTacToe> line:winningLines) {
			int lineNum = 0;
			positionTicTacToe ptnlWinMove = new positionTicTacToe(0,0,0);
			for(positionTicTacToe position:line){
				int state = getStateOfPositionFromBoard(position, board);
				if(state == player)
					lineNum += 1;
				else if(state == 0){
                    ptnlWinMove = position;
					continue;
				}
				else{
				    lineNum = -1;
				    break;
                }
			}
			if(lineNum == -1)
			    continue;
			int temp = 0; //1<<(lineNum) - 1;//1->1; 2->3; 3->7; 4->15
            switch(lineNum){
                case 1:
                    temp = one;
                    break;
                case 2:
                    temp = two;
                    break;
                case 3:
                    temp = three;
                    break;
                case 4:
                    temp = four;
            }
			score += temp;
			if(temp == four){//find the wining step
			    boolean flag = true;
				for(positionTicTacToe wMove:ptnlWinMoves){
				    if(wMove.x == ptnlWinMove.x && wMove.y == ptnlWinMove.y && wMove.z == ptnlWinMove.z) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    ptnlWinMoves.add(ptnlWinMove);
                }
			}
		}
		score += ptnlWinMoves.size()*arg;
		return score;
	}

	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player) {
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
        List<Help> rec = new ArrayList<>();
        List<Help> rec_2 = new ArrayList<>();
        int max = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    int location = i * 16 + j * 4 + k;
                    positionTicTacToe temp = board.get(location);
                    if (temp.state == 0){
                        Help help = new Help(temp,board);
                        if(max <= help.value) {
                            max = help.value;
                            rec.add(help);
                        }
                    }
                }
            }
        }
        for(Help h:rec){
            if(h.value == max) {
                //h.pos.printPosition();
                //System.out.println("value:" + h.value);
                rec_2.add(h);
            }
        }

        if(rec_2.size()<2){
            return rec_2.get(0).pos;
        }
        rec.clear();
        //System.out.println(rec.size());
        int minMax = 99999;
        for(Help h:rec_2){
            if(h.getNextMax()<=minMax) {
                minMax = h.nextMax;
                rec.add(h);
            }
        }
        rec_2.clear();
        for(Help h:rec){
            if(h.nextMax == minMax)
                rec_2.add(h);
        }
        Random random = new Random();
        return rec_2.get(random.nextInt(rec_2.size())).pos;
			
		
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
	public aiTicTacToe2(int setPlayer)
	{
		player = setPlayer;
		winningLines = initializeWinningLines();
	}
}
