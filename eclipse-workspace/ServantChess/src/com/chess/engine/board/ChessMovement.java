package com.chess.engine.board;

public class ChessMovement {
	
	public static boolean hasMoved;
	ChessModel chessModel;

	static boolean isValidMove(ChessPiece candidate, int fromCol, int fromRow, int toCol, int toRow) {

		if (fromCol >= 9 || fromCol < 0 || fromRow >= 9 || fromRow < 0) {
			return false;
		}

		if (candidate.rank == Rank.ARCHER) {

		}

		if (candidate.rank == Rank.ASSASSIN) {
			if (fromRow == toRow || fromCol == toCol) {
				// Denies vertical and horizontal movement.
				return false;
			}

			// Denies weird movements that go entirely off row/col
			if (Math.abs(toRow - fromRow) != Math.abs(toCol - fromCol)) {
				return false;
			}

			int rowOffset, colOffset;

			if (fromRow < toRow) {
				rowOffset = 1;
			} else {
				rowOffset = -1;
			}

			if (fromCol < toCol) {
				colOffset = 1;
			} else {
				colOffset = -1;
			}

			int y = fromCol + colOffset;
			for (int x = fromRow + rowOffset; x != toRow; x += rowOffset) {
				y += colOffset;
			}
			return true;
		}

		if (candidate.rank == Rank.BERSERKER) {
			if (toCol == fromCol || toRow == fromRow)
				return true;
		}

		if (candidate.rank == Rank.CASTER) {
		}

		if (candidate.rank == Rank.LANCER) {
		}

		if (candidate.rank == Rank.RIDER) {
			if (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 1) {
				return true;
			}

			if (Math.abs(toRow - fromRow) == 1 && Math.abs(toCol - fromCol) == 2) {
				return true;
			}

			return false;
		}

		if (candidate.rank == Rank.SABER) {

			if (toCol == fromCol || toRow == fromRow) {
				return true;
			}

			if (Math.abs(toRow - fromRow) != Math.abs(toCol - fromCol)) {
				return false;
			}

			int rowOffset, colOffset;

			if (fromRow < toRow) {
				rowOffset = 1;
			}

			else {
				rowOffset = 1;
			}

			if (fromCol < toCol) {
				colOffset = 1;
			}

			else {
				colOffset = -1;
			}

			int y = fromCol + colOffset;

			for (int x = fromRow + rowOffset; x != toRow; x += rowOffset) {
				y += colOffset;
			}
			return true;
		}

		// int fromCol, int fromRow, int toCol, int toRow

		if (candidate.rank == Rank.MASTER) {

			if (Math.abs(toRow - fromRow) > 1 || Math.abs(toCol - fromCol) > 1) {

				// hasMoved = true;
				return true;
			}
		}
		// if every check for every move fails.
		return false;
	}
}

/* 
 * if (fromCol == toCol) {
				// Not taking a piece
					if (fromCol == toCol) {
						System.out.println("Fault3");
						return false;
					}
				

				else {
					if (newPosition[toCol - 1][fromCol] != 0) {
						System.out.println("Fault4");
						return false;
					}
				}
				
				return true;

				 if (Math.abs(toRow - toCol) > 2) {
					System.out.println("Fault5");
					return false;
				}

				else if (Math.abs(toRow - toCol) == 2) {

					

					
						if (newPosition[toCol + 2][fromCol] != 0) {
							System.out.println("Fault6");
							return false;
						}
					

					else {
						if (newPosition[toCol - 2][fromCol] != 0) {
							System.out.println("Fault7");
							return false;
						}
					}
				}
				
			}
		
 */


//Advancing two spaces at beginning
/*
 * if(hasMoved){ return false; }
 */

/* Diagonal movement
if(fromRow == toRow || fromCol == toCol){
				//Did not move diagonally
				return false;
			}
			
			if(Math.abs(toRow - fromRow) != Math.abs(toCol - fromCol)){
				return false;
			}
			
			int rowOffset, colOffset;
			
			if(fromRow < toRow){
				rowOffset = 1;
			}else{
				rowOffset = -1;
			}
			
			if(fromCol < toCol){
				colOffset = 1;
			}else{
				colOffset = -1;
			}
			
			int y = fromCol + colOffset;
			for(int x = fromRow + rowOffset; x != toRow; x += rowOffset){
				
				y += colOffset;
			}
			
			return true; */
