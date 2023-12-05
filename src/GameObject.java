import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import javax.swing.Icon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameObject implements KeyListener {
	private int x;
	private int y;
	private int velocity;
	private int direction;
	protected List<Icon> imageList;
	protected int currentImage;
	protected Canvas canvas;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		velocity = 0;
		currentImage = 0;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void draw(Component c, Graphics g) {
		imageList.get(currentImage).paintIcon(c, g, x, y);
		if (isSelected()) {
			// Add visual indication for selected GameObject

			g.drawRect(x, y, imageList.get(currentImage).getIconWidth(), imageList.get(currentImage).getIconHeight());
		}
	}

	public boolean isSelected() {
		return this.equals(canvas.getSelectedGameObject());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Icon getCurrentImage() {
		return imageList.get(currentImage);
	}

	@Override
	public abstract void keyPressed(KeyEvent e);

	@Override
	public abstract void keyReleased(KeyEvent e);

	public abstract void move(Canvas c);

	public abstract void setImage();
}
