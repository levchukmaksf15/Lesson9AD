package ua.itea.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class IDReceiver implements Runnable {

	// private FileWriter writer = null;
	private boolean isIdResived;
	private int id;

	@Override
	public void run() {
		while (true) {
			if (!isIdResived) {
				System.out.println("Waiting...");
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(id + "   writing id into file");
				writeIdIntoFile(id);
				// System.out.println(id);
				isIdResived = false;
			}
		}
	}

	public IDReceiver() {
		isIdResived = false;
		new Thread(this).start();
	}

	public synchronized void receiveId(int id) {
		isIdResived = true;
		this.id = id;
		notify();
	}

	public String writeIdIntoFile(int id) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		FileWriter writer = null;

		builder.append(String.valueOf(id));
		builder.append("           " + LocalDate.now());
		builder.append(" " + LocalTime.now() + "\n");

		try {
			reader = new BufferedReader(new FileReader("result.txt"));

			String row = null;
			while ((row = reader.readLine()) != null) {
				builder.append(row + "\n");
			}

			writer = new FileWriter("result.txt");
			writer.write(builder.toString());
			// System.out.println("write into file");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		isIdResived = false;

		return builder.toString();
	}
}
