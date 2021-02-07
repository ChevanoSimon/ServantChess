package com.chess.engine.board;

import javax.swing.JFrame;

public class ChessTile {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Servant Chess");
		frame.setSize(800, 700);
		frame.setLocation(0, 0);
		
		ChessPanel panel = new ChessPanel();
		
		frame.add(panel);
		frame.setVisible(true);
	}
}
