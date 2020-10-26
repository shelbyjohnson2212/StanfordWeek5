import acm.graphics.*;
import acm.util.*;
import acm.program.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.*;


public class BoxDiagram extends GraphicsProgram{
	
//	initializing the program 
	public void init() {
		contents = new HashMap<String,GObject>();
		createController();
		addActionListeners();
		addMouseListeners();
	}
	
	//creates control strip at bottom of the page
private void createController() {
	nameField = new JTextField(MAX_NAME);
	nameField.addActionListener(this);
	addButton = new JButton("Add");
	removeButton = new JButton("Remove");
	clearButton = new JButton("Clear");
	add(new JLabel ("Name"), SOUTH);
	add(nameField, SOUTH);
	add(addButton, SOUTH);
	add(removeButton, SOUTH);
	add(clearButton, SOUTH);
	}

//adds a box with name in the center of the window 
private void addBox(String name) {
	GCompound box = new GCompound();
	GRect outline = new GRect (BOX_WIDTH, BOX_HEIGHT);
	GLabel label = new GLabel(name);
	box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
	box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
	add(box, getWidth() / 2, getHeight() / 2);
	contents.put(name, box);
	}

// removes the box 
private void removeBox(String name) {
	GObject obj = contents.get(name);
	if (obj != null) {
		remove(obj);
	
		}
	}

//removes all the boxes
private void removeContents() {
	Iterator <String> iterator = contents.keySet().iterator();
	while (iterator.hasNext()) {
		removeBox(iterator.next());
		}
	contents.clear();	//clears the hashmap
	}

// called in response to the actions 
public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();
	if (source == addButton || source == nameField) {
		addBox(nameField.getText());
	} else if(source == removeButton) {
		removeBox(nameField.getText());
	} else if(source == clearButton) {
		removeContents();
		}
	}

//records the mouse click 
public void mousePressed(MouseEvent e) {
	last = new GPoint(e.getPoint());
	currentObject = getElementAt(last);
	}

//mouse drags to an item 
public void mouseDragged(MouseEvent e) {
	if(currentObject != null) {
		currentObject.move(e.getX() - last.getX(), 
							e.getY() - last.getY());
		last = new GPoint(e.getPoint());
		}
	}

// mouse clicked to move the object to the front
public void mouseClicked(MouseEvent e) {
	if(currentObject != null) currentObject.sendToFront();
	}

// constants
private static final int MAX_NAME = 25;
private static final double BOX_WIDTH = 120;
private static final double BOX_HEIGHT = 50;

//instances and variables 
private HashMap <String, GObject> contents;
private JTextField nameField;
private JButton addButton;
private JButton removeButton;
private JButton clearButton;
private GObject currentObject;
private GPoint last;


}

