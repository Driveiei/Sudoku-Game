package enummer;

public class GridFactory {
	private static GridFactory instance = null;

	public static GridFactory getInstance() {
		if (instance == null)
			instance = new GridFactory();
		return instance;
	}
	
	public GridType[] getUnitTypes() {
		return GridType.values();
	}
	
	public Grid<?>[] getUnits(GridType unitType) {
		switch (unitType) {
		case GridThreeSquare:
			return GridThreeSquare.values();
		case GridFourSquare:
			return GridFourSquare.values();
		default:
			return null;
		}
	}
}
