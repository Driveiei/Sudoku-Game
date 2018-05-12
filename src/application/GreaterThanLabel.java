package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GridManager;
import strategy.Mode;

public class GreaterThanLabel {

	private Mode mode;

	private final int TWO = 2;
	
	private Image vLeft = new Image("source/v-left.png");
	private Image vUp = new Image("source/v-up.png");
	private Image vDown = new Image("source/v-down.png");
	private Image vRight = new Image("source/v-right.png");


	public GreaterThanLabel(Pane greaterPane) {
		mode = Mode.getInstance();
		int size = mode.getSize();
		int computeSize = size * size * size * (size - 1);
		List<ImageView> horizontal = new ArrayList<>();
		List<ImageView> vertical = new ArrayList<>();

			horizontal.addAll(horizontalSymbols(computeSize,size));
			vertical.addAll(verticalSymbols(computeSize,size));
			

		for (int cursor = 0; cursor < horizontal.size(); cursor++) {
			greaterPane.getChildren().add(horizontal.get(cursor));
			greaterPane.getChildren().add(vertical.get(cursor));
		}
	}

	public List<ImageView> horizontalSymbols(int computeSize,int size) {
		List<ImageView> images = new ArrayList<ImageView>();
		images.addAll(createLabel(computeSize));
		
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		int base = mode.getBase();
		int imageSize = base/size;
		int count = 0;
		
		for (int rowTimes = 0; rowTimes < size; rowTimes++) {
			for (int columnTimes = 0; columnTimes < size; columnTimes++) {
				for (int vertical = 0; vertical < size; vertical++) {
					for (int times = 0; times < size-1; times++) {
						int left = puzzle.get(size*rowTimes+vertical).getList().get(size*columnTimes+times).getNumber();
						int right = puzzle.get(size*rowTimes+vertical).getList().get(size*columnTimes+ times + 1).getNumber();
						if(left<right) {
							images.get(count).setImage(vLeft);
						} else {
							images.get(count).setImage(vRight);
						}
						images.get(count).setFitWidth(imageSize);
						images.get(count).setFitHeight(imageSize);
						images.get(count).setTranslateX(base+times*base+vertical*base*size-imageSize/TWO);
						images.get(count).setTranslateY(base/TWO+(base*columnTimes)+rowTimes*base*size-imageSize/TWO);
						count++;
					}
				}
			}
		}
		return images;
	}

	public List<ImageView> verticalSymbols(int computeSize , int size) {
		List<ImageView> images = new ArrayList<ImageView>();
		images.addAll(createLabel(computeSize));
		
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		int base = mode.getBase();
		int imageSize = base/size;
		int count = 0;
		
		for (int rowTimes = 0; rowTimes < size; rowTimes++) {
			for (int columnTimes = 0; columnTimes < size; columnTimes++) {
				for (int times = 0; times < size - 1; times++) {
					for (int vertical = 0; vertical < size; vertical++) {
						int up = puzzle.get(size*rowTimes+columnTimes).getList().get(vertical+times*size).getNumber();
						int down = puzzle.get(size*rowTimes+columnTimes).getList().get(vertical+times*size+size).getNumber();
						if(up<down) {
							images.get(count).setImage(vUp);
						} else {
							images.get(count).setImage(vDown);
						}
						images.get(count).setFitWidth(imageSize);
						images.get(count).setFitHeight(imageSize);
						images.get(count).setTranslateX(base/TWO+base*vertical+base*size*columnTimes-imageSize/TWO);
						images.get(count).setTranslateY(base+base*times+base*size*rowTimes-imageSize/TWO);
						count++;
					}
				}
			}
		}
		return images;
	}
	
	public List<ImageView> createLabel(int size) {
		List<ImageView> images = new ArrayList<ImageView>();
		for (int times = 0; times < size; times++) {
			images.add(new ImageView());
		}
		return images;
	}
}
