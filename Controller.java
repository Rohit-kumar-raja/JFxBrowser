package sample;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller implements Initializable {
    @FXML
    WebView webView;
    @FXML
    TextField textField;
    @FXML
    Button back,forward,go,Reload;
    @FXML
    WebEngine webEngine;
    @FXML
    ProgressIndicator progressID ;
    public static String title;
    


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       webEngine = webView.getEngine();

        webEngine.load("http://www.google.com");
        webEngine.setJavaScriptEnabled(true);

        webView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textField.setText(webView.getEngine().getLocation());

            }
      

    });




      textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              textField.selectAll();
          }
      });
      textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent keyEvent) {
              if (keyEvent.getCode()==KeyCode.ENTER){

                  pageLoded();
              }
          }

      });

      progressID.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
        progressID.visibleProperty().bind(
                Bindings.when(progressID.progressProperty().lessThan(0).or(progressID.progressProperty().isEqualTo(1)))
                        .then(false)
                        .otherwise(true)
        );
        progressID.managedProperty().bind(progressID.visibleProperty());
    }
public void pageLoded(){
       String string=textField.getText().toString();
    if (string.startsWith("https://")){
        webEngine.load(string);

    }
     else if (string.startsWith("www.")) {
               webEngine.load("https://"+ string);
               progressID.setVisible(true);
           }
       else {
           webEngine.load("https://www.google.com/search?q=" + string);
           webEngine.setJavaScriptEnabled(true);
           webEngine.isJavaScriptEnabled();

       }
    webView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            textField.setText(webView.getEngine().getLocation());
        }
    });

}
public void back(){
        webEngine.executeScript("history.back()");
        

}
public void forward(){
webEngine.executeScript("history.forward()");
}

public void Reload(){
        webEngine.reload();
}

}
