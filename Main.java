package me.vrekt.r5674;

import java.io.File;
import java.util.Scanner;

import me.vrekt.r5674.benchmark.Benchmark;
import me.vrekt.r5674.benchmark.Benchmark.Mode;
import me.vrekt.r5674.mode.BFAttack;
import me.vrekt.r5674.mode.DictionaryAttack;

public class Main {

	public static void main(String[] args) {
		System.out.println("R5674: Java password cracker that utilizes the CPU.");
		System.out.println("Version: r-2.0.1a");

		System.out.println("----------------------------------------------------\nPlease choose an option.");
		System.out.println("[A] Basic Benchmark - Test and see how fast your CPU is. ");
		System.out.println("[B] Long Benchmark - See how fast your CPU is when the password is 7 characters (24^7)");
		System.out.println("[C] Brute force attack.");
		System.out.println("[D] Dictionary attack.");
		new Main().getInput();

	}

	public void getInput() {
		System.out.println("-> ");

		Scanner in = new Scanner(System.in);
		String input = in.nextLine();

		switch (input.toLowerCase()) {
		case "a":
			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}

			in.close();
			new Benchmark(Mode.BASIC);
			break;
		case "b":
			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}

			in.close();
			new Benchmark(Mode.LONG);
			break;

		case "c":
			System.out.println("Please enter the desired password: ");
			String password = in.nextLine();

			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < password.length(); i++) {
				builder.append("a");
			}

			in.close();

			// Make things look nice by "clearing" the console.

			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}

			new BFAttack(password, builder.toString());
			break;
		case "d":
			System.out.println("Please enter the desired password: ");
			String pw = in.nextLine();

			System.out.println("Dictionary file location: ");
			String loc = in.nextLine();

			File f = new File(loc);
			if (!f.exists()) {
				System.out.println("File not found.");
				System.exit(-1);
				break;
			}

			in.close();

			// Make things look nice by "clearing" the console.

			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}

			new DictionaryAttack(f, pw);
			break;

		default:
			in.close();
			main(new String[] { "arguments" });
		}

	}

}
