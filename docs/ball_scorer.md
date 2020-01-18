# Ball Scorer Software Documentation

## Requirements
Our ball shooter subsystem is attempting to shoot balls during the autonomous and teleop phases of the game. It will be scoring directly into the lower port and can be controlled both autonomously and through teleop. The shooter will score howver many balls are stored in the robot all at once (between 3-5 balls at a time).

## Procedure
The acquirer and the shooter will be controlled by one talon each (the rest of the details of the shooter design remain unknown). Both talons will only spin in one direction (towards the belt or towards the port), and will switch between spinning and stationary when their corresponding buttons on their joystick are pressed. We will be doing software testing to find the best speed to acquire/shoot the balls.

## Timeline/Details

Again this a sample, but this is what it would like for the same example as the last:

- [x] **1/17/2020** - Create the software documentation markdown page.
- [ ] **1/18/2020** - Write and test code to make sure that the talons work when the buttons are pressed.
- [ ] **1/24/2020** - Test talons to find optimal speed for acquiring/shooting.
