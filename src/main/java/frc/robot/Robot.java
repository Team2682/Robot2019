package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.FourBarLinkageSystem;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.MastSystem;

public class Robot extends TimedRobot {
   public static DriveSystem drive;
   public static IntakeSystem intake;
   public static MastSystem mast;
   public static OI oi;
   public static FourBarLinkageSystem linkage;

   public static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
   public static boolean limeVision = false;

   Command selectedAuto;
   Compressor c;
   SendableChooser autoChooser = new SendableChooser();

   public void robotInit() {
      this.c = new Compressor(13);
      this.c.start();
      mast = new MastSystem();
      intake = new IntakeSystem();
      linkage = new FourBarLinkageSystem();
      oi = new OI();
      drive = new DriveSystem(0, 1);
      drive.initializeMotors();
      SmartDashboard.putData("Auto mode", this.autoChooser);
      UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
      cam.setExposureManual(50);
      cam.setFPS(30);
      cam.setResolution(320, 240);
   }

   public void robotPeriodic() {

      if (!limeVision) {
         limelight.getEntry("ledMode").setNumber(1);
         limelight.getEntry("camMode").setNumber(1);
      } else {
         limelight.getEntry("ledMode").setNumber(3);
         limelight.getEntry("camMode").setNumber(0);
      }
   }

   public void disabledInit() {
   }

   public void disabledPeriodic() {
      Scheduler.getInstance().run();
   }

   public void autonomousInit() {
      drive.initializeMotors();
      this.teleopInit();
   }

   public void autonomousPeriodic() {
      this.teleopPeriodic();
   }

   public void teleopInit() {
      if (this.selectedAuto != null) {
         this.selectedAuto.cancel();
      }

   }

   public void teleopPeriodic() {
      Scheduler.getInstance().run();
   }

   public void testPeriodic() {
   }
}
