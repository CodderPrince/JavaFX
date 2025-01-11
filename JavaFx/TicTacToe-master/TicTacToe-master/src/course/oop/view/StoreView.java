package course.oop.view;

import course.oop.controller.Controller;
import course.oop.fileio.FileIO;
import course.oop.model.Player;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class StoreView implements course.oop.view.TTTView {
    private final Parent root;
    private Player p;
    private int cols = 15;
    private Label cash;
    private ComboBox playerSelection;
    //private Scene scene;
    public StoreView(){

        BorderPane parent = new BorderPane();

        this.playerSelection = new ComboBox();
        this.playerSelection.setEditable(true);
        List<String>  entries = new LinkedList<>();
        FileIO.loadHashMap().values().forEach(player -> entries.add(player.asEntry()));
        playerSelection.getItems().addAll(entries);

        GridPane store = new GridPane();
        //final int cols = 15;
        // fill with emojis
        for (int i = 0; i < 39; i++){
            VBox storeItem = new VBox();
            ImageView emoji = new ImageView(new Image(i + ".png"));
            emoji.setFitHeight(50);
            emoji.setFitWidth(50);
            Button buy = new Button("$100");
            int I = i;
            buy.setOnAction(e -> {
                Controller.execute("buy " + p.getUsername() + " " + I);
                this.p = FileIO.loadPlayer(p.getUsername());
                loadPlayer(p, store);
            });
            storeItem.getChildren().addAll(emoji, buy);
            store.add(storeItem, i % cols, i / cols);
            //storeItem.getChildren().addAll();

        }
        // load player, update bought emojis
        //playerSelection.getEditor().setEditable(true);
        playerSelection.setOnAction(e -> {
            System.out.println(playerSelection.getEditor().getText());
            String username = playerSelection.getEditor().getText();
            username = username.replaceFirst("(\\w*).*", "$1");
            //this.username = username;
            this.p = FileIO.loadPlayer(username);
            /*
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            */
            loadPlayer(p, store);

        });
        store.setGridLinesVisible(true);

        Button mainmenu = new Button("Return to main menu");
        mainmenu.setOnAction(e -> Controller.execute("mainmenu"));
        parent.setBottom(mainmenu);
        this.cash = new Label("-");
        parent.setRight(this.cash);


        parent.setTop(playerSelection);
        parent.setCenter(store);
        //this.scene = new Scene(parent, 800, 600);
        this.root = parent;
        //this.scene = new Scene(parent);
    }
    @Override
    public Parent getRoot() {
        return this.root;
    }

    private void loadPlayer(Player p, GridPane store){
        this.p = p;
        // update the money
        this.cash.setText(Integer.toString(p.getCash()));
        // the emojis
        List<Integer> boughtEmojis = p.emojisUnlocked;
        store.getChildren().forEach(child -> child.setEffect(null));
        boughtEmojis.forEach(emojiID -> {
            int row = emojiID / cols;
            int col = emojiID % cols;

            for (Node child : store.getChildren()){
                int childCol = GridPane.getColumnIndex(child);
                int childRow = GridPane.getRowIndex(child);
                if (row == childRow && col==childCol){
                    child.setEffect(new ColorAdjust(0, -10, 0, 0));
                    child.setDisable(true);
                    break;
                }
            }
        });
    }
}
