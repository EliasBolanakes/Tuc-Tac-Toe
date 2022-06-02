package Model;

import java.io.Serializable;

public class MediocrePlayer extends PerfectPlayer implements Serializable {

    public MediocrePlayer(String name){
        super(name);
    }

    // minimax algorithm with max depth of one for semi-optimal move

    @Override
    protected double minimax(char[][] board, int depth, Boolean maximizerTurn, char maximizerChar, double alpha, double beta) {
        char minimizerChar=(maximizerChar=='X'?'O':'X');

        double score = Board.checkIfWon(board,maximizerChar);
        double evaluation;

        if(score == 10||score==-10)
            return score;

        else if(!Board.hasMovesLeft(board))
            return 0;

        else if(depth==1)
            return score;

        if(maximizerTurn) {
            double maxEvaluation = Double.NEGATIVE_INFINITY;

            outerMaxLoop:for (int i = 0; i < Board.ROWS; i++){
                for (int j = 0; j < Board.COLS; j++){
                    if (Board.isAvailable(board,i,j)){

                        board[i][j] = maximizerChar;
                        evaluation= minimax(board,depth + 1,false,maximizerChar,alpha,beta);
                        maxEvaluation = Math.max(maxEvaluation,evaluation);
                        Board.undoMove(board,i,j);

                        if(evaluatedBoardsFirst.containsKey(Hashing.computeHash(board)) && maximizerChar=='X')
                            return evaluatedBoardsFirst.get(Hashing.computeHash(board)).MaxScore;
                        else if(evaluatedBoardsSecond.containsKey(Hashing.computeHash(board))&& maximizerChar=='O')
                            return evaluatedBoardsSecond.get(Hashing.computeHash(board)).MaxScore;

                        alpha=Math.max(alpha,evaluation);
                        if(alpha>=beta){
                            break outerMaxLoop;
                        }
                    }
                }
            }
            evaluateBoard(board,maxEvaluation,maximizerChar,true);
            return maxEvaluation;
        }

        else {
            double minEvaluation = Double.POSITIVE_INFINITY;

            outerMinLoop:for (int i = 0; i < Board.ROWS; i++){
                for (int j = 0; j < Board.COLS; j++){
                    if (Board.isAvailable(board,i,j)){

                        board[i][j] = minimizerChar;
                        evaluation= minimax(board,depth + 1,true,maximizerChar,alpha,beta);
                        minEvaluation = Math.min(minEvaluation,evaluation);
                        Board.undoMove(board,i,j);

                        if(evaluatedBoardsFirst.containsKey(Hashing.computeHash(board)) && maximizerChar=='X')
                            return evaluatedBoardsFirst.get(Hashing.computeHash(board)).MinScore;
                        else if(evaluatedBoardsSecond.containsKey(Hashing.computeHash(board)) && maximizerChar=='O')
                            return evaluatedBoardsSecond.get(Hashing.computeHash(board)).MinScore;

                        beta=Math.min(evaluation,beta);
                        if(beta<=alpha){
                            break outerMinLoop;
                        }
                    }
                }
            }
            evaluateBoard(board,minEvaluation,maximizerChar,false);
            return minEvaluation;
        }
    }
}
