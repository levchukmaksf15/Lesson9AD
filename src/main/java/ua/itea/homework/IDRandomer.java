package ua.itea.homework;

import java.util.List;

public class IDRandomer implements Runnable {
	List<Integer> listOfId;

	public IDRandomer(List<Integer> listOfId) {
		this.listOfId = listOfId;
		new Thread(this).start();
	}

	private int putRandomIdIntoList() {
		int randomInt = (int) (Math.random() * 100);
		listOfId.add(randomInt);
		//System.out.println(randomInt + "   id from randomer");
		return randomInt;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			putRandomIdIntoList();

		}
	}

}
