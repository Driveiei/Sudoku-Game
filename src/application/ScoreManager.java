package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import strategy.Mode;

public class ScoreManager {

	private String name;
	private String time;
	private String symbol;
	private List<Score> listScore;
	
	public ScoreManager() {
		
	}

	public ScoreManager(String name, String time) {
		this.name = name;
		this.time = time;
	}

	public List<Score> readScore() {
		String filename = "score/score.md";
		ClassLoader loader = ScoreManager.class.getClassLoader();
		InputStream in = null;
		Scanner readText;
		in = loader.getResourceAsStream(filename);
		InputStreamReader reader = new InputStreamReader(in);
		readText = new Scanner(in);
		Score save ;
		listScore = new ArrayList<Score>();
		while (readText.hasNextLine()) {
			String score = readText.nextLine();
			System.out.println(score);
			if (symbol.equals("+")) {
				if (score.startsWith("@") || score.startsWith("&") || score.startsWith("*")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("@")) {
				if (score.startsWith("#") || score.startsWith("&") || score.startsWith("*")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(":")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("&")) {
				if (score.startsWith("#") || score.startsWith("@") || score.startsWith("*")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else {
				if (score.startsWith("#") || score.startsWith("@") || score.startsWith("&")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			}
		}
		if (in != null)
			try {
				in.close();
			} catch (IOException ioe) {
			}
		return listScore;
	}

	public void recordScore() {
		// if jar file(score.txt)
		String outputfile = "src/score/score.md";
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputfile, true);
		} catch (FileNotFoundException ex) {
			System.out.println("Couldn't open output file " + outputfile);
			return;
		}
		PrintStream printOut = new PrintStream(out);
		if (Mode.getInstance().getSize() == 3) {
			if (Mode.getInstance().getClass().getName().equals("strategy.EasyStrategy")) {
				printOut.printf("+%s , %s\n", name, time);
				this.symbol = "#";
			}

			if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("@%s , %s\n", name, time);
				this.symbol = "@";
			}
		}
		if (Mode.getInstance().getSize() == 4) {
			if (Mode.getInstance().getClass().getName().equals("strategy.EasyStrategy")) {
				printOut.printf("&%s , %s\n", name, time);
				this.symbol = "&";
			}
			if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("*%s , %s\n", name, time);
				this.symbol = "*";
			}
		}

		printOut.close();

	}

	public String getsymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
