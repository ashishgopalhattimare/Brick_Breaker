//#Zhaoyang

//Import required packages

package ashish.hattimare;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class HowMenu extends JFrame implements ActionListener
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // Creates JPanels
  private JPanel masPan = new JPanel();
  private JPanel wordPan = new JPanel();
  private JPanel backPan = new JPanel();
  
  // Create JLabel for instructions
  private JLabel instruction;
  
  // Creates title JLabel and back button, then the attached image icon for the JLabel, and the back button
  private JButton back = new JButton("back");
  private JLabel title = new JLabel();
  private Image backImage, titleImage;
  
  
  /*****************************
    * Constructors
    * **************************/
  
  /**
   * Default Constructor: Constructs a LevelMenu (extends JFrame) and the contents of the JFrame
   * @param none
   * */
  public HowMenu()
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Sets the size for the JFrame
    this.setPreferredSize(new Dimension(500, 550));
    
    // Sets layouts for the JPanels
    this.masPan.setLayout(new BorderLayout());
    this.backPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    
    // Sets the background color of the master panel to light-grey, seta all
    // other panels to transparent
    this.wordPan.setOpaque(false);
    this.backPan.setOpaque(false);
    
    this.instruction = new JLabel(new ImageIcon(new ImageIcon("instruction.png").
                                                  getImage().getScaledInstance(480, 250, java.awt.Image.SCALE_SMOOTH)));
    
    // Assigns an image cropped from a spritesheet to titleImage, sets the
    // image icon of the title label to the image
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(900)).getImage()
      .getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    this.title.setIcon(new ImageIcon(this.titleImage));
    
    // Retrieves the image of the back button
    this.backImage = new ImageIcon(SpriteSheets.buttonCrop(360)).getImage()
      .getScaledInstance(150, 55, java.awt.Image.SCALE_SMOOTH);
    
    // Gives the back buttons its image, sets its size, and then adds a action listener
    this.back.setIcon(new ImageIcon(this.backImage));
    this.back.setPreferredSize(new Dimension(150, 50));
    this.back.setMaximumSize(back.getPreferredSize());
    this.back.addActionListener(this);
    
    // Add the back button to the back button panel
    this.backPan.add(back);
    
    // Adds the title to the title panel
    this.wordPan.add(title);
    
    // Adds the panels to the master panel, which is set to BorderLayout
    this.masPan.add(wordPan, BorderLayout.NORTH);
    this.masPan.add(instruction, BorderLayout.CENTER);
    this.masPan.add(backPan, BorderLayout.SOUTH);
    
    this.masPan.setBackground(Color.WHITE);
    this.masPan.setBorder(BorderFactory.createRaisedBevelBorder());
    
    // Adds the master panel to the frame and then packs the frame
    this.add(this.masPan);
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
    // Deactivates this frame and returns to the previous frame is the back button was pressed
    if (e.getSource() == back)
    {
      Main.core.setting();
      this.deactivate();
    }// end if
  }// end actionPerformed
  
}// end HowMenu
