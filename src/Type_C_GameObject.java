import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject {

	public Type_C_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.NONE);

		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_C_Right.png"));
		imageList.add(new ImageIcon("images/Type_C_Left.png"));
	}

	public void move(Canvas c) {
		Icon icon = getCurrentImage();
		int iconWidth = icon.getIconWidth();
		int canvasWidth = (int) c.getSize().getWidth();

		// MOVE PURPLE GAME OBJECT

		// automatic movement
		if (!isSelected()) {
			if (getDirection() == Direction.RIGHT) {
				setX(getX() + getVelocity());
				if (getX() + iconWidth > c.getSize().getWidth()) {
					setX((int) (c.getSize().getWidth() - iconWidth));
					setDirection(Direction.LEFT);
				}

			} else {
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
					setDirection(Direction.RIGHT);
				}
			}
		}
		// user controlled movement
		else {
			switch (getDirection()) {
			case Direction.LEFT:
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
				}
				break;
			case Direction.RIGHT:
				setX(getX() + getVelocity());
				if (getX() + iconWidth > canvasWidth) {
					setX((int) (canvasWidth - iconWidth));
				}
				break;
			default:
				break;
			}
		}
	}

	// SPECIFY THE IMAGE TO DISPLAY
	// USED FOR ANIMATION
	public void setImage() {
		switch (getDirection()) {
		case Direction.NONE:
			break;
		case Direction.LEFT:
			currentImage = 0;
			break;
		case Direction.RIGHT:
			currentImage = 1;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (isSelected() && e.getKeyCode() != KeyEvent.VK_TAB) {
			setDirection(Direction.NONE);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (isSelected()) { // Check if this object is selected
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.RIGHT);
			}
		}
	}
}
