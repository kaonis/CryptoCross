/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.BevelBorder;

/**
 * Main GUI application class for the CryptoCross word game.
 * Extends JFrame to provide the graphical user interface and implements
 * ActionListener to handle user interactions.
 * 
 * The game allows players to form words from letters on a board, with
 * colored letters providing point multipliers. Players have access to
 * various help actions like deleting rows, reordering, and swapping letters.
 */
public class CryptoCross extends JFrame implements ActionListener {

    /** Path to the dictionary file to use for the game (shared across instances) */
    private static String str_dictionaryPath = "el-dictionary.txt";

    /** Resource bundle for HTML messages and help text */
    private static final ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");

    private JFrame thisFrame;
    /** 2D array of letters representing the game board */
    private Letter[][] ar_letters;
    /** Player object tracking the current player's state */
    private Player player;
    /** Board object managing the game board state */
    private Board gameBoard;
    /** Helper to validate letter selection for word formation */
    private WordPilot wordPilot;
    /** Service for non-UI word submission decisions */
    private WordSubmissionService wordSubmissionService;
    /** Service for adjacency checks while selecting letters */
    private WordSelectionService wordSelectionService;
    /** Service for board-mutation help-action state updates */
    private HelpActionStateService helpActionStateService;
    /** Maximum number of words the player is allowed to complete */
    private Integer int_maxAllowedWords;
    /** Target/goal number of points to be attained by the player */
    private Integer int_goalPoints;
    /** Points accumulated for the current word being formed */
    private Integer int_currentWordPoints;
    /** List of letters in the current word being formed */
    private ArrayList<Letter> currentWord;

    // Total allowed help actions
    /** Total allowed delete row actions */
    private Integer int_TotalDeleteRow = 3;
    /** Total allowed reorder row actions */
    private Integer int_TotalReorderRow = 3;
    /** Total allowed reorder column actions */
    private Integer int_TotalReorderColumn = 3;
    /** Total allowed reorder board actions */
    private Integer int_TotalReorderBoard = 5;
    /** Total allowed swap letter actions */
    private Integer int_TotalSwapLetter = 6;

    // Number of used help actions
    /** Number of delete row actions used */
    private Integer int_UsedDeleteRow;
    /** Number of reorder row actions used */
    private Integer int_UsedReorderRow;
    /** Number of reorder column actions used */
    private Integer int_UsedReorderColumn;
    /** Number of reorder board actions used */
    private Integer int_UsedReorderBoard;
    /** Number of swap letter actions used */
    private Integer int_UsedSwapLetter;
    
    // Swap letters mode tracking
    /** Flag indicating if swap letters mode is active */
    private Boolean tf_swapMode = false;
    /** First letter selected for swapping */
    private Letter swapLetter1 = null;
    /** Second letter selected for swapping */
    private Letter swapLetter2 = null;

    private Container contentPane;
    //JPanels
    private JPanel containerPanel; //Parent of all panels except for playerNamePanel, child of contentPane
    private JPanel leftPanel; //Child of containerPanel
    private JPanel rightPanel; //Child of containerPanel
    private JPanel boardPanel; //Child of leftPanel
    private JPanel helpPanel; //Child of rightPanel
    private JPanel playerNamePanel; //Child of contentPane
    //Rows of rightPanel
    private JPanel row1Panel;
    private JPanel row2Panel;
    private JPanel row3Panel;
    private JPanel row4Panel;
    private JPanel row5Panel;
    private JPanel row6Panel;
    private JPanel row7Panel;
    private JPanel row8Panel;
    private JPanel row9Panel;
    private JPanel row10Panel;
    //Menus
    private JMenuBar menuBar; //Menu Bar that will hold the menus
    private JMenu mainMenu; //Main menu containing all the main game functions
    private JMenu toolsMenu; //Secondary menu for misc functions
    //mainMenu Items
    private JMenuItem newGameMenuItem; //Start a new game
    private JMenuItem stopGameMenuItem; //Stop current game
    private JMenuItem playerInfoMenuItem; //Insert player info
    private JMenuItem helpSettingsMenuItem; //Open help settings
    private JMenuItem pickWordFileMenuItem; //Pick the wordlist file
    private JMenuItem exitMenuItem; //Exit application
    //toolsMenu Items
    private JMenuItem helpMenuItem; //Open game help
    private JMenuItem aboutMenuItem; //Open about infoa
    //letter JButtons
    private JButton[][] btnArray_letter;
    private JButton btn_deleteRow;
    private JButton btn_reorderRow;
    private JButton btn_reorderColumn;
    private JButton btn_reorderBoard;
    private JButton btn_swapLetters;
    private JButton btn_checkWord;
    //JTextFields
    //Help JTF
    private JTextField tf_deleteRow;
    private JTextField tf_reorderRow;
    private JTextField tf_reorderColumn;
    //Player Info JTF
    private JTextField tf_playerName;
    //JLabels
    private JLabel lb_playerName;
    private JLabel lb_deleteRow;
    private JLabel lb_reorderRow;
    private JLabel lb_reorderColumn;
    private JLabel lb_reorderBoard;
    private JLabel lb_swapLetters;
    private JLabel lb1_goalPoints;
    private JLabel lb2_goalPoints;
    private JLabel lb1_totalPoints;
    private JLabel lb2_totalPoints;
    private JLabel lb1_wordPoints;
    private JLabel lb2_wordPoints;
    private JLabel lb1_wordsFound;
    private JLabel lb2_wordsFound;
    private JLabel lb_foundAword;

