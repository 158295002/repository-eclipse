package pulgin_fx.views;

import org.eclipse.fx.ui.workbench3.FXViewPart;

import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class MyViewPart extends FXViewPart {
	WebView mWebView;

	@Override
	protected Scene createFxScene() {
		mWebView = new WebView();
		// ScrollPane scrollPane = new ScrollPane(mWebView);
		mWebView.getEngine().load("http://www.baidu.com");
		return new Scene(mWebView);
	}

	@Override
	protected void setFxFocus() {
		mWebView.requestFocus();
	}
}