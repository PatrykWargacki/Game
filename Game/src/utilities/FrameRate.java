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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (delta ^ (delta >>> 32));
		result = prime * result + fps;
		result = prime * result + frameCount;
		result = prime * result + (int) (lastTime ^ (lastTime >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FrameRate)) {
			return false;
		}
		FrameRate other = (FrameRate) obj;
		if (delta != other.delta) {
			return false;
		}
		if (fps != other.fps) {
			return false;
		}
		if (frameCount != other.frameCount) {
			return false;
		}
		if (lastTime != other.lastTime) {
			return false;
		}
		return true;
	}

}