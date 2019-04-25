import java.util.*;
public class aiTicTacToe1 {

    public int player; //1 for player 1 and 2 for player 2
    private List<List<positionTicTacToe>>  winningLines = new ArrayList<>();
    private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
    {
        //a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
        int index = position.x*16+position.y*4+position.z;
        return board.get(index).state;
    }
    private int calculateHeuristicValue(List<positionTicTacToe> board, positionTicTacToe curPosition, int curPlayer){
        // check direction: x, y, z, diagnal, anti-diagnal
        // different layer: increasing/decreasing
        int maximum = 0;
        int availablePath = 0;
        int opponentMax = 0;
        // row
        int same_z_row_count = 0;
        int same_z_row_oppo = 0;
        for(int j = 0; j < 4; j++){
            if(j == curPosition.y)
                continue;
            positionTicTacToe curp = new positionTicTacToe(curPosition.x, j, curPosition.z);
            if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                same_z_row_count++;
            if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                same_z_row_oppo++;
        }
        if(same_z_row_oppo == 0) {
            maximum = Math.max(maximum, same_z_row_count);
            availablePath++;
        }
        opponentMax = Math.max(opponentMax, same_z_row_oppo);

        // col
        int same_z_col_count = 0;
        int same_z_col_oppo = 0;
        for(int i = 0; i < 4; i++){
            if(i == curPosition.x)
                continue;
            positionTicTacToe curp = new positionTicTacToe(i, curPosition.y, curPosition.z);
            if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                same_z_col_count++;
            if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                same_z_col_oppo++;
        }
        if(same_z_col_oppo == 0) {
            maximum = Math.max(maximum, same_z_col_count);
            availablePath++;
        }
        opponentMax = Math.max(opponentMax, same_z_col_oppo);

        // z
        int same_z_count = 0;
        int same_z_oppo = 0;
        for(int k = 0; k < 4; k++){
            if(k == curPosition.z)
                continue;
            positionTicTacToe curp = new positionTicTacToe(curPosition.x, curPosition.y, k);
            if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                same_z_count++;
            if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                same_z_oppo++;
        }
        if(same_z_oppo == 0) {
            maximum = Math.max(maximum, same_z_count);
            availablePath++;
        }
        opponentMax = Math.max(opponentMax, same_z_oppo);

        // check if on the diagnal/antidiagnal
        if(curPosition.x == curPosition.y){
            int same_z_diagnal_count = 0;
            int same_z_diagnal_oppo = 0;
            for(int i = 0; i < 3; i++){
                if(i == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(i, i, curPosition.z);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    same_z_diagnal_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    same_z_diagnal_oppo++;
            }
            if(same_z_diagnal_oppo == 0) {
                maximum = Math.max(maximum, same_z_diagnal_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, same_z_diagnal_oppo);
        }
        if(curPosition.x == (3 - curPosition.y)){
            int same_z_antidiagnal_count = 0;
            int same_z_antidiagnal_oppo = 0;
            for(int i = 0; i < 3; i++){
                if(i == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(i, 3-i, curPosition.z);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    same_z_antidiagnal_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    same_z_antidiagnal_oppo++;
            }
            if(same_z_antidiagnal_oppo == 0) {
                maximum = Math.max(maximum, same_z_antidiagnal_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, same_z_antidiagnal_oppo);
        }

        // row with diff z
        // increasing
        if(curPosition.y == curPosition.z){
            int diff_z_row_inc_count = 0;
            int diff_z_row_inc_oppo = 0;
            for(int j = 0; j < 4; j++) {
                if (j == curPosition.y)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(curPosition.x, j, j);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_row_inc_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_row_inc_oppo++;
            }
            if(diff_z_row_inc_oppo == 0) {
                maximum = Math.max(maximum, diff_z_row_inc_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_row_inc_oppo);
        }

        // decreasing
        if(curPosition.y == 3 - curPosition.z){
            int diff_z_row_dec_count = 0;
            int diff_z_row_dec_oppo = 0;
            for(int j = 0; j < 4; j++) {
                if (j == curPosition.y)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(curPosition.x, j, 3-j);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_row_dec_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_row_dec_oppo++;
            }
            if(diff_z_row_dec_oppo == 0) {
                maximum = Math.max(maximum, diff_z_row_dec_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_row_dec_oppo);
        }

        // col with diff z
        // increasing
        if(curPosition.x == curPosition.z){
            int diff_z_col_inc_count = 0;
            int diff_z_col_inc_oppo = 0;
            for(int i = 0; i < 4; i++) {
                if(i == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(i, curPosition.y, i);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_col_inc_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_col_inc_oppo++;
            }
            if(diff_z_col_inc_oppo == 0) {
                maximum = Math.max(maximum, diff_z_col_inc_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_col_inc_oppo);
        }
        // decreasing
        if(curPosition.x == 3-curPosition.z){
            int diff_z_col_dec_count = 0;
            int diff_z_col_dec_oppo = 0;
            for(int i = 0; i < 4; i++) {
                if(i == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(i, curPosition.y, 3-i);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_col_dec_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_col_dec_oppo++;
            }
            if(diff_z_col_dec_oppo == 0) {
                maximum = Math.max(maximum, diff_z_col_dec_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_col_dec_oppo);
        }


        // diagnal with diff z
        // increasing
        if(curPosition.x == curPosition.y && curPosition.x == curPosition.z){
            int diff_z_diagnal_inc_count = 0;
            int diff_z_diagnal_inc_oppo = 0;
            for(int k = 0; k < 4; k++){
                if(k == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(k, k, k);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_diagnal_inc_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_diagnal_inc_oppo++;
            }
            if(diff_z_diagnal_inc_oppo == 0) {
                maximum = Math.max(maximum, diff_z_diagnal_inc_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_diagnal_inc_oppo);
        }
        // decreasing
        if(curPosition.x == curPosition.y && curPosition.x == 3 - curPosition.z){
            int diff_z_diagnal_dec_count = 0;
            int diff_z_diagnal_dec_oppo = 0;
            for(int k = 0; k < 4; k++){
                if(k == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(k, k, 3-k);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_diagnal_dec_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_diagnal_dec_oppo++;
            }
            if(diff_z_diagnal_dec_oppo == 0) {
                maximum = Math.max(maximum, diff_z_diagnal_dec_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_diagnal_dec_oppo);
        }

        // anti-diagnal with diff z
        // increasing
        if(curPosition.x == 3-curPosition.y && curPosition.x == curPosition.z){
            int diff_z_antidiagnal_inc_count = 0;
            int diff_z_antidiagnal_inc_oppo = 0;
            for(int k = 0; k < 4; k++){
                if(k == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(k, 3-k, k);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_antidiagnal_inc_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_antidiagnal_inc_oppo++;
            }
            if(diff_z_antidiagnal_inc_oppo == 0) {
                maximum = Math.max(maximum, diff_z_antidiagnal_inc_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_antidiagnal_inc_oppo);
        }
        // decreasing
        if(curPosition.x == 3-curPosition.y && curPosition.y == curPosition.z){
            int diff_z_antidiagnal_dec_count = 0;
            int diff_z_antidiagnal_dec_oppo = 0;
            for(int k = 0; k < 4; k++){
                if(k == curPosition.x)
                    continue;
                positionTicTacToe curp = new positionTicTacToe(k, 3-k, 3-k);
                if(getStateOfPositionFromBoard(curp ,board) == curPlayer)
                    diff_z_antidiagnal_dec_count++;
                if(getStateOfPositionFromBoard(curp ,board) == (curPlayer == 1 ? 2 : 1))
                    diff_z_antidiagnal_dec_oppo++;
            }
            if(diff_z_antidiagnal_dec_oppo == 0) {
                maximum = Math.max(maximum, diff_z_antidiagnal_dec_count);
                availablePath++;
            }
            opponentMax = Math.max(opponentMax, diff_z_antidiagnal_dec_oppo);
        }
        // self 0-3 oppo 0-3
        int[] weight = {(int)Math.pow(10, 0), (int)Math.pow(10, 1), (int)Math.pow(10, 2), (int)Math.pow(100, 3),
                (int)Math.pow(10, 0), (int)Math.pow(10, 0), (int)Math.pow(10, 1), (int)Math.pow(10, 3)};

        return (curPlayer == player ? 1 : -1) * Math.max(weight[maximum] + availablePath, weight[opponentMax]);
    }



    private List<positionTicTacToe> getAllEmptyPosition(List<positionTicTacToe> board){
        List<positionTicTacToe> emptyPosition = new ArrayList<positionTicTacToe>();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                for(int k = 0; k < 4; k++)
                {
                    positionTicTacToe curPosition = new positionTicTacToe(i, j, k);
                    if(getStateOfPositionFromBoard(curPosition, board) == 0)
                        emptyPosition.add(curPosition);
                }
        return emptyPosition;
    }

    private int minimax(List<positionTicTacToe> board, int level, boolean curPlayer){
        List<positionTicTacToe> emptyPosition = getAllEmptyPosition(board);

        if(level == 0 || emptyPosition.size() == 0)
            return curPlayer? Integer.MAX_VALUE : Integer.MIN_VALUE;

        int curHeuristicValue = curPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for(positionTicTacToe position: emptyPosition){
            int curValue = calculateHeuristicValue(board, position, curPlayer ? player : (player == 1 ? 2 : 1));

            int index = position.x*16+position.y*4+position.z;
            board.get(index).state = curPlayer ? player : (player == 1 ? 2 : 1);

            int heuValue = curPlayer ? minimax(board, level-1, false) : minimax(board, level-1, true);

            heuValue = curPlayer ? (heuValue > Integer.MIN_VALUE ? heuValue : curValue) : (heuValue < Integer.MAX_VALUE ? heuValue : curValue);

            curHeuristicValue = curPlayer ? Math.max(curHeuristicValue, heuValue) : Math.min(curHeuristicValue, heuValue);

            String playerN = (curPlayer ? "Player" : "Opponent") + "-heuValue " + heuValue + "-position " + position.x + "," + position.y + "," + position.z;
            System.out.println(playerN);

            board.get(index).state = 0;
        }
        String result = (curPlayer ? "Player" : "opponent" ) + "=" + curHeuristicValue;
        System.out.println(result);

        return curHeuristicValue;
    }

    public positionTicTacToe myAIAlgorithm_random(List<positionTicTacToe> board, int player)
    {
        //TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
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


    public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
    {
        //TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
        List<positionTicTacToe> emptyPosition = getAllEmptyPosition(board);

        int heuValueMax = Integer.MIN_VALUE;
        positionTicTacToe resultPosi = null;
        for(positionTicTacToe position: emptyPosition){
            int initHeuValue = calculateHeuristicValue(board, position, player);
            int index = position.x*16+position.y*4+position.z;
            board.get(index).state = player;

            int heuValue = minimax(board, 1, false);
            heuValue = heuValue > Integer.MIN_VALUE ? heuValue : initHeuValue;
            if(heuValue > heuValueMax){
                heuValueMax = heuValue;
                resultPosi = position;
            }
            board.get(index).state = 0;
        }
        return resultPosi;
    }

    public positionTicTacToe myAIAlgorithm_func2(List<positionTicTacToe> board, int player)
    {
        //TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
        List<positionTicTacToe> emptyPosition = getAllEmptyPosition(board);
        int heuValueMax = Integer.MIN_VALUE;
        positionTicTacToe resultPosi = null;
        for(positionTicTacToe position: emptyPosition){
            int initHeuValue = calculateHeuristicValue(board, position, player);
            int index = position.x*16+position.y*4+position.z;
            board.get(index).state = player;
            int heuValue = minimax(board, 0, false);
            heuValue = heuValue > Integer.MIN_VALUE ? heuValue : initHeuValue;
            if(heuValue > heuValueMax){
                heuValueMax = heuValue;
                resultPosi = position;
            }
            board.get(index).state = 0;
        }
        return resultPosi;
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
    public aiTicTacToe1(int setPlayer)
    {
        player = setPlayer;
        winningLines = initializeWinningLines();
    }
}

