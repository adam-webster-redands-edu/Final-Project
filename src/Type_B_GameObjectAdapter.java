import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.LinkedList;

public class Type_B_GameObjectAdapter extends Type_D_GameObject {

	public Type_B_GameObjectAdapter(int x, int y) {
		super(x, y);
		changeImagesForTypeB();
	}

	private void changeImagesForTypeB() {
		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_B_Up.png"));
		imageList.add(new ImageIcon("images/Type_B_Down.png"));
		imageList.add(new ImageIcon("images/Type_B_Left.png"));
		imageList.add(new ImageIcon("images/Type_B_Right.png"));
	}
}
