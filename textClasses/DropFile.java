package textClasses;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class DropFile  implements DropTargetListener {
	DropTarget dt;
	RSyntaxTextArea ta=Editor.area;
	public DropFile() {     dt = new DropTarget(ta, this);  }

	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("Drag Enter"); }

	public void dragExit(DropTargetEvent dte) {
		System.out.println("Drag Exit");  }

	public void dragOver(DropTargetDragEvent dtde) {
		System.out.println("Drag Over");  }

	public void dropActionChanged(DropTargetDragEvent dtde) {
		System.out.println("Drop Action Changed");  }

	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				System.out.println("Possible flavor: " + flavors[i].getMimeType());
				if (flavors[i].isFlavorJavaFileListType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					java.util.List list = (java.util.List)tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						Editor.dropfile+=list.get(j); }

					ActionPerform perobj=new ActionPerform();
					ActionPerform.cdnd=true;
					perobj.openFile();
					ActionPerform.cdnd=false;
					Editor.dropfile="";
					dtde.dropComplete(true);
					return;
				}
						}
			System.out.println("Drop failed: " + dtde);
			dtde.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			dtde.rejectDrop();
		}	}
} 