package misc;

public class ElapsedTime {
	private long old = System.currentTimeMillis();
	private long last = old;
	
	public ElapsedTime() {
		
	}
	
	public float mark() {
		old = last;
		last = System.currentTimeMillis();
		long dif = last-old;
		float elapsedTime = dif/1000.0f;
		return elapsedTime;
	}
}
