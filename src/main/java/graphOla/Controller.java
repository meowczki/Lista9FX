package graphOla;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import odeOla.myFODESolver;

public class Controller {

    public TextField tfGetMi;
    public TextField tfGetT0;
    public TextField tfGetT;
    public TextField tfGetV0;
    public TextField tfGetX0;
    public TextField tfGetLP;
    @FXML
    private ResourceBundle resources;

    @FXML
    private ChoiceBox<?> choiceBoxGetMethod;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private Button BtnGenerate;
    @FXML
    private Button clearBtn;

    @FXML
    private Label labelMi;



    @FXML
    private ScatterChart<?, ?> graph;
private myFODESolver xy;
    @FXML
    void generateOnClick(ActionEvent event) {
        if (!((tfGetMi.getText().isEmpty())|(tfGetT.getText().isEmpty())|(tfGetT0.getText().isEmpty())|(tfGetV0.getText().isEmpty())|(tfGetX0.getText().isEmpty()))) {
            XYChart.Series series = new XYChart.Series();

            try {
                xy = new myFODESolver(Double.parseDouble(tfGetMi.getText()),Double.parseDouble(tfGetT0.getText()),Double.parseDouble(tfGetT.getText()),Double.parseDouble(tfGetX0.getText()) ,Double.parseDouble(tfGetV0.getText()),(String) choiceBoxGetMethod.getValue(),Integer.parseInt(tfGetLP.getText()));
                List<Double> x = xy.getxValues();
                List<Double> y = xy.getyValues();
                series.setName("mi= " + tfGetMi.getText());
                IntStream.rangeClosed(0, Integer.parseInt(tfGetLP.getText())-1).forEach(z -> series.getData().add(new XYChart.Data(x.get(z), y.get(z))));
                graph.getData().add(series);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nieprawidłowa wartość");
                alert.setContentText("Podaj wartość liczbową");
                alert.show();
            } catch (IllegalArgumentException e1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nieprawidłowa wartość");
                alert.setContentText("Podaj wartość mi większą od 0");
                alert.show();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pole jest puste");
            alert.setContentText("Wpisz wartości do pola");
            alert.show();
        }

    }

    @FXML
    void initialize() {
        assert tfGetT != null : "fx:id=\"tfGetT\" was not injected: check your FXML file 'graph.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert tfGetX0 != null : "fx:id=\"tfGetX0\" was not injected: check your FXML file 'graph.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'graph.fxml'.";
        assert BtnGenerate != null : "fx:id=\"BtnGenerate\" was not injected: check your FXML file 'graph.fxml'.";
        assert tfGetV0 != null : "fx:id=\"tfGetV0\" was not injected: check your FXML file 'graph.fxml'.";
        assert tfGetMi != null : "fx:id=\"tfGetMi\" was not injected: check your FXML file 'graph.fxml'.";
        assert tfGetT0 != null : "fx:id=\"tfGetT0\" was not injected: check your FXML file 'graph.fxml'.";
        assert labelMi != null : "fx:id=\"labelMi\" was not injected: check your FXML file 'graph.fxml'.";
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'graph.fxml'.";
        assert choiceBoxGetMethod != null : "fx:id=\"choiceBoxGetMethod\" was not injected: check your FXML file 'graph.fxml'.";

    }

    public void clearOnClick(ActionEvent actionEvent) {
        graph.getData().clear();
    }
}
