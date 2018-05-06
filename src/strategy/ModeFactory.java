package strategy;


public abstract class ModeFactory {
	
	private static ModeFactory factory = null;
	
	public abstract void randomInvisible();
	
	public static ModeFactory getInstance() {
		if (factory == null)
			factory = new EasyStrategy();
		return factory;
	}
	
	public static void setFactory(String text) {
		if(text.equals("easy"))
		factory = new EasyStrategy();
		else factory = new HardStrategy();
	}
	
	public abstract void setPuzzle() ;
	}