    //Constructor
    public CryptoCross() {

        wordPilot = new WordPilot(ar_letters);

        thisFrame = this;

        currentWord = new ArrayList<>();
        wordSubmissionService = new WordSubmissionService();
        wordSelectionService = new WordSelectionService();
        helpActionStateService = new HelpActionStateService();

        //Initialize Player
        initializePlayer();

        Integer int_boardSize = inputBoardSize();
        if (int_boardSize == 0) {
            System.exit(0);
        }

        try {
            gameBoard = new Board(int_boardSize, str_dictionaryPath);
        } catch (UknownCharacterException ex) {
            Logger.getLogger(CryptoCross.class.getName()).log(Level.SEVERE, null, ex);
        }

        initializeGameValues(); //goal score & helps

        initializeGUI();

        checkHelps();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Set game LookAndFeel to Nimbus
                try {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Nimbus unavailable
                }
                new CryptoCross();
            }
        });
    }

    private void initializeGameValues() {
        int_currentWordPoints = 0;

        if (gameBoard.getBoardLength() == 5) {
            int_maxAllowedWords = 4;
            int_goalPoints = 200;
            int_TotalDeleteRow = 3;
            int_TotalReorderRow = 3;
            int_TotalReorderColumn = 3;
            int_TotalReorderBoard = 5;
            int_TotalSwapLetter = 6;
        } else if (gameBoard.getBoardLength() == 8) {
            int_maxAllowedWords = 6;
            int_goalPoints = 300;
            int_TotalDeleteRow = 4;
            int_TotalReorderRow = 4;
            int_TotalReorderColumn = 4;
            int_TotalReorderBoard = 7;
            int_TotalSwapLetter = 8;
        } else {
            int_maxAllowedWords = 8;
            int_goalPoints = 400;
            int_TotalDeleteRow = 5;
            int_TotalReorderRow = 5;
            int_TotalReorderColumn = 5;
            int_TotalReorderBoard = 8;
            int_TotalSwapLetter = 10;
        }

        int_UsedDeleteRow = 0;
        int_UsedReorderRow = 0;
        int_UsedReorderColumn = 0;
        int_UsedReorderBoard = 0;
        int_UsedSwapLetter = 0;
    }

    //Checks if any more helps are available and grays out accordingly
    private void checkHelps() {
        lb_deleteRow.setText(int_UsedDeleteRow + "/" + int_TotalDeleteRow);
        lb_reorderRow.setText(int_UsedReorderRow + "/" + int_TotalReorderRow);
        lb_reorderColumn.setText(int_UsedReorderColumn + "/" + int_TotalReorderColumn);
        lb_reorderBoard.setText(int_UsedReorderBoard + "/" + int_TotalReorderBoard);
        lb_swapLetters.setText(int_UsedSwapLetter + "/" + int_TotalSwapLetter);
        
        if (int_UsedDeleteRow >= int_TotalDeleteRow) {
            btn_deleteRow.setEnabled(false);
        }
        if (int_UsedReorderRow >= int_TotalReorderRow) {
            btn_reorderRow.setEnabled(false);
        }
        if (int_UsedReorderColumn >= int_TotalReorderColumn) {
            btn_reorderColumn.setEnabled(false);
        }
        if (int_UsedReorderBoard >= int_TotalReorderBoard) {
            btn_reorderBoard.setEnabled(false);
        }
        if (int_UsedSwapLetter >= int_TotalSwapLetter) {
            btn_swapLetters.setEnabled(false);
        }
    }

    private void initializePlayer() {
        player = new Player();
        player.setPlayerName(inputPlayerInfo());

        if (player.getPlayerName() == null) {
            System.exit(0);
        }

        while (player.getPlayerName().trim().length() == 0) {
            JOptionPane.showMessageDialog(thisFrame,
                    "Το όνομα του παίκτη δεν μπορεί να είναι κενό",
                    "Σφάλμα εισαγωγής!",
                    JOptionPane.ERROR_MESSAGE);

            player.setPlayerName(inputPlayerInfo());
            if (player.getPlayerName() == null) {
                System.exit(0);
            }
        }

    }

    private String inputPlayerInfo() {
        String str_playerName = (String) JOptionPane.showInputDialog(
                thisFrame,
                "Παρακαλώ εισάγετε το όνομα του παίκτη:",
                "Καλωσήρθατε στο CryptoCross",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
        if (str_playerName == null) {
            return null;
        } else {
            return str_playerName;
        }
    }

    private Integer inputBoardSize() {
        Object[] options = {"Ταμπλό 5x5",
            "Ταμπλό 8x8",
            "Ταμπλό 10x10"};
        String str_boardSize = (String) JOptionPane.showInputDialog(
                thisFrame,
                "Παρακαλώ επιλέξτε μέγεθος ταμπλό παιχνιδιού",
                "Επιλογή μεγέθους ταμπλό",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (str_boardSize == null) {
            return 0;
        }

        if (str_boardSize.equals("Ταμπλό 5x5")) {
            return 5;
        } else if (str_boardSize.equals("Ταμπλό 8x8")) {
            return 8;
        } else if (str_boardSize.equals("Ταμπλό 10x10")) {
            return 10;
        }

        return 0;
    }

    //Initialization of GUI Components
    private void initializeGUI() {

        initializeMenuBar();

        contentPane = thisFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        containerPanel = new JPanel(new GridLayout(1, 2, 4, 4)); //1 row, 2 columns
        containerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        leftPanel = new JPanel(new BorderLayout());

        setupGameBoard(); //Prints the game board

        leftPanel.add(boardPanel, BorderLayout.NORTH);

        btn_checkWord = new JButton("Έλεγχος λέξης");
        btn_checkWord.addActionListener(this);

        leftPanel.add(btn_checkWord, BorderLayout.AFTER_LAST_LINE);

        rightPanel = new JPanel(new GridLayout(11, 1, 2, 2)); //11 rows, 1 column

        helpPanel = new JPanel(new BorderLayout());
        helpPanel.add(new JLabel("Βοήθειες"), BorderLayout.EAST);

        rightPanel.add(helpPanel);

        //Help Buttons
        btn_deleteRow = new JButton("Διαγραφή Γραμμής");
        btn_deleteRow.addActionListener(this);
        btn_reorderRow = new JButton("Αναδιάταξη Γραμμής");
        btn_reorderRow.addActionListener(this);
        btn_reorderColumn = new JButton("Αναδιάταξη Στήλης");
        btn_reorderColumn.addActionListener(this);
        btn_reorderBoard = new JButton("Αναδιάταξη Ταμπλό");
        btn_reorderBoard.addActionListener(this);
        btn_swapLetters = new JButton("Εναλλαγή Γραμμάτων");
        btn_swapLetters.addActionListener(this);

        //Help Text Fields
        tf_deleteRow = new JTextField("0");
        tf_reorderRow = new JTextField("0");
        tf_reorderColumn = new JTextField("0");

        //Help Labels
        lb_deleteRow = new JLabel(int_UsedDeleteRow + "/" + int_TotalDeleteRow);
        lb_reorderRow = new JLabel(int_UsedReorderRow + "/" + int_TotalReorderRow);
        lb_reorderColumn = new JLabel(int_UsedReorderColumn + "/" + int_TotalReorderColumn);
        lb_reorderBoard = new JLabel(int_UsedReorderBoard + "/" + int_TotalReorderBoard);
        lb_swapLetters = new JLabel(int_UsedSwapLetter + "/" + int_TotalSwapLetter);
        lb1_goalPoints = new JLabel("Στόχος:");
        lb2_goalPoints = new JLabel(Integer.toString(int_goalPoints));
        lb1_totalPoints = new JLabel("Συνολική Βαθμολογία:");
        lb2_totalPoints = new JLabel(Integer.toString(player.getPlayerScore()));
        lb1_wordPoints = new JLabel("Βαθμολογία Λέξης:");
        lb2_wordPoints = new JLabel(Integer.toString(int_currentWordPoints));
        lb1_wordsFound = new JLabel("Λέξεις που βρέθηκαν:");
        lb2_wordsFound = new JLabel(player.getCompletedWordsNum() + "/" + int_maxAllowedWords);
        lb_foundAword = new JLabel("");

        row1Panel = new JPanel(new BorderLayout());
        row2Panel = new JPanel(new BorderLayout());
        row3Panel = new JPanel(new BorderLayout());
        row4Panel = new JPanel(new BorderLayout());
        row5Panel = new JPanel(new BorderLayout());
        row6Panel = new JPanel(new BorderLayout());
        row7Panel = new JPanel(new BorderLayout());
        row8Panel = new JPanel(new BorderLayout());
        row9Panel = new JPanel(new BorderLayout());
        row10Panel = new JPanel(new BorderLayout());

        //Right Row1
        row1Panel.add(btn_deleteRow, BorderLayout.LINE_START);
        row1Panel.add(lb_deleteRow, BorderLayout.LINE_END);

        rightPanel.add(row1Panel);

        //Right Row2
        row2Panel.add(btn_reorderRow, BorderLayout.LINE_START);
        row2Panel.add(lb_reorderRow, BorderLayout.LINE_END);

        rightPanel.add(row2Panel);

        //Right Row3
        row3Panel.add(btn_reorderColumn, BorderLayout.LINE_START);
        row3Panel.add(lb_reorderColumn, BorderLayout.LINE_END);

        rightPanel.add(row3Panel);

        //Right Row4
        row4Panel.add(btn_reorderBoard, BorderLayout.LINE_START);
        row4Panel.add(lb_reorderBoard, BorderLayout.LINE_END);

        rightPanel.add(row4Panel);

        //Right Row5
        row5Panel.add(btn_swapLetters, BorderLayout.LINE_START);
        row5Panel.add(lb_swapLetters, BorderLayout.LINE_END);

        rightPanel.add(row5Panel);

        //Right Row6
        row6Panel.add(lb1_goalPoints, BorderLayout.LINE_START);
        row6Panel.add(lb2_goalPoints, BorderLayout.LINE_END);

        rightPanel.add(row6Panel);

        //Right Row7
        row7Panel.add(lb1_totalPoints, BorderLayout.LINE_START);
        row7Panel.add(lb2_totalPoints, BorderLayout.LINE_END);

        rightPanel.add(row7Panel);

        //Right Row8
        row8Panel.add(lb1_wordPoints, BorderLayout.LINE_START);
        row8Panel.add(lb2_wordPoints, BorderLayout.LINE_END);

        rightPanel.add(row8Panel);

        //Right Row9
        row9Panel.add(lb1_wordsFound, BorderLayout.LINE_START);
        row9Panel.add(lb2_wordsFound, BorderLayout.LINE_END);

        rightPanel.add(row9Panel);

        //Right Row10
        row10Panel.add(lb_foundAword, BorderLayout.LINE_START);

        rightPanel.add(row10Panel);

        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        contentPane.add(containerPanel);

        lb_playerName = new JLabel("Παίκτης: " + player.getPlayerName());
        lb_playerName.setFont(new Font("Arial Black", Font.BOLD, 13));

        playerNamePanel = new JPanel();
        playerNamePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        contentPane.add(playerNamePanel, BorderLayout.SOUTH);
        playerNamePanel.setPreferredSize(new Dimension(thisFrame.getWidth(), 21));
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));
        lb_playerName.setHorizontalAlignment(SwingConstants.LEFT);
        playerNamePanel.add(lb_playerName);

        contentPane.add(containerPanel, BorderLayout.NORTH);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CryptoCross");  // 
//        if (gameBoard.getBoardLength() == 5) {
//            setSize(650, 350);
//        }
        setLocationRelativeTo(null); //
        setVisible(true);    // 
        setResizable(false);
    }

    private void setupGameBoard() {
        boardPanel = new JPanel(new GridLayout(gameBoard.getBoardLength(), gameBoard.getBoardLength(), 2, 2));
        ar_letters = gameBoard.getBoardArray();

        btnArray_letter = new JButton[gameBoard.getBoardLength()][gameBoard.getBoardLength()];

        for (int i = 0; i < gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < gameBoard.getBoardLength(); j++) {
                btnArray_letter[i][j] = new JButton();

                String str_letterBtnTxt = ar_letters[i][j].getLetterChar()
                        + " " + ar_letters[i][j].getPoints();

                //All letters icons are now available
                try {
                    if (ar_letters[i][j].getLetterChar() == 'Α') {

                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Α.png"))));

                    } else if (ar_letters[i][j].getLetterChar() == 'Β') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Β.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Γ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Γ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Δ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Δ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ε') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ε.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ζ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ζ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Η') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Η.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Θ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Θ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ι') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ι.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Κ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Κ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Λ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Λ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Μ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Μ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ν') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ν.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ξ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ξ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ο') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ο.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Π') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Π.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ρ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ρ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Σ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Σ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Τ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Τ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Υ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Υ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Φ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Φ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Χ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Χ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ψ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ψ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ω') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ω.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == '?') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Balandeur.png"))));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CryptoCross.class.getName()).log(Level.SEVERE, null, ex);
                }

                Color tempColor = ar_letters[i][j].getColor();
                btnArray_letter[i][j].setBackground(tempColor);
                if (btnArray_letter[i][j].getIcon() == null) {
                    btnArray_letter[i][j].setText(str_letterBtnTxt);
                }

                btnArray_letter[i][j].setBorder(BorderFactory.createEmptyBorder());
                btnArray_letter[i][j].setPreferredSize(new Dimension(48, 48));

                Letter tempLetter = ar_letters[i][j];

                if (currentWord.size() > 0) {
                    Integer tempXpos = currentWord.get(currentWord.size() - 1).getXcoord();
                    Integer tempYpos = currentWord.get(currentWord.size() - 1).getYcoord();
                }

                btnArray_letter[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tf_swapMode) {
                            // Swap letters mode
                            if (swapLetter1 == null) {
                                // First letter selected
                                swapLetter1 = tempLetter;
                                ((JButton) e.getSource()).setBackground(Color.CYAN);
                                lb_foundAword.setText(messages.getString("status.swap.select.second"));
                            } else if (swapLetter2 == null && tempLetter != swapLetter1) {
                                // Second letter selected
                                swapLetter2 = tempLetter;
                                
                                // Perform the swap
                                gameBoard.swapLetters(swapLetter1, swapLetter2);
                                
                                // Update counters
                                int_UsedSwapLetter++;
                                checkHelps();

                                applyHelpMutationStateReset();
                                // Refresh the board
                                refreshBoard();
                                lb_foundAword.setText(messages.getString("status.swap.completed"));
                            } else if (tempLetter == swapLetter1) {
                                // Cancel swap mode if clicking the same letter
                                tf_swapMode = false;
                                swapLetter1 = null;
                                swapLetter2 = null;
                                refreshBoard();
                                lb_foundAword.setText(messages.getString("status.swap.cancelled"));
                            }
                        } else if (((JButton) e.getSource()).getBackground().equals(Color.YELLOW)) {
                            // Deselect letter
                            if (!wordSelectionService.canDeselect(currentWord, tempLetter)) {
                                lb_foundAword.setText(messages.getString("status.select.strict.deselect.last"));
                                return;
                            }
                            ((JButton) e.getSource()).setBackground(tempColor);
                            currentWord.remove(tempLetter);
                            
                            // Update word points
                            int_currentWordPoints = calculateWordPoints(currentWord);
                            lb2_wordPoints.setText(Integer.toString(int_currentWordPoints));
                        } else {
                            // Select letter
                            if (!wordSelectionService.canSelectNext(currentWord, tempLetter)) {
                                lb_foundAword.setText(messages.getString("status.select.neighbor"));
                                return;
                            }
                            ((JButton) e.getSource()).setBackground(Color.YELLOW);
                            currentWord.add(tempLetter);
                            
                            // Update word points
                            int_currentWordPoints = calculateWordPoints(currentWord);
                            lb2_wordPoints.setText(Integer.toString(int_currentWordPoints));
                        }
                    }
                });
                boardPanel.add(btnArray_letter[i][j]);
            }
        }

    }

    private void initializeMenuBar() {
        menuBar = new javax.swing.JMenuBar();

        //MainMenu initialization
        mainMenu = new javax.swing.JMenu();
        newGameMenuItem = new javax.swing.JMenuItem();
        stopGameMenuItem = new javax.swing.JMenuItem();
        playerInfoMenuItem = new javax.swing.JMenuItem();
        helpSettingsMenuItem = new javax.swing.JMenuItem();
        pickWordFileMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        mainMenu.setText("Μενού");

        newGameMenuItem.setText("Νέο Παιχνίδι");
        newGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(newGameMenuItem);

        stopGameMenuItem.setText("Ακύρωση/Τερματισμός Παιχνιδιού");
        stopGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopGameMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(stopGameMenuItem);

        playerInfoMenuItem.setText("Εισαγωγή στοιχείων παίχτη");
        playerInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerInfoMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(playerInfoMenuItem);

        helpSettingsMenuItem.setText("Ρυθμίσεις βοηθειών");
        helpSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpSettingsMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(helpSettingsMenuItem);

        pickWordFileMenuItem.setText("Αναζήτηση αρχείου λέξεων");
        pickWordFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickWordFileMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(pickWordFileMenuItem);

        exitMenuItem.setText("Έξοδος");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(exitMenuItem);

        //ToolsMenu initialization
        toolsMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        toolsMenu.setText("Εργαλεία");

        helpMenuItem.setText("Βοήθεια");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(helpMenuItem);

        aboutMenuItem.setText("About...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(aboutMenuItem);

        menuBar.add(mainMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    private void newGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        new CryptoCross();
    }

    private void stopGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(thisFrame,
                "Είστε σίγουροι ότι θέλετε να τερματίσετε το τρέχον παιχνίδι;\n" +
                "Όλη η πρόοδος θα χαθεί.",
                "Τερματισμός Παιχνιδιού",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
            disableGameControls();
            lb_foundAword.setText(messages.getString("status.game.cancelled"));
            JOptionPane.showMessageDialog(thisFrame,
                    "Το παιχνίδι τερματίστηκε.\nΒαθμολογία: " + player.getPlayerScore(),
                    "Παιχνίδι Τερματίστηκε",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void playerInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        player.setPlayerName(inputPlayerInfo());
        lb_playerName.setText("Παίκτης: " + player.getPlayerName());

    }

    private void helpSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        String settingsText = MessageFormat.format(
                messages.getString("help.settings.html"),
                int_UsedDeleteRow,
                (int_TotalDeleteRow - int_UsedDeleteRow),
                int_TotalDeleteRow,
                int_UsedReorderRow,
                (int_TotalReorderRow - int_UsedReorderRow),
                int_TotalReorderRow,
                int_UsedReorderColumn,
                (int_TotalReorderColumn - int_UsedReorderColumn),
                int_TotalReorderColumn,
                int_UsedReorderBoard,
                (int_TotalReorderBoard - int_UsedReorderBoard),
                int_TotalReorderBoard,
                int_UsedSwapLetter,
                (int_TotalSwapLetter - int_UsedSwapLetter),
                int_TotalSwapLetter
        );
        
        JOptionPane.showMessageDialog(thisFrame,
                settingsText,
                "Ρυθμίσεις Βοηθειών",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void pickWordFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        //Create a file chooser
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Επιλέξτε Αρχείο Λέξεων");
        
        // Add description about supported format
        fileChooser.setAccessory(new JLabel(messages.getString("file.chooser.format.html")));
        
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }
            
            @Override
            public String getDescription() {
                return "Αρχεία Κειμένου (*.txt)";
            }
        });

        int returnVal = fileChooser.showOpenDialog(thisFrame);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            
            // Validate the dictionary file
            try {
                // Try to create a test Dictionary to validate the file
                Dictionary testDict = new Dictionary(selectedFile.getAbsolutePath(), 5);
                
                // If successful, update the dictionary path
                str_dictionaryPath = selectedFile.getAbsolutePath();
                
                JOptionPane.showMessageDialog(thisFrame,
                        "Επιλέχθηκε το αρχείο: " + selectedFile.getName() + "\n\n" +
                        "Η αλλαγή του αρχείου λέξεων θα ισχύσει στο επόμενο παιχνίδι.\n" +
                        "Το τρέχον παιχνίδι χρησιμοποιεί ακόμα το προηγούμενο αρχείο.",
                        "Αρχείο Λέξεων",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(thisFrame,
                        "Σφάλμα κατά τη φόρτωση του αρχείου λέξεων:\n" +
                        selectedFile.getName() + "\n\n" +
                        "Παρακαλώ βεβαιωθείτε ότι το αρχείο:\n" +
                        "• Είναι αρχείο κειμένου (.txt)\n" +
                        "• Περιέχει μία λέξη ανά γραμμή\n" +
                        "• Χρησιμοποιεί Ελληνικά κεφαλαία γράμματα\n" +
                        "• Δεν έχει τόνους (χρησιμοποιήστε Α, Ε, Η, Ι, Ο, Υ, Ω)",
                        "Σφάλμα Αρχείου",
                        JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(CryptoCross.class.getName()).log(Level.WARNING, 
                        "Failed to load dictionary file: " + selectedFile.getAbsolutePath(), ex);
            }
        }
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        String helpText = MessageFormat.format(
                messages.getString("help.main.html"),
                int_TotalDeleteRow,
                int_TotalReorderRow,
                int_TotalReorderColumn,
                int_TotalReorderBoard,
                int_TotalSwapLetter
        );
        
        JOptionPane.showMessageDialog(thisFrame,
                helpText,
                "Βοήθεια - CryptoCross",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(thisFrame,
                "Συντελεστές:\nΧάρης Ιωαννίκιος Καώνης icsd10069\nΓεώργιος Μουστάκας icsd11102", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    //Additional listeners
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_checkWord) {
            if (currentWord.isEmpty()) {
                JOptionPane.showMessageDialog(thisFrame,
                        "Δεν έχετε επιλέξει καμία λέξη!",
                        "Σφάλμα",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            WordSubmissionService.SubmissionResult submissionResult =
                    wordSubmissionService.evaluate(currentWord, gameBoard, player);

            if (submissionResult.getStatus() == WordSubmissionService.SubmissionStatus.ACCEPTED) {
                int wordPoints = submissionResult.getPoints();

                // Update player score
                player.setPlayerScore(player.getPlayerScore() + wordPoints);
                player.playerCompletedAWord();
                
                // Update UI
                lb2_totalPoints.setText(Integer.toString(player.getPlayerScore()));
                lb2_wordsFound.setText(player.getCompletedWordsNum() + "/" + int_maxAllowedWords);
                lb_foundAword.setText(MessageFormat.format(
                        messages.getString("status.word.accepted"), wordPoints));
                
                clearCurrentWord();
                
                // Check win condition
                if (player.getPlayerScore() >= int_goalPoints) {
                    JOptionPane.showMessageDialog(thisFrame,
                            "Συγχαρητήρια! Κερδίσατε το παιχνίδι!\n" +
                            "Βαθμολογία: " + player.getPlayerScore() + "\n" +
                            "Λέξεις που βρήκατε: " + player.getCompletedWordsNum(),
                            "Νίκη!",
                            JOptionPane.INFORMATION_MESSAGE);
                    disableGameControls();
                    return;
                }
                
                // Check lose condition
                if (player.getCompletedWordsNum() >= int_maxAllowedWords) {
                    JOptionPane.showMessageDialog(thisFrame,
                            "Το παιχνίδι τελείωσε!\n" +
                            "Δεν κατάφερες να φτάσεις τον στόχο.\n" +
                            "Βαθμολογία: " + player.getPlayerScore() + "/" + int_goalPoints,
                            "Τέλος παιχνιδιού",
                            JOptionPane.INFORMATION_MESSAGE);
                    disableGameControls();
                }
            } else if (submissionResult.getStatus() == WordSubmissionService.SubmissionStatus.DUPLICATE) {
                lb_foundAword.setText(messages.getString("status.word.duplicate"));
                clearCurrentWord();
            } else {
                // Invalid word
                lb_foundAword.setText(messages.getString("status.word.invalid"));
                clearCurrentWord();
            }

        } else if (e.getSource() == btn_deleteRow) {
            if (int_TotalDeleteRow - int_UsedDeleteRow > 0) {
                String input = JOptionPane.showInputDialog(thisFrame,
                        "Εισάγετε αριθμό γραμμής (0-" + (gameBoard.getBoardLength() - 1) + "):",
                        "Διαγραφή Γραμμής",
                        JOptionPane.QUESTION_MESSAGE);
                
                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int row = Integer.parseInt(input.trim());
                        if (row >= 0 && row < gameBoard.getBoardLength()) {
                            gameBoard.deleteRow(row);
                            int_UsedDeleteRow++;
                            checkHelps();
                            applyHelpMutationStateReset();
                            refreshBoard();
                        } else {
                            JOptionPane.showMessageDialog(thisFrame,
                                    "Μη έγκυρος αριθμός γραμμής!",
                                    "Σφάλμα",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisFrame,
                                "Παρακαλώ εισάγετε έναν έγκυρο αριθμό!",
                                "Σφάλμα",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else if (e.getSource() == btn_reorderRow) {
            if (int_TotalReorderRow - int_UsedReorderRow > 0) {
                String input = JOptionPane.showInputDialog(thisFrame,
                        "Εισάγετε αριθμό γραμμής (0-" + (gameBoard.getBoardLength() - 1) + "):",
                        "Αναδιάταξη Γραμμής",
                        JOptionPane.QUESTION_MESSAGE);
                
                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int row = Integer.parseInt(input.trim());
                        if (row >= 0 && row < gameBoard.getBoardLength()) {
                            gameBoard.reorderRow(row);
                            int_UsedReorderRow++;
                            checkHelps();
                            applyHelpMutationStateReset();
                            refreshBoard();
                        } else {
                            JOptionPane.showMessageDialog(thisFrame,
                                    "Μη έγκυρος αριθμός γραμμής!",
                                    "Σφάλμα",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisFrame,
                                "Παρακαλώ εισάγετε έναν έγκυρο αριθμό!",
                                "Σφάλμα",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else if (e.getSource() == btn_reorderColumn) {
            if (int_TotalReorderColumn - int_UsedReorderColumn > 0) {
                String input = JOptionPane.showInputDialog(thisFrame,
                        "Εισάγετε αριθμό στήλης (0-" + (gameBoard.getBoardLength() - 1) + "):",
                        "Αναδιάταξη Στήλης",
                        JOptionPane.QUESTION_MESSAGE);
                
                if (input != null && !input.trim().isEmpty()) {
                    try {
                        int column = Integer.parseInt(input.trim());
                        if (column >= 0 && column < gameBoard.getBoardLength()) {
                            gameBoard.reorderColumn(column);
                            int_UsedReorderColumn++;
                            checkHelps();
                            applyHelpMutationStateReset();
                            refreshBoard();
                        } else {
                            JOptionPane.showMessageDialog(thisFrame,
                                    "Μη έγκυρος αριθμός στήλης!",
                                    "Σφάλμα",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisFrame,
                                "Παρακαλώ εισάγετε έναν έγκυρο αριθμό!",
                                "Σφάλμα",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else if (e.getSource() == btn_reorderBoard) {
            if (int_TotalReorderBoard - int_UsedReorderBoard > 0) {
                gameBoard.reorderBoard();
                int_UsedReorderBoard++;
                checkHelps();
                applyHelpMutationStateReset();
                refreshBoard();
            }

        } else if (e.getSource() == btn_swapLetters) {
            if (int_TotalSwapLetter - int_UsedSwapLetter > 0) {
                // Enable swap mode - user needs to select two letters
                tf_swapMode = true;
                swapLetter1 = null;
                swapLetter2 = null;
                lb_foundAword.setText(messages.getString("status.swap.select.two"));
            }
        }
    }

    private int calculateWordPoints(ArrayList<Letter> word) {
        int points = 0;
        for (Letter letter : word) {
            points += letter.getPoints();
        }
        return points;
    }

    void setStrictSelectionMode(boolean strictSelectionMode) {
        wordSelectionService.setStrictSelectionMode(strictSelectionMode);
    }

    private void applyHelpMutationStateReset() {
        HelpActionStateService.HelpActionState state =
                helpActionStateService.onBoardMutation(player.getPlayerScore(), tf_swapMode, currentWord);
        tf_swapMode = state.isSwapMode();
        swapLetter1 = null;
        swapLetter2 = null;
        int_currentWordPoints = state.getCurrentWordPoints();
        lb2_wordPoints.setText(Integer.toString(int_currentWordPoints));
    }
    
    private void clearCurrentWord() {
        // Clear the current word and reset button colors
        for (Letter letter : currentWord) {
            int x = letter.getXcoord();
            int y = letter.getYcoord();
            btnArray_letter[x][y].setBackground(letter.getColor());
        }
        currentWord.clear();
        int_currentWordPoints = 0;
        lb2_wordPoints.setText("0");
    }
    
    private void disableGameControls() {
        btn_checkWord.setEnabled(false);
        btn_deleteRow.setEnabled(false);
        btn_reorderRow.setEnabled(false);
        btn_reorderColumn.setEnabled(false);
        btn_reorderBoard.setEnabled(false);
        btn_swapLetters.setEnabled(false);
        
        for (int i = 0; i < gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < gameBoard.getBoardLength(); j++) {
                btnArray_letter[i][j].setEnabled(false);
            }
        }
    }
    
    private void refreshBoard() {
        ar_letters = gameBoard.getBoardArray();
        for (int i = 0; i < gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < gameBoard.getBoardLength(); j++) {
                Color tempColor = ar_letters[i][j].getColor();
                btnArray_letter[i][j].setBackground(tempColor);
                
                // Update button text/icon for the letter
                Character letterChar = ar_letters[i][j].getLetterChar();
                String str_letterBtnTxt = Character.toString(letterChar);
                
                // Clear existing icon
                btnArray_letter[i][j].setIcon(null);
                
                // Set icon for all Greek letters
                try {
                    if (letterChar == 'Α') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Α.png"))));
                    } else if (letterChar == 'Β') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Β.png"))));
                    } else if (letterChar == 'Γ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Γ.png"))));
                    } else if (letterChar == 'Δ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Δ.png"))));
                    } else if (letterChar == 'Ε') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ε.png"))));
                    } else if (letterChar == 'Ζ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ζ.png"))));
                    } else if (letterChar == 'Η') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Η.png"))));
                    } else if (letterChar == 'Θ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Θ.png"))));
                    } else if (letterChar == 'Ι') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ι.png"))));
                    } else if (letterChar == 'Κ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Κ.png"))));
                    } else if (letterChar == 'Λ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Λ.png"))));
                    } else if (letterChar == 'Μ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Μ.png"))));
                    } else if (letterChar == 'Ν') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ν.png"))));
                    } else if (letterChar == 'Ξ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ξ.png"))));
                    } else if (letterChar == 'Ο') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ο.png"))));
                    } else if (letterChar == 'Π') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Π.png"))));
                    } else if (letterChar == 'Ρ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ρ.png"))));
                    } else if (letterChar == 'Σ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Σ.png"))));
                    } else if (letterChar == 'Τ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Τ.png"))));
                    } else if (letterChar == 'Υ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Υ.png"))));
                    } else if (letterChar == 'Φ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Φ.png"))));
                    } else if (letterChar == 'Χ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Χ.png"))));
                    } else if (letterChar == 'Ψ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ψ.png"))));
                    } else if (letterChar == 'Ω') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ω.png"))));
                    } else if (letterChar == '?') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Balandeur.png"))));
                    }
                } catch (IOException ex) {
                    // If icon fails to load, will use text instead
                }
                
                // Use text if no icon was set
                if (btnArray_letter[i][j].getIcon() == null) {
                    btnArray_letter[i][j].setText(str_letterBtnTxt);
                } else {
                    btnArray_letter[i][j].setText("");
                }
            }
        }
    }

}
