package com.evada.browser.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {
	static final int BROWSER_STYLE = SWT.NONE;
	public static void main(String[] args) {final String SCRIPT = "document.onmousedown = function(e) {if (!e) {e = window.event;} if (e) {window.status = 'MOUSEDOWN: ' + e.clientX + ',' + e.clientY;}}";
	Display display = new Display();
	final Shell shell = new Shell(display);
	shell.setLayout(new FillLayout());
	final Browser browser;
	try {
		browser = new Browser(shell, SWT.NONE);
	} catch (SWTError e) {
		System.out.println("Could not instantiate Browser: " + e.getMessage());
		display.dispose();
		return;
	}
	browser.addProgressListener(new ProgressListener() {
		
		@Override
		public void completed(ProgressEvent event) {
			browser.execute(SCRIPT);
		}
		
		@Override
		public void changed(ProgressEvent event) {
			// TODO Auto-generated method stub
			
		}
	});
	browser.addStatusTextListener(event -> {
		if (event.text.startsWith("MOUSEDOWN: ")) {
			System.out.println(event.text);
			browser.execute("window.status = '';");
		}
	});
	browser.setUrl("eclipse.org");
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
	display.dispose();}

/* register WindowEvent listeners */
static void initialize(final Display display, Browser browser) {
	browser.addOpenWindowListener(event -> {
		Shell shell = new Shell(display);
		shell.setText("New Window");
		shell.setLayout(new FillLayout());
		Browser browser1 = new Browser(shell, BROWSER_STYLE);
		initialize(display, browser1);
		event.browser = browser1;
	});
	browser.addVisibilityWindowListener(new VisibilityWindowListener() {
		@Override
		public void hide(WindowEvent event) {
			Browser browser = (Browser)event.widget;
			Shell shell = browser.getShell();
			shell.setVisible(false);
		}
		@Override
		public void show(WindowEvent event) {
			Browser browser = (Browser)event.widget;
			final Shell shell = browser.getShell();
			/* popup blocker - ignore windows with no style */
			boolean isOSX = SWT.getPlatform().equals ("cocoa");
			if (!event.addressBar && !event.statusBar && !event.toolBar && (!event.menuBar || isOSX)) {
				System.out.println("Popup blocked.");
				event.display.asyncExec(() -> shell.close());
				return;
			}
			if (event.location != null) shell.setLocation(event.location);
			if (event.size != null) {
				Point size = event.size;
				shell.setSize(shell.computeSize(size.x, size.y));
			}
			shell.open();
		}
	});
	browser.addCloseWindowListener(event -> {
		Browser browser1 = (Browser)event.widget;
		Shell shell = browser1.getShell();
		shell.close();
	});}

}
