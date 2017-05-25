/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Esila
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Model;

public class BoardView extends JPanel implements Observer {

	// an array of buttons
	private JButton tiles[][];

	// the model that this view is showing
	private Model model;

	// record the foreground color of the button
	private Color foreColor;
	private Color backColor;

	public BoardView(Model model_) {
		// create the view UI

		// a GridBagLayout with default constraints centres
		// the widget in the window
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.GRAY);
                        
		JPanel p = new JPanel(new GridLayout(3, 3));
		this.add(p, new GridBagConstraints());

		p.setMaximumSize(new Dimension(300, 300));
//		p.setBackground(Color.BLACK);

		// set the model
		model = model_;

		tiles = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tiles[i][j] = new JButton();
				tiles[i][j].setPreferredSize(new Dimension(100, 100));
				Font curFont = tiles[i][j].getFont();
				tiles[i][j].setFont(new Font(curFont.getFontName(), curFont
						.getStyle(), 22));
				tiles[i][j].setFocusable(false);
				p.add(tiles[i][j]);
			}
		}
		
		foreColor = tiles[0][0].getForeground();
		backColor = tiles[0][0].getBackground();

		this.registerControllers();

	}

	/* the controller part */
	
	private void registerControllers() {
		this.tiles[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(0, 0);
			}
		});

		this.tiles[0][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(0, 1);
			}
		});

		this.tiles[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(0, 2);
			}
		});

		this.tiles[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(1, 0);
			}
		});

		this.tiles[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(1, 1);
			}
		});

		this.tiles[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(1, 2);
			}
		});

		this.tiles[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(2, 0);
			}
		});

		this.tiles[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(2, 1);
			}
		});

		this.tiles[2][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.playerMove(2, 2);
			}
		});
	}

	// Observer interface
	@Override
	public void update(Observable arg0, Object arg1) {
		int changedX = model.getChangedX();
		int changedY = model.getChangedY();
		int changedValue = model.getChangedValue();

		if (changedValue == 0) {
			tiles[changedX][changedY].setText("O");
		} else if (changedValue == 1) {
			tiles[changedX][changedY].setText("X");
		}

		if (model.getStatus() == 0) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					tiles[i][j].setText("");
					tiles[i][j].setBackground(backColor);
					tiles[i][j].setForeground(foreColor);
					tiles[i][j].setOpaque(true);
				}
			}
		}

		// if anyone wins we need to highlight
		if (model.getStatus() == 4) {
			int winType = model.getWinType();
			int winPos = model.getWinPos();

			switch (winType) {
			case 1: {
				for (int i = 0; i < 3; i++) {
					tiles[winPos][i].setBackground(Color.GREEN);
					tiles[winPos][i].setForeground(Color.WHITE);
					tiles[winPos][i].setOpaque(true);
				}
				break;
			}
			case 2: {
				for (int i = 0; i < 3; i++) {
					tiles[i][winPos].setBackground(Color.GREEN);
					tiles[i][winPos].setForeground(Color.WHITE);
					tiles[i][winPos].setOpaque(true);
				}
				break;
			}
			case 3: {
				for (int i = 0; i < 3; i++) {
					tiles[i][i].setBackground(Color.GREEN);
					tiles[i][i].setForeground(Color.WHITE);
					tiles[i][i].setOpaque(true);
				}
				break;
			}
			case 4: {
				int counter = 2;
				while (counter >= 0) {
					tiles[2 - counter][counter].setBackground(Color.GREEN);
					tiles[2 - counter][counter].setForeground(Color.WHITE);
					tiles[2 - counter][counter].setOpaque(true);
					counter--;
				}
				break;
			}
			default:
				break;
			}
		}
	}
}