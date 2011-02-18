package movieMaker;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * Class that holds the buttons for the movie player
 * @author Barb Ericson
 */
public class ButtonPanel extends JPanel
{
  //////////////// fields ////////////////////////
   /** list for the frame rate */
  private JList frameRateList = null;
  /** label for frame rate */
  private JLabel frameRateLabel = null;
  private JButton nextButton = new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("NEXT"));
  private JButton playButton = new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("PLAY_MOVIE"));
  private JButton prevButton = new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("PREV"));
  private JButton delBeforeButton = 
    new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("DELETE_ALL_PREVIOUS"));
  private JButton delAfterButton = 
    new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("DELETE_ALL_AFTER"));
  private JButton writeQuicktimeButton = 
    new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("WRITE_QUICKTIME"));
  private JButton writeAVIButton = new JButton(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("WRITE_AVI"));
  private MoviePlayer moviePlayer = null;
  
  ///////////////// Constructors /////////////////
  
  /**
   * Constructor that doesn't take any parameters
   */
  public ButtonPanel(MoviePlayer player) 
  {
    this.moviePlayer = player;
    
    // add the previous and next buttons to this panel
    this.add(prevButton);
    this.add(nextButton);
    
    // set up the frame rate list
    frameRateLabel = new JLabel(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("FRAMES_PER_SECOND:_")+" ");
    this.add(frameRateLabel);
    String[] rates = {"16","24","30"};
    frameRateList = new JList(rates);
    JScrollPane scrollPane = new JScrollPane(frameRateList);
    frameRateList.setSelectedIndex(0);
    frameRateList.setVisibleRowCount(1);
    frameRateList.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("THE_NUMBER_OF_FRAMES_PER_SECOND_IN_THE_MOVIE"));
    frameRateList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        String rateS = (String) frameRateList.getSelectedValue();
        int rate = Integer.parseInt(rateS);
        moviePlayer.setFrameRate(rate);
      }
    });
    this.add(scrollPane);
    
    this.add(playButton);
    this.add(delBeforeButton);
    this.add(delAfterButton);
    this.add(writeQuicktimeButton);
    this.add(writeAVIButton);
    
    // add the action listeners to the buttons
    nextButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_SEE_THE_NEXT_FRAME"));
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.showNext();
      }});
    prevButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_SEE_THE_PREVIOUS_FRAME"));
    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)  {
        moviePlayer.showPrevious();
      }});
    playButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_PLAY_THE_MOVIE"));
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.playMovie();
      }});
    delBeforeButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_DELETE_ALL_FRAMES_BEFORE_THE_CURRENT_ONE"));
    delBeforeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.delAllBefore();
      }});
    delAfterButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_DELETE_ALL_FRAMES_AFTER_THE_CURRENT_ONE"));
    delAfterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.delAllAfter();
      }});
    writeQuicktimeButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_WRITE_OUT_A_QUICKTIME_MOVIE_FROM_THE_FRAMES"));
    writeQuicktimeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.writeQuicktime();
      }});
    writeAVIButton.setToolTipText(java.util.ResourceBundle.getBundle("movieMaker/ButtonPanel").getString("CLICK_TO_WRITE_OUT_AN_AVI_MOVIE_FROM_THE_FRAMES"));
    writeAVIButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        moviePlayer.writeAVI();
      }});
  }
  
}