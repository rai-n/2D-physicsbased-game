package game.GameController;

import city.cs.engine.SoundClip;
import game.Bodies.Frog;
import game.IO.HighScoreReader;
import game.IO.HighScoreWriter;
import game.IO.PlayerStatsReader;
import game.IO.PlayerStatsWriter;
import game.ListenersUpdater.*;
import game.View.MyView;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * The class in which all the different game objects and controllers are linked to work together.
 */
public class Game extends JFrame implements ActionListener {
    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;
    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private MyView view;
    /**
     * A value used to decide whether or not the game is completed.
     */
    private Boolean beaten;
    /**
     * A value used to set the current level of the game.
     */
    private int level;
    /**
     *The character controller class which is assigned to a player.
     */
    private Controller controller;
    /**
     *An instance of the current active world is assigned to save the state.
     */
    private GameLevel currentWorld;
    /**
     * Hold is initialised as false in start; representing the current state of the game as to whether or not physics are currently turned off.
     */
    private Boolean stopped = false;
    /**
     * Holds the current value for total amount of points the player has.
     */
    private int total;
    /**
     * Constructs a card pane with type card layout.
     */
    private static CardLayout card = new CardLayout(0, 0);
    /**
     * Container for the frame is assigned.
     */
    private static Container c;
    /**
     * A linked list containing different worlds is created.
     */
    private LinkedList<GameLevel> worlds;
    /**
     * A hash-map containing player points and name is created for the leader-board.
     */
    private HashMap<Integer, String> map;
    /**
     * An array of values to populate the game to load the presence of the game.
     */
    private ArrayList<Float> tokens;
    /**
     * A JFrame frame is created to hold the container and objects.
     */
    private JFrame frame;
    /**
     * A button is created to put inside the container.
     */
    private JButton jb1, jb2;
    /**
     * A String is created to store the username inputted from JTextField.
     */
    private String userName = "";
    /**
     * A JTextField is created where the player can input their username.
     */
    private JTextField userInput;
    /**
     * A JScrollPanel which is used to contain a scroll box for leader-board.
     */
    private JScrollPane listScroller;
    /**
     * Playing sound
     */
    private SoundClip gameMusic;
    /**
     * Toggle playing of music
     */
    private Boolean toggleMusic = true;

