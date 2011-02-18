package movieMaker;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.Properties;
import java.io.*;
import java.net.*;
  
/**
 * A class to make working with a file chooser easier
 * for students.  It uses a JFileChooser to let the user
 * pick a file and returns the choosen file name.
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class FileChooser 
{

  ///////////////////////////// class fields ///////////////////
   /**
   * Properities to use during execution
   */
  private static Properties appProperties = null;
  
  /**
   * Property key for the media directory
   */
  private static final String MEDIA_DIRECTORY = "mediaDirectory";
  
  /**
   * Name for property file
   */
  private static final String PROPERTY_FILE_NAME = 
    "SimplePictureProperties.txt";
  
  /////////////////////// methods /////////////////////////////
  
  /**
   * Method to pick an item using the file chooser
   * @param fileChooser the file Chooser to use
   * @return the path name
   */
  public static String pickPath(JFileChooser fileChooser)
  {
    String path = null;
    
    /* create a JFrame to be the parent of the file 
     * chooser open dialog if you don't do this then 
     * you may not see the dialog.
     */
    JFrame frame = new JFrame();
   // frame.setAlwaysOnTop(true);
    
    // get the return value from choosing a file
    int returnVal = fileChooser.showOpenDialog(frame);
    
    // if the return value says the user picked a file 
    if (returnVal == JFileChooser.APPROVE_OPTION)
      path = fileChooser.getSelectedFile().getPath();
    return path;
  }
 
  /**
   * Method to let the user pick a file and return
   * the full file name as a string.  If the user didn't 
   * pick a file then the file name will be null.
   * @return the full file name of the picked file or null
   */
  public static String pickAFile()
  {
    JFileChooser fileChooser = null;
    
    // start off the file name as null
    String fileName = null;
    
    // get the current media directory
    String mediaDir = getMediaDirectory();
    
    /* create a file for this and check that the directory exists
     * and if it does set the file chooser to use it
     */
    try {
      File file = new File(mediaDir);
      if (file.exists())
        fileChooser = new JFileChooser(file);
    } catch (Exception ex) {
    }
    
    // if no file chooser yet create one
    if (fileChooser == null)
      fileChooser = new JFileChooser();
    
    // pick the file
    fileName = pickPath(fileChooser);
    
    return fileName;
  }
  
  /**
   * Method to let the user pick a directory and return
   * the full path name as a string. 
   * @return the full directory path
   */
  public static String pickADirectory()
  {
    JFileChooser fileChooser = null;
    String dirName = null;
    
    // get the current media directory
    String mediaDir = getMediaDirectory();
    
    // if no file chooser yet create one
    if (mediaDir != null)
      fileChooser = new JFileChooser(mediaDir);
    else
      fileChooser = new JFileChooser();
    
    // allow only directories to be picked
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
    // pick the directory
    dirName = pickPath(fileChooser);
    
    return dirName + "/";
  }
  
   /**
  * Method to get the full path for the passed file name
  * @param fileName the name of a file
  * @return the full path for the file
  */
 public static String getMediaPath(String fileName) 
 {
   String path = null;
   String directory = getMediaDirectory();
   
   // if the directory is null ask the user for it
   if (directory == null)
   {
     SimpleOutput.showError(java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("THE_MEDIA_PATH_(DIRECTORY)") +
       " "+java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("_HAS_NOT_BEEN_SET_YET!__") +"  "+
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("PLEASE_PICK_THE_DIRECTORY_") +" "+
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("THAT_CONTAINS_YOUR_MEDIA_") +" "+
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("(USUALLY_CALLED_MEDIASOURCES)_") +" "+
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("WITH_THE_FOLLOWING_FILECHOOSER.__") +"  "+
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("THE_DIRECTORY_NAME_WILL_BE_STORED_")+" " +
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("IN_A_FILE_AND_REMAIN_UNCHANGED_UNLESS_YOU_USE_")+" " +
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("FILECHOOSER.PICKMEDIAPATH()_OR_")+" " +
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("FILECHOOSER.SETMEDIAPATH(\"FULL_PATH_NAME\")_")+" " +
       java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("(EX:_FILECHOOSER.SETMEDIAPATH(\"C:/INTRO-PROG-JAVA/MEDIASOURCES/\"))_") +" "+
       " "+java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("_TO_CHANGE_IT."));
     pickMediaPath();
     directory = getMediaDirectory();
   }
   
   // get the full path
   path = directory + fileName;
   
   return path;
 }
 
 /**
  * Method to get the directory for the media
  * @return the media directory
  */
 public static String getMediaDirectory() 
 {
   String directory = null;
   
   
   
   // check if the application properties are null
   if (appProperties == null)
   {
     appProperties = new Properties();
     
     // load the properties from a file
     try {
       // get the URL for where we loaded this class 
       Class currClass = Class.forName("FileChooser");
       URL classURL = currClass.getResource("FileChooser.class");
       URL fileURL = new URL(classURL,PROPERTY_FILE_NAME);
       FileInputStream in = new FileInputStream(fileURL.getPath());
       appProperties.load(in);
       in.close();
     } catch (Exception ex) {
       directory = null;
     }
   }
   
   // get the media directory
   if (appProperties != null)
     directory = (String) appProperties.get(MEDIA_DIRECTORY);
   
   return directory;
 }
 
 /**
  * Method to set the media path by setting the directory to use
  * @param directory the directory to use for the media path
  */
 public static void setMediaPath(String directory) 
 { 
   
   // check if the directory exists
   File file = new File(directory);
   if (!file.exists())
   {
     System.out.println(java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("SORRY_BUT_") +" "+ directory +
                 " "+java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("_DOESN'T_EXIST,_TRY_A_DIFFERENT_DIRECTORY."));
     FileChooser.pickMediaPath();
   }
   
   else {
     
     /* check if there is an application properties object 
      * and if not create one
      */
     if (appProperties == null)
       appProperties = new Properties();
       
       // set the media directory property
       appProperties.put(MEDIA_DIRECTORY,directory);
     
     // write out the application properties to a file
     try {
       
       // get the URL for where we loaded this class 
       Class currClass = Class.forName("FileChooser");
       URL classURL = currClass.getResource("FileChooser.class");
       URL fileURL = new URL(classURL,PROPERTY_FILE_NAME);
       FileOutputStream out = 
         new FileOutputStream(fileURL.getPath());
       appProperties.store(out, 
                     java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("PROPERTIES_FOR_THE_SIMPLE_PICTURE_CLASS"));
       out.close();
       //System.out.println("The media directory is now " + directory);
     } catch (Exception ex) {
       System.err.println(java.util.ResourceBundle.getBundle("movieMaker/FileChooser").getString("COULDN'T_SAVE_MEDIA_PATH_TO_A_FILE"));
     }
   }
 }
 
 /**
  * Method to pick a media path using
  * the file chooser and set it 
  */
 public static void pickMediaPath()
 {
   String dir = pickADirectory();
   setMediaPath(dir);
 }
   
}