package mvc;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class View extends JFrame implements Observer, ActionListener {
	protected Model model;
	protected Controller controller;
	protected JMenuBar menuBar;

	public View(Model m, Controller c, ViewPanel p) {
		model = m;
		model.addObserver(this);
		controller = c;
		if (p != null) {
			p.setModel(model);
			p.setController(controller);
			add(p);
		}
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
	}

	public void setModel(Model newModel) {
		if (this.model != null) {
			this.model.deleteObserver(this);
		}
		this.model = newModel;
		if (this.model != null) {
			this.model.addObserver(this);
		}
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void addMenu(String name, String[] items) {
		JMenu result;
		int j = name.indexOf('&');
		if (j != -1) {
			char c = name.charAt(j + 1);
			String s = name.substring(0, j) + name.substring(j + 1);
			result = new JMenu(s);
			result.setMnemonic(c);
		} else {
			result = new JMenu(name);
		}
		for(int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				result.addSeparator();
			} else {
				j = items[i].indexOf('&');
				JMenuItem item;
				if (j != -1) {
					char c = items[i].charAt(j + 1);
					String s = items[i].substring(0, j) +
							items[i].substring(j + 1);
					item = new JMenuItem(s, items[i].charAt(j + 1));
					item.setAccelerator(
							KeyStroke.getKeyStroke(c, InputEvent.CTRL_MASK));
				} else { // no accelerator or shortcut key
					item = new JMenuItem(items[i]);
				}
				item.addActionListener(this);
				result.add(item);
			}
		}
		menuBar.add(result);
	}

	public static void error(String error) {
		JOptionPane.showMessageDialog(null,
				error,
				"OOPS!",
				JOptionPane.ERROR_MESSAGE);
	}

	public void update(Observable subject, Object msg) {
		// stub
	}

	public void actionPerformed(ActionEvent ae) {
		String cmmd = ae.getActionCommand();
		try {
			controller.execute(cmmd);
		} catch(Exception e) {
			View.error(e.getMessage());
		}
	}
}