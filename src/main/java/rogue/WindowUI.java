package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;




import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class WindowUI extends JFrame {


    private SwingTerminal terminal;
    private TerminalScreen screen;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1800;
    // Screen buffer dimensions are different than terminal dimensions
    public static final int COLS = 90;
    public static final int ROWS = 54;
   private final char startCol = 0;
   private final char msgRow = 1;
   private final char roomRow = 5;
   private Container contentPane;
   
   
   private static JPanel labelPanel = new JPanel();
   
   /* Inventory */ 
   private static JPanel invPanel = new JPanel();
   private static JList<Item> invList = new JList<>();
   private static DefaultListModel<Item> invModel = new DefaultListModel<>();

   private ArrayList<Item> uniqueItems = new ArrayList<>();



/**
Constructor.
**/

    public WindowUI() {
        super("my awesome game");
        contentPane = getContentPane();
        setWindowDefaults(getContentPane());
        setUpPanels();
        makeMenuBar();
        pack();
        start();
    }

    private void setWindowDefaults(Container contentPane) {
        setTitle("Rogue!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());

    }

    private void setTerminal() {
        JPanel terminalPanel = new JPanel();
        terminal = new SwingTerminal();
        terminalPanel.add(terminal);
        contentPane.add(terminalPanel, BorderLayout.CENTER);
    }

    private void setUpPanels() {
        setUpLabelPanel();
        setUpInventoryPanel();
        setTerminal();
    }

    private void setUpLabelPanel() {
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        labelPanel.setBorder(prettyLine);
        JLabel exampleLabel = new JLabel("Tomorrow and tomorrow and tomorrow");
        labelPanel.add(exampleLabel);
        // JTextField dataEntry = new JTextField("Enter text here", 25);
        JTextField dataEntry = new JTextField("Enter text here");
        labelPanel.add(dataEntry);
        JButton clickMe = new JButton("Click Me");
        labelPanel.add(clickMe);
        contentPane.add(labelPanel, BorderLayout.SOUTH);
    }


    private void setUpInventoryPanel() {
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        
        
        invList.setModel(invModel);

        invList.setFixedCellHeight(30);
        invList.setFixedCellWidth(100);

        invList.setCellRenderer(new InventoryRenderer());
        
        invPanel.setBorder(prettyLine);
     
        invPanel.add(invList);
        
        contentPane.add(invPanel, BorderLayout.EAST);

        invList.getSelectionModel().addListSelectionListener(e-> {
            Item selectedItem = (Item)invList.getSelectedValue();
            String message = "Item Name : " + selectedItem.getName() + "\nItem Descriton : " + selectedItem.getDescription();
            JOptionPane.showMessageDialog(invPanel, message);


        });
    }



    private void makeMenuBar() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu fileMenu = new JMenu("File");
        menu.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open JSON");
        fileMenu.add(openItem);
        // add listener that will open and parse a new JSON file
        JMenuItem loadItem = new JMenuItem("Load Game");
        fileMenu.add(loadItem);
        // add listener that will load a prviously saved game
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        // add listener that will save the game
        JMenuItem playerItem = new JMenuItem("Change Name");
        fileMenu.add(playerItem);
        // add listener that will change the players name
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
    }

    private void start() {
        try {
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(TerminalPosition.TOP_LEFT_CORNER);
            screen.startScreen();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
Prints a string to the screen starting at the indicated column and row.
@param toDisplay the string to be printed
@param column the column in which to start the display
@param row the row in which to start the display
**/
        public void putString(String toDisplay, int column, int row) {

            Terminal t = screen.getTerminal();
            try {
                t.setCursorPosition(column, row);
            for (char ch: toDisplay.toCharArray()) {
                t.putCharacter(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

/**
Changes the message at the top of the screen for the user.
@param msg the message to be displayed
**/
            public void setMessage(String msg) {
                putString("                                                ", 1, 1);
                putString(msg, startCol, msgRow);
            }



/**
Redraws the whole screen including the room and the message.
@param message the message to be displayed at the top of the room
@param room the room map to be drawn
**/
            public void draw(String message, String room) {

                try {
                    screen.clear();
                    screen.refresh();
                    setMessage(message);
                    putString(room, startCol, roomRow);
                    screen.refresh();
                } catch (IOException e) {

                }

        }

/**
Obtains input from the user and returns it as a char.  Converts arrow
keys to the equivalent movement keys in rogue.
@return the ascii value of the key pressed by the user
**/
        public char getInput() {
            KeyStroke keyStroke = null;
            char returnChar;
            while (keyStroke == null) {
            try {
                keyStroke = screen.pollInput();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
         if (keyStroke.getKeyType() == KeyType.ArrowDown) {
            returnChar = Rogue.DOWN;  //constant defined in rogue
        } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
            returnChar = Rogue.UP;
        } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
            returnChar = Rogue.LEFT;
        } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
            returnChar = Rogue.RIGHT;
        } else {
            returnChar = keyStroke.getCharacter();
        }
        return returnChar;
    }


    private void addToInventory (Rogue theGame) {
        
        
        HashMap<Integer, Item> currentInventory = null;
        currentInventory = theGame.getInventory();
        // for (Item item: currentInventory.values()) {
        //     System.out.println("Teheeee : " + item.getDescription());
        // }
        
        if (currentInventory.isEmpty()) {
            return;
        } else {
            for (Item item: currentInventory.values()) {
                if (uniqueItems.indexOf(item) == -1) {
                    uniqueItems.add(item);
                    invModel.addElement(item);
                }
                
                System.out.println("Description : " + item.getDescription());

            }   

        }
    
        
    }

/**
The controller method for making the game logic work.
@param args command line parameters
**/
    public static void main(String[] args) {

    char userInput = 'h';
    String message;
    String configurationFileLocation = "fileLocations.json";
    //Parse the json files
    RogueParser parser = new RogueParser(configurationFileLocation);
    //allocate memory for the GUI
    WindowUI theGameUI = new WindowUI();
    // allocate memory for the game and set it up
    Rogue theGame = new Rogue(parser);
   //set up the initial game display
    Player thePlayer = new Player("Judi");
    theGame.setPlayer(thePlayer);
    message = "Welcome to my Rogue game";
    theGameUI.draw(message, theGame.getNextDisplay());
    theGameUI.setVisible(true);


    
    while (userInput != 'q') {
    //get input from the user
    userInput = theGameUI.getInput();

    //ask the game if the user can move there
    try {
        message = theGame.makeMove(userInput);
        theGameUI.draw(message, theGame.getNextDisplay());
        theGameUI.addToInventory(theGame);
        // get inventory update
    } catch (InvalidMoveException badMove) {
        message = "I didn't understand what you meant, please enter a command";
        theGameUI.setMessage(message);
    }
    }

    }

}
