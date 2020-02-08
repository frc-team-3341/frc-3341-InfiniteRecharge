package option16.util;

public class PID {
	private double kp, ki, kd;
	private double error, sum, slope;
	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	public void update(double newError) {
		this.sum += newError;
		if (this.error != 0) {
			this.slope = newError - this.error;
		}
		this.error = newError;
	}
	public double getPower() {
		return kp * error + ki * sum + kd * slope;
	}
	public void reset() {
		error = sum = slope = 0;
	}
}