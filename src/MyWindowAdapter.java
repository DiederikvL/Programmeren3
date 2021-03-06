// ===========================\\
// Class MyWindowAdapter.java \\
// Diederik van Linden        \\
// TI1A                       \\
// 08/03/2019                 \\
//============================\\

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter {
    Frame f;
    MyWindowAdapter(Frame f) {
        this.f = f;
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();

        System.exit(0);
    }
}

