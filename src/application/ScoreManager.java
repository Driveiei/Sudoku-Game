package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import logic.RandomNumber;
import logic.Table;
import strategy.Mode;

public class ScoreManager {

	private static ScoreManager score = null;

	private static String symbol;

	private List<Score> listScore;

	private ScoreManager() {
		listScore = new ArrayList<Score>();
	}

	public static ScoreManager getInstance() {
		if(score == null) {
			symbol = "+";
			score = new ScoreManager();
		}
		return score;
	}

	public static void setSymbol(String symbol) {
		ScoreManager.symbol = symbol;
		score = new ScoreManager();
	}

	public String getsymbol() {
		return symbol;
	}

	public void sortTime(List<Score> list) {
		Collections.sort(list, new Comparator<Score>() {
			@Override
			public int compare(Score time1, Score time2) {
				return time1.getTime().compareTo(time2.getTime());
			}

		});
	}

	
	
	public List<Score> readScore() throws FileNotFoundException  {
		String path = System.getProperty("user.dir");
		String filename = path+"score.md";
		InputStream in = new FileInputStream(filename);
		Scanner readText = new Scanner(in);
		Score save;
		while (readText.hasNextLine()) {
			String score = readText.nextLine();
			if (symbol.equals("+")) {
				if (score.startsWith("@") || score.startsWith("&") || score.startsWith("*") || score.startsWith("?")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("@")) {
				if (score.startsWith("+") || score.startsWith("&") || score.startsWith("*") || score.startsWith("?")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("&")) {
				if (score.startsWith("+") || score.startsWith("@") || score.startsWith("*") || score.startsWith("?")) {
					continue;
				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("*")) {
				if (score.startsWith("+") || score.startsWith("@") || score.startsWith("&") || score.startsWith("?")) {
					continue;

				}
				String name = score.split(",")[0].trim();
				String time = score.split(",")[1].trim();
				save = new Score(name, time);
				listScore.add(save);
			} else if (symbol.equals("?")) {
				if (score.startsWith("+") || score.startsWith("@") || score.startsWith("&") || score.startsWith("*")) {
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

	public void recordScore(String name,String time) {
		String path = System.getProperty("user.dir");
		String outputfile = path+"score.md";
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
				symbol = "+";
			}

			else if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("@%s , %s\n", name, time);
				symbol = "@";
			}

			else if (Mode.getInstance().getClass().getName().equals("strategy.GreaterThanStrategy")) {
				printOut.printf("?%s , %s\n", name, time);
				symbol = "?";
			}
		}
		else if (Mode.getInstance().getSize() == 4) {
			if (Mode.getInstance().getClass().getName().equals("strategy.EasyStrategy")) {
				printOut.printf("&%s , %s\n", name, time);
				symbol = "&";
			}
			else if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("*%s , %s\n", name, time);
				symbol = "*";
			}
		}
		printOut.close();

	}
}
