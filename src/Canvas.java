import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Canvas extends JComponent implements ActionListener, KeyListener {
	private JFrame frame;
	private Timer gameLoopTimer;
	private List<GameObject> gameObjectList;
	private GameObject selectedGameObject;

	public Canvas() {
		gameObjectList = new LinkedList<>();
		frame = new JFrame("Animation Canvas");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();

		setFocusTraversalKeysEnabled(false);
		addKeyListener(this);

		frame.setVisible(true);
	}

	public synchronized void addGameObject(GameObject sprite) {
		gameObjectList.add(sprite);
		sprite.setCanvas(this); // Set the canvas reference in the GameObject

		// Set the first added GameObject as the selected one by default
		if (gameObjectList.size() == 1) {
			selectedGameObject = sprite;
		}
	}

	public synchronized void setSelectedGameObject(GameObject gameObject) {
		selectedGameObject = gameObject;
	}

	public synchronized GameObject getSelectedGameObject() {
		return selectedGameObject;
	}

	public void paint(Graphics g) {
		for (GameObject s : gameObjectList) {
			s.draw(this, g);
		}
	}

	public synchronized void actionPerformed(ActionEvent e) {
		for (GameObject gameObject : gameObjectList) {
			gameObject.move(this);
			gameObject.setImage();
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			int currentIndex = gameObjectList.indexOf(selectedGameObject);
			int nextIndex = (currentIndex + 1) % gameObjectList.size();
			selectedGameObject = gameObjectList.get(nextIndex);
			repaint();
		} else {
			GameObject selectedObject = getSelectedGameObject();
			if (selectedObject != null) {
				selectedObject.keyPressed(e);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		GameObject selectedObject = getSelectedGameObject();
		if (selectedObject != null) {
			selectedObject.keyReleased(e);
		}
	}
}
