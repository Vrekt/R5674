package me.vrekt.r5674.benchmark;

public class Benchmark implements Runnable {

	public enum Mode {
		BASIC, LONG
	}

	private Mode mode;

	private String start = "";
	private long attempts;
	private long startTime;

	private String prePassword = "";

	public Benchmark(Mode mode) {
		Thread thr = new Thread(this);
		this.mode = mode;
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
		System.out.println("[*] Setting up....");

		startTime = System.currentTimeMillis();

		boolean cc = false;

		switch (mode) {
		case BASIC:
			start = "aaaaaa";
			prePassword = "zzzzzz";

			System.out.println("Benchmark Started.");
			while (!cc) {
				attempts++;
				start = next(start);
				cc = result(start);
			}

			long time = System.currentTimeMillis() - startTime;

			long score = time / 60;

			System.out.println("Score (lower is better): " + score);
			System.out.println("Benchmark finished. Total time: " + time + "ms (" + time / 1000 / 60 + " minutes)");
			System.out.println("Attempts: " + attempts);
			break;
		case LONG:
			start = "aaaaaaa";
			prePassword = "zzzzzzz";

			System.out.println("Benchmark Started.");
			while (!cc) {
				attempts++;
				start = next(start);
				cc = result(start);
			}

			long timel = System.currentTimeMillis() - startTime;

			long score1 = timel / 60;

			System.out.println("Score (lower is better): " + score1);

			System.out.println("Benchmark finished. Total time: " + timel + "ms (" + timel / 1000 / 60 + " minutes)");
			System.out.println("Attempts: " + attempts);
			break;
		}

	}

}
