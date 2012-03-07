package main;

public class Main {

	public static void main(String args[]) {
		Datenhaltung.recoverSavedState();
		graphic.MainFrame frame = new graphic.MainFrame();
		frame.setVisible(true);
	}

}
