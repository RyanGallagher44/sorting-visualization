import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingVisualization extends JPanel implements KeyListener {

	JFrame frame;
	int[] arr = new int[1000];
	int x = 0;
	int y = 0;
	int count = 0;
	boolean running = true;
	boolean restart = false;
	boolean sorting = false;
	Font font = new Font("Times New Roman",Font.PLAIN,12);
	
	public SortingVisualization() {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*450)+1;
		}
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 550);
		frame.setVisible(true);
		frame.addKeyListener(this);
	}
	
	public void sort(int[] arr, int count) {
		if(count < arr.length) {
			for(int i = 0; i < arr.length - count - 1; i++) {
				if(arr[i] > arr[i+1]) {
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
			this.count++;
		}
	}
	
	public void draw(Graphics g, int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			g.setColor(Color.RED);
			g.drawLine(this.x, this.y, this.x, arr[i]);
			this.x++;
		}
	}
	
	public void text(Graphics g, String s, int x, int y) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, x, y);
	}
	
	public void paintComponent(Graphics g) {
		if(running) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1000, 550);
			x = 0;
			if(sorting) {
				sort(arr, count);
				draw(g, arr);
			}
			draw(g, arr);
			text(g, "Press the spacebar to reset", 25, 505);
			text(g, "Hold ENTER to sort", 25, 480);
			if(restart) {
				restart();
			}
			repaint();
		}
	}
	
	public void restart() {
		arr = new int[1000];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*450)+1;
		}
		x = 0;
		y = 0;
		count = 0;
		running = true;
		restart = false;
	}
	
	public static void main(String[] args) {
		SortingVisualization app = new SortingVisualization();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sorting = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sorting = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
