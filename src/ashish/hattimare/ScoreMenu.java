//#Zhaoyang

// Import required packages

package ashish.hattimare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ScoreMenu extends JFrame implements ActionListener
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Initialize JPanels
  JPanel backPan = new JPanel();
  JPanel wordPan = new JPanel();
  JPanel masPan = new JPanel();
  JPanel scorePan = new JPanel();
  
  // Initialize images
  Image backImage, titleImage;
  
  // Initialize JLabel, for holding the title image
  JLabel title = new JLabel();
  
  // Initializes JButton
  JButton back = new JButton();
  
  // Initializes an array of JLabels, used to display the ten top scores
  JLabel[] ranking = new JLabel[10];
  
  // Initializes two arrays, used to hold the two anme and score arrays in Score
  ArrayList<Integer> scores;
  ArrayList<String> names;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor: Constructs a ScoreMenu (extends JFrame) and the contents of the JFrame
   * @param none
   * */
  public ScoreMenu()
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Sets the size for the JFrame and sets Layouts for the JPanels
    this.setPreferredSize(new Dimension(500, 600));
    scorePan.setLayout(new BoxLayout(scorePan, BoxLayout.PAGE_AXIS));
    masPan.setLayout(new BorderLayout());
    backPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    
    // Sets the background color of the master panel to light-grey, seta all other panels to transparent
    scorePan.setOpaque(false);
    wordPan.setOpaque(false);
    backPan.setOpaque(false);
    
    // Retrieves the image of the title
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(0)).getImage()
      .getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    title.setIcon(new ImageIcon(this.titleImage));
    
    // Retrieves the image of the back button
    this.backImage = new ImageIcon(SpriteSheets.buttonCrop(6 * 60))
      .getImage().getScaledInstance(150, 55, java.awt.Image.SCALE_SMOOTH);
    
    // Retrieves the two arrays from Score
    scores = Main.score.getScoreNum();
    names = Main.score.getScoreName();
    
    // Display the highscore
    for (int i = 0; i < 10; i++)
    {
      // Creates a placeholder JLabel if there are less then 10 elements in the name and score arrays
      if (i + 1 > names.size())
      {
        ranking[i] = new JLabel(String.format("%10d : %-18s %-10s", i + 1, "Empty", "0"));
      }
      
      // Writes the position, name and score to a JLabel
      else
      {
        ranking[i] = new JLabel(String.format("%10d : %-18s %-10s",
                                              i + 1, names.get(names.size() - i - 1), scores.get(scores.size() - i - 1)));
      }// end if
      
      // Sets the font for the label, adds the label to a panel, and then
      // creates a small space between it and the next label
      ranking[i].setFont(new Font("Consolas", Font.BOLD, 18));
      scorePan.add(ranking[i]);
      scorePan.add(Box.createVerticalStrut(5));
      
    }// end for
    
    // Gives the back buttons its image, sets its size, and then adds a action listener
    back.setIcon(new ImageIcon(this.backImage));
    back.setPreferredSize(new Dimension(150, 50));
    back.setMaximumSize(back.getPreferredSize());
    back.addActionListener(this);
    
    // Add the back button to the back button panel
    backPan.add(back);
    
    // Adds the title to the title panel
    wordPan.add(title);
    
    // Adds the panels to the master panel, which is set to BorderLayout
    masPan.add(wordPan, BorderLayout.NORTH);
    masPan.add(scorePan, BorderLayout.CENTER);
    masPan.add(backPan, BorderLayout.SOUTH);
    
    masPan.setBackground(Color.WHITE);
    masPan.setBorder(BorderFactory.createRaisedBevelBorder());
    
    // Adds the master panel to the frame and then packs the frame
    this.add(masPan);
    this.pack();
    this.setResizable(false);
    
  }// end default constructor
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Refreshes the contents of the JLabels to refelect the change after the player adds another score
   * @param none
   * @return void
   * */
  public void refreshScore()
  {
    // Retrieves the new arrays from Score
    scores = Main.score.getScoreNum();
    names = Main.score.getScoreName();
    
    // Goes through all of the JLabels and changes their contents according to the new arrays
    for (int i = 0; i < 10; i++)
    {
      // Changes the contents of each JLabel according to the new arrays
      ranking[i].setText(String.format("%10d : %-18s %-10s", i + 1,
                                       names.get(names.size() - i - 1), scores.get(scores.size() - i - 1)));
      
      // Break the loop
      if ((i + 1) == names.size())
      {
        break;
      }// end if
    }// end for
  }// end refreshScore
  
  /**
   * Sets this JFrame to visible.
   * @param none
   * @return void
   * */
  public void activate()
  {
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }// end activate
  
  /**
   * Sets this JFrame to invisible.
   * @param none
   * @return void
   **/
  public void deactivate()
  {
    this.setVisible(false);
  }// end deactivate
  
  
  /**************************
    * Event Handler Methods
    * **********************/
  
  /**
   * Action listener for JButton presses
   * @param ActionEvent e - java action listener
   * @return void
   **/
  public void actionPerformed(ActionEvent e)
  {
    // Deactivates this frame and returns to the previous frame is the back button was pressed
    if (e.getSource() == back)
    {
      Main.core.main();
      this.deactivate();
    }// end if
  }// end actionPerformed
  
}// end ScoreMenu Class
