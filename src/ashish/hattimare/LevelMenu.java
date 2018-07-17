//#Zhaoyang

// Import required packages

package ashish.hattimare;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LevelMenu extends JFrame implements ActionListener
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // Creates JPanels
  JPanel backPan = new JPanel();
  JPanel masPan = new JPanel();
  JPanel butPan = new JPanel();
  JPanel wordPan = new JPanel();
  
  // Creates JLabel and back button, then the attached image icon for the JLabel, and the back button
  JLabel title = new JLabel();
  JButton back = new JButton();
  Image backImage, titleImage;
  
  // JButton array stores JButtons for each of the levl options
  JButton[] btnLevel = new JButton[Destructor.LEVEL_END];
  JLabel[] btnLabel = new JLabel[Destructor.LEVEL_END];
  
  // Image array stores images for each of the levels
  ImageIcon[] levelImage = new ImageIcon[Destructor.LEVEL_END];
  
  // FileControl object creates the level after it has been selected
  FileControl file;
  
  
  /*****************************
    * Constructors
    * **************************/
  
  /**
   * Default Constructor: Constructs a LevelMenu (extends JFrame) and the contents of the JFrame
   * @param none
   * */
  public LevelMenu(FileControl file)
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Assigns the file object parameter to file
    this.file = file;
    
    // Sets the size for the JFrame
    this.setPreferredSize(new Dimension(580, 590));
    
    // Sets layouts for the JPanels
    masPan.setLayout(new BorderLayout());
    backPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    butPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    
    // Sets the background color of the master panel to light-grey, seta all other panels to transparent
    masPan.setBackground(new Color(230, 230, 230));
    butPan.setOpaque(false);
    wordPan.setOpaque(false);
    backPan.setOpaque(false);
    
    // Assigns an image cropped from a spritesheet to titleImage, sets the
    // image icon of the title label to the image
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(720)).getImage()
      .getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    title.setIcon(new ImageIcon(this.titleImage));
    
    // Retrieves the image of the back button
    this.backImage = new ImageIcon(SpriteSheets.buttonCrop(6 * 60))
      .getImage().getScaledInstance(150, 55, java.awt.Image.SCALE_SMOOTH);
    
    // Creates JButtons for each of the levels
    for (int i = 0; i < btnLevel.length; i++)
    {
      btnLevel[i] = new JButton( "" + (i + 1));
      btnLabel[i] = new JLabel();
      
      levelImage[i] = new ImageIcon(new ImageIcon(SpriteSheets.levelCrop(i % 5, i / 5))
                                    .getImage().getScaledInstance(100, 75, java.awt.Image.SCALE_SMOOTH));
      
      btnLabel[i].setIcon(levelImage[i]);
      btnLevel[i].add(btnLabel[i]);
      
      // Attaches an action listener to the JButton
      btnLevel[i].addActionListener(this);
      
      // Gives the JButton a beveled border
      btnLevel[i].setBorder(new CompoundBorder(BorderFactory.createRaisedBevelBorder(),
                                               BorderFactory.createLoweredBevelBorder()));
      
      // Sets the size of the JButton and then center aligns it
      btnLevel[i].setPreferredSize(new Dimension(100, 75));
      
      // Adds the JButton to the button panel
      butPan.add(btnLevel[i]);
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
    masPan.add(butPan, BorderLayout.CENTER);
    masPan.add(backPan, BorderLayout.SOUTH);
    
    masPan.setBackground(Color.WHITE);
    masPan.setBorder(BorderFactory.createRaisedBevelBorder());
    
    // Adds the master panel to the frame and then packs the frame
    this.add(masPan);
    this.pack();
    this.setResizable(false);
    
  }// end default constructor
  
  
  /*****************************
    * Instance Methods
    * **************************/
  
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
  
  /**
   * Action listener for JButton presses
   * @param ActionEvent e - java action listener
   * @return void
   * */
  public void actionPerformed(ActionEvent e)
  {
    // Determines which button was pressed
    for (int i = 0; i < btnLevel.length; i++)
    {
      if (e.getSource() == btnLevel[i])
      {
        // Sends the level to file for level construction
        Destructor.rectBrick_List = file.rectLevel("level" + (i + 1) + ".txt", (i + 1));
        
        // Sets in game variable sot reflect the level chosen
        Destructor.campaign = false;
        Destructor.currentLevel = i + 1;
        
        // Reset the scoreBoard and game components
        Destructor.player.setDefault();
        Destructor.lblLives.setText("Pads Left : " + Destructor.player.getLives());
        Destructor.lblScore.setText("Score : " + Destructor.player.getPoints());
        
        // Reset the slider
        Destructor.slider.setDefault();
        
        // Starts the game
        Destructor.setDefault();
        Destructor.activate();
        
        // Deactivates this frame
        this.deactivate();
      }// end if
    }// end for
    
    // Deactivates this frame and returns to the previous frame is the back button was pressed
    if (e.getSource() == back)
    {
      Main.core.main();
      this.deactivate();
    }// end if
  }// end actionPerformed
  
}// end LevelMenu