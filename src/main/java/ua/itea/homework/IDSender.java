package ua.itea.homework;

import java.util.Iterator;
import java.util.List;

public class IDSender implements Runnable {

	IDReceiver receiver;
	List<Integer> listOfId;

	public IDSender(IDReceiver receiver, List<Integer> listOfId) {
		this.receiver = receiver;
		this.listOfId = listOfId;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Iterator<Integer> iterator = null;
			iterator = listOfId.iterator();
			while (iterator.hasNext()) {
				receiver.receiveId(iterator.next());
				iterator.remove();
			}
		}
	}

}
