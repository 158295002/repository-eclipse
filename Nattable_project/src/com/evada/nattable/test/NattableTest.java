package com.evada.nattable.test;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.sourceforge.nattable.NatTable;


public class NattableTest extends Composite{

	public NattableTest(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		this.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		this.setLayoutData(data);
		Browser browser = new Browser(this, SWT.BORDER);
		browser.setText("°Ù¶ÈËÑË÷");
		browser.setUrl("http://www.baidu.com");
		browser.setLayoutData(data);
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 1;
//		shell.setLayout(layout);
		GridLayoutFactory.fillDefaults().applyTo(shell);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(shell);
		NatTable table = new NatTable(shell);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
//		NattableTest test = new NattableTest(shell, SWT.NONE);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
	
}
