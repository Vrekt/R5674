package me.vrekt.r5674.mode;

public class BFAttack implements Runnable {

	private String prePassword;

	private long startTime;

	private long attempts;

	private String start;

	public BFAttack(String password, String start) {

		this.prePassword = password;
		this.start = start;

		Thread thr = new Thread(this);
		thr.start();

	}

	private String next(String s) {

		int length = s.length();
		char c = s.charAt(length - 1);

		String i = s.substring(0, length - 1);
		return c == 'z' ? length > 1 ? next(i) + 'a' : "aa" : i + ++c;

	}

	private boolean result(String str) {
		return str.equals(prePassword);
	}

	@Override
	public void run() {
		boolean cc = false;

		System.out.println("Attack started.");
		startTime = System.currentTimeMillis();

		cc = result(start);

		while (!cc) {

			// Increment then check.
			attempts++;
			start = next(start);
			cc = result(start);
		}

		// finally, results.

		long time = System.currentTimeMillis() - startTime;

		System.out.println("Attack finished. Password found: " + start);
		System.out.println("Attack took a total of " + attempts + " attempts.");
		System.out.println("Finished in " + time + "ms (" + time / 1000 / 60 + " minutes)");

	}

}
