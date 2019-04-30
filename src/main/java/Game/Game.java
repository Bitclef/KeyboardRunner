package Game;

import Menu.CreateContent;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Game {

    private int score = 0;
    private Text scoreText;
    private int previousHighScore;
    private double obstaclesPassed = 4;  //Must be 4 to start out so the obstacle moves.

    private Text label;

    private Stage mainStage;

    private Pane root = new Pane();

    private AnimationTimer timer;

    private Stage gameStage;
    private Scene gameScene;

    private boolean isUpKeyPressed = false;
    private boolean isUpKeyHeld = false;
    private boolean nextWordSameAsLast = false;

    private final ImagePattern runAnimation = new ImagePattern(new Image("run.gif"));
    private final ImagePattern jumpAnimation = new ImagePattern(new Image("jump.gif"));
    private final ImagePattern fallAnimation = new ImagePattern(new Image("fall.png"));

    private Sprite player = new Sprite(50, 200, 45, 70, "player");
    private Sprite floor = new Sprite(0, 520, 1200, 2, "floor", Color.BLACK);
    private Sprite jumpLine = new Sprite(0, 350, 1200, 2, "jumpLine", Color.TRANSPARENT);
    private Sprite obstacle;

    //Keyboard Image Letters
    private Sprite q, w, e, r, t, y, u, i, o, p, a, s, d, f, g, h, j, k, l, z, x, c, v, b, n, m;

    private ImageView background2;
    private ImageView background2_2;
    private ImageView background3;
    private ImageView background3_3;
    private ImageView background4;
    private ImageView background4_4;
    private ImageView background5;
    private ImageView background5_5;

    //List of words
    private List<String> wordList = new LinkedList<>();
    private String currentWordBeingDisplayed;
    private Text obstacleWordTextLabel;
    private Text obstacleWordTextLabelCOLOR;

    //index of word displayed so it can be matched
    private int indexWordI;
    private int indexWordJ;


    private Character whatLetterIsPressed = null;

    public Game(){
    }

    public void createNewGame(Stage mainStage, int previousHighScore){

        this.previousHighScore = previousHighScore;

        this.mainStage = mainStage;
        mainStage.hide();

        addDictionary();

        createBackground();

        initializeStage();
        createKeyListeners();

        Text highScoreText = new Text("HighScore: " + previousHighScore);
        highScoreText.setTranslateX(800);
        highScoreText.setTranslateY(50);
        highScoreText.setFont(new Font(35));
        root.getChildren().add(highScoreText);
        
        
        scoreText = new Text("Score: " + score);
        scoreText.setTranslateX(800);
        scoreText.setTranslateY(100);
        scoreText.setFont(new Font(50));
        root.getChildren().add(scoreText);
        

        label = new Text("LETTER THAT IS CURRENTLY PRESSED = " + whatLetterIsPressed);
        label.setLayoutX(600);
        label.setLayoutY(300);
        root.getChildren().add(label);



        addObstacle();
        addObstacleText();

        gameStage.show();
    }

    private void initializeStage() {
        gameStage = new Stage();
        gameScene = new Scene(createContent());
        gameStage.setScene(gameScene);

        addKeyImages();
    }



    private Parent createContent(){ //WHERE THE GAME LOOP IS

        root.setPrefSize(1200, 600);

        //Add player. Was created at the top.
        root.getChildren().add(player);

        //add floor
        root.getChildren().add(floor);
        root.getChildren().add(jumpLine);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    //THIS ONLY GET'S RAN ONCE PER GAME
    private void addObstacle(){


            obstacle = new Sprite(1300, 490, 20, 30, "obstacle", Color.DARKOLIVEGREEN);
            root.getChildren().add(obstacle);


    }

    private void addObstacleText(){
        indexWordI = 0;
        indexWordJ = 1;

        Random rand = new Random();
        int randomNumber = rand.nextInt(wordList.size());

        currentWordBeingDisplayed = wordList.get(randomNumber);
        obstacleWordTextLabel = new Text(currentWordBeingDisplayed);


        wordList.remove(randomNumber);

        obstacleWordTextLabel.setTranslateX(1250); obstacleWordTextLabel.setY(400); obstacleWordTextLabel.setFont(new Font(50));
        obstacleWordTextLabel.setStroke(Color.WHITE); obstacleWordTextLabel.setStrokeWidth(1);

        obstacleWordTextLabelCOLOR = new Text("");
        obstacleWordTextLabelCOLOR.setFont(new Font(50)); obstacleWordTextLabelCOLOR.setFill(Color.RED); obstacleWordTextLabelCOLOR.setY(400);

        root.getChildren().add(obstacleWordTextLabel);
        root.getChildren().add(obstacleWordTextLabelCOLOR);

    }

    private void onUpdate(){

        label.setText("LETTER THAT IS CURRENTLY PRESSED = " + whatLetterIsPressed);
        
        scoreText.setText("Score: " + score++);

        // TODO: 4/29/19 Fix double letter being automatically typed
        if(whatLetterIsPressed != null && whatLetterIsPressed.toString().equals(currentWordBeingDisplayed.substring(indexWordI, indexWordJ)) && indexWordI != currentWordBeingDisplayed.length()) {
            checkTextTyped();
        }

        moveBackground();
        obstacle.moveLeft(obstaclesPassed);
        obstacleWordTextLabel.setTranslateX(obstacleWordTextLabel.getTranslateX() - obstaclesPassed);
        obstacleWordTextLabelCOLOR.setTranslateX(obstacleWordTextLabelCOLOR.getTranslateX() - obstaclesPassed);

            if(isUpKeyPressed)
                jump(player);

            if(!(isUpKeyPressed) && !(player.getBoundsInParent().intersects(floor.getBoundsInParent())))
                fall(player);

            if(!(isUpKeyPressed) && (player.getBoundsInParent().intersects(floor.getBoundsInParent())))
                player.setFill(runAnimation);

            if(obstacle.getTranslateX() < -15){
                obstacle.setTranslateX(1300);
                obstacleWordTextLabel.setTranslateX(1300);
                obstacleWordTextLabelCOLOR.setTranslateX(1300);
                addObstacleText();
                obstaclesPassed += 0.3; //INCREASES GAME SPEED
            }
            if(obstacle.getBoundsInParent().intersects(player.getBoundsInParent())){

                CreateContent.highScore = score > previousHighScore ? score : previousHighScore; //I understand this is the incorrect way to do this!
                gameStage.close();
                timer.stop();
                mainStage.show();
            }

//        if(!(isUpKeyHeld) && player.getBoundsInParent().intersects(floor.getBoundsInParent())){
//            jumpLine.setTranslateY(350);
//        }

        //THIS IS AUTO JUMP
        if(obstacle.getTranslateX() < 175 && obstacle.getTranslateX() > 50 && indexWordI == currentWordBeingDisplayed.length()){
            isUpKeyPressed = true;
            jump(player);
        }

            //THIS IS BAD PROGRAMMING NEVER DO THIS!
//        if(whatLetterIsPressed != null) {
//            keyPressed = 1;
//        }else{
//            keyPressed = 0;
//        }
    }

    private void checkTextTyped(){

//        if(!nextWordSameAsLast){
            obstacleWordTextLabelCOLOR.setText(obstacleWordTextLabelCOLOR.getText() + currentWordBeingDisplayed.substring(indexWordI, indexWordJ));
            obstacleWordTextLabel.setText("          " + currentWordBeingDisplayed.substring(indexWordJ));

            obstacleWordTextLabelCOLOR.setTranslateX(obstacleWordTextLabel.getTranslateX() - 30);

            indexWordI++;
            if(indexWordJ != currentWordBeingDisplayed.length())
                indexWordJ++;
//        }

//
//        if (currentWordBeingDisplayed.charAt(indexWordI) == currentWordBeingDisplayed.charAt(indexWordJ))
//            nextWordSameAsLast = true;
//        else nextWordSameAsLast = false;
    }

    private void jump(Sprite sprite){
        if((sprite.getBoundsInParent().intersects(jumpLine.getBoundsInParent()))){
            isUpKeyPressed = false;
        }

        sprite.moveUp();
        player.setFill(jumpAnimation);
//
//        if(isUpKeyHeld){
//            jumpLine.moveUp(1);
//        }



    }
    private void fall(Sprite sprite){
        sprite.moveDown();
        player.setFill(fallAnimation);
    }

    private void addDictionary() {
        BufferedReader reader = null;

        try{
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("words.txt");
            if(inputStream == null)
                throw  new IOException("Word text file not found!");

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String word;
            while((word = reader.readLine()) != null){
                word = word.trim().toLowerCase();
                wordList.add(word);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException ignored){
            }
        }
    }

    private void createBackground(){
        //Background Images
        ImageView background1 = new ImageView("background/plx-1.png");
        background1.setFitHeight(600); background1.setFitWidth(1200);

        background2 = new ImageView("background/plx-2.png");
        background2.setFitHeight(600); background2.setFitWidth(1200);

        background3 = new ImageView("background/plx-3.png");
        background3.setFitHeight(600); background3.setFitWidth(1200);

        background4 = new ImageView("background/plx-4.png");
        background4.setFitHeight(600); background4.setFitWidth(1200);

        background5 = new ImageView("background/plx-5.png");
        background5.setFitHeight(600); background5.setFitWidth(1200);

        background2_2 = new ImageView("background/plx-2.png");
        background2_2.setFitHeight(600); background2_2.setFitWidth(1200); background2_2.setTranslateX(1200);

        background3_3 = new ImageView("background/plx-3.png");
        background3_3.setFitHeight(600); background3_3.setFitWidth(1200); background3_3.setTranslateX(1200);

        background4_4 = new ImageView("background/plx-4.png");
        background4_4.setFitHeight(600); background4_4.setFitWidth(1200); background4_4.setTranslateX(1200);

        background5_5 = new ImageView("background/plx-5.png");
        background5_5.setFitHeight(600); background5_5.setFitWidth(1200); background5_5.setTranslateX(1200);


        root.getChildren().addAll(background1, background2, background2_2, background3, background3_3, background4, background4_4, background5, background5_5);
    }

    private void moveBackground(){
        background2.setTranslateX(background2.getTranslateX() - 0.3);
        background2_2.setTranslateX(background2_2.getTranslateX() - 0.3);

        background3.setTranslateX(background3.getTranslateX() - 0.8);
        background3_3.setTranslateX(background3_3.getTranslateX() - 0.8);

        background4.setTranslateX(background4.getTranslateX() - 1.2);
        background4_4.setTranslateX(background4_4.getTranslateX() - 1.2);

        background5.setTranslateX(background5.getTranslateX() - 2.5);
        background5_5.setTranslateX(background5_5.getTranslateX() - 2.5);

        if(background2.getTranslateX() < -1200)
            background2.setTranslateX(1190);

        if(background2_2.getTranslateX() < -1200)
            background2_2.setTranslateX(1190);

        if(background3.getTranslateX() < -1200)
            background3.setTranslateX(1190);

        if(background3_3.getTranslateX() < -1200)
            background3_3.setTranslateX(1190);

        if(background4.getTranslateX() < -1200)
            background4.setTranslateX(1190);

        if(background4_4.getTranslateX() < -1200)
            background4_4.setTranslateX(1190);

        if(background5.getTranslateX() < -1200)
            background5.setTranslateX(1190);

        if(background5_5.getTranslateX() < -1200)
            background5_5.setTranslateX(1190);

    }

    private void createKeyListeners(){
        gameScene.setOnKeyPressed(event -> {
            switch(event.getCode()){
//                case UP:
//                    if(player.getBoundsInParent().intersects(floor.getBoundsInParent()))
//                        isUpKeyPressed = true;
//                    isUpKeyHeld = true;
//                    break;
                case Q:
                    whatLetterIsPressed = 'q';
                    q.setFill(Color.RED);
                    break;
                case W:
                    whatLetterIsPressed = 'w';
                    w.setFill(Color.RED);
                    break;
                case E:
                    whatLetterIsPressed = 'e';
                    e.setFill(Color.RED);
                    break;
                case R:
                    whatLetterIsPressed = 'r';
                    r.setFill(Color.RED);
                    break;
                case T:
                    whatLetterIsPressed = 't';
                    t.setFill(Color.RED);
                    break;
                case Y:
                    whatLetterIsPressed = 'y';
                    y.setFill(Color.RED);
                    break;
                case U:
                    whatLetterIsPressed = 'u';
                    u.setFill(Color.RED);
                    break;
                case I:
                    whatLetterIsPressed = 'i';
                    i.setFill(Color.RED);
                    break;
                case O:
                    whatLetterIsPressed = 'o';
                    o.setFill(Color.RED);
                    break;
                case P:
                    whatLetterIsPressed = 'p';
                    p.setFill(Color.RED);
                    break;
                case A:
                    whatLetterIsPressed = 'a';
                    a.setFill(Color.RED);
                    break;
                case S:
                    whatLetterIsPressed = 's';
                    s.setFill(Color.RED);
                    break;
                case D:
                    whatLetterIsPressed = 'd';
                    d.setFill(Color.RED);
                    break;
                case F:
                    whatLetterIsPressed = 'f';
                    f.setFill(Color.RED);
                    break;
                case G:
                    whatLetterIsPressed = 'g';
                    g.setFill(Color.RED);
                    break;
                case H:
                    whatLetterIsPressed = 'h';
                    h.setFill(Color.RED);
                    break;
                case J:
                    whatLetterIsPressed = 'j';
                    j.setFill(Color.RED);
                    break;
                case K:
                    whatLetterIsPressed = 'k';
                    k.setFill(Color.RED);
                    break;
                case L:
                    whatLetterIsPressed = 'l';
                    l.setFill(Color.RED);
                    break;
                case Z:
                    whatLetterIsPressed = 'z';
                    z.setFill(Color.RED);
                    break;
                case X:
                    whatLetterIsPressed = 'x';
                    x.setFill(Color.RED);
                    break;
                case C:
                    whatLetterIsPressed = 'c';
                    c.setFill(Color.RED);
                    break;
                case V:
                    whatLetterIsPressed = 'v';
                    v.setFill(Color.RED);
                    break;
                case B:
                    whatLetterIsPressed = 'b';
                    b.setFill(Color.RED);
                    break;
                case N:
                    whatLetterIsPressed = 'n';
                    n.setFill(Color.RED);
                    break;
                case M:
                    whatLetterIsPressed = 'm';
                    m.setFill(Color.RED);
                    break;
                case ESCAPE:
                    timer.stop();
                    break;
            }
        });

        gameScene.setOnKeyReleased(event -> {
            switch(event.getCode()){
//                case UP:
//                    isUpKeyHeld = false;
//                    break;
                case Q:
                    whatLetterIsPressed = null;
                    q.setFill(new ImagePattern(new Image("keyboard/q.png")));
                    break;
                case W:
                    whatLetterIsPressed = null;
                    w.setFill(new ImagePattern(new Image("keyboard/w.png")));
                    break;
                case E:
                    whatLetterIsPressed = null;
                    e.setFill(new ImagePattern(new Image("keyboard/e.png")));
                    break;
                case R:
                    whatLetterIsPressed = null;
                    r.setFill(new ImagePattern(new Image("keyboard/r.png")));
                    break;
                case T:
                    whatLetterIsPressed = null;
                    t.setFill(new ImagePattern(new Image("keyboard/t.png")));
                    break;
                case Y:
                    whatLetterIsPressed = null;
                    y.setFill(new ImagePattern(new Image("keyboard/y.png")));
                    break;
                case U:
                    whatLetterIsPressed = null;
                    u.setFill(new ImagePattern(new Image("keyboard/u.png")));
                    break;
                case I:
                    whatLetterIsPressed = null;
                    i.setFill(new ImagePattern(new Image("keyboard/i.png")));
                    break;
                case O:
                    whatLetterIsPressed = null;
                    o.setFill(new ImagePattern(new Image("keyboard/o.png")));
                    break;
                case P:
                    whatLetterIsPressed = null;
                    p.setFill(new ImagePattern(new Image("keyboard/p.png")));
                    break;
                case A:
                    whatLetterIsPressed = null;
                    a.setFill(new ImagePattern(new Image("keyboard/a.png")));
                    break;
                case S:
                    whatLetterIsPressed = null;
                    s.setFill(new ImagePattern(new Image("keyboard/s.png")));
                    break;
                case D:
                    whatLetterIsPressed = null;
                    d.setFill(new ImagePattern(new Image("keyboard/d.png")));
                    break;
                case F:
                    whatLetterIsPressed = null;
                    f.setFill(new ImagePattern(new Image("keyboard/f.png")));
                    break;
                case G:
                    whatLetterIsPressed = null;
                    g.setFill(new ImagePattern(new Image("keyboard/g.png")));
                    break;
                case H:
                    whatLetterIsPressed = null;
                    h.setFill(new ImagePattern(new Image("keyboard/h.png")));
                    break;
                case J:
                    whatLetterIsPressed = null;
                    j.setFill(new ImagePattern(new Image("keyboard/j.png")));
                    break;
                case K:
                    whatLetterIsPressed = null;
                    k.setFill(new ImagePattern(new Image("keyboard/k.png")));
                    break;
                case L:
                    whatLetterIsPressed = null;
                    l.setFill(new ImagePattern(new Image("keyboard/l.png")));
                    break;
                case Z:
                    whatLetterIsPressed = null;
                    z.setFill(new ImagePattern(new Image("keyboard/z.png")));
                    break;
                case X:
                    whatLetterIsPressed = null;
                    x.setFill(new ImagePattern(new Image("keyboard/x.png")));
                    break;
                case C:
                    whatLetterIsPressed = null;
                    c.setFill(new ImagePattern(new Image("keyboard/c.png")));
                    break;
                case V:
                    whatLetterIsPressed = null;
                    v.setFill(new ImagePattern(new Image("keyboard/v.png")));
                    break;
                case B:
                    whatLetterIsPressed = null;
                    b.setFill(new ImagePattern(new Image("keyboard/b.png")));
                    break;
                case N:
                    whatLetterIsPressed = null;
                    n.setFill(new ImagePattern(new Image("keyboard/n.png")));
                    break;
                case M:
                    whatLetterIsPressed = null;
                    m.setFill(new ImagePattern(new Image("keyboard/m.png")));
                    break;
                case ESCAPE:
                    timer.start();
                    break;
            }
        });
    }
    private void addKeyImages(){
        q = new Sprite(150, 50, 50, 50, "letter");
        root.getChildren().add(q);
        q.setFill(new ImagePattern(new Image("keyboard/q.png")));

        w = new Sprite(205, 50, 50, 50, "letter");
        w.setFill(new ImagePattern(new Image("keyboard/w.png")));
        root.getChildren().add(w);

        e = new Sprite(260, 50, 50, 50, "letter");
        e.setFill(new ImagePattern(new Image("keyboard/e.png")));
        root.getChildren().add(e);

        r = new Sprite(315, 50, 50, 50, "letter");
        r.setFill(new ImagePattern(new Image("keyboard/r.png")));
        root.getChildren().add(r);

        t = new Sprite(370, 50, 50, 50, "letter");
        t.setFill(new ImagePattern(new Image("keyboard/t.png")));
        root.getChildren().add(t);

        y = new Sprite(425, 50, 50, 50, "letter");
        y.setFill(new ImagePattern(new Image("keyboard/y.png")));
        root.getChildren().add(y);

        u = new Sprite(480, 50, 50, 50, "letter");
        u.setFill(new ImagePattern(new Image("keyboard/u.png")));
        root.getChildren().add(u);

        i = new Sprite(535, 50, 50, 50, "letter");
        i.setFill(new ImagePattern(new Image("keyboard/i.png")));
        root.getChildren().add(i);

        o = new Sprite(590, 50, 50, 50, "letter");
        o.setFill(new ImagePattern(new Image("keyboard/o.png")));
        root.getChildren().add(o);

        p = new Sprite(645, 50, 50, 50, "letter");
        p.setFill(new ImagePattern(new Image("keyboard/p.png")));
        root.getChildren().add(p);

        a = new Sprite(175, 105, 50, 50, "letter");
        a.setFill(new ImagePattern(new Image("keyboard/a.png")));
        root.getChildren().add(a);

        s = new Sprite(230, 105, 50, 50, "letter");
        s.setFill(new ImagePattern(new Image("keyboard/s.png")));
        root.getChildren().add(s);

        d = new Sprite(285, 105, 50, 50, "letter");
        d.setFill(new ImagePattern(new Image("keyboard/d.png")));
        root.getChildren().add(d);

        f = new Sprite(340, 105, 50, 50, "letter");
        f.setFill(new ImagePattern(new Image("keyboard/f.png")));
        root.getChildren().add(f);

        g = new Sprite(395, 105, 50, 50, "letter");
        g.setFill(new ImagePattern(new Image("keyboard/g.png")));
        root.getChildren().add(g);

        h = new Sprite(450, 105, 50, 50, "letter");
        h.setFill(new ImagePattern(new Image("keyboard/h.png")));
        root.getChildren().add(h);

        j = new Sprite(505, 105, 50, 50, "letter");
        j.setFill(new ImagePattern(new Image("keyboard/j.png")));
        root.getChildren().add(j);

        k = new Sprite(560, 105, 50, 50, "letter");
        k.setFill(new ImagePattern(new Image("keyboard/k.png")));
        root.getChildren().add(k);

        l = new Sprite(615, 105, 50, 50, "letter");
        l.setFill(new ImagePattern(new Image("keyboard/l.png")));
        root.getChildren().add(l);

        z = new Sprite(200, 160, 50, 50, "letter");
        z.setFill(new ImagePattern(new Image("keyboard/z.png")));
        root.getChildren().add(z);

        x = new Sprite(255, 160, 50, 50, "letter");
        x.setFill(new ImagePattern(new Image("keyboard/x.png")));
        root.getChildren().add(x);

        c = new Sprite(310, 160, 50, 50, "letter");
        c.setFill(new ImagePattern(new Image("keyboard/c.png")));
        root.getChildren().add(c);

        v = new Sprite(365, 160, 50, 50, "letter");
        v.setFill(new ImagePattern(new Image("keyboard/v.png")));
        root.getChildren().add(v);

        b = new Sprite(420, 160, 50, 50, "letter");
        b.setFill(new ImagePattern(new Image("keyboard/b.png")));
        root.getChildren().add(b);

        n = new Sprite(475, 160, 50, 50, "letter");
        n.setFill(new ImagePattern(new Image("keyboard/n.png")));
        root.getChildren().add(n);

        m = new Sprite(530, 160, 50, 50, "letter");
        m.setFill(new ImagePattern(new Image("keyboard/m.png")));
        root.getChildren().add(m);

    }
}
