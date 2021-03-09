package com.chess.engine.board;
 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChessModelTest {
	
	@Test
	void testReset() {
		ChessModel chessModel = new ChessModel();
		chessModel.reset();
		chessModel.movePiece(1, 1, 1, 2);
		chessModel.reset();
		System.out.println(chessModel);
	}
	
	// (horizontal, vertical)
	
	@Test
	void testMovePiece() {
		ChessModel chessModel = new ChessModel();
		chessModel.reset();
		
		assertNull(chessModel.pieceAt(1, 2));
		System.out.println(chessModel);
		
		chessModel.movePiece(1, 1, 1, 2);
		assertNotNull(chessModel.pieceAt(1, 2));
		System.out.println(chessModel);
	}

	@Test
	void testPieceAt() {
		ChessModel chessModel = new ChessModel();
		assertNull(chessModel.pieceAt(0, 0));
		chessModel.reset();
		assertNotNull(chessModel.pieceAt(1, 0));
		assertEquals(Player.GOLD, chessModel.pieceAt(1, 0).player);
		assertEquals(Rank.CASTER, chessModel.pieceAt(1, 0).rank);
		
		//fault here is related to ChestView drawPiece()
		
	}
	
	
	@Test
	void testToString() {
		ChessModel chessModel = new ChessModel();
		assertTrue(chessModel.toString().contains("0 . . . . . . . . ."));
		chessModel.reset();
		System.out.println(chessModel);
		assertTrue(chessModel.toString().contains("0 . C B L S A N R ."));
	}

}
