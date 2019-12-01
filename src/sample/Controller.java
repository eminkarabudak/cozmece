package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Controller {

    @FXML
    private Button start;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button next;
    @FXML
    private Label questionText;
    @FXML
    private Label correctionText;
    @FXML
    private Label fin;
    @FXML
    private ImageView imageView;

    private int count = 0;

    private final String correctMessage = "Doğru cevap verdiniz bir sonraki soruya geçebilirsiniz";
    private final String correctAnswerColor = "-fx-background-color: limegreen";
    private final String wrongAnswerColor = "-fx-background-color: red";
    private final String defaultColor = "-fx-background-color: gainsboro";

    private final static Soru[] sorus = new Soru[]{
            new Soru("/resources/1.jpg", "olduğuna göre f(-1) değeri kaçtır?", "0", "2", "1"),
            new Soru("/resources/2.jpg", "İntegralinin değeri nedir?", "20", "15", "12"),
            new Soru("/resources/3.jpg", "İntegralinin değeri nedir?", "1", "0", "2"),
            new Soru("/resources/4.jpg", "İntegralinin eşiti hangisisidir?", "2", "3", "0"),
            new Soru("/resources/5.jpg", "Olduğuna göre, a+b kaçtır?", "7", "11", "9"),
            new Soru("/resources/6.jpg", "İntegralinin değeri nedir?", "0", "-1", "-2")
    };

    public void initialize() {
        next.setVisible(false);
        imageView.setVisible(false);
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        questionText.setVisible(false);
        start();
    }

    public void start() {
        start.setOnAction(event -> {
            start.setVisible(false);
            next.setVisible(true);
            imageView.setVisible(true);
            button1.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);
            questionText.setVisible(true);
            setValues(count);
            button1Action();
            button2Action();
            button3Action();
            nextAction();
        });
    }

    public void setValues(int count) {
        Random random = new Random();
        int correct = random.nextInt(3) + 1;
        switch (correct) {
            case 1:
                button1.setText(sorus[count].getCorrectAnswer());
                button2.setText(sorus[count].getWrongAnswer1());
                button3.setText(sorus[count].getWrongAnswer2());
                break;

            case 2:

                button1.setText(sorus[count].getWrongAnswer1());
                button2.setText(sorus[count].getCorrectAnswer());
                button3.setText(sorus[count].getWrongAnswer2());
                break;

            case 3:
                button1.setText(sorus[count].getWrongAnswer1());
                button2.setText(sorus[count].getWrongAnswer2());
                button3.setText(sorus[count].getCorrectAnswer());
                break;
        }
        imageView.setImage(new Image(sorus[count].getPath()));
        questionText.setText(sorus[count].getQuestionText());
        next.setVisible(false);
    }

    public void button1Action() {
        button1.setOnAction(e -> setStatus(button1));
    }

    public void button2Action() {
        button2.setOnAction(e -> setStatus(button2));
    }

    public void button3Action() {
        button3.setOnAction(e -> setStatus(button3));
    }

    public void nextAction() {
        System.out.println("Sonraki");
        next.setOnAction(event -> {
            button1.setDisable(false);
            button2.setDisable(false);
            button3.setDisable(false);
            clearButtons();
            if (count < sorus.length) {
                setValues(count);
            } else {
                fin.setText("Tebrikler Tüm Soruları Doğru Cevapladınız");
                next.setVisible(false);
                imageView.setVisible(false);
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                questionText.setVisible(false);
            }
        });

    }

    private void clearButtons() {
        correctionText.setText(null);
        next.setVisible(false);
        button1.setStyle(defaultColor);
        button2.setStyle(defaultColor);
        button3.setStyle(defaultColor);
    }


    public void setStatus(Button button) {
        if (button.getText().equalsIgnoreCase(sorus[count].getCorrectAnswer())) {
            correctAnswered(button);
            button1.setDisable(true);
            button2.setDisable(true);
            button3.setDisable(true);
        } else {
            worngAnswered(button);
        }
    }

    private void correctAnswered(Button button) {
        clearButtons();
        button.setStyle(correctAnswerColor);
        correctionText.setText(correctMessage);
        next.setVisible(true);
        count++;
    }

    private void worngAnswered(Button button) {
        clearButtons();
        button.setStyle(wrongAnswerColor);
    }
}
