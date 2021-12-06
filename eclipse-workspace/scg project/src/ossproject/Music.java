package ossproject;

import java.io.*;
import javax.swing.JOptionPane;
import javazoom.jl.player.*;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private File file;
	private InputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {

		try {
			this.isLoop = isLoop;
			fis = scg.class.getClassLoader().getResourceAsStream("music/" + name);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error with " + name);
			JOptionPane.showMessageDialog(null,"음악 재생이 되지 않습니다.", "Warning!!",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}