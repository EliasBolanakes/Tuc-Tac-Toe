package Model;

import java.io.Serializable;
import java.util.HashMap;

public class PerfectPlayer extends Player implements Serializable{

    HashMap<Long,Table> evaluatedBoardsFirst=new HashMap<>();
    HashMap<Long,Table> evaluatedBoardsSecond=new HashMap<>();
    HashMap<Long,Move> calculatedMoves=new HashMap<>();

    public PerfectPlayer(String name){

        super(name);
    }

    protected static class Table{
        protected double MinScore;
        protected double MaxScore;
    }

    // returning score for a given move

    protected double minimax(char[][] board, int depth, Boolean maximizerTurn, char maximizerChar, double alpha, double beta){

        char minimizerChar=(maximizerChar=='X'?'O':'X');

        double score = Board.checkIfWon(board,maximizerChar);
        double evaluation;

        if(score == 10||score==-10)
            return score;

        else if(!Board.hasMovesLeft(board))
            return 0;

        if(maximizerTurn) {
            double maxEvaluation = Double.NEGATIVE_INFINITY;

            outerMaxLoop:for (int i = 0; i < Board.ROWS; i++){
                for (int j = 0; j < Board.COLS; j++){
                    if (Board.isAvailable(board,i,j)){

                        board[i][j] = maximizerChar;
                        evaluation= minimax(board,depth + 1,false,maximizerChar,alpha,beta);
                        maxEvaluation = Math.max(maxEvaluation,evaluation);
                        Board.undoMove(board,i,j);

                        // checking if given board state is already evaluated for maximizer
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

                        // checking if given board state is already evaluated for minimizer
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

    // finding board evaluation and memorizing it

    public void evaluateBoard(char[][] board, double evaluation,char maximizerChar,boolean maxTurn){

        long hashKey=Hashing.computeHash(board);

        if((!evaluatedBoardsFirst.containsKey(hashKey)) && maximizerChar=='X' && maxTurn){
            Table t=new Table();
            t.MaxScore=evaluation;
            evaluatedBoardsFirst.put(hashKey,t);
        }
        else if((!evaluatedBoardsFirst.containsKey(hashKey)) && maximizerChar=='X' && !maxTurn){
            Table t=new Table();
            t.MinScore=evaluation;
            evaluatedBoardsFirst.put(hashKey,t);
        }
        else if((!evaluatedBoardsSecond.containsKey(hashKey)) && maximizerChar=='O' && maxTurn){
            Table t=new Table();
            t.MaxScore=evaluation;
            evaluatedBoardsSecond.put(hashKey,t);
        }
        else if((!evaluatedBoardsSecond.containsKey(hashKey)) && maximizerChar=='O' && !maxTurn){
            Table t=new Table();
            t.MinScore=evaluation;
            evaluatedBoardsSecond.put(hashKey,t);
        }
    }

    // finding the score for each move and keeping the best

    public Move findBestMove(char[][] board,char maximizerChar) {

        double maxVal = Double.NEGATIVE_INFINITY;
        Move bestMove = new Move(0,0);

        long hashKey =Hashing.computeHash(board);
        if(calculatedMoves.containsKey(hashKey)){
            return calculatedMoves.get(hashKey);
        }

        else{
            for (int i = 0; i < Board.ROWS; i++){
                for (int j = 0; j < Board.COLS; j++){
                    if (Board.isAvailable(board,i,j)){

                        board[i][j] = maximizerChar;
                        double moveVal = minimax(board,0,false,maximizerChar,
                                Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
                        Board.undoMove(board,i,j);
                        if (moveVal > maxVal){
                            bestMove.setRow(i);
                            bestMove.setCol(j);
                            maxVal = moveVal;
                        }
                    }
                }
            }
            calculatedMoves.put(Hashing.computeHash(board),bestMove);
            return bestMove;
        }
    }
}
