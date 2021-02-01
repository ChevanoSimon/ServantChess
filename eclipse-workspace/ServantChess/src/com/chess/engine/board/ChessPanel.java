package com.chess.engine.board;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4662480070988518883L;

	int cellSize = 50;
	int boardSize = 9;
	int boardSpacingX = 50; // vertical
	int boardSpacingY = 50; // horisontal

	Map<String, Image> keyNameValueImage = new HashMap<String, Image>();

	public ChessPanel() {
		String[] imageNames = {
				"ArcherGold",
				"AssassinGold",
				"BerserkerGold",
				"CasterGold",
				"LancerGold",
				"MasterGold",
				"RiderGold",
				"SaberGold",
				
				"ArcherSilver",
				"AssassinSilver",
				"BerserkerSilver",
				"CasterSilver",
				"LancerSilver",
				"MasterSilver",
				"RiderSilver",
				"SaberSilver",

		};

		try {
			for (String imgNm : imageNames) {
				Image img = loadImage(imgNm + ".png");
				keyNameValueImage.put(imgNm, img);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paintChildren(g);
		
		drawBoard(g);
		
		drawImage(g, 1, 0, "ArcherGold");
		drawImage(g, 1, 8, "ArcherSilver");
		
		drawImage(g, 2, 0, "AssassinGold");
		drawImage(g, 2, 8, "AssassinSilver");
		
		drawImage(g, 3, 0, "BerserkerGold");
		drawImage(g, 3, 8, "BerserkerSilver");
		
		drawImage(g, 4, 0, "CasterGold");
		drawImage(g, 4, 8, "CasterSilver");
		
		drawImage(g, 5, 0, "LancerGold");
		drawImage(g, 5, 8, "LancerSilver");
		
		drawImage(g, 6, 0, "RiderGold");
		drawImage(g, 6, 8, "RiderSilver");
		
		drawImage(g, 7, 0, "SaberGold");
		drawImage(g, 7, 8, "SaberSilver");
		
		// Masters (horizontal, vertical)
		
		drawImage(g, 1, 1, "MasterGold");
		drawImage(g, 1, 7, "MasterSilver");
		
		drawImage(g, 2, 1, "MasterGold");
		drawImage(g, 2, 7, "MasterSilver");
		
		drawImage(g, 3, 1, "MasterGold");
		drawImage(g, 3, 7, "MasterSilver");
		
		drawImage(g, 4, 1, "MasterGold");
		drawImage(g, 4, 7, "MasterSilver");
		
		drawImage(g, 5, 1, "MasterGold");
		drawImage(g, 5, 7, "MasterSilver");
		
		drawImage(g, 6, 1, "MasterGold");
		drawImage(g, 6, 7, "MasterSilver");
		
		drawImage(g, 7, 1, "MasterGold");
		drawImage(g, 7, 7, "MasterSilver");
		

	}
	
	private void drawBoard(Graphics g) {
		int row; // Row number, from 0 to 7
		int col; // Column number, from 0 to 7
		int x, y; // Top-left corner of square

		for (row = 0; row < boardSize; row++) {
			for (col = 0; col < boardSize; col++) {
				x = col * cellSize;
				y = row * cellSize;

				if ((row % 2) == (col % 2)) {
					g.setColor(Color.white);
				}

				else {
					g.setColor(Color.black);
				}

				g.fillRect(x + boardSpacingX, y + boardSpacingY, cellSize, cellSize);
			}
		}
	}
	
	private void drawImage(Graphics g, int col, int row, String imgName) {
		Image img = keyNameValueImage.get(imgName);
		g.drawImage(img, boardSpacingX + col * cellSize, boardSpacingY + row * cellSize, cellSize, cellSize, null);
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
}
