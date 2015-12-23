import com.geom.MVCControllers.GeneralController;
import com.geom.MVCModel.Model;
import com.geom.MVCviews.GeneralView;

public class Main {
	public static void main(String[] args) {
		GeneralView view =new GeneralView();
		Model model = new Model();
		new GeneralController(model,view);
	}
}
