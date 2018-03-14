package application;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class Swt_webview {

	private static Scene createScene() {
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root);
		WebView mWebView = new WebView();
		mWebView.autosize();
		 mWebView.getEngine().load("http://192.168.3.160:10380/inno-de-web/");
//		mWebView.getEngine().load("http://wiki.eclipse.org/");
		scene.setRoot(mWebView);
		return scene;
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
		Scene scene = createScene();
		canvas.setScene(scene);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
