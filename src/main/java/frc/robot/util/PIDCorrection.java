package frc.robot.util;

public class PIDCorrection {
   double p;

   public PIDCorrection(double pValue) {
      this.p = pValue;
   }

   public double getCorretion(double setPoint, double currentHeading) {
      double error = setPoint - currentHeading;
      double correction = Math.abs(error) * this.p;
      return correction;
   }

   public double getP() {
      return this.p;
   }

   public void setP(double p) {
      this.p = p;
   }
}
