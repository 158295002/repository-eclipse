

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BroswerT extends Composite {

	public BroswerT(Composite parent, int style) {
		super(parent, style);
//		System.setProperty("org.eclipse.swt.browser.XULRunnerPath", "C:\\Program Files (x86)\\xulrunner");
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		this.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		this.setLayoutData(data);
		Browser browser = new Browser(this, SWT.NONE);
		browser.setText("浏览器");
		browser.setUrl("http://192.168.3.160:10380/inno-de-web/");
//		browser.setUrl("https://www.baidu.com/");
		browser.setLayoutData(data);
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		shell.setLayout(layout);
		BroswerT test = new BroswerT(shell, SWT.NONE);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
