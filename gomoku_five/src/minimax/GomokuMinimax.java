package minimax;

import java.util.Scanner;

public class GomokuMinimax {

	static int player = 1;
	static int opponent = 2;
	// static int row;
	static int cnt = 0;
	int score1 = 0;
	static int c = 0, d = 6, e = 0, f = 6,dt=3,g=3;
	static int Point[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

	};
	public static int a,b;
	public static int[][] board ;
	public static Move getAIMove() {

		// System.out.printf("The Optimal Move is :\n");
		// System.out.printf("ROW: %d COL: %d\n\n", bestMove.row,
		// bestMove.col );
//		print();
		//int turn = 1;
		Move bestMove = null;
		
		//while (isMovesLeft()) {
			cnt = 0;
			// checkGameOver();
			dimesionShortening(a, b, 1);
			int numberOfCubeOnDimension=cubeCount();
			if(numberOfCubeOnDimension<6){
				dt=2;
				g=1;
			}
//			else if(numberOfCubeOnDimension<2) {
//				dt=3;
//				g
//			}
			else {
////				g=4;
//				c=0;
//				d=9,e=0,f=9;
				initialization();
				dt=2;
			}
//			dimesionShortening(a, b, g);
//				c = b - 3;
//				d = b + 3;
//				e = a - 3;
//				f = a + 3;
//				// System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f);
//
//				if (c < 0) {
//					c = c * (-1);
//					d += c;
//					c = 0;
//				}
//				if (e < 0) {
//					e = e * (-1);
//					f += e;
//					e = 0;
//				}
//				if (d > 9) {
//					d = d - 9;
//					c = c - d;
//					d = 9;
//				}
//				if (f > 9) {
//					f = f - 9;
//					e = e - f;
//					f = 9;
//				}
			
				// System.out.println(c+" "+d+" "+e+" "+f);

				bestMove = findBestMove();
				//System.out.println(bestMove.row+" "+bestMove.col);
				//board[bestMove.row][bestMove.col] = 2;
				
		
		return bestMove;
		//System.out.println("Match drawn!");
		

	}
	private static void initialization() {
		c=0;
		d=9;
		e=0;
		f=9;
	}
	private static int cubeCount() {
		// TODO Auto-generated method stub
		int count=0;
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				if(board[i][j]==1||board[i][j]==2){
					++count;
				}
			}
		}
		return count;
	}


	private static void dimesionShortening(int a,int b,int g){

		c=a-g;
		d=a+g;
		e=b-g;
		f=b+g;
//		System.out.println("kkkkkkk"+" "+e+" "+f+" "+g+" a b "+a+" "+b);
		if(c<0){
			c=c*(-1);
			d+=c;
			c=0;
		}
		if(e<0){
			e=e*(-1);
			f+=e;
			e=0;
		}
		if(d>9){
			d=d-9;
			c=c-d;
			d=9;
		}
		if(f>9){
			f=f-9;
			e=e-f;
			f=9;
		}
	}
	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(board[i][j] + "\t");
				// System.out.println();
			}
			System.out.println("\n");
		}

	}

	// This function returns true if there are moves
	// remaining on the board. It returns false if
	// there are no moves left to play.
	static boolean isMovesLeft() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (board[i][j] == 0)
					return true;
		return false;
	}

	static int evaluate(int b[][], int dp) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 8; row++) {

			for (int j = 0; j < 3; j++) {
				if (b[row][j] == b[row][j + 1] && b[row][j + 1] == b[row][j + 2]) {
					if (b[row][j] == player)
						return -10;
					else if (b[row][j] == opponent)
						return +10;
				}
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 8; col++) {
			for (int i = 0; i < 3; i++) {
				if (b[i][col] == b[i + 1][col] && b[i + 1][col] == b[i + 2][col]) {
					if (b[i][col] == player)
						return -10;

					else if (b[i][col] == opponent)
						return +10;
				}
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return -10;
			else if (b[0][0] == opponent)
				return +10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return -10;
			else if (b[0][2] == opponent)
				return +10;
		}

		// Else if none of them have won then return 0
		return 0;
	}

	static int minimax(int depth, boolean isMax, int alpha, int beta)

	{

		// System.out.println(depth+" "+cnt++);
//		cnt++;
		// int score = evaluate(board,depth);
		// int score = winChecker();
		int score;
		// if(isMax)
		// score = evaluateValue(2);
		// else score = evaluateValue(1);
		score = winChecker();
		// if(score>1000)
		// System.out.println(score);
		// If Maximizer has won the game return his/her
		// evaluated score
		if (score == 1000000000)
			return score;

		// If Minimizer has won the game return his/her
		// evaluated score
		if (score == -1000000000)
			return score;

		// If there are no more moves and no winner then
		// it is a tie
		if (isMovesLeft() == false)
			return 0;

		if (depth == dt) {
			if (isMax) {
				return evaluateValue(2);
			} else
				return evaluateValue(1);
		}

		// If this maximizer's move
		if (isMax) {

			int best = -1000000000;
			// if(depth==4) return best;
//			if (cnt > 60000000)
//				return best;
			// if(depth==3) return best;
			// Traverse all cells
			// if(cnt>100000) return 0;
			for (int i = c; i <=d; i++) {
				for (int j = e; j <=f; j++) {
					// Check if cell is empty
					if (board[i][j] == 0) {
						// Make the move
						board[i][j] = 2;

						// Call minimax recursively and choose
						// the maximum value
						best = Math.max(best, minimax(depth + 1, !isMax, alpha, beta));
						// System.out.println(best);
						alpha = Math.max(alpha, best);

						// Undo the move
						board[i][j] = 0;
						if (beta <= alpha) {
							// System.out.println("Alpha--"+alpha+"Beta--"+beta);
							break;

						}
					}
				}
			}
			return best;
		}

		// If this minimizer's move
		else {
			int best = 1000000000;
			// if(depth==4) return best;
			// if(cnt>100000) return 0;
			// if(cnt>500000) return best;
//			if (cnt > 60000000)
//				return best;

			// Traverse all cells
			for (int i = c; i <=d; i++) {
				for (int j = e; j <=f; j++) {
					// Check if cell is empty
					if (board[i][j] == 0) {
						// Make the move
						board[i][j] = 1;

						// Call minimax recursively and choose
						// the minimum value
						best = Math.min(best, minimax(depth + 1, !isMax, alpha, beta));
						// System.out.println(best);

						beta = Math.min(best, beta);
						// Undo the move
						board[i][j] = 0;
						if (beta <= alpha) {
							// System.out.println("Alpha--"+alpha+"Beta--"+beta);
							break;

						}

					}

				}
			}
			return best;
		}
	}

	static Move findBestMove() {
		int bestVal = -1000000000;
		Move bestMove = new Move(-1, -1);
		// row = -1;
		// col = -1;

		// Traverse all cells, evalutae minimax function for
		// all empty cells. And return the cell with optimal
		// value.

		for (int i = c; i <=d; i++) {
			for (int j = e; j <=f; j++) {
				// Check if celll is empty
				// System.out.println("Hello"+i*8+j);
				if (board[i][j] == 0) {
					// Make the move
					board[i][j] = 2;
					if(winChecker()==1000000000) {
						bestMove.row = i;
						bestMove.col = j;
						return bestMove;
					}

					// compute evaluation function for this
					// move.
					int moveVal = minimax(0, false, -1000000000, 1000000000);
					System.out.println(moveVal+" "+i+" "+j);

					// Undo the move
					board[i][j] = 0;

					// If the value of the current move is
					// more than the best value, then update
					// best/
					if(moveVal==-1000000000) {
						if (moveVal >= bestVal) {
							bestMove.row = i;
							bestMove.col = j;
							bestVal = moveVal;
						}
					}
					else {
						if (moveVal > bestVal) {
							bestMove.row = i;
							bestMove.col = j;
							bestVal = moveVal;
						}
					}
					
				}
			}
		}

		System.out.printf("The value of the best Move is : %d and cnt is %d row is %d collumn is %d\n\n", bestVal, cnt,
				bestMove.row, bestMove.col);

		return bestMove;
	}

	private static int winChecker() {

		int checkFor = 1;
		if (checkHorizonatlly(checkFor) || checkRightDiagonally(checkFor) || checkVertically(checkFor)
				|| checkLeftDiagonally(checkFor)) {
			return -1000000000;

		}

		checkFor = 2;
		if (checkHorizonatlly(checkFor) || checkRightDiagonally(checkFor) || checkVertically(checkFor)
				|| checkLeftDiagonally(checkFor)) {
			return +1000000000;

		}
		return 0;

	}

	private static boolean checkHorizonatlly(int checkFor) {
		int size = board.length;
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			int c = 0;
			for (int j = 0; j < size; j++) {
				if (board[i][j] == checkFor)
					c++;
				else
					c = 0;

				if (c == 5) {
					flag = true;
					break;
				}

			}
		}
		return flag;
	}

	private static boolean checkVertically(int checkFor) {
		int size = board.length;
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			int c = 0;
			for (int j = 0; j < size; j++) {
				if (board[j][i] == checkFor)
					c++;
				else
					c = 0;

				if (c == 5) {
					flag = true;
					break;
				}

			}
		}

		return flag;
	}

	private static boolean checkRightDiagonally(int checkFor) {
		int size = board.length;
		boolean flag = false;
		int c = 0;
		// major diag
		for (int i = 0; i < size; i++) {
			if (board[i][i] == checkFor) {
				c++;
			} else
				c = 0;
			if (c == 5)
				return true;
		}
		for (int i = 1; i <= size - 5; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[m++][j] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}
		}
		for (int i = 1; i <= size - 5; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[j][m++] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}

		}

		return flag;

	}

	private static boolean checkLeftDiagonally(int checkFor) {
		int size = board.length;
		int c;
		for (int i = size - 6; i < size; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j >= 0; j--) {
				if (board[m++][j] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}

		}

		for (int i = 1; i <= size - 5; i++) {
			int m = size - 1;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[j][m] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;

			}
		}
		return false;

	}

	// Evolution function

	public static int evaluateValue(int current_turn) {
		int cnt12 = 0;
		++cnt;
		Point = new int[10][10];
		analyzeHorizontalSets(current_turn);
		analyzeVerticalSets(current_turn);
		checkRightDiagonallyEvaluate(current_turn);
		checkLeftDiagonallyEvaluate(current_turn);
		// int [] maxArray = new int [2];
		// Move m = new Move(-1, -1);
		// System.out.println("Evaluate Value"+ " "+cnt12);
		if(cnt==83
				) {
    		System.out.println('\n'+" "+"Boaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaard");
//    		System.out.println("kkkkkkkkkkkkk");
    		for(int i=0;i<10;++i) {
	    		for(int j=0;j<10;++j) {
//	    			System.out.print(i+j);
	    			System.out.print(board[i][j]+" \t");
//	    			System.out.println(dx+" "+dy);
	    		}
	    		System.out.println("\n");
	    	}
    		System.out.println("Pooooooooooooooooooooooooooooooooooooooooooooooint");
	    	for(int i=0;i<10;++i) {
	    		for(int j=0;j<10;++j) {
//	    			System.out.print(i+j);
	    			System.out.print(Point[i][j]+" \t");
//	    			System.out.println(dx+" "+dy);
	    		}
	    		System.out.println("\n");
	    	}
//    	cnt12++;
    	}

		int max = valueReturn();

		return max;

	}

	// Value return

	public static int valueReturn() {
		int max = 0;
		int[] maxArray = new int[2];
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				// if(Point[i][j]>=max){
				// max=Point[i][j];
				// maxArray[0]=i;
				// maxArray[1]=j;
				// }
				max += Point[i][j];
			}
		}
		return max;
	}

	// Vertical Checking

    static void analyzeVerticalSets(int current_turn)
    {
//    	var score = 0;
//    	var countConsecutive = 0;
//    	var openEnds = 0;
//    	 = 0;
        int ccOwn=0,ccOp=0,openEnds=0,collumn = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int a = 0; a < 10; a++)
            {
                collumn=a;
                if(board[a][i]==2)
                {
                    ccOwn++;
                    if(ccOp>0 )
                    {
                        if(openEnds>0)
                        	if(current_turn==1)
                            Point[a-(ccOp+1)][i]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                        else
                        Point[a-(ccOp+1)][i]+=gomokuShapeScore2(ccOp,openEnds,1);

                        ccOp=0;
                        openEnds=0;
                    }
                }

                else if(board[a][i]==1)
                {
                    ccOp++;

                    if(ccOwn>0)
                    {
//                    	if(openEnds>0)
//                    	System.out.println(a+" "+i+" "+openEnds);
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[a-(ccOwn+1)][i]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        else
                            Point[a-(ccOwn+1)][i]+=gomokuShapeScore1(ccOwn,openEnds,2);

                        ccOwn=0;
                        openEnds=0;
                    }
                }
                else if (board[a][i] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    Point[a][i] += gomokuShapeScore2(ccOwn,
                                                    openEnds, current_turn);
                    
                    else
                    Point[a][i] += gomokuShapeScore1(ccOwn,
                                                    openEnds, 2);
                    ccOwn = 0;
                    openEnds = 1;
                }

                else if (board[a][i] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn==1)
                    Point[a][i] += gomokuShapeScore1(ccOp,
                                                    openEnds, current_turn);
                    else
                    Point[a][i] += gomokuShapeScore2(ccOp,
                                                    openEnds, 1);
                    ccOp = 0;
                    openEnds = 1;
                }

                else if (board[a][i] == 0)
                    openEnds = 1;
//    			else if (countConsecutive > 0) {
//    				score += gomokuShapeScore(countConsecutive,
//    					openEnds, current_turn == 1);
//    				countConsecutive = 0;
//    				openEnds = 0;
//    			}
                else openEnds = 0;
            }
            if(ccOwn>0&& openEnds>0)
            {
                if(current_turn==2)
                Point[board.length-(ccOwn)][i]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                else
                    Point[board.length-(ccOwn)][i]+=gomokuShapeScore1(ccOwn,openEnds,2);

            }
            else if(ccOp>0&& openEnds>0)
            {                
            	if(current_turn==1)

                Point[board.length-(ccOp)][i]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
            	else
                    Point[board.length-(ccOp)][i]+=gomokuShapeScore2(ccOp,openEnds,1);

            }
            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }
    }

	// Horizontal Check

    static void analyzeHorizontalSets(int current_turn)
    {
//    	var score = 0;
//    	var countConsecutive = 0;
//    	var openEnds = 0;
    	
        int ccOwn=0,ccOp=0,openEnds=0;

        for (int i = 0; i < board.length; i++)
        {
            for (int a = 0; a < board[i].length; a++)
            {
//    			if (board[i][a] == 'B')
//    				countConsecutive
//            	System.out.println(i+" "+a+" "+ccOp);
                if(board[i][a]==2)
                {
                    ccOwn++;
                    if(ccOp>0)
                    {
//                    	if(openEnds>0)
	                        if(openEnds>0)
	                        	if(current_turn==1)
	                            Point[i][a-(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
	                        else
	                            Point[i][a-(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);
                        ccOp=0;
                        openEnds=0;
                    }
                }
                else if(board[i][a]==1)
                {
                    ccOp++;
                    if(ccOwn>0)
                    {
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[i][a-(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        else
                            Point[i][a-(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);
                        ccOwn=0;
                        openEnds=0;
                    }
                }
                else if (board[i][a] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    	Point[i][a] += gomokuShapeScore2(ccOwn, openEnds, current_turn);
                    else
                    	Point[i][a] += gomokuShapeScore1(ccOwn, openEnds, 2);
                    ccOwn = 0;
                    openEnds = 1;
                }
                else if (board[i][a] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn==1)
                    	Point[i][a] += gomokuShapeScore1(ccOp,
                                                    openEnds, current_turn);
                    else
                    	Point[i][a] += gomokuShapeScore2(ccOp,
                                openEnds, 1);

                    ccOp = 0;
                    openEnds = 1;
                }
                else if (board[i][a] == 0)
                    openEnds = 1;
//    			else if (countConsecutive > 0) {
//    				score += gomokuShapeScore(countConsecutive,
//    					openEnds, current_turn == '1');
//    				countConsecutive = 0;
//    				openEnds = 0;
//    			}
                else openEnds = 0;
            }
//    		if (countConsecutive > 0)
//    			score += gomokuShapeScore(countConsecutive,
//    				openEnds, current_turn == '1');
            if(ccOwn>0&& openEnds>0)
            {
                if(current_turn==2)
                Point[i][board[i].length-(ccOwn)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                else
                Point[i][board[i].length-(ccOwn)]+=gomokuShapeScore1(ccOwn,openEnds,2);

            }
            else if(ccOp>0&& openEnds>0)
            {
                if(current_turn==1)
                Point[i][board[i].length-(ccOp)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                else
                Point[i][board[i].length-(ccOp)]+=gomokuShapeScore2(ccOp,openEnds,1);

            }
            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }
//    	return Point;
    }

	// Gomoku Shape Score

	static int gomokuShapeScore1(int consecutive, int openEnds, int currentTurn) {

		// if(winChecker()==1000000000) return 1000000000;
		// if(winChecker()==-1000000000) return -1000000000;
		if (openEnds == 0 && consecutive < 5)
			return 0;
		switch (consecutive) {
		case 4:
			switch (openEnds) {
			case 1:
				if (currentTurn == 1)
					return -10000000;
				return 10000000;
			case 2:
				if (currentTurn == 1)
					return -1000000000;
				return 100000000;
			}
		case 3:
			switch (openEnds) {
			case 1:
				if (currentTurn == 1)
					return -10000;
				return 10000;
			case 2:
				if (currentTurn == 1)
					return -100005;
				return 100000;
			}
		case 2:
			switch (openEnds) {
			case 1:
				if (currentTurn == 1)
					return -100;
				return 100;
			case 2:
				if (currentTurn == 1)
					return -1000;
				return 1000;
			}
		case 1:
			switch (openEnds) {
			case 1:
				if (currentTurn == 1)
					return -1;
				return 1;
			case 2:
				if (currentTurn == 1)
					return -10;
				return 10;
			}
		default:
			return -200000000;
		}
	}

	static int gomokuShapeScore2(int consecutive, int openEnds, int currentTurn) {

		// if(winChecker()==1000000000) return 1000000000;
		// if(winChecker()==-1000000000) return -1000000000;
		if (openEnds == 0 && consecutive < 5)
			return 0;
		switch (consecutive) {
		case 4:
			switch (openEnds) {
			case 1:
				if (currentTurn == 2)
					return 10000000;
				return -10000000;
			case 2:
				if (currentTurn == 2)
					return 100000000;
				return -1000000000;
			}
		case 3:
			switch (openEnds) {
			case 1:
				if (currentTurn == 2)
					return 10000;
				return -10000;
			case 2:
				if (currentTurn == 2)
					return 100000;
				return -100005;
			}
		case 2:
			switch (openEnds) {
			case 1:
				if (currentTurn == 2)
					return 100;
				return -100;
			case 2:
				if (currentTurn == 2)
					return 1000;
				return -1000;
			}
		case 1:
			switch (openEnds) {
			case 1:
				if (currentTurn == 2)
					return 1;
				return -1;
			case 2:
				if (currentTurn == 2)
					return 10;
				return -10;
			}
		default:
			return 200000000;
		}
	}

	// Check left to right diagonal

    private static void checkRightDiagonallyEvaluate(int current_turn)
    {
        int ccOwn=0,ccOp=0,openEnds=0,collumn;

        int size = board.length;
        boolean flag = false;
        int c=0;
        //int current_turn = 0;
        //major diag
        for(int i=0; i<size; i++)
        {

            if(board[i][i]==2)
            {
                ccOwn++;
                if(ccOp>0 )
                {
                    if(openEnds>0)
                    	if(current_turn==1)
                    	
                        Point[i-(ccOp+1)][i-(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                    else
                        Point[i-(ccOp+1)][i-(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);
                    ccOp=0;
                    openEnds=0;
                }
            }
            else if(board[i][i]==1)
            {
                ccOp++;

                if(ccOwn>0)
                {
                    if(openEnds>0)
                    	if(current_turn==2)
                        Point[i-(ccOwn+1)][i-(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                    else
                    Point[i-(ccOwn+1)][i-(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);

                    ccOwn=0;
                    openEnds=0;
                }
            }
            else if (board[i][i] == 0 && ccOwn > 0)
            {
                openEnds++;
                if(current_turn==2)
                Point[i][i] += gomokuShapeScore2(ccOwn,
                                                openEnds, current_turn);
                else
                Point[i][i] += gomokuShapeScore1(ccOwn,
                                                openEnds, 2);
                
                ccOwn = 0;
                openEnds = 1;
            }

            else if (board[i][i] == 0 && ccOp > 0)
            {
                openEnds++;
                if(current_turn==1)
                Point[i][i] += gomokuShapeScore1(ccOp,
                                                openEnds, current_turn);
                else
                Point[i][i] += gomokuShapeScore2(ccOp,
                                                openEnds, 1);
                ccOp = 0;
                openEnds = 1;
            }
            else if (board[i][i] == 0)
                openEnds = 1;

            else openEnds = 0;

        }
        if(ccOwn>0 && openEnds>0) {
        	Point[board.length-ccOwn][board.length-ccOwn]+=gomokuShapeScore1(ccOwn, openEnds, 2);
        }
        else if(ccOp>0 && openEnds>0) {
        	Point[board.length-ccOp][board.length-ccOp]+=gomokuShapeScore1(ccOp, openEnds, 1);
        }

        ccOwn = 0;
        ccOp = 0;
        openEnds = 0;
        for(int i=1; i<size; i++)
        {
            int m = 0;
//    			c=0;
            for(int j=i; j<size; j++)
            {
//    				if(board[j][m++] == checkFor)
//    					c++;
//    				else
//    					c=0;
//    				if(c==5)
//    					return true;
//            	if(m<9)
//            	System.out.println(m+""+j);
                if(board[m][j]==2)
                {
                    ccOwn++;
                    if(ccOp>0 )
                    {
                        if(openEnds>0)
                        	if(current_turn==1)
                            Point[(m)-(ccOp+1)][(j)-(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                        else
                        Point[(m)-(ccOp+1)][(j)-(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);

                        ccOp=0;
                        openEnds=0;
                    }
                    m++;
                }
                else if(board[m][j]==1)
                {
                    ccOp++;

                    if(ccOwn>0)
                    {
//                    	System.out.println(965+" "+m+" "+j+" "+ccOwn);
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[(m)-(ccOwn+1)][(j)-(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        else
                        Point[(m)-(ccOwn+1)][(j)-(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);

                        ccOwn=0;
                        openEnds=0;
                    }
                    m++;
                }
                else if (board[m][j] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    Point[m][j] += gomokuShapeScore2(ccOwn,
                                                      openEnds, current_turn);
                    else
                    Point[m][j] += gomokuShapeScore1(ccOwn,
                                                      openEnds, 2);
                    ccOwn = 0;
                    openEnds = 1;
                    m++;
                }

                else if (board[m][j] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn==1)
                    Point[m][j] += gomokuShapeScore1(ccOp,
                                                      openEnds, current_turn );
                    else
                        Point[m][j] += gomokuShapeScore2(ccOp,
                                openEnds, 1 );
                    	
                    ccOp = 0;
                    openEnds = 1;
                    m++;
                }
                else if (board[m++][j] == 0)
                    openEnds = 1;

                else openEnds = 0;

            }
            m--;
            if(ccOwn>0 && openEnds >0)
            	Point[m-ccOwn][board.length-ccOwn]+=gomokuShapeScore1(ccOwn, openEnds, 2);

            else if(ccOp>0 && openEnds >0)
            	Point[m-ccOp][board.length-ccOp]+=gomokuShapeScore1(ccOp, openEnds, 1);
            

            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }

        ccOwn = 0;
        ccOp = 0;
        openEnds = 0;
        for(int i=1; i<size; i++)
        {
            int m = 0;
//    			c=0;
            for(int j=i; j<size; j++)
            {
//    				if(board[j][m++] == checkFor)
//    					c++;
//    				else
//    					c=0;
//    				if(c==5)
//    					return true;
            	if(m<9)
                if(board[j][m]==2)
                {
                    ccOwn++;
                    if(ccOp>0 )
                    {
                        if(openEnds>0)
                        	if(current_turn==1)
                            Point[(j)-(ccOp+1)][(m)-(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                        else
                            Point[(j)-(ccOp+1)][(m)-(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);
                        ccOp=0;
                        openEnds=0;
                    }
                    m++;
                }
            	
                else if(m<9&&board[j][m]==1)
                {
                    ccOp++;

                    if(ccOwn>0)
                    {
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[(j)-(ccOwn+1)][(m)-(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        else
                            Point[(j)-(ccOwn+1)][(m)-(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);
                        ccOwn=0;
                        openEnds=0;
                    }
                    m++;
                }
                else if (board[j][m] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    Point[j][m] += gomokuShapeScore2(ccOwn,
                                                      openEnds, current_turn );
                    else
                    Point[j][m] += gomokuShapeScore1(ccOwn,
                                                      openEnds, 2 );
                    ccOwn = 0;
                    openEnds = 1;
                    m++;
                }

                else if (board[j][m] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn==1)
                    Point[j][m] += gomokuShapeScore1(ccOp,
                                                      openEnds, current_turn );
                    else
                        Point[j][m] += gomokuShapeScore2(ccOp,
                                openEnds, 1 );
                    	
                    ccOp = 0;
                    openEnds = 1;
                    m++;
                }
                else if (board[j][m++] == 0)
                    openEnds = 1;

                else openEnds = 0;

            }
            m--;
            if(ccOwn>0&&openEnds>0)
            	Point[board.length-ccOwn][m-ccOwn]+=gomokuShapeScore1(ccOwn, openEnds, 2);
            if(ccOp>0&&openEnds>0)
            	Point[board.length-ccOp][m-ccOp]+=gomokuShapeScore1(ccOp, openEnds, 1);

            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }

        ccOwn = 0;
        ccOp = 0;
        openEnds = 0;

    }

	// return flag;

	// }

	// Check right to left diagonal

    private static  void checkLeftDiagonallyEvaluate(int current_turn)
    {
        int size = board.length;
//    		int c;
        int ccOwn=0,ccOp=0,openEnds=0,collumn=0;
        //int current_turn = 0;
        for(int i=0; i<size; i++)
        {
            int m=0;
//    			c=0;
            for(int j=i; j>=0; j--)
            {
//    				if(board[m++][j] == checkFor)
//    					c++;
//    				else
//    					c=0;
//    				if(c == 5)
//    					return true;

                if(board[m][j]==2)
                {
                    ccOwn++;
                    if(ccOp>0 )
                    {
                    	if(openEnds>0)
                        if(openEnds>0&&current_turn==1)
                            Point[(m)-(ccOp+1)][(j)+(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                        else
                        Point[(m)-(ccOp+1)][(j)+(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);

                        ccOp=0;
                        openEnds=0;
                    }
                    m++;
                }
                else if(board[m][j]==1)
                {
                    ccOp++;

                    if(ccOwn>0)
                    {
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[(m)-(ccOwn+1)][(j)+(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        	else
                                Point[(m)-(ccOwn+1)][(j)+(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);
                        ccOwn=0;
                        openEnds=0;
                    }
                    m++;
                }
                else if (board[m][j] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    Point[m][j] += gomokuShapeScore2(ccOwn,
                                                      openEnds, current_turn );
                    else
                    Point[m][j] += gomokuShapeScore1(ccOwn,
                                                      openEnds, 2 );
                    ccOwn = 0;
                    openEnds = 1;
                    m++;
                }

                else if (board[m][j] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn==1)
                    Point[m][j] += gomokuShapeScore1(ccOp,
                                                      openEnds, current_turn );
                    else
                    Point[m][j] += gomokuShapeScore2(ccOp,
                                                      openEnds, 1 );
                    ccOp = 0;
                    openEnds = 1;
                    m++;
                }
                else if (board[m++][j] == 0)
                    openEnds = 1;

                else openEnds = 0;


            }

            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;

        }

        ccOwn = 0;
        ccOp = 0;
        openEnds = 0;

        for(int i=1; i<size; i++)
        {
            int m=size-1;
//            c=0;
            for(int j=i; j<size; j++)
            {
//                if(board[j][m--] == checkFor)
//                    c++;
//                else
//                    c=0;
//                if(c == 5)
//                    return true;
                if(board[j][m]==2)
                {
                    ccOwn++;
                    if(ccOp>0 )
                    {
                        if(openEnds>0)
                        	if(current_turn==1)
                            Point[(j)-(ccOp+1)][(m)+(ccOp+1)]+=gomokuShapeScore1(ccOp,openEnds,current_turn);
                        	else
                                Point[(j)-(ccOp+1)][(m)+(ccOp+1)]+=gomokuShapeScore2(ccOp,openEnds,1);

                        ccOp=0;
                        openEnds=0;
                    }
                    m--;
                }
                else if(board[j][m]==1)
                {
                    ccOp++;

                    if(ccOwn>0)
                    {
                        if(openEnds>0)
                        	if(current_turn==2)
                            Point[(j)-(ccOwn+1)][(m)+(ccOwn+1)]+=gomokuShapeScore2(ccOwn,openEnds,current_turn);
                        	else
                                Point[(j)-(ccOwn+1)][(m)+(ccOwn+1)]+=gomokuShapeScore1(ccOwn,openEnds,2);
                        ccOwn=0;
                        openEnds=0;
                    }
                    m--;
                }
                else if (board[j][m] == 0 && ccOwn > 0)
                {
                    openEnds++;
                    if(current_turn==2)
                    Point[j][m] += gomokuShapeScore2(ccOwn,
                                                      openEnds, current_turn );
                    else
                        Point[j][m] += gomokuShapeScore1(ccOwn,
                                openEnds, 2 );
                    	
                    ccOwn = 0;
                    openEnds = 1;
                    m--;
                }

                else if (board[j][m] == 0 && ccOp > 0)
                {
                    openEnds++;
                    if(current_turn ==1)
                    Point[j][m] += gomokuShapeScore1(ccOp,
                                                      openEnds, current_turn );
                    else

                        Point[j][m] += gomokuShapeScore2(ccOp,
                                                          openEnds, 1 );
                    ccOp = 0;
                    openEnds = 1;
                    m--;

                }
                else if (board[j][m--] == 0)
                    openEnds = 1;

                else openEnds = 0;

    			

            }
            m++;
//            if(ccOwn>0&&openEnds>0) {
//            	Point[board.length-ccOwn][m-ccOwn]+=gomokuShapeScore1(ccOwn, openEnds, 2);
//            }
//            else if(ccOp>0&&openEnds>0) {
//            	Point[board.length-ccOp][m-ccOp]+=gomokuShapeScore1(ccOp, openEnds, 1);
//            }

            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }

        ccOwn = 0;
        ccOp = 0;
        openEnds = 0;
        for(int i=1;i<size;i++) {
        	int m=size-1;
        	for(int j=i;j<size;j++ ) {
//        		if(cnt==2) {
//        			System.out.println(size);
//        		System.out.println("m "+m+"j "+j+"ccOwn "+ccOwn+"ccOp "+ccOp);
//        		}
        		if(board[m][j]==2) {
        			ccOwn++;
        			m--;
        		}
        		else if (board[m][j]==1) {
        			ccOp++;
        			m--;
        		}
        		else if(board[m][j]==0 && ccOwn>0) {
        			++openEnds;
        			Point[m][j]+=gomokuShapeScore1(ccOwn, openEnds, 2);
        			
        			m--;
        			break;
        		}
        		else if(board[m][j]==0 && ccOp>0){
        			openEnds++;
        			Point[m][j]+=gomokuShapeScore1(ccOp, openEnds, 1);
//        			if(cnt==2){
//        				System.out.println("lllllllllllllllllllllllllllllllllll"+ ccOp);
//        			System.out.println(m+" "+j+"  Point[m][j]"+Point[m][j]);
//        			}
        			m--;
        			break;
        		}
        		
        	}

            ccOwn = 0;
            ccOp = 0;
            openEnds = 0;
        }
//        return false;

    }

}
