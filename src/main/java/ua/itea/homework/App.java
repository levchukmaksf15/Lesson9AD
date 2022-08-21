package ua.itea.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		List<Integer> listOfId = new LinkedList<>();
		// FileWriter writer = null;
//		try {
//			writer = new FileWriter("result.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		IDReceiver receiver = new IDReceiver();
		IDRandomer randomer = new IDRandomer(listOfId);
		IDSender sender = new IDSender(receiver, listOfId);
	}
}
