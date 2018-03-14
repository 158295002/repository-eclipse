package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
	private String mInitUrl = "https://www.baidu.com";

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane borderPane  = new BorderPane();
			Scene scene = new Scene(borderPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			WebView mWebView = new WebView();
			// ScrollPane scrollPane = new ScrollPane(mWebView);
			// scrollPane.setPrefSize(scene.getWidth(), scene.getHeight());
			borderPane.setCenter(mWebView);
//			mWebView.autosize();
			mWebView.getEngine().load(mInitUrl);
			mWebView.getEngine().executeScript("alert('dd')");

			mWebView.getEngine().locationProperty().addListener(new ChangeListener() {

				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					Desktop d = Desktop.getDesktop();
					URI address;
					try {
						if (!((String) observable.getValue()).contentEquals(mInitUrl)) {
							address = new URI((String) observable.getValue());
							d.browse(address);
						}
					} catch (URISyntaxException | IOException e) {
						e.printStackTrace();
					}
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
