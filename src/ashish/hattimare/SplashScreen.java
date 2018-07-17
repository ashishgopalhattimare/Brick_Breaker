//#Ashish

// Import all the required java packages

package ashish.hattimare;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow implements ActionListener
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  private Timer time;
  private int wait;
  
  private long startTime, endTime;
  private int duration;
  
  private JLabel lblImage;
  private JPanel mainPanel, splashPanel, imagePanel;
  private ImageIcon image;
  
  private JProgressBar loading;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Contructor for the splash screen
   * This runs only when the game is opened
   * */
  public SplashScreen(int wait)
  {
    time = new Timer(10, this);
    this.wait = wait;
    
    this.startTime = System.currentTimeMillis();
    
    time.start();
  }// end 
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  // A simple little method to show a title screen in the center
  // of the screen for the amount of time given in the constructor
  public void showSplash()
  {
    mainPanel = (JPanel) getContentPane();
    
    splashPanel = new JPanel();
    splashPanel.setLayout(new BoxLayout(splashPanel, BoxLayout.Y_AXIS));
    
    imagePanel = new JPanel(new BorderLayout());
    
    loading = new JProgressBar();
    loading.setValue(25);
    loading.setStringPainted(true);
    
    image = new ImageIcon(new ImageIcon("splash.jpg").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
    
    // Build the splash screen
    lblImage = new JLabel(image, JLabel.CENTER);
    
    imagePanel.add(lblImage, BorderLayout.CENTER);
    
    splashPanel.add(imagePanel);
    splashPanel.add(loading);
    splashPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    
    mainPanel.add(splashPanel);
    
    // Display the splash screen
    setVisible(true);
    pack();
    setLocationRelativeTo(null);
    setAlwaysOnTop(true);
    
   
    // Wait for the wait time to over
    try
    {
      Thread.sleep(wait);
    } 
    catch (Exception e){}
    
    setVisible(false);
    
  }// end showSplash()
  
  /**
   * Run the splash
   * */
  public void showSplashAndExit()
  {
    showSplash();
    time.stop();
  }// end showSplashAndExit()
  
  
  /**************************
    * Event Handler Methods
    * **********************/
  
  public void actionPerformed(ActionEvent e)
  {
    // Calculate the current time
    this.endTime = System.currentTimeMillis();
    this.duration = (int) (endTime - startTime) / 1000;
    
    // Load the game
    this.loading.setValue((int) ((duration / 5.0) * 100));
  
  }// end actionPerformed(ActionEvent)
  
}// end SplashScreen Class