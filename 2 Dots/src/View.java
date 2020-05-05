/**
 * @author Khizar Siddiqui
 * Description: View_Controller Class
 */

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @brief Main Class to call components
 */
public class View {
	
	private JFrame mainmenu;

	/**
	 * @brief Initializes the GUI for the game
	 */
	public View() {
		this.mainmenu = new JFrame("2 Dots");
		this.mainmenu.setBounds(0, 0, 300, 300);
		this.mainmenu.getContentPane().setLayout(null);
		
		//Input for turns
		final JLabel turnLabel = new JLabel("Number of turns");
		final JTextField turnField = new JTextField();
		turnLabel.setBounds(20, 10, 150, 30);
		turnField.setBounds(160, 10, 60, 30);
		
		//Input for red dots
		final JLabel redLabel = new JLabel("Red to reach");
		final JTextField redField = new JTextField();
		redLabel.setBounds(20, 45, 150, 30);
		redField.setBounds(160, 45, 60, 30);
		
		//Input for green dots
		final JLabel greenLabel = new JLabel("Green to reach");
		final JTextField greenField = new JTextField();
		greenLabel.setBounds(20, 80, 150, 30);
		greenField.setBounds(160, 80, 60, 30);
		
		//Input for blue dots
		final JLabel blueLabel = new JLabel("Blue to reach");
		final JTextField blueField = new JTextField();
		blueLabel.setBounds(20, 115, 150, 30);
		blueField.setBounds(160, 115, 60, 30);
		
		//Input for orange dots
		final JLabel orangeLabel = new JLabel("Orange to reach");
		final JTextField orangeField = new JTextField();
		orangeLabel.setBounds(20, 150, 150, 30);
		orangeField.setBounds(160, 150, 60, 30);
		
		//Input for pink dots
		final JLabel pinkLabel = new JLabel("Pink to reach");
		final JTextField pinkField = new JTextField();
		pinkLabel.setBounds(20, 185, 150, 30);
		pinkField.setBounds(160, 185, 60, 30);
		
		//Play button to read input and start the game
		final JButton playButton = new JButton("Start");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int red = Integer.parseInt(redField.getText());
					int green = Integer.parseInt(greenField.getText());
					int blue = Integer.parseInt(blueField.getText());
					int orange = Integer.parseInt(orangeField.getText());
					int pink = Integer.parseInt(pinkField.getText());
					int turn = Integer.parseInt(turnField.getText());
					//Passes input to generate a new Model
					Model m = new Model(generateBoard(6), red, green, blue, orange, pink, turn);
					new Board(m);
				} catch (Exception err) {
					JOptionPane.showMessageDialog(mainmenu, err.getMessage());
				}
			}
		});
		playButton.setBounds(100, 230, 70, 30);
		
		this.mainmenu.add(redField);
		this.mainmenu.add(greenField);
		this.mainmenu.add(blueField);
		this.mainmenu.add(orangeField);
		this.mainmenu.add(pinkField);
		this.mainmenu.add(turnField);
		this.mainmenu.add(playButton);
		this.mainmenu.add(orangeLabel);
		this.mainmenu.add(redLabel);
		this.mainmenu.add(greenLabel);
		this.mainmenu.add(blueLabel);
		this.mainmenu.add(pinkLabel);
		this.mainmenu.add(turnLabel);
		this.mainmenu.setVisible(true);
	}
	
	/**
	 * @brief Generates a new (random) board
	 * @param size Represents the size of the board
	 * @return A 2D-ArrayList representation on the board
	 * @details Utilizes the randomColor function from Model
	 */
	public ArrayList<ArrayList<DotT>> generateBoard(int size) {
        ArrayList<ArrayList<DotT>> s = new ArrayList<ArrayList<DotT>>();
        for (int i = 0; i < size; i++) {
            s.add(new ArrayList<DotT>());
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                DotT x = Model.randomColor();
                s.get(i).add(x);
            }
        }
        return s;
    }
	
	//Main Function that calls the GUI at runtime
	public static void main(String[] args) {
		new View();
	}
}