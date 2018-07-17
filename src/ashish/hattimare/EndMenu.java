//#Zhaoyang

//Import required packages

package ashish.hattimare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class EndMenu extends JFrame implements ActionListener, WindowListener
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // Initialize JPanels
  private JPanel masPan = new JPanel();
  private JPanel wordPan;
  private JPanel namePan;
  private JPanel warnPan;
  private JPanel savePan;
  private JPanel scorPan;
  
  // Initialize JButton for saving player name
  private JButton btnSave;
  
  // Initialize JLabel for warning and name
  private JLabel lblName, lblWarn, lblScor;
  
  // Initialize JTextField for entering name
  private JTextField nameField;
  
  // Initialize int to store the score of the eplayer
  private int playScore = 0;
  
  // Initialize font for text
  private Font font, warnFont, scorFont;
  
  /*****************************
    * Constructors
    ****************************/
  
  /**
   * Default Constructor:
   * Constructs a EndMenu (extends JFrame) and the contents of the JFrame
   * @param none
   **/
  public EndMenu()
  {
    
    this.setTitle("DESTRUCTOR");
    
    // Creates the fonts in use
    font = new Font("Consolas", Font.PLAIN, 18);
    scorFont = new Font("Consolas", Font.BOLD, 20);
    warnFont = new Font("Consolas", Font.PLAIN | Font.BOLD, 10);
    
    // Sets layout, border and size for master panel
    masPan.setLayout(new BoxLayout(masPan, BoxLayout.Y_AXIS));
    masPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    masPan.setPreferredSize(new Dimension(200, 150));
    
    // Creates layouts for the JPanels
    wordPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
    warnPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
    namePan = new JPanel(new BorderLayout());
    savePan = new JPanel(new BorderLayout());
    scorPan = new JPanel(new BorderLayout());
    
    // Sets text for JLabels
    lblName = new JLabel("Your name");
    lblName.setFont(font);
    lblWarn = new JLabel("[ in 15 characters]");
    lblWarn.setFont(warnFont);
    lblScor = new JLabel("Score : " + this.playScore);
    lblScor.setFont(scorFont);
    
    // Set size of JTextField for entering name
    nameField = new JTextField(10);
    
    // Set text for button and adds action listener
    btnSave = new JButton("Save Score");
    btnSave.addActionListener(this);
    
    // Adds various components to JPanels
    scorPan.add(lblScor);
    wordPan.add(lblName);
    warnPan.add(lblWarn);
    namePan.add(nameField);
    savePan.add(btnSave, BorderLayout.CENTER);
    
    // Adds JPanels to master panel
    masPan.add(scorPan);
    masPan.add(wordPan);
    masPan.add(warnPan);
    masPan.add(namePan);
    masPan.add(Box.createVerticalStrut(10));
    masPan.add(savePan);
    
    // Adds the master panel to the frame and then packs the frame
    this.add(masPan);
    this.pack();
    this.setResizable(false);
    this.setAlwaysOnTop(true);
    
    // Adds a window listener
    addWindowListener(this);
    
  }// end default constructor
  
  
  /*****************************
    * Instance Methods
    * **************************/
  
  /**
   * Sets scoreLbl to display the player score, and sets playScore to score
   * @param int score - the score of the player in the game
   * @return void
   **/
  public void endScore(int score)
  {
    this.playScore = score;
    lblScor.setText("Score : " + this.playScore);
    
    // disable all the main menu buttons
    for(int x = 0; x < MainMenu.buttons.length; x++)
    {
      MainMenu.buttons[x].setEnabled(false);
    }// end for
    
  }// end endScore
  
  /**
   * Sets this JFrame to visible.
   * @param none
   * @return void
   **/
  public void activate()
  {
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
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
  
  //#error
  /**
   * Action listener for JButton presses
   * @param ActionEvent e - java action listener
   * @return void
   **/
  public void actionPerformed(ActionEvent e)
  {
    // Submit button takes the text from the text field, confirms its
    // validity, the sends it and th escore off to the Score class
    if (e.getSource() == btnSave)
    {
      // If the name is empty then it is not valid
      if (nameField.getText().length() == 0)
      {
        lblWarn.setText("Please enter your name");
      }
      else
      {
        // Score class is called to add the score and player to the score text
        Main.score.addScore(nameField.getText(), playScore);
        
        // Score class will then write everything to the score text file
        // and then refresh the score display under the score menu
        Main.score.writeScore();
        Main.mScore.refreshScore();
        
        // Reset this Class and the player score
        nameField.setText("");
        Destructor.player.setPoints(0);
        
        Destructor.deactivate();
        this.deactivate();
        
        // enable all the main menu buttons
        for(int x = 0; x < MainMenu.buttons.length; x++)
        {
          MainMenu.buttons[x].setEnabled(true);
        }// end for
        
        Main.core.main();
        
      }// end if
    }// end if
  }// end actionPerformed
  
  @Override
  /**
   * If the close button is clicked, go the main menu
   */
    public void windowClosing(WindowEvent e)
  {
    // Go to the main menu
    nameField.setText("");
    Destructor.player.setPoints(0);
    this.deactivate();
    
    Destructor.deactivate();
    
    // enable all the main menu buttons
    for(int x = 0; x < MainMenu.buttons.length; x++)
    {
      MainMenu.buttons[x].setEnabled(true);
    }// end for
    
    Main.core.main();
  }// end windowClosing(WindowEvent)
  
  
  /*******************************
    * Unused windowListeners
    * ****************************/
  @Override
  public void windowActivated(WindowEvent e){}
  public void windowClosed(WindowEvent e){}
  public void windowDeactivated(WindowEvent e){}
  public void windowDeiconified(WindowEvent e){}
  public void windowIconified(WindowEvent e){}
  public void windowOpened(WindowEvent e){}
  public void windowStateChanged(WindowEvent e){}
  
}// end EndMenu
