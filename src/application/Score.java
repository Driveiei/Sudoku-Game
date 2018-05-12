package application;

public class Score {
	
	private String name;
	private String time;

	public Score(String name, String time) {
		this.name = name.substring(1);
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return String.format("%-6s%20s", name, time);
	}
}
