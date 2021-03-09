package com.chess.engine.board;

enum Player {
	SILVER, GOLD,
}

enum Rank {
	SABER, ARCHER, LANCER, RIDER, ASSASSIN, CASTER, BERSERKER, MASTER
}

public class ChessPiece {

	int col, row;
	Player player;
	Rank rank;
	String imgName;

	public ChessPiece(int col, int row, Player player, Rank rank, String imgName) {
		super();
		this.col = col;
		this.row = row;
		this.player = player;
		this.rank = rank;
		this.imgName = imgName;
	}
}
