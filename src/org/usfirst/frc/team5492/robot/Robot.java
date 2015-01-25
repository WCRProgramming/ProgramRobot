
package org.usfirst.frc.team5492.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.*;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;
    Joystick stick;
    Joystick stick2;
    Gyro gyro;
    PowerDistributionPanel pdp;
    static double Kp = 0.03;

    public Robot() {

        myRobot = new RobotDrive(0, 1, 2, 3);
        myRobot.setExpiration(0.1);
        stick = new Joystick(0);
        stick2 = new Joystick(1);
        gyro = new Gyro(0); 
        gyro.setSensitivity(.013);
        pdp = new PowerDistributionPanel();
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
        /*myRobot.setSafetyEnabled(false);
        myRobot.drive(-0.5, 0.0);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        myRobot.drive(0.0, 0.0);	// stop robot
        */
    	/*gyro.reset();
    	while(isAutonomous()){
    		double angle = gyro.getAngle();
        	myRobot.drive(0, -angle *  Kp);	
        	Timer.delay(.004);
    	}
    	myRobot.drive(0.0, 0.0);
    	*/
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
    	myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	double x = stick2.getRawAxis(0);
        	double y = stick2.getRawAxis(1);
        	double rot = stick.getRawAxis(0);
        	SmartDashboard.putNumber("x1", x);
            SmartDashboard.putNumber("y1", y);
            SmartDashboard.putNumber("rot1", rot);
        	if(Math.abs(x) < .2)
        		x = 0;
        	if(Math.abs(y) < .2)
        		y = 0;
        	if(Math.abs(rot) < .2)
        		rot = 0;
        	myRobot.mecanumDrive_Cartesian(x*-1, y*-1,rot*-1, gyro.getAngle());
            double current1 = pdp.getCurrent(0);
            double current2 = pdp.getCurrent(13);
            double current3 = pdp.getCurrent(15);
            double current4 = pdp.getCurrent(12);
            SmartDashboard.putNumber("Front Left current", current1);
            SmartDashboard.putNumber("back Left current", current2);
            SmartDashboard.putNumber("Front right current", current3);
            SmartDashboard.putNumber("back right current", current4);
            SmartDashboard.putNumber("x", x);
            SmartDashboard.putNumber("y", y);
            SmartDashboard.putNumber("rot", rot);
            SmartDashboard.putNumber("Gyro",  gyro.getAngle());
        	Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
