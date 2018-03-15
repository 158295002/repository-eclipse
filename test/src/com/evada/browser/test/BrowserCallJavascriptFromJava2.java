package com.evada.browser.test;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author dingqin
 * 2018年3月2日
 *
 */
public class BrowserCallJavascriptFromJava2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setBounds(10, 10, 300, 200);

		final Browser browser;
		try {
			browser = new Browser(shell, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}
		File file = new File(System.getProperty("user.dir")+"\\html\\browserInfo.html");
		browser.setUrl(file.toURI().toString());

		final BrowserFunction function = new CustomFunction(browser, "theJavaFunction");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	static class CustomFunction extends BrowserFunction {
		CustomFunction(Browser browser, String name) {
			super(browser, name);
		}

		@Override
		public Object function(Object[] arguments) {
			String[] strs = new String[arguments.length];
			for (int i = 0; i < arguments.length; i++) {
				strs[i] = (String) arguments[i];
			}
			try {
				double version = Double.valueOf(strs[0]);
				if (version <10) {
					return String.format("%s:%s\t版本过低无法打开", strs[1],strs[0]);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			getBrowser().setUrl("www.baidu.com");
			return null;

		}
	}

	
}
