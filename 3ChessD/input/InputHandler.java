package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements MouseListener {

    private int x;
    private int y;
    private boolean mouseClicked;

    public InputHandler() {
        this.mouseClicked = false;
    }

    public int getMouseX() {
        return this.x;
    }

    public int getMouseY() {
        return this.y;
    }

    public boolean getMouseClicked() {
        return this.mouseClicked;
    }
    
    public void resetMouseClicked() {
        this.mouseClicked = false;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.x = e.getX();
            this.y = e.getY();
            this.mouseClicked = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
