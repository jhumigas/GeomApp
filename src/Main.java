import com.geom.MVCControllers.GeneralController;
import com.geom.MVCModel.Model;
import com.geom.MVCviews.GeneralView;
/**
 * The main class runs the whole application
 * <p>It initializes: <p>
 * <ul>
 * <li>the GeneralView, our user interface</li>
 * <li>the model i.e the class that deals with the database</li>
 * <li>the General Controller that manages the interaction between the view and the model.</li>
 * </ul>
 * @author Dasha
 */

public class Main {
	/**
	 * This method initializes the whole application.
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralView view =new GeneralView();
		Model model = new Model();
		new GeneralController(model,view);
	}
}