    /**
     * Initialise a new Game.
     * <p>
     *     This constructor creates the initial JFrame with containers for UI interface such as buttons, text-field, combo-box, etc.
     *     In addition to that, such listeners are added to provide feedback if user interacts with an object.
     *     In addition to this, it creates an initial state for game level 1 and populates it by calling another function.
     * </p>
     */
    public Game() {
        map = new HashMap<>();
        beaten = false;
        total = 0;
        createFirstWorld();
        view = new MyView(world, this, 400, 500);
        view.setZoom(10);
        // make the world
        // make a view
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);
        // display the view in a frame
        JFrame frame = new JFrame("Fro-ventures");
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view, BorderLayout.CENTER);
        frame.setBackground(Color.DARK_GRAY);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // create a JPanel to host the different containers.
        // Assigning the layout type of the grid and setting it to the panel and assigning the grid constraints.
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints constraint = new GridBagConstraints();


        // Put constraints on different buttons
        // Creating a combo-box with Array values of levels present in them.
        // Applying the grid constraints and fitting it into the panel.
        String[] Levels = {"Level 1", "Level 2", "Level 3","Pan view","Infinity"};
        JComboBox<String> b1 = new JComboBox<>(Levels);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(b1, constraint);

        // Creating a button to mute sounds
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel
        // Use of try catch exception to handle errors while loading the picture.
        JButton b2 = new JButton("MUTE MEDIA");
        try {
            ImageIcon img = new ImageIcon("data/audio.png");
            b2.setIcon(img);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(b2, constraint);


        // Creating a button to restart levels
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JButton b3 = new JButton("RESTART LEVEL");
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        panel.add(b3, constraint);


        // Creating a button to create an trading shop
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel
        // Use of try catch exception to handle exceptions while loading the picture.
        JButton b4 = new JButton("1 Life : 3 Points");
        try {
            ImageIcon img = new ImageIcon("data/heartUI.gif");
            b4.setIcon(img);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        constraint.gridx = 3;
        constraint.gridy = 0;
        panel.add(b4, constraint);



        // Creating a button to creating an in-game shop
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JButton b5 = new JButton("1 Rock : 1 Point [Level 2+]");
        try {
            ImageIcon img = new ImageIcon("data/rockUI.png");
            b5.setIcon(img);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        constraint.gridx = 3;
        constraint.gridy = 1;
        panel.add(b5, constraint);


        // Creating a button to save the game
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JButton b6 = new JButton("Save game");
        constraint.gridx = 6;
        constraint.gridy = 0;
        panel.add(b6, constraint);




        // Creating a button to load the game
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JButton b7 = new JButton("Load game");
        constraint.gridx = 6;
        constraint.gridy = 1;
        panel.add(b7, constraint);



        // Creating a button to apply a submit function and listener to assign the player name
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JButton jButton = new JButton("USERNAME");
        userInput = new JTextField("", 5);
        jButton.addActionListener( (e) -> {
            submitAction();
        });
        panel.add(userInput);
        panel.add(jButton);



        // Creating a label to state the navigation menu
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JLabel navLabel = new JLabel("Navigation menu", SwingConstants.CENTER);
        navLabel.setBackground(Color.lightGray);
        navLabel.setOpaque(true);
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        panel.add(navLabel, constraint);

        // Creating a label to state the shop menu
        // Put constraints on different buttons
        // Applying the grid constraints and fitting it into the panel.
        JLabel navLabel2 = new JLabel("Shop", SwingConstants.CENTER);
        navLabel2.setBackground(Color.lightGray);
        navLabel2.setOpaque(true);
        constraint.gridx = 3;
        constraint.gridy = 3;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 2;
        panel.add(navLabel2, constraint);


        //Adding the panel to the frame.
        // Adding the card layout type to card.
        frame.add(panel, BorderLayout.NORTH);
        c = getContentPane();
        c.setLayout(card);

        // Define new buttons
        jb1 = new JButton("PAUSE");
        jb2 = new JButton("UNPAUSE");

        // Adding action listeners to the buttons.
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        // Adding the action listener to combo-box interface
        // Given a click on a particular level, the world is generated and player is send there
        // The world is created and a last check for errors is preformed and that the game is synced correctly.
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                if (comboBox.getSelectedItem() == "Level 1") {
                    System.out.println(comboBox.getSelectedItem());
                    populateWorld();
                    createFirstWorld();
                    restartSync();
                } else if (comboBox.getSelectedItem() == "Level 2") {
                    System.out.println(comboBox.getSelectedItem());
                    populateWorld();
                    goNLevel(1);
                    restartSync();
                } else if (comboBox.getSelectedItem() == "Level 3") {
                    System.out.println(comboBox.getSelectedItem());
                    populateWorld();
                    goNLevel(2);
                    restartSync();
                } else if (comboBox.getSelectedItem() == "Pan view") {
                    setBeaten(true);
                    populateWorld();
                    goNLevel(3);
                    restartSync();
                } else if (comboBox.getSelectedItem() == "Infinity"){
                    populateWorld();
                    System.out.println(comboBox.getSelectedItem());
                    goNLevel(5);
                    restartSync();
                }
                setFocus();
            }
        });

        // Enables and disables audio on click event.
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toggleMusic){
                    toggleMusic = false;
                    gameMusic.pause();
                } else {
                    toggleMusic = true;
                    gameMusic.resume();
                }
                System.out.println("Music muted");
                setFocus();
            }
        });


        // Manages button press events for restart button, refreshes LinkedList to generate new data
        // Loads the world and sets the player's tracker there to allow the player to see the world.
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (level == 1) {
                    populateWorld();
                    world = worlds.get(0);
                    constructWorld(world);
                    world.setGravity(7f);
                    world.addStepListener(new Tracker(view, world.getPlayer()));
                    view.setZoom(10);
                } else if (level == 2) {
                    populateWorld();
                    world = worlds.get(1);
                    constructWorld(world);
                    world.setGravity(7f);
                    world.addStepListener(new spTracker(view, world.getPlayer()));
                } else if (level == 3) {
                    populateWorld();
                    world = worlds.get(2);
                    constructWorld(world);

                    world.setGravity(7f);
                } else if (level == 5){
                    populateWorld();
                    world = worlds.get(4);
                    constructWorld(world);
                    world.setGravity(3f);
                    world.getPlayer().setGravityScale(0.01f);

                }
                setFocus();
            }
        });
        // The player's point is decreased by 3 and lives is added as a trade system.
        // Further validation is done to ensure points cannot be negative.

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (world.getPlayer().getCount() >= 3) {
                    world.getPlayer().setLives(world.getPlayer().getLives() + 1);
                    world.getPlayer().setCount(world.getPlayer().getCount() - 3);
                    setFocus();
                }
            }
        });
        // The player's point is decreased by 1 and lives is added as a trade system.
        // Further validation is done to ensure points cannot be negative.

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (world.getPlayer().getCount() >= 1) {
                    world.getPlayer().setRockCount(world.getPlayer().getRockCount() + 1);
                    world.getPlayer().setCount(world.getPlayer().getCount() - 1);
                    setFocus();
                }
            }
        });

        // Write points to file with name if game is over (if level == 4)
        // Saves current positions of entity and player data.
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        PlayerStatsWriter writer = new PlayerStatsWriter(fileToSave.getAbsolutePath());
                        writer.writeHighScore(getLevel(),world.getPlayer().getCount(), world.getPlayer().getLives(), world.getPlayer().getRockCount(), world.getPlayer().getPosition().x, world.getPlayer().getPosition().y);
                    } catch (IOException exceptionSave){
                        exceptionSave.printStackTrace();
                    }
                }
                if (getLevel() == 4){

             }
            }
        });

        // Loads from file
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               makeHighScore();
               repaint();

                JFileChooser fileChooserRead = new JFileChooser();
                fileChooserRead.setDialogTitle("Specify a file to load");

                int userSelection = fileChooserRead.showOpenDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooserRead.getSelectedFile();
                    try {
                        PlayerStatsReader reader = new PlayerStatsReader(fileToSave.getAbsolutePath());
                       tokens = reader.readScores();

                       if (Math.round(tokens.get(0)) == 1){
                           populateWorld();
                           createFirstWorld();
                           restartSync();
                       } else if (Math.round(tokens.get(0)) == 2){
                           populateWorld();
                           goNLevel(1);
                           restartSync();
                       } else if (Math.round(tokens.get(0)) == 3){
                        populateWorld();
                        goNLevel(2);
                        restartSync();
                    }   else if (Math.round(tokens.get(0)) == 4){
                        populateWorld();
                        goNLevel(3);
                        restartSync();
                    }   else if (Math.round(tokens.get(0)) == 5){
                        populateWorld();
                        goNLevel(4);
                        restartSync();
                    }

                        getPlayer().setCount(Math.round(tokens.get(1)));
                        getPlayer().setLives(Math.round(tokens.get(2)));
                        getPlayer().setRockCount(Math.round(tokens.get(3)));
                        getPlayer().setPosition(new Vec2(tokens.get(4), tokens.get(5)));


                } catch (IOException exceptionSave){
                        exceptionSave.printStackTrace();
                    }
                }
            }
        });

        // Saving current state of frame as a field.
        this.frame = frame;
        HighscoreReader();


        // the pause-unpause panel is added onto the west
        // such that two states of jb1 and jb2 can be toggled between from.
        frame.add(c, BorderLayout.WEST);
        panel.setBackground(Color.DARK_GRAY);
        c.add(jb1);
        c.add(jb2);

        // the panel c is added to the frame
        // the background color is also set to fit the current colour scheme.
        frame.add(c, BorderLayout.WEST);
        panel.setBackground(Color.DARK_GRAY);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));
        view.addMouseListener(new DropRock(view, world.getPlayer(), this));
        // a controller is added to the player
        controller = new Controller(world.getPlayer());
        // a key listener is added to the controller return values
        frame.addKeyListener(controller);
        MouseTest mt = new MouseTest();
        // mouse listener is added to test for rock drops
        view.addMouseListener(mt);
        // key listener is added to the player and its controller
        frame.addKeyListener(new Controller(world.getPlayer()));

        //Testing-->
        // uncomment to make the view track the bird
        world.addStepListener(new Tracker(view, world.getPlayer()));

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);

        // assigning the frame and starting the physics engine simulation.
        this.frame = frame;
        world.getPlayer().setRockCount(0);
        world.start();

        // creating the first world.
        populateWorld();

        try {
            //Open an audio input stream
            gameMusic = new SoundClip("data/Audio/level1.wav");
            //Set it to continuous playback
            gameMusic.loop();
        } catch (UnsupportedAudioFileException |IOException| LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Function to add a high-score panel on the side
     */
    public void makeHighScore(){
        frame = this.getFrame();
        frame.remove(listScroller);
        HighscoreReader();
        repaint();
    }

    public void HighscoreReader(){
        // The values read from the high-scores are read which is in a hash-map format
        // Such values for points are populated into an ArrayList using iteration and are arranged in
        // Decreasing order like a normal high-score board.
        try{
            HighScoreReader reader = new HighScoreReader("data/highscore.txt");
            map = reader.readScores();

            List<String> labels = new ArrayList<>(20);
            for (int i = 100; i > 0; i--) {
                if (map.get(i) != null) {
                    labels.add(map.get(i) +" : "+ i);
                }
            }
            // The values in the ArrayList are added onto the list
            // The list scroller object is added to the fame on the east layout side.
            JList<String> list = new JList<String>(labels.toArray(new String[labels.size()]));
            list.setFont(new Font("Cambria", Font.ITALIC, 30));
            JScrollPane listScroller = new JScrollPane();
            listScroller.setViewportView(list);
            this.listScroller = listScroller;
            list.setLayoutOrientation(JList.VERTICAL);
            frame.add(listScroller, BorderLayout.EAST);
        } catch (IOException read){
            read.printStackTrace();
        }

    }

    /**
     * A function which- when called, assigns userName field the value in the text-field.
     */
    private void submitAction() {
        userName = userInput.getText();
    }

    /**
     * A function which is used to populate the world and re-assign step listeners in the case where there is an error in populating the world.
     */
    public void restartSync(){
        // Regenerates the worlds from the worlds LinkedList and constructs the world from a fresh data pool
        // Adds the listeners and fixes any problems regarding the tracking of the player
        // It is called to handle any problems during loading of levels.
        if (level == 1) {
            populateWorld();
            world = worlds.get(0);
            constructWorld(world);
            world.setGravity(7f);
            world.addStepListener(new Tracker(view, world.getPlayer()));
            view.setZoom(10);
            gameMusic.stop();
            try {
                //Open an audio input stream
                gameMusic = new SoundClip("data/Audio/level1.wav");
                //Set it to continuous playback
                gameMusic.loop();
            } catch (UnsupportedAudioFileException |IOException| LineUnavailableException e) {
                System.out.println(e);
            }
        } else if (level == 2) {
            populateWorld();
            world = worlds.get(1);
            constructWorld(world);
            world.setGravity(7f);
            world.addStepListener(new spTracker(view, world.getPlayer()));
            gameMusic.stop();
            try {
                //Open an audio input stream
                gameMusic = new SoundClip("data/Audio/level2.wav");
                //Set it to continuous playback
                gameMusic.loop();
            } catch (UnsupportedAudioFileException |IOException| LineUnavailableException e) {
                System.out.println(e);
            }
        } else if (level == 3) {
            populateWorld();
            world = worlds.get(2);
            constructWorld(world);
            world.setGravity(7f);
            //world.addStepListener(new spTracker(view, world.getPlayer()));
            gameMusic.stop();
            try {
                //Open an audio input stream
                gameMusic = new SoundClip("data/Audio/level3.wav");
                //Set it to continuous playback
                gameMusic.loop();
            } catch (UnsupportedAudioFileException |IOException| LineUnavailableException e) {
                System.out.println(e);
            }
        } else if (level == 5){
            populateWorld();
            world = worlds.get(4);
            constructWorld(world);
            world.setGravity(3f);
            world.getPlayer().setGravityScale(0.01f);

        }
    }

    /**
     * A function which populates the worlds LinkedList by adding instances of different game levels to it.
     */
    public void populateWorld(){
        // A LinkedList is created which is populated with difference instances
        // of the worlds
        // This ensures that when the world is fully re-generated, a fresh pool of data is used.
        worlds = new LinkedList<>();
        GameLevel world1 = new Level1();
        this.addWorld(world1);
        GameLevel world2 = new Level2();
        this.addWorld(world2);
        GameLevel world3 = new Level3();
        this.addWorld(world3);
        GameLevel levelOver = new LevelOver();
        this.addWorld(levelOver);
        GameLevel Infinity = new InfinityRunner();
        this.addWorld(Infinity);
        GameLevel Infinity2 = new InfinityRunner();
        this.addWorld(Infinity2);
        System.out.println(worlds);
    }

    /**
     * An initial world is created which is called directly from the constructor.
     */
    public void createFirstWorld() {
        this.level = 1;
        world = new Level1();
        world.populate(this);

    }

    /**
     * A function that changes the card in the card layout to switch because Pause and Start.
     * @param e The input value from the user button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        card.next(c);
        // Pause-Unpause function which disables and enables physics engine.
        if (!getStopped()) {
            world.stop();
            setStopped(true);
            frame.setBackground(Color.darkGray);
            this.setFocus();
        } else {
            world.start();
            frame.setBackground(Color.lightGray);
            setStopped(false);
            this.setFocus();
        }
    }
    /**
     * A function that is used to get the current state of physics engine. Whether or not it is active.
     * @return Returns true of false depending on whether or not the game is currently paused.
     */
    public Boolean getStopped() {
        return stopped;
    }

    /**
     * A function that is used to set the current state of the game physics engine.
     * @param stopped An input for whether the game is stopped or not.
     */
    public void setStopped(Boolean stopped) {
        this.stopped = stopped;
    }

    /**
     * The player in the current level.
     */
    public Frog getPlayer() {
        return world.getPlayer();
    }

    /**
     * Is the current level of the game finished?
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }


    public void addWorld(GameLevel world) {
        worlds.add(world);

    }

    /**
     * Advance to the next level of the game.
     */
    public void goNextLevel() {
        world.stop();
        // Switching allowing for more specialised world control
        // World is constructed from fetching a particular element from the LinkedList of worlds
        // The currentWorld field is changed to that particular world and variable changes can be made for the specific world.
        switch (level) {
            case 1:
                this.level++;
                world = worlds.get(1);
                this.currentWorld = world;
                constructWorld(world);
                world.setGravity(7f);
                break;
            case 2:
                this.level++;
                world = worlds.get(2);
                this.currentWorld = world;
                constructWorld(world);
                world.setGravity(9.81f);

                break;
            case 3:
                this.level++;
                world = worlds.get(3);
                this.currentWorld = world;
                constructWorld(world);

                break;
            case 4:

                world = worlds.get(4);
                this.currentWorld = world;
                constructWorld(world);
                break;
            case 5:
                world = worlds.get(5);
                this.currentWorld = world;
                constructWorld(world);
                System.out.println("level 5 loaded");
                world.setGravity(3f);
                world.getPlayer().setGravityScale(0.0001f);
                break;
            case 6:
                world = worlds.get(6);
                this.currentWorld = world;
                constructWorld(world);
                break;
            case 7:  System.exit(0);
                break;


        }
    }

    /**
     * Advance to a given parameter level
     * @param level The levels from 1 to n...
     */
    public void goNLevel(int level) {
        // Gets the counts and sets the count to transfer points between levels.
        this.total = this.getPlayer().getCount();
        this.level = level;
        this.currentWorld = null;
        goNextLevel();
    }

    /**
     * Returns the current world the player is playing in.
     * @return Returns the active state of game world.
     */
    public GameLevel getGameWorld() {
        return currentWorld;
    }

    /**
     * Constructs a world, populating it with entities and assigning the controller and setting its count.
     * @param world
     */
    public void constructWorld(GameLevel world) {
        // Creates the world, populates it and sets the count value from previous level.
        world.populate(this);
        controller.setBody(world.getPlayer());
        view.setWorld(world);
        world.start();
        this.getPlayer().setCount(this.total);
    }

    /**
     * Fetches the current level which the player is playing in.
     * @return Returns a value from 1 to n... regarding which level the player is currently on.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Set the current level to a particular level.
     * @param level A level from 1 to n...
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * To ask the frame to be listened to by the game.
     */
    public void setFocus() {
        frame.requestFocus();
    }

    /**
     * Pause the game
     */
    public void pauseGame() {
        world.stop();
    }
    /**
     * Un-Pause the game
     */
    public void unPauseGame() {
        world.start();
    }
    /**
     * Check whether or not the game has been cleared yet.
     */
    public Boolean getBeaten() {
        return beaten;
    }
    /**
     * Change the state of whether or not the game has been cleared.
     */
    public void setBeaten(Boolean beaten) {
        this.beaten = beaten;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        new Game();


    }


}

