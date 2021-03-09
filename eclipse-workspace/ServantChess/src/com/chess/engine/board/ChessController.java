package com.chess.engine.board;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessController implements ChessDelegate, ActionListener, Runnable {

	private ChessModel chessModel = new ChessModel();
	private ChessView chestBoardPanel;
	private JButton resetBtn;
	private JButton serverBtn;
	private JButton clientBtn;

	ChessController() {
		chessModel.reset();

		JFrame frame = new JFrame("Servant Chess");
		frame.setSize(800, 700);
		frame.setLocation(0, 0);
		frame.setLayout(new BorderLayout());

		chestBoardPanel = new ChessView(this);

		frame.add(chestBoardPanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		resetBtn = new JButton("Reset");
		buttonsPanel.add(resetBtn);
		resetBtn.addActionListener(this);

		serverBtn = new JButton("Listen");
		buttonsPanel.add(serverBtn);
		serverBtn.addActionListener(this);

		clientBtn = new JButton("Connect");
		buttonsPanel.add(clientBtn);
		clientBtn.addActionListener(this);

		frame.add(buttonsPanel, BorderLayout.PAGE_END);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ChessController();
	}

	@Override
	public ChessPiece pieceAt(int col, int row) {
		return chessModel.pieceAt(col, row);
	}

	@Override
	public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
		chessModel.movePiece(fromCol, fromRow, toCol, toRow);
		chestBoardPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() == resetBtn) {
			chessModel.reset();
			chestBoardPanel.repaint();
		} else if (e.getSource() == serverBtn) {

		} else if (e.getSource() == clientBtn) {
			System.out.println("Connect (for socket client) clicked");
		}
	}

	@Override
	public void run() {
		try (var listener = new ServerSocket(50000)) {
			System.out.println("server is listening to port 50000");
			while (true) {
				try (var socket = listener.accept()) {
					var out = new PrintWriter(socket.getOutputStream(), true);
					out.println("from (0, 1) to (0, 1)");
				}
			}
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
