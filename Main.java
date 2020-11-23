
        import java.io.IOException;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javafx.application.Application;
        import javafx.event.Event;
        import javafx.event.EventHandler;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Tab;
        import javafx.scene.control.TabPane;
        import javafx.stage.Stage;

     public class JFxwebBrowser extends Application {

        TabPane root;

        @Override
        public void start(Stage stage) throws IOException {
        Parent browser = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Tab browserTab = new Tab("new tab ", browser);
        Tab addTab = new Tab("+", null);
        addTab.setClosable(false);
        addTab.setOnSelectionChanged(new EventHandler<Event>() {
        @Override
        public void handle(Event event) {
        addNewTab();
        }
        });
        root = new TabPane(browserTab, addTab);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Browser");
        stage.show();
        }
        final void addNewTab() {
        try {
        Parent browser = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Tab browserTab = new Tab("  new tab  ", browser);
        root.getTabs().add(root.getTabs().size() - 1, browserTab);
        root.getSelectionModel().select(browserTab);
        } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public static void main(String[] args) {
        launch(args);
        }

        }
