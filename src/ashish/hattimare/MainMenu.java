//#Zhaoyang

// Import required packages

package ashish.hattimare;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Creates JPanels
  private JPanel masPan = new JPanel();
  private JPanel butPan = new JPanel();
  private JPanel wordPan = new JPanel();
  
  // Creates JLabel, and the attached image icon
  private JLabel title = new JLabel();;
  private Image titleImage;
  
  // Int value stores length and width of JButtons
  private int width, height;
  
  // Creates array of image icons
  private Image[] image = new Image[4];
  
  
  /**************************
    * Class Variables
    * **********************/
  
  // Creates array of JButtons
  public static JButton[] buttons = new JButton[4];
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor: Constructs a MainMenu (extends JFrame) and the contents of the JFrame
   * @param none
   **/
  public MainMenu()
  {
    // Assigns values of length and width of JButtons
    this.width = 160;
    this.height = 50;
    
    this.setTitle("DESTRUCTOR");
    
    // Sets preferred size of JFrame
    this.setPreferredSize(new Dimension(500, 550));
    
    // Sets layout for the master panel and the button panel
    masPan.setLayout(new BoxLayout(masPan, BoxLayout.PAGE_AXIS));
    butPan.setLayout(new BoxLayout(butPan, BoxLayout.PAGE_AXIS));
    
    // Sets the background color of the master panel to light-grey, seta all other panels to transparent
    butPan.setOpaque(false);
    wordPan.setOpaque(false);
    
    // Assigns an image cropped from a spritesheet to titleImage, sets the
    // image icon of the title label to the image
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(1 * 180))
      .getImage().getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    title.setIcon(new ImageIcon(this.titleImage));
    
    // Goes through the ImageIcon array and assigns images from the sprite sheet to the image icons
    for (int i = 0; i < this.image.length; i++)
    {
      this.image[i] = new ImageIcon(SpriteSheets.buttonCrop(i * 60))
        .getImage().getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
    }// end for
    
    // Goes through the JButton array and creates the JButtons
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      
      // Sets the size for the JButtons
      buttons[i].setPreferredSize(new Dimension(this.width, this.height));
      buttons[i].setMaximumSize(buttons[i].getPreferredSize());
      
      // Aligns the JButtons to teh center of teh screen
      buttons[i].setAlignmentX(CENTER_ALIGNMENT);
      
      // Gives the JButtons a beveled border
      buttons[i].setBorder(BorderFactory.createRaisedBevelBorder());
      
      // Assigns image icons to teh buttons
      buttons[i].setIcon(new ImageIcon(this.image[i]));
      buttons[i].setContentAreaFilled(false);
      
      // Adds a action listener to the JButton
      buttons[i].addActionListener(this);
      
      // Adds the JButton tot eh button panel, then creates a small strut
      butPan.add(buttons[i]);
      butPan.add(Box.createVerticalStrut(15));
      
    }// end for
    
    // Adds the title to the title panel
    wordPan.add(title);
    
    // Adds all the panels to the master panel
    masPan.add(wordPan);
    masPan.add(butPan);
    masPan.add(Box.createVerticalStrut(180));
    
    masPan.setBackground(Color.WHITE);
    masPan.setBorder(BorderFactory.createRaisedBevelBorder());
    
    // Adds the master panel to the JFrame, then packs the JFrame
    this.add(masPan);
    this.pack();
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  }// end default constructor
  
  
  /**************************
    * Instance Methods
    * **********************/
  
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
   * */
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
   * */
  public void actionPerformed(ActionEvent e)
  {
    // Determines which button was pressed
    for (int i = 0; i < buttons.length; i++)
    {
      if (buttons[i] == e.getSource())
      {
        // Start the game
        if (i == 0)
        {
          Main.core.startGame();
          this.deactivate();
        } 
        
        // Go to level selection
        else if (i == 1)
        {
          Main.core.level();
          this.deactivate();
        }
        
        // Go to score menu
        else if (i == 2)
        {
          Main.core.score();
          this.deactivate();
        }
        
        // Go to setting menu
        else
        {
          Main.core.setting();
          this.deactivate();
        }// end if
      }// end if
    }// end for
  }// end actionPerformed
  
}// end MainMenu Class
