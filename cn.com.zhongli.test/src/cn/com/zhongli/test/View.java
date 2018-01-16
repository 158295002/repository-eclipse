package cn.com.zhongli.test;

import java.util.List;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import cn.com.zhongli.test.extension.ExtensionFactory;
import cn.com.zhongli.test.extension.bean.Student;
/**
 * @author 钟利
 * @data 2012-12-18
 */
public class View extends ViewPart {
	public static final String ID = "cn.com.zhongli.test.view";

	private TreeViewer viewer;
	

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements ITreeContentProvider  {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}
        @Override
		public Object[] getElements(Object parent) {
			if (parent instanceof List) {
				return ((List) parent).toArray();
			}
	        return new Object[0];
		}
		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof cn.com.zhongli.test.extension.bean.Class){
				return ((cn.com.zhongli.test.extension.bean.Class) parentElement).getStu();
			}
			return null;
		}
		@Override
		public Object getParent(Object element) {
			return null;
		}
		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof cn.com.zhongli.test.extension.bean.Class){
				return true;
			}
			return false;
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			if(obj instanceof cn.com.zhongli.test.extension.bean.Class){
				cn.com.zhongli.test.extension.bean.Class cla = (cn.com.zhongli.test.extension.bean.Class)obj;
				switch (index) {
				case 0:
					return cla.getName(); 
				case 1:
					if(cla.getClassInfo()  != null){
						return cla.getClassInfo().student_Count();
					}
				case 2:	
					if(cla.getClassInfo()  != null){
						return  cla.getClassInfo().class_Brief_Introduction();
					}
				}
			}else{
				if(index == 0){
					if(obj instanceof Student)
					return obj.toString();
				}
			}
			return null;
		}

		public Image getColumnImage(Object obj, int index) {
			if(index == 0){
				return getImage(obj);
			}
			return null;
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);
		TreeColumn column1 = new TreeColumn(viewer.getTree(), SWT.NONE);
		column1.setText("班级名称");
		column1.setWidth(380);
		
		TreeColumn column2 = new TreeColumn(viewer.getTree(), SWT.NONE);
		column2.setText("学生人数");
		column2.setWidth(100);
		
		TreeColumn column3 = new TreeColumn(viewer.getTree(), SWT.NONE);
		column3.setText("班级简介");
		column3.setWidth(160);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
	
		viewer.setInput(ExtensionFactory.Default().getTestExtention());
		viewer.expandAll();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}