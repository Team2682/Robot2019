/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Add your docs here.
 */
public class PIDCorrection {

    double p;

    public PIDCorrection(double pValue) {
        this.p = pValue;
    }

    public double getCorretion(double setPoint, double currentHeading) {
        double error = setPoint - currentHeading;
        double correction = Math.abs(error) * p;
        return correction;
    }

    /**
     * @return the p
     */
    public double getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(double p) {
        this.p = p;
    }

}
