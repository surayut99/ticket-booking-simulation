package theatre.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URISyntaxException;

public class NodeCreator {
    public static AnchorPane createAnchorPane(double width, double height, Node ...nodes) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        for (Node node : nodes) anchorPane.getChildren().add(node);

        return anchorPane;
    }

    public static ImageView createImageView(double width, double height, String path, boolean preserveRatio) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(preserveRatio);
        try {
            imageView.setImage(new Image(NodeCreator.class.getResource(path).toURI().toString()));
        } catch (NullPointerException e) {
            imageView.setImage(new Image(new File(path).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return imageView;
    }

    public static Label createLabel(String string, double size, String color) {
        Label label = new Label(string);
        label.setFont(Font.font("System", FontWeight.BOLD, size));
        label.setTextFill(Paint.valueOf(color));
        label.setAlignment(Pos.CENTER);

        return label;
    }

    public static void setAlignmentNodeOnAnchorPane(Node node, Double top, Double left, Double right, Double bottom) {
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setBottomAnchor(node, bottom);
    }

    public static AnchorPane createSeatIcon(double imgSize, String position, String pathImg) {
        ImageView seatImg = createImageView(imgSize, imgSize, pathImg, true);
        Label seatPosition = createLabel(position, 18, "#ffffff");
        setAlignmentNodeOnAnchorPane(seatPosition, 29.,0.,4.,0.);
        seatPosition.setEffect(new Glow(0.5));
        AnchorPane groupImg = new AnchorPane(seatImg, seatPosition);
        groupImg.setCursor(Cursor.HAND);

        return groupImg;
    }

    public static Label createTimeLabel(String text) {
        Label label = createLabel(text, 24, "#000000");
        label.setPrefHeight(40);
        label.setPrefWidth(100);
        label.setCursor(Cursor.HAND);
        label.setStyle("-fx-background-color: linear-gradient(#FDC830,#F37335);\n" +
                "    -fx-background-radius: 25;\n" +
                "    -fx-background-insets: 0;");

        return label;
    }

    public static AnchorPane createWarningAnchorPane(EventHandler<ActionEvent> event, String ...texts) {
        Text topic = new Text(texts[0]);
        Text description = new Text(texts[1]);
        Text question = new Text("You want to continue?");

        topic.setFont(Font.font("System", FontWeight.BOLD, 24));
        topic.setFill(Color.BLACK);
        question.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 24));
        question.setFill(Color.BLACK);
        description.setFont(Font.font("System", FontPosture.ITALIC, 24));
        description.setFill(Color.BLACK);

        setAlignmentNodeOnAnchorPane(topic, 15. , 20., null, null);
        setAlignmentNodeOnAnchorPane(description, 50. , 20., null, null);
        setAlignmentNodeOnAnchorPane(question, 100. , 20., null, null);

        Button yes = new Button("Yes");
        Button no = new Button("No");

        yes.setFont(Font.font("System", FontWeight.BOLD, 18));
        setAlignmentNodeOnAnchorPane(yes, 150., 177., null, null);
        yes.setPrefWidth(120);
        yes.setOnAction(event);
        no.setFont(Font.font("System", FontWeight.BOLD, 18));
        setAlignmentNodeOnAnchorPane(no, 150., 327., null, null);
        no.setPrefWidth(120);
        no.setOnAction(event);

        AnchorPane anchorPane = new AnchorPane(topic, description, question, yes, no);
        anchorPane.setStyle(
                "-fx-background-color: rgba(255,255,255,0.5);\n" +
                "-fx-background-radius: 20;");
        anchorPane.setPadding(new Insets(10,10,10,10));

        return anchorPane;
    }
}
