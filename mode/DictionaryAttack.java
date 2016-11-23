package me.vrekt.r5674.mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryAttack implements Runnable {

	private File dictionary;
	private String password;

	private long startTime;
	private long attempts;

	public DictionaryAttack(File dictionary, String password) {

		this.dictionary = dictionary;
		this.password = password;

		Thread thr = new Thread(this);
		thr.start();

	}

	private boolean result(String str) {
		return str.equals(password);
	}

	@Override
	public void run() {
		System.out.println("Setting up dictionary attack.");

		boolean cc = false;
		String pw = "";

		startTime = System.currentTimeMillis();

		System.out.println("Starting attack.");
		try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
			String line;
			while ((line = br.readLine()) != null && !cc) {
				pw = line;
				attempts++;
				cc = result(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - startTime;

		System.out.println("Attack finished. Password found: " + pw);
		System.out.println("Attack took a total of " + attempts + " attempts.");
		System.out.println("Finished in " + time + "ms (" + time / 1000 / 60 + " minutes)");

	}

}
