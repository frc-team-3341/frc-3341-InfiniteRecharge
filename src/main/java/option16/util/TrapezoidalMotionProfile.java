package option16.util;

import edu.wpi.first.wpilibj.Timer;

public class TrapezoidalMotionProfile {

    private double accelerationTime;
    private double maxVelocity;
    private double distance;
    private edu.wpi.first.wpilibj.Timer timer;
    private boolean finished;

    /**
     * A constructor for the trapezoidal motion profile class.
     *
     * @param accelerationTime the time taken to accelerate to max velocity (Seconds).
     * @param maxVelocity the max velocity the motor can travel.
     * @param distance the total distance for the motor to travel.
     */
    public TrapezoidalMotionProfile(double accelerationTime, double maxVelocity, double distance){
        finished = false;
        this.accelerationTime = accelerationTime;
        this.maxVelocity = maxVelocity;
        this.distance = distance;
        timer = new Timer();
        timer.start();
    }

    /**
     * Use this to get the next velocities needed.
     *
     * @return The next velocity in the sequence.
     */
    public double getNextVelocity(){
        double currentTime = timer.get();
        if (currentTime < accelerationTime){
            return (timer.get() * Math.tan(calculateTriangleTheta()));
        }
        else if (currentTime > accelerationTime && currentTime < (accelerationTime + calculateMaxVelTime())){
            return maxVelocity;
        }
        else if (currentTime > (accelerationTime + calculateMaxVelTime()) && currentTime < (2 * accelerationTime + calculateMaxVelTime())){
            return ((2 * accelerationTime + calculateMaxVelTime() - currentTime) * Math.tan(calculateTriangleTheta()));
        }
        else{
            finished = true;
        }
        return 0.0;
    }

    /**
     * Checks if the motion profile has been completed.
     *
     * @return A boolean indicating whether or not the profile has been completed.
     */
    public boolean isFinished(){
        return finished;
    }

    /**
     *
     * @return The current running time of the trapezoidal motion profile.
     */
    public double getTime(){
        return timer.get();
    }

    /**
     *
     * @return The area of the triangles on either side of the trapezoid.
     */
    private double calculateTriangleAreas(){
        return (accelerationTime * maxVelocity);
    }

    /**
     *
     * @return The area of the rectangle in the middle of the trapezoid.
     */
    private double calculateRectangleArea(){
        return (distance - calculateTriangleAreas());
    }

    /**
     *
     * @return The time during which the motors will travel at the maximum velocity.
     */
    private double calculateMaxVelTime(){
        return (calculateRectangleArea() / maxVelocity);
    }

    /**
     * Uses the max velocity and the acceleration time to find out the theta
     * of the two triangles on either end.
     *
     * @return The angle of the two triangles, in radians.
     */
    private double calculateTriangleTheta(){
        return Math.atan(maxVelocity / accelerationTime);
    }

}
