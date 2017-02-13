package utilities;

import java.util.Objects;

import Handler.annotations.Single;

@Single
public class FrameRate {
	
	private long lastTime;
	private long delta;
	private int frameCount;
	private int fps;

	public void initialize() {
		lastTime = System.currentTimeMillis();
	}

	public void calculate() {
		long current = System.currentTimeMillis();
		delta += current - lastTime;
		lastTime = current;
		frameCount++;
		if( delta > 1000 ) {
			delta -= 1000;
			fps = frameCount;
			frameCount = 0;
		}
	}

	public int getFrameRate() {
		return fps;
	}

	@Override
	public int hashCode(){
		return Objects.hash(lastTime, delta, frameCount, fps);
	}
}