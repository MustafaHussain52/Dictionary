package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MyDictionary extends Application {

    int yLine = 10;
    TextField wordTextField;
    Label meaningLabel;

    Button searchButton;

    ListView<String> suggestionWordList;

    DictionaryUsingHashMap dictionary;


    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(300,300);

        wordTextField = new TextField();
        wordTextField.setPromptText("Please enter a word");
        wordTextField.setTranslateY(yLine);
        wordTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
         //   meaningLabel.setText(wordTextField.getText());
            String word = wordTextField.getText();
            if(word.isBlank()==false && word.length()>=3){
                //fetch suggestions
                String[] suggestions = dictionary.getSuggestions(word);

                //bind suggestions to list
                suggestionWordList.getItems().clear();
                suggestionWordList.getItems().addAll(suggestions);
            }
            }
        });

        meaningLabel = new Label("I am the meaning");
        meaningLabel.setTranslateY(yLine+30);

         dictionary = new DictionaryUsingHashMap();


        searchButton = new Button("Search");
        searchButton.setTranslateY(yLine);
        searchButton.setTranslateX(200);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word = wordTextField.getText();
                if(word.isBlank()) {
                    meaningLabel.setText("Please enter a word!");
                    meaningLabel.setTextFill(Color.RED);

                }
                else{
                    meaningLabel.setText(dictionary.findMeaning(word));
                    meaningLabel.setTextFill(Color.BLACK);
                }
            }
        });

        suggestionWordList = new ListView<>();
        suggestionWordList.setTranslateY(yLine+70);
        String [] suggestedList = {"Mustafa" , "concern","weeping","minute"};
        suggestionWordList.getItems().addAll(suggestedList);
        suggestionWordList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWords = suggestionWordList.getSelectionModel().getSelectedItem();
                meaningLabel.setText(selectedWords);
            }
        });



        root.getChildren().addAll(wordTextField,searchButton,meaningLabel,suggestionWordList);


        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}