package main;

public class Main {

	/**
	 * startet das MainFrame und ruft die Datenhaltung auf
	 * @param args wird immer ignoriert
	 */
	public static void main(String args[]) {
		Datenhaltung.recoverSavedState();
		graphic.MainFrame frame = new graphic.MainFrame();
	}
}
