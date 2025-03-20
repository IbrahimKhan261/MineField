package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.*;

public class Model extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;

	private File file;
	private String fileName;
	private boolean unsavedChanges = false;

	private PropertyChangeSupport propertyChangeSupport;

	public Model() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void initSupport() {
		if (propertyChangeSupport == null) {
			propertyChangeSupport = new PropertyChangeSupport(this);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		initSupport();
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.removePropertyChangeListener(listener);
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File newFile) {
		file = newFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isChanged() {
		return unsavedChanges;
	}

	public boolean getUnsavedChanges() {
		return unsavedChanges;
	}

	public void setChanged(boolean changed) {
		this.unsavedChanges = changed;
	}

	public void setUnsavedChanges(boolean value) {
		this.unsavedChanges = value;
	}

	public void setChanged() {
		super.setChanged();
		this.notifyObservers();
		this.unsavedChanges = true;
	}
}