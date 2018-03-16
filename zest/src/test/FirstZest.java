package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;

public class FirstZest { 

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("test");
		shell.setLayout(new FillLayout());
		shell.setLocation(300, 300);
		
		Graph graph = new Graph(shell, SWT.NONE);
		
		GraphNode startNode = new GraphNode(graph, SWT.NONE,"start");
		
		for(int index = 0; index <= 16 ; index ++) {
			
			GraphNode node = new GraphNode(graph, SWT.NONE,"node"+index);
			
			new GraphConnection(graph, SWT.NONE, startNode, node);
			
			for(int i = 0; i<4;i++) {

				GraphNode n = new GraphNode(graph, SWT.NONE,"node"+i);
				
				new GraphConnection(graph, SWT.NONE, node, n);
			}
		}

		
		graph.setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);
		
//		graph.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		shell.open();
		while(!shell.isDisposed()) {
			while(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		
	}
	
}