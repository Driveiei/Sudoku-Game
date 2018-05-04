package enummer;

public enum GridThreeSquare implements Grid<Object> {

		One(0,0),TWO(1,0),Three(2,0),Four(0,1);

		private final int column;
		private final int row;

		
		private GridThreeSquare(int column, int row) {
			this.column = column;
			this.row = row;
		}

		@Override
		public int getRow() {
			return row;
		}

		@Override
		public int getColumn() {
			return column;
		}

	
	}


