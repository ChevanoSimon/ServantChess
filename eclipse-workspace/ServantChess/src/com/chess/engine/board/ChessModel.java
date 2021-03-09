package com.chess.engine.board;

import java.util.Set;
import java.util.HashSet;

public class ChessModel {
	public Set<ChessPiece> piecesBox = new HashSet<ChessPiece>();
	private Player playerInTurn = Player.SILVER;

	void reset() {

		piecesBox.removeAll(piecesBox);

		for (int i = 0; i < 1; i++) {
			// (horizontal, vertical)

			piecesBox.add(new ChessPiece(5, 0, Player.GOLD, Rank.ARCHER, ChessConstants.gArcher));
			piecesBox.add(new ChessPiece(3, 8, Player.SILVER, Rank.ARCHER, ChessConstants.sArcher));

			piecesBox.add(new ChessPiece(6, 0, Player.GOLD, Rank.ASSASSIN, ChessConstants.gAssassin));
			piecesBox.add(new ChessPiece(2, 8, Player.SILVER, Rank.ASSASSIN, ChessConstants.sAssassin));

			piecesBox.add(new ChessPiece(2, 0, Player.GOLD, Rank.BERSERKER, ChessConstants.gBerserker));
			piecesBox.add(new ChessPiece(6, 8, Player.SILVER, Rank.BERSERKER, ChessConstants.sBerserker));

			piecesBox.add(new ChessPiece(1, 0, Player.GOLD, Rank.CASTER, ChessConstants.gCaster));
			piecesBox.add(new ChessPiece(7, 8, Player.SILVER, Rank.CASTER, ChessConstants.sCaster));

			piecesBox.add(new ChessPiece(3, 0, Player.GOLD, Rank.LANCER, ChessConstants.gLancer));
			piecesBox.add(new ChessPiece(5, 8, Player.SILVER, Rank.LANCER, ChessConstants.sLancer));

			piecesBox.add(new ChessPiece(7, 0, Player.GOLD, Rank.RIDER, ChessConstants.gRider));
			piecesBox.add(new ChessPiece(1, 8, Player.SILVER, Rank.RIDER, ChessConstants.sRider));

			piecesBox.add(new ChessPiece(4, 0, Player.GOLD, Rank.SABER, ChessConstants.gSaber));
			piecesBox.add(new ChessPiece(4, 8, Player.SILVER, Rank.SABER, ChessConstants.sSaber));

		}

		for (int i = 1; i < 8; i++) {
			piecesBox.add(new ChessPiece(i, 1, Player.GOLD, Rank.MASTER, ChessConstants.gMaster));
			piecesBox.add(new ChessPiece(i, 7, Player.SILVER, Rank.MASTER, ChessConstants.sMaster));
		}

		// playerInTurn = Player.SILVER;
	}

	void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
		ChessPiece candidate = pieceAt(fromCol, fromRow);

		// Checks who's turn it is.
		/*
		 * if (candidate == null || candidate.player != playerInTurn) {
		 * System.out.println("It is not your turn yet."); return; }
		 */

		// Checks if the move made is valid. If 'false' returns the piece to it's
		// original place.
		if (ChessMovement.isValidMove(candidate, fromCol, fromRow, toCol, toRow) == false) {
			System.out.println("False movement detected");
			return;
		}

		ChessPiece target = pieceAt(toCol, toRow);
		if (target != null) {
			// Denies moves on own team.
			if (target.player == candidate.player) {
				return;
			}

			// removes pieces from the opposing team
			else {
				piecesBox.remove(target);
			}
		}

		// Moves piece to new position if move is valid.
		candidate.col = toCol;
		candidate.row = toRow;
		System.out.println("Move made");

		// playerInTurn = playerInTurn == Player.SILVER ? Player.GOLD : Player.SILVER;
	}

	ChessPiece pieceAt(int col, int row) {
		for (ChessPiece chessPiece : piecesBox) {
			if (chessPiece.col == col && chessPiece.row == row) {
				return chessPiece;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String desc = "";

		for (int row = 7; row >= 0; row--) {

			desc += "" + row; // "7"

			for (int col = 0; col < 9; col++) {

				ChessPiece p = pieceAt(col, row);

				if (p == null) {
					desc += " .";
				}

				else {
					desc += "";
					switch (p.rank) {
					case SABER:
						desc += p.player == Player.SILVER ? " s" : " S";
						break;

					case ARCHER:
						desc += p.player == Player.SILVER ? " a" : " A";
						break;

					case LANCER:
						desc += p.player == Player.SILVER ? " l" : " L";
						break;

					case RIDER:
						desc += p.player == Player.SILVER ? " r" : " R";
						break;

					case CASTER:
						desc += p.player == Player.SILVER ? " c" : " C";
						break;

					case ASSASSIN:
						desc += p.player == Player.SILVER ? " n" : " N";
						break;

					case BERSERKER:
						desc += p.player == Player.SILVER ? " b" : " B";
						break;

					case MASTER:
						desc += p.player == Player.SILVER ? " m" : " M";
						break;
					}
				}
			}
			desc += "\n";
		}
		desc += "  0 1 2 3 4 5 6 7 8";

		return desc;
	}
}
