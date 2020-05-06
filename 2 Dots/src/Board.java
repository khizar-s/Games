/**
 * @author Khizar Siddiqui
 * Description: Board GUI Class
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @brief Class to call Gameboard GUI 
 */
public class Board {
		private JFrame gameboard;
		int size, red, green, blue, orange, pink, turn;
		final Model mod;
		private ArrayList<ArrayList<JButton>> dots = new ArrayList<ArrayList<JButton>>();
		private JPanel chessBoard;
		private JPanel info;
		private JLabel tmp;
		private JButton move;
		private ArrayList<PointT> input_move = new ArrayList<PointT>();
		
		/**
		 * @brief Initializes the Board
		 * @param m Represents the Model to create a GUI of
		 */
		public Board(Model m) {
			this.mod = m;
			this.size = m.get_size();
			this.red = m.get_red();
			this.green = m.get_green();
			this.blue = m.get_blue();
			this.orange = m.get_orange();
			this.orange = m.get_pink();
			this.turn = m.get_turns();
			this.info = new JPanel();
			this.info.setSize(600, 200);
			
			//Move button to perform a move
			this.move = new JButton("Move");
			this.move.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		//Use try and catch to catch exception errors and display them
               		try {
               			//Decrease dots left
               			mod.updatecount(input_move);
               			//Decrease turns left
               			mod.decrement_turn();
               			//Perform move and recreate the move ArrayList
                   		mod.move(input_move);
                   		input_move = new ArrayList<PointT>();
                   		//update gameboard to show new information
                   		updateBoard();
                   		updateInfo();
                   		//Check for end of game and return message
                   		if (mod.isgameover()) {
                   			JOptionPane.showMessageDialog(gameboard, mod.get_finalmessage());
                   		}
               		} catch (RuntimeException re) {
               			JOptionPane.showMessageDialog(gameboard, re.getMessage());
               		}
               	}
               });
			//Create panel and generate board in it
			this.chessBoard = new JPanel(new GridLayout(0, size));
			this.gameboard = new JFrame("2 Dots");
			this.gameboard.setBounds(300, 50, 600, 600);
			generateBoardGUI();
			//Add info and button to top panel
			this.move.setPreferredSize(new Dimension(100, 50));
			info.add(this.move, BorderLayout.WEST);
			tmp = new JLabel(getinfo());
			info.add(tmp, BorderLayout.EAST);			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(info, BorderLayout.NORTH);
			mainPanel.add(chessBoard, BorderLayout.SOUTH);
			this.gameboard.setLayout(new BorderLayout());
			this.gameboard.add(mainPanel);
			this.gameboard.setVisible(true);
		}
		
		/**
		 * @brief Get current game information
		 * @return String containing current game information
		 */
		public String getinfo() {
			String message = "<html><br>Turns left: %d<br>"
	        		+ "Red counters left: %d<br>"
	        		+ "Green counters left: %d<br>"
	        		+ "Blue counters left: %d<br>"
	        		+ "Orange counters left: %d<br>"
	        		+ "Pink counters left: %d</html>";
			String message2 = String.format(message, this.mod.get_turns(), this.mod.get_red(), this.mod.get_green(), this.mod.get_blue(), this.mod.get_orange(), this.mod.get_pink());
			return message2;
		}
		
		/**
		 * @brief Updates the current game information
		 * @details Removes components from panel and readds them with new info
		 */
		public void updateInfo() {
			this.info.remove(move);
			this.info.remove(tmp);
			info.add(this.move, BorderLayout.WEST);
			tmp = new JLabel(getinfo());
			info.add(tmp, BorderLayout.EAST);
		}
		
		/**
		 * @brief Updates board
		 * @details Rechecks each point and updates color of button accordingly
		 */
		public void updateBoard() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					DotT c = this.mod.getDot(i, j);
					JButton button = dots.get(i).get(j);
					if (c == DotT.RED) {
	                	button.setBackground(Color.red);
	                } else if (c == DotT.GREEN) {
	                	button.setBackground(Color.green);
	                } else if (c == DotT.BLUE) {
	                	button.setBackground(Color.blue);
	                } else if (c == DotT.ORANGE) {
	                	button.setBackground(Color.orange);
	                } else if (c == DotT.PINK) {
	                	button.setBackground(Color.pink);
	                }
					button.setBorder(new LineBorder(Color.black, 1));
				}
			}
		}

		/**
		 * @brief Generates the GUI representation of the board
		 * @details Creates a grid of buttons that also help perform the move
		 */
		public void generateBoardGUI() {
			Insets buttonMargin = new Insets(0,0,0,0);
			for (int a = 0; a < size; a++) {
				for (int b = 0; b < size; b++) {
					dots.add(new ArrayList<JButton>());
				}
			}
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					JButton button = new JButton();
					button.setMargin(buttonMargin);
					ImageIcon icon = new ImageIcon(
	                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	                button.setIcon(icon);
	                DotT c = this.mod.getDot(i, j);
	                //Sets button color based on dot color
	                if (c == DotT.RED) {
	                	button.setBackground(Color.red);
	                } else if (c == DotT.GREEN) {
	                	button.setBackground(Color.green);
	                } else if (c == DotT.BLUE) {
	                	button.setBackground(Color.blue);
	                } else if (c == DotT.ORANGE) {
	                	button.setBackground(Color.orange);
	                } else if (c == DotT.PINK) {
	                	button.setBackground(Color.pink);
	                }
	                button.setBorder(new LineBorder(Color.black, 1));
	                final int row = i;
	                final int col = j;
	                //On click, add point to list of point for move and highlights
	                button.addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent e) {
	                		PointT p = new PointT(row, col);
	                		if (input_move.contains(p)) {
	                			button.setBorder(new LineBorder(Color.black, 1));
	                			input_move.remove(p);
	                		} else {
	                			input_move.add(p);
	                			button.setBorder(new LineBorder(Color.white, 2));
	                		}
	                	}
	                });
	                dots.get(i).add(button);
				}
			}
			for (int ii = 0; ii < size; ii++) {
				for (int jj = 0; jj < size; jj++) {
					chessBoard.add(dots.get(ii).get(jj));
				}
			}
		}
}