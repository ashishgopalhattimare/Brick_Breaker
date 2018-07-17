//#Zhaoyang

// Import required packages

package ashish.hattimare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class SettingMenu extends JFrame implements ActionListener
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Initialize JPanels
  JPanel masPan   = new JPanel();
  JPanel butPan   = new JPanel();
  JPanel radioPan = new JPanel();
  JPanel wordPan  = new JPanel();
  JPanel backPan  = new JPanel();
  
  // Initialize JLabel, holds the title image
  JLabel title = new JLabel();
  
  // Initialize an array of JButtons
  JButton[] buttons = new JButton[3];
  
  //JRadioButtons for the player mode or God mode
  public static JRadioButton playerRadio, godRadio;
  private ButtonGroup modeGroup;
  
  // Initialize the JButton, for going back to previous menu
  JButton back = new JButton();
  
  // Initializes images for the buttons and the title image
  Image[] image = new Image[3];
  Image backImage, titleImage;
  
  // Initailize a image, done seperately due to size difference (not stored in sprite sheets)
  private String btnHow = "howBut.png";
  
  // Int Arrays, contains height and width of buttons
  int[] butWidth = { 280, 180, 180 };
  int[] butHeight = { 55, 55, 55 };
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor: Constructs a SettingMenu (extends JFrame) and the contents of the JFrame
   * @param none
   * */
  public SettingMenu()
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Sets the size for the JFrame and sets Layouts for the JPanels
    this.setPreferredSize(new Dimension(500, 550));
    masPan.setLayout(new BorderLayout());
    butPan.setLayout(new BoxLayout(butPan, BoxLayout.PAGE_AXIS));
    backPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    radioPan.setLayout(new FlowLayout());
    
    // Sets the background color of the master panel to light-grey, seta all other panels to transparent
    butPan.setOpaque(false);
    wordPan.setOpaque(false);
    backPan.setOpaque(false);
    radioPan.setOpaque(false);
    
    // Retrieves the image of the title
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(540)).getImage()
      .getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    title.setIcon(new ImageIcon(this.titleImage));
    
    // Retrieves the image of the back button
    this.backImage = new ImageIcon(SpriteSheets.buttonCrop(6 * 60))
      .getImage().getScaledInstance(150, 55, java.awt.Image.SCALE_SMOOTH);
    
    // Assings images from sprite sheets to each image in the image array,
    // then scales the images according to button heigh and width
    for (int i = 0; i < image.length; i++)
    {
      if (i == 0)
      {
        image[i] = new ImageIcon(btnHow).getImage().getScaledInstance(
                                                                      butWidth[i], butHeight[i], java.awt.Image.SCALE_SMOOTH);
      }
      else
      {
        this.image[i] = new ImageIcon(SpriteSheets.buttonCrop((i + 3) * 60)).getImage()
          .getScaledInstance(butWidth[i], butHeight[i], java.awt.Image.SCALE_SMOOTH);
      }// end if
    }// end for
    
    // Goes through the JButton array and creates the JButtons
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      
      // Sets the size for the JButtons
      buttons[i].setPreferredSize(new Dimension(butWidth[i], butHeight[i]));
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
      butPan.add(Box.createVerticalStrut(10));
      
    }// end for
    
    // Gives the back buttons its image, sets its size, and then adds a action listener
    back.setIcon(new ImageIcon(this.backImage));
    back.setPreferredSize(new Dimension(150, 50));
    back.setMaximumSize(back.getPreferredSize());
    back.addActionListener(this);
    
    // GAME MODE
    playerRadio = new JRadioButton("PLAYER MODE", true);
    godRadio = new JRadioButton("GOD MODE", false);
    
    // Add actionListeners to the modes
    playerRadio.addActionListener(this);
    godRadio.addActionListener(this);
    
    playerRadio.setOpaque(false);
    godRadio.setOpaque(false);
    
    // Add all the mode radioButtons to the group buttons
    modeGroup = new ButtonGroup();
    modeGroup.add(playerRadio);
    modeGroup.add(godRadio);
    
    // Add radio buttons to the radio Panel
    radioPan.add(playerRadio);
    radioPan.add(godRadio);
    
    butPan.add(radioPan);
    butPan.add(Box.createVerticalStrut(10));
    
    // Add the back button to the back button panel
    backPan.add(back);
    
    // Adds the title to the title panel
    wordPan.add(title);
    
    // Adds the panels to the master panel, which is set to BorderLayout
    masPan.add(wordPan, BorderLayout.NORTH);
    masPan.add(butPan, BorderLayout.CENTER);
    masPan.add(backPan, BorderLayout.SOUTH);
    
    masPan.setBackground(Color.WHITE);
    masPan.setBorder(BorderFactory.createRaisedBevelBorder());
    
    // Adds the master panel to the frame and then packs the frame
    this.add(masPan);
    this.pack();
    this.setResizable(false);
    
  }// edn default constructor
  
  
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
    // Goes throught the button array to determine which button was pressed
    for (int i = 0; i < buttons.length; i++)
    {
      if (buttons[i] == e.getSource())
      {
        /* Calls a different method in Main depening on the button
         * pressed, each method will open a different menu, then sets
         * this menu to invisible*/
        if (i == 0)
        {
          Main.core.howTo();
          this.deactivate();
        }
        else if (i == 1)
        {
          Main.core.credit();
          this.deactivate();
        } 
        else if (i == 2)
        {
          Main.core.control();
          this.deactivate();
        }// end if
      }// end if
    }// end for
    
    // If playerRadio is slected, turn off the god mode
    if(e.getSource() == playerRadio)
    {
      Destructor.godMode = false;
    }
    
    //#cheatMenu
    // Activate the god Mode if godRadio is selected
    else if(e.getSource() == godRadio)
    {
      Destructor.godMode = true;
    }
    
    // The back button was pressed, returns to previous menu
    else if (e.getSource() == back)
    {
      Main.core.main();
      this.deactivate();
    }// end if
  }// end actionPerformed
  
}// end SettingMenu
