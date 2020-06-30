import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;


public class event extends MouseAdapter {
	private VueLampe vl;
	private Rectangle rc;

	public event(VueLampe vue,Rectangle rct) {
		this.vl = vue;
		this.rc = rct;
	}

	public void mouseClicked(MouseEvent evt) {
		if (SwingUtilities.isLeftMouseButton(evt)) {
			
			if (rc.contains(evt.getPoint())) {
				System.out.println(vl.getinterup().idfrominterup(vl.getlamp()));
				vl.getinterup().setchange(vl.getlamp().isalumer());
				vl.repaint();
			}
		}
          
		if (SwingUtilities.isRightMouseButton(evt)) 
          {
			if (rc.contains(evt.getPoint())) {
				System.out.println(vl.getinterup().idfrominterup(vl.getlamp()));
				vl.getinterup().setoff();
				vl.repaint();
			}
		}
	}
}

