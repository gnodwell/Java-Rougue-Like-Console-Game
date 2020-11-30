package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.util.HashMap;
// import java.util.zip.CheckedInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
// import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.border.EtchedBorder;
// import javax.swing.event.ListSelectionListener;




// import java.awt.Color;
// import java.awt.BorderLayout;
// import javax.swing.JComponent;
import javax.swing.JList;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
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
    private static JPanel invPanel = new JPanel(); //change it back from static
    private static JList<Item> invList = new JList<>();
    private static DefaultListModel<Item> invModel = new DefaultListModel<>();

    private ArrayList<Item> uniqueItems = new ArrayList<>();

    private  JList<Item> edibleList = new JList();
    private ArrayList<Item> uniqueEdibles = new ArrayList<>();
    private  DefaultListModel<Item> edibleModel = new DefaultListModel<>();

    private  JList<Item> wearableList = new JList();
    private ArrayList<Item> uniqueWearables = new ArrayList<>();
    private  DefaultListModel<Item> wearableModel = new DefaultListModel<>();

    private  JList<Item> tossableList = new JList();
    private ArrayList<Item> uniqueTossables = new ArrayList<>();
    private  DefaultListModel<Item> tossableModel = new DefaultListModel<>();

    // private JTextField playerField = new JTextField(30); //commented thius out idk if it breaks
    private JTextField playerField = new JTextField();

    private JButton nameSubmit = new JButton("Submit");
    private JTextField playerSubmit = new JTextField("Enter Text Here");

    private static final int THIRTY = 30;
    private static final int ONEHUNDRED = 100;

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


        JLabel exampleLabel = new JLabel("Player Name");
        labelPanel.add(exampleLabel);
        // JTextField dataEntry = new JTextField("Enter text here", 25);
        labelPanel.add(playerSubmit);


        labelPanel.add(nameSubmit);

        contentPane.add(labelPanel, BorderLayout.SOUTH);
    }


    private void setUpInventoryPanel() {
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        invList.setModel(invModel);
        invList.setFixedCellHeight(THIRTY);
        invList.setFixedCellWidth(ONEHUNDRED);

        invList.setCellRenderer(new InventoryRenderer());

        invPanel.setBorder(prettyLine);

        invPanel.add(invList);

        contentPane.add(invPanel, BorderLayout.EAST);

        // change it to single responsibilty
        invList.getSelectionModel().addListSelectionListener(e -> {
            Item selectedItem = (Item) invList.getSelectedValue();
            if (selectedItem != null) {
                String message = "Item Name : " + selectedItem.getName()
                + "\nItem Descriton : " + selectedItem.getDescription();
                String[] options = {"Eat", "Wear", "Toss"};
                int optionChosen = JOptionPane.showOptionDialog(invPanel, message, "Options for "
                + selectedItem.getName(), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, null);
                handleETW(optionChosen, selectedItem);

            }

        });
    }

    private void handleETW(int option, Item chosenItem) {
        switch (option) {
            case 0:
            Class e = chosenItem.getClass();
            if (!chosenItem.getCanEat()) {
                System.out.println("Not edible");
                JOptionPane.showMessageDialog(invPanel, "The item is not edible !");
            } else {
                //EAT
                invModel.removeElement(chosenItem);
                edibleModel.removeElement(chosenItem);
            }
            break;

            case 1:
            Class w = chosenItem.getClass();
            if (!chosenItem.getCanWear()) {
                System.out.println("Not wearable");
                JOptionPane.showMessageDialog(invPanel, "The item is not wearable !");
            } else {
                //WEAR
                invModel.removeElement(chosenItem);
                wearableModel.removeElement(chosenItem);
            }
            break;

            case 2:
            Class t = chosenItem.getClass();
            if (!chosenItem.getCanThrow()) {
                System.out.println("Not tossable");
                JOptionPane.showMessageDialog(invPanel, "The item is not tossable !");
            } else {
                //TOSS
                invModel.removeElement(chosenItem);
                tossableModel.removeElement(chosenItem);
            }
            break;
            default:
            break;

        }
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


    private void addToInventory(Rogue theGame) {

        HashMap<Integer, Item> currentInventory = null;
        currentInventory = theGame.getInventory();

        if (currentInventory.isEmpty()) {
            return;
        } else {
            for (Item item: currentInventory.values()) {
                if (uniqueItems.indexOf(item) == -1) {
                    uniqueItems.add(item);
                    invModel.addElement(item);
                }
            }
        }
    }

    private void checkInventoryAction(char input, Rogue theGame) {
        switch (input) {
            case 'e':
            displayEdbileList(theGame);
            break;

            case 'w':
            displayWearableList(theGame);
            break;

            case 't':
            displayTossableList(theGame);
            break;
            default:
            break;
        }

        return;
    }


    private void displayTossableList(Rogue theGame) {
        HashMap<Integer, Item> currentInventory = null;
        currentInventory = theGame.getInventory();
        tossableList.setModel(tossableModel);
        tossableList.setCellRenderer(new InventoryRenderer());
        if (currentInventory.isEmpty()) {
            return;
        } else {
            for (Item item: currentInventory.values()) {
                if (uniqueTossables.indexOf(item) == -1 && item.getCanThrow()) {
                    uniqueTossables.add(item);
                    tossableModel.addElement(item);
                }
            }
        }
        tossableList.getSelectionModel().addListSelectionListener(e -> {
            Item selectedItem = (Item) tossableList.getSelectedValue();
            if (selectedItem != null) {
                String message = "Item Name : " + selectedItem.getName()
                + "\nItem Descriton : " + selectedItem.getDescription();
                System.out.println("LOOL" + message);
                invModel.removeElement(selectedItem);
                tossableModel.removeElement(selectedItem);
                removeTossableItem(selectedItem, theGame);
            }
            System.out.println(selectedItem);
        });
        JOptionPane.showMessageDialog(null, tossableList, "Tossable Items", JOptionPane.PLAIN_MESSAGE);
    }


    private void removeTossableItem(Item theItem, Rogue myGame) {
        Room itemRoom = theItem.getCurrentRoom();
        ArrayList<Item> roomItems = itemRoom.getRoomItems();

        ArrayList<Room> gameRooms = myGame.getRooms();

        for (Room r: gameRooms) {
            if (r == itemRoom) {
                roomItems.add(theItem);
                r.setRoomItems(roomItems);
                break;
            }
        }

        myGame.setRooms(gameRooms);

    }



    private void displayWearableList(Rogue theGame) {
        HashMap<Integer, Item> currentInventory = null;
        currentInventory = theGame.getInventory();
        wearableList.setModel(wearableModel);
        wearableList.setCellRenderer(new InventoryRenderer());
        if (currentInventory.isEmpty()) {
            return;
        } else {
            for (Item item: currentInventory.values()) {
                if (uniqueWearables.indexOf(item) == -1 && item.getCanWear()) {
                    uniqueWearables.add(item);
                    wearableModel.addElement(item);
                }
            }
        }
        wearableList.getSelectionModel().addListSelectionListener(e -> {
            Item selectedItem = (Item) wearableList.getSelectedValue();
            if (selectedItem != null) {
                String message = "Item Name : " + selectedItem.getName()
                + "\nItem Descriton : " + selectedItem.getDescription();
                System.out.println("LOOL" + message);
                //WEAR
                invModel.removeElement(selectedItem);
                wearableModel.removeElement(selectedItem);
            }
            System.out.println(selectedItem);
        });
        JOptionPane.showMessageDialog(null, wearableList, "Wearable Items", JOptionPane.PLAIN_MESSAGE);
    }


    private void displayEdbileList(Rogue theGame) {

        HashMap<Integer, Item> currentInventory = null;
        currentInventory = theGame.getInventory();
        edibleList.setModel(edibleModel);
        edibleList.setCellRenderer(new InventoryRenderer());
        if (currentInventory.isEmpty()) {
            return;
        } else {
            for (Item item: currentInventory.values()) {
                if (uniqueEdibles.indexOf(item) == -1 && item.getCanEat()) {
                    uniqueEdibles.add(item);
                    edibleModel.addElement(item);
                }
            }
        }

        edibleList.getSelectionModel().addListSelectionListener(e -> {
            Item selectedItem = (Item) edibleList.getSelectedValue();
            if (selectedItem != null) {
                String message = "Item Name : " + selectedItem.getName()
                + "\nItem Descriton : " + selectedItem.getDescription();
                System.out.println("LOOL" + message);
                //EAT
                invModel.removeElement(selectedItem);
                edibleModel.removeElement(selectedItem);
            }
            System.out.println(selectedItem);
        });

        JOptionPane.showMessageDialog(null, edibleList, "Edible Items", JOptionPane.PLAIN_MESSAGE);
    }


    private void setPlayerDisplay(Rogue theGame) {
        labelPanel.add(playerField);
        playerField.setText("Player name is : " + theGame.getPlayer().getName());
    }

    private void listenSetPlayer(Rogue theGame) {
        nameSubmit.addActionListener(e -> {
            String submittedText = playerSubmit.getText();
            if (!submittedText.toString().equals("Enter Text Here")) {
                Player currentPlayer = theGame.getPlayer();
                System.out.println(submittedText);
                currentPlayer.setName(submittedText);
                theGame.setPlayer(currentPlayer);
            }
        });
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

    theGameUI.listenSetPlayer(theGame);
    theGameUI.setPlayerDisplay(theGame);

    while (userInput != 'q') {
    //get input from the user
    theGameUI.listenSetPlayer(theGame);
    theGameUI.setPlayerDisplay(theGame);

    userInput = theGameUI.getInput();

    //ask the game if the user can move there
    try {
        message = theGame.makeMove(userInput);
        theGameUI.draw(message, theGame.getNextDisplay());
        theGameUI.addToInventory(theGame);

        theGameUI.checkInventoryAction(userInput, theGame);

        // get inventory update
    } catch (InvalidMoveException badMove) {
        message = "I didn't understand what you meant, please enter a command";
        theGameUI.setMessage(message);
    }
    }

    }

}
