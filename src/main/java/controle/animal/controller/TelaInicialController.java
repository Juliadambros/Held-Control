package controle.animal.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button btnCadastrarFazenda;

    @FXML
    private Button btnCadastroAnimal;

    @FXML
    private Button btnPesquisaAnimal;

    @FXML
    private Button btnTelaInicial;

    @FXML
    private ImageView imagem;

    @FXML
    private ImageView imagem1;

    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResourceAsStream("/controle/animal/imagem/Logo.jpeg"));
        imagem.setImage(img);

    }

    @FXML
    private void handleTelaInicial(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        abrirNovaTela("/controle/animal/view/TelaInicial.fxml", "Tela Inicial");
    }

    @FXML
    private void handleCadastroAnimal(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        abrirNovaTela("/controle/animal/view/CadastroAnimal.fxml", "Cadastro de Animal");
    }

    @FXML
    private void handleCadastroFazenda(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        abrirNovaTela("/controle/animal/view/CadastroFazenda.fxml", "Cadastro de Fazenda");
    }

    @FXML
    private void handlePesquisaAnimal(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        abrirNovaTela("/controle/animal/view/PesquisaAnimal.fxml", "Pesquisa de Animal");
    }

    private void abrirNovaTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);

            newStage.setScene(newScene);
            newStage.setTitle(titulo);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Erro ao abrir a tela: " + titulo, e);
        }
    }

    private void showErrorAlert(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setResizable(true);
        alert.setHeight(150);
        alert.setWidth(300);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
