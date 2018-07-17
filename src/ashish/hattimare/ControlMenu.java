//#Zhaoyang

//Import required packages

package ashish.hattimare;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ControlMenu extends JFrame implements ActionListener
{
  
  
  /**************************
    * Instance variables
    * **********************/
  
  // Creates JPanels
  JPanel masPan = new JPanel();
  JPanel wordPan = new JPanel();
  JPanel backPan = new JPanel();
  
  Image backImage, titleImage;
  JLabel title = new JLabel();
  JButton back = new JButton();
  
  // Create JLabel for instructions, instruction attached as iamge
  private JLabel instruction;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor: Constructs a LevelMenu (extends JFrame) and the contents of the JFrame
   * @param none
   * */
  public ControlMenu()
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Sets the size for the JFrame
    this.setPreferredSize(new Dimension(500, 610));
    
    // Sets layouts for the JPanels
    masPan.setLayout(new BorderLayout());
    backPan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    
    // Sets the background color of the master panel to light-grey, seta all other panels to transparent
    wordPan.setOpaque(false);
    backPan.setOpaque(false);
    
    this.instruction = new JLabel(new ImageIcon(new ImageIcon("controls.png").
                                                  getImage().getScaledInstance(450, 350, java.awt.Image.SCALE_SMOOTH)));
    
    // Assigns an image cropped from a spritesheet to titleImage, sets the
    // image icon of the title label to the image
    this.titleImage = new ImageIcon(SpriteSheets.titleCrop(360)).getImage()
      .getScaledInstance(500, 180, java.awt.Image.SCALE_SMOOTH);
    
    title.setIcon(new ImageIcon(this.titleImage));
    
    // Retrieves the image of the back button
    this.backImage = new ImageIcon(SpriteSheets.buttonCrop(360)).getImage()
      .getScaledInstance(150, 55, java.awt.Image.SCALE_SMOOTH);
    
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
    masPan.add(instruction, BorderLayout.CENTER);
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
    * Event Handling Methods
    * **********************/
  
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
  
}// end ControlMenu
