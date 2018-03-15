package com.evada.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LayoutDataVisible {
	Composite parent = null;
	Composite center = null ;
	boolean visible = false;
	public static void main(String[] args) {
		new LayoutDataVisible().create();
	}
	
	public void create(){
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Visiable");
		shell.setLayout(new FillLayout());
		
		parent = new Composite(shell, SWT.NONE);
		parent.setLayout(new GridLayout(1, false));
		
		Composite top = new Composite(parent, SWT.BORDER);
		top.setLayout(new FillLayout());
		top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Button seleted = new Button(top, SWT.NONE);
		seleted.setText("点击我");
		seleted.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("mouseDown");
				if(center != null && !center.isDisposed()){
					center.setVisible(visible);
					((GridData)center.getLayoutData()).exclude = ! visible;
					center.getParent().layout();
					visible = ! visible;
				}
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
			}
		});
		
		center = new Composite(parent, SWT.BORDER);
		center.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		center.setLayout(new FillLayout());
		Button visiabel = new Button(center, SWT.NONE);
		visiabel.setText("我要隐藏");
		
		Composite bottom = new Composite(parent, SWT.BORDER);
		bottom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		bottom.setLayout(new FillLayout());
		Text lab = new Text(bottom, SWT.V_SCROLL);
		lab.setText("看那sb");
		
		shell.open();
		while(!shell.isDisposed()){
			if(display.readAndDispatch()){
				display.sleep();
			}
		}
		shell.dispose();
		display.dispose();
		
	}
}
