package com.evada.browser.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author dingqin
 * @date 2018年1月11日
 *
 */
public class BrowserCallFromJavascript {

	static Browser browser;

	public static void main(String[] args) throws IOException {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));
		shell.setText("从java调用javascript");
		Button btn = new Button(shell, SWT.None);
		btn.setText("调用javascript");
		btn.addListener(SWT.Selection, evt -> btnListener());
		try {
			browser = new Browser(shell, SWT.BORDER);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return;
		}
		browser.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));

		// //从指定网址获取html，将html加载到浏览器中
		 String url="http://dict.youdao.com/";
		 browser.setText(fetchHtml(url, "utf-8"));
		shell.setSize(1200, 1200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static void btnListener() {
		browser.execute("alert(\"JavaScript, called from Java\");");
	}

	/** 
	 * 抓取某个网页的源代码 
	 *  
	 * @param urlstr 
	 *            要抓取网页的地址 
	 * @param charset 
	 *            网页所使用的编码 如"utf-8","gbk" 
	 * @return 
	 * @throws IOException 
	 */
	public static String fetchHtml(String urlstr, String charset) throws IOException {
		URL url = new URL(urlstr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, charset);
		String result = "";
		int read;
		while ((read = isr.read()) != -1) {
			result += (char) read;
		}
		isr.close();
		return result;
	}

}
