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
 * @date 2018年1月11日
 *
 */
public class BrowserCallJavascriptFromJava {

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
		// browser.setText (createHTML ());
		File file = new File(System.getProperty("user.dir")+"\\html\\map.html");
		System.out.println(BrowserCallJavascriptFromJava.class.getResource(System.getProperty("user.dir")+"\\html"));
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
			System.out.println("theJavaFunction() called from javascript with args:");
			for (int i = 0; i < arguments.length; i++) {
				Object arg = arguments[i];
				if (arg == null) {
					System.out.println("\t-->null");
				} else {
					System.out.println("\t-->" + arg.getClass().getName() + ": " + arg.toString());
				}
			}
			Object returnValue = new Object[] { new Short((short) 3), new Boolean(true), null,
					new Object[] { "a string", new Boolean(false) }, "hi", new Float(2.0f / 3.0f), };
			// int z = 3 / 0; // uncomment to cause a java error instead
			return returnValue;
		}
	}

	static String createHTML() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<html>\n");
		buffer.append("<head>\n");
		buffer.append("<script language=\"JavaScript\">\n");
		buffer.append("function function1() {\n");
		buffer.append("    var result;\n");
		buffer.append("    try {\n");
		buffer.append("        result = theJavaFunction(12, false, null, [3.6, ['swt', true]], 'eclipse');\n");
		buffer.append("    } catch (e) {\n");
		buffer.append("        alert('a java error occurred: ' + e.message);\n");
		buffer.append("        return;\n");
		buffer.append("    }\n");
		buffer.append("    for (var i = 0; i < result.length; i++) {\n");
		buffer.append("        alert('returned ' + i + ': ' + result[i]);\n");
		buffer.append("    }\n");
		buffer.append("}\n");
		buffer.append("</script>\n");
		buffer.append("</head>\n");
		buffer.append("<body>\n");
		buffer.append("<input id=button type=\"button\" value=\"调用java方法\" onclick=\"function1();\">\n");
		buffer.append("<p><a href=\"http://www.eclipse.org\">go to eclipse.org</a>\n");
		buffer.append("</body>\n");
		buffer.append("</html>\n");
		return buffer.toString();
	}
}
