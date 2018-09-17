package testing;

import java.awt.Dimension;
import java.awt.Toolkit;

public enum Screen {
	
	MAX_HEIGHT(getScreen().getHeight()),
	MAX_WIDTH(getScreen().getWidth()),
	MIN_HEIGHT(MAX_HEIGHT.get() / 2),
	MIN_WIDTH(MAX_WIDTH.get() / 2),
	DEFAULT_HEIGHT((MAX_HEIGHT.get() + MIN_HEIGHT.get()) / 2),
	DEFAULT_WIDTH((MAX_WIDTH.get() + MIN_WIDTH.get()) / 2);
	
	private double size;
	
	Screen(double size){
		this.size = size;
	}
	
	public double get() {
		return size;
	}
	
	private static Dimension getScreen() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
}
