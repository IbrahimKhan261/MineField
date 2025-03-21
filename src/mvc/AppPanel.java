package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

	protected Model model;
	protected AppFactory factory;
	protected Set<View> views;
	private JFrame frame;
	public static int FRAME_WIDTH = 700; // Increased from 500 to 700
	public static int FRAME_HEIGHT = 700; // Increased from 300 to 700

	public AppPanel(AppFactory factory) {
		super();
		this.factory = factory;
		model = factory.makeModel();
		views = new HashSet<View>();
		if (model != null) model.addPropertyChangeListener(this);

		frame = new JFrame();
		Container cp = frame.getContentPane();
		cp.add(this);
		frame.setJMenuBar(createMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(factory.getTitle());
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	public void addView(View view) { views.add(view); }

	@Override
	public Component add(Component c) {
		if ( c instanceof View ) addView((View)c);
		return super.add(c);
	}

	public void display() { frame.setVisible(true); }

	//
	public void propertyChange(PropertyChangeEvent evt) {
	}

	public Model getModel() { return model; }

	public void setModel(Model newModel) {
		this.model.removePropertyChangeListener(this);
		this.model = newModel;
		this.model.initSupport();
		this.model.addPropertyChangeListener(this);
		for(View view: views) view.setModel(this.model);
	}

	protected JMenuBar createMenuBar() {
		JMenuBar result = new JMenuBar();
		JMenu fileMenu =
				Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
		result.add(fileMenu);

		JMenu editMenu =
				Utilities.makeMenu("Edit", factory.getEditCommands(), this);
		result.add(editMenu);

		JMenu helpMenu =
				Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
		result.add(helpMenu);

		return result;
	}

	public void actionPerformed(ActionEvent ae) {
		String cmmd = ae.getActionCommand();

		if (cmmd == "Save") {
			Utilities.save(model, false);
		} else if (cmmd == "SaveAs") {
			Utilities.save(model, true);
		} else if (cmmd == "Open") {
			Model newModel = Utilities.open(model);
			if (newModel != null) setModel(newModel);
		} else if (cmmd == "New") {
			Utilities.saveChanges(model);
			setModel(factory.makeModel());
			// needed cuz setModel sets to true:
			model.setUnsavedChanges(false);
		} else if (cmmd == "Quit") {
			Utilities.saveChanges(model);
			System.exit(1);
		} else if (cmmd == "About") {
			Utilities.inform(factory.about());
		} else if (cmmd == "Help") {
			Utilities.inform(factory.getHelp());
		} else {
			Command command = factory.makeEditCommand(model, cmmd);
			CommandProcessor.execute(command);
		}
	}
}
