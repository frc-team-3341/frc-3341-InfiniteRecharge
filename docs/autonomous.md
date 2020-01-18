# Autonomous Software Documentation

## Requirements

During the first 15 seconds, we have three pathways we plan to put the robot through, depending on the starting position of the robot. The pathways are outlined below:

Path 1: The robot will start on the sideline, next to our alliance robot who will be in front of a goal. The robot will then move to where the alliance robot is after the alliance robot scores, and we will score after our alliance does, and then we plan to cross the auto line.

Path 2: The robot will start in front of the goal, score, cross the autoline, and look for balls in order to get ready to score in tele-op.

Path 3: The robot will start elsewhere, not near the goal or at the goal, and the robot will just cross the auto line and look for balls.

## Procedure

Path 1: The robot will wait for an inputed amount (through driver station) before it displaces the position of the alliance robot. After it is at the location of the alliance robot, it will turn and score with it's current amount of balls. After, it will turn again and cross the line. We plan to use encoders for movement and gyros for the turning. It will score using the talons on the dispenser.

Path 2: The robot will start in front of the goal, oriented towards the goal, and it will start by dispensing the balls (using the talon SRX). Then it will turn using the gyro, and after, the robot will cross the auto line using encoders. After driving a certain amount of distance (into the vicinity of the balls), the robot driving will be taken over by limelight so that we can place the robot in front of a ball.

Path 3: The robot will start oriented toward the auto line. It's first task will be to drive forward to cross the auto line using encoders, and after driving a certain distance (into the general area of the balls), we will use the limelight to track a ball so that the robot can drive toward it.
## Timeline/Details

Again this a sample, but this is what it would like for the same example as the last:

- [x] **1/7/2020** - Create the autnomous pathways.
- [x] **1/9/2020** - Download the new software from ctre
- [ ] **1/15/2020** - start implementing code.
