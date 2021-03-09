package com.chess.engine.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessView extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4662480070988518883L;

	private ChessDelegate chessDelegate;

	int boardSize = 9;

	private double scaleFactor = 0.9;
	private int cellSize = -1;
	private int boardSpacingX = -1; // vertical
	private int boardSpacingY = -1; // horizontal

	private Map<String, Image> keyNameValueImage = new HashMap<String, Image>();
	private int fromCol = -1;
	private int fromRow = -1;
	private ChessPiece movingPiece;
	private Point movingPiecePoint;

	ChessView(ChessDelegate chessDelegate) {

		this.chessDelegate = chessDelegate;

		String[] imageNames = { 
				
				ChessConstants.gArcher, ChessConstants.sArcher,

				ChessConstants.gAssassin, ChessConstants.sAssassin,

				ChessConstants.gBerserker, ChessConstants.sBerserker,

				ChessConstants.gCaster, ChessConstants.sCaster,

				ChessConstants.gLancer, ChessConstants.sLancer,

				ChessConstants.gRider, ChessConstants.sRider,

				ChessConstants.gSaber, ChessConstants.sSaber,

				ChessConstants.gMaster, ChessConstants.sMaster };

		try {
			for (String imgNm : imageNames) {
				Image img = loadImage(imgNm + ".png");
				keyNameValueImage.put(imgNm, img);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void paintChildren(Graphics g) {
		super.paintChildren(g);

		int smaller = Math.min(getSize().width, getSize().height);
		cellSize = (int) (((double) smaller) * scaleFactor / 9);

		boardSpacingX = (getSize().width - 9 * cellSize) / 2;
		boardSpacingY = (getSize().height - 9 * cellSize) / 2;

		Graphics2D g2 = (Graphics2D) g;

		drawBoard(g2);
		drawPieces(g2);
	}

	private void drawPieces(Graphics g2) {

		// Determines until were the pieces of ChessModel.reset() can be drawn.
		// (int row = 0; row < 9; row++)
		// (int row = 8; row >= 0; row--)

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				ChessPiece p = chessDelegate.pieceAt(col, row);
				if (p != null && p != movingPiece) {
					drawImage(g2, col, row, p.imgName);
				}
			}
		}

		if (movingPiece != null && movingPiecePoint != null) {
			Image img = keyNameValueImage.get(movingPiece.imgName);
			g2.drawImage(img, movingPiecePoint.x - cellSize / 2, movingPiecePoint.y - cellSize / 2, cellSize, cellSize,
					null);
		}
	}

	private void drawBoard(Graphics g2) {
		int row; // Row number, from 0 to 7
		int col; // Column number, from 0 to 7
		int x, y; // Top-left corner of square

		for (row = 0; row < boardSize; row++) {
			for (col = 0; col < boardSize; col++) {
				x = col * cellSize;
				y = row * cellSize;

				if ((row % 2) == (col % 2)) {
					g2.setColor(Color.white);
				}

				else {
					g2.setColor(Color.black);
				}

				g2.fillRect(x + boardSpacingX, y + boardSpacingY, cellSize, cellSize);
			}
		}
	}

	private void drawImage(Graphics g2, int col, int row, String imgName) {
		Image img = keyNameValueImage.get(imgName);
		g2.drawImage(img, boardSpacingX + col * cellSize, boardSpacingY + row * cellSize, cellSize, cellSize, null);
	}

	private Image loadImage(String imgFileName) throws Exception {
		ClassLoader classloader = getClass().getClassLoader();
		URL resURL = classloader.getResource("pieceImages/" + imgFileName);

		if (resURL == null) {
			return null;
		}

		else {
			File imgFile = new File(resURL.toURI());
			return ImageIO.read(imgFile);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		fromCol = (e.getPoint().x - boardSpacingX) / cellSize;
		fromRow = (e.getPoint().y - boardSpacingY) / cellSize;
		movingPiece = chessDelegate.pieceAt(fromCol, fromRow);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int col = (e.getPoint().x - boardSpacingX) / cellSize;
		int row = (e.getPoint().y - boardSpacingY) / cellSize;
		System.out.println("from " + fromCol + " to " + col);
		chessDelegate.movePiece(fromCol, fromRow, col, row);
		movingPiece = null;
		movingPiecePoint = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		movingPiecePoint = e.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
