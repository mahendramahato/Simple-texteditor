package com.example.texteditor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.*;

public class HelloController {
    @FXML
    private ColorPicker colorPicker;

    // Create new text file
    public void newFile(ActionEvent event) throws IOException {
        textArea.setText("");
    }

    // Load file into the text editor
    public void chooseFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        // only choose text files
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // user choose file
        File fileToLoad = fileChooser.showOpenDialog(null);
        // load chosen file
        if (fileToLoad != null) {
            openFile(fileToLoad);
        }
    }
    @FXML
    private TextArea textArea;
    private void openFile(File fileToLoad) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToLoad));
            String line;
            while((line = br.readLine()) != null) {
                textArea.appendText(line);
                textArea.appendText("\n");
            }
        } catch( Exception e) {
            textArea.setText("Could not load file from:\n" + fileToLoad.getAbsolutePath());
        }
    }

    // Save text file
    public void saveFile(ActionEvent event) {
        String sampleText = textArea.getText();
        FileChooser fileChooser = new FileChooser();
        // extension filter
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        // show save file dialog
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            saveText(sampleText, file);
        }
    }
    private void saveText(String sampleText, File file) {
        try {
            PrintWriter write = new PrintWriter(file);
            write.println(sampleText);
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Exit text editor
    public void exitEditor(ActionEvent event) {
        Platform.exit();
    }

    // Undo
    public void undo(ActionEvent event) {
        textArea.undo();
    }
    // Redo
    public void redo(ActionEvent event) {
        textArea.redo();
    }

    // Font style
    public void ariel(ActionEvent event) {
       textArea.setFont(Font.font("Ariel"));
    }
    public void timesNewRoman(ActionEvent event) {
        textArea.setFont(Font.font("Times New Roman"));
    }
    public void helvetica(ActionEvent event) {
        textArea.setFont(Font.font("Helvetica"));
    }
    public void impact(ActionEvent event) {
        textArea.setFont(Font.font("Impact"));
    }

    // Set font size
    public void forTeen(ActionEvent event) {
        textArea.setFont(Font.font(14));
    }
    public void sixTeen(ActionEvent event) {
        textArea.setFont(Font.font(16));
    }
    public void eightTeen(ActionEvent event) {
        textArea.setFont(Font.font(18));
    }
    public void twenty(ActionEvent event) {
        textArea.setFont(Font.font(20));
    }

    public void pickColor(ActionEvent event) {
        Color myColor = colorPicker.getValue();
        String contents = textArea.getText();

    }

}


