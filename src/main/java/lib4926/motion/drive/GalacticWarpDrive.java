package lib4926.motion.drive;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class GalacticWarpDrive {

  private DifferentialDrive m_drive;

  public GalacticWarpDrive(DifferentialDrive drive) {
    this.m_drive = drive;
    drive.setSafetyEnabled(false);
  }

  private double slewRate = 0.9;
  private SlewRateLimiter filter = new SlewRateLimiter(slewRate);
  private boolean enableSlewLimiting = false;

  public void resetSlewLimiter() {
    filter = new SlewRateLimiter(slewRate);
  }

  public void enableSlewLimiting(double slewLimit) {
    enableSlewLimiting = true;
    this.slewRate = slewLimit;
  }

  public void disableSlewLimiting() {
    enableSlewLimiting = false;
  }

  public void setSlewLimit(double slewLimit) {
    this.slewRate = slewLimit;
  }

  public void arcadeDrive(double forward, double rotation) {

    m_drive.feed();
    if (enableSlewLimiting) {
      forward = filter.calculate(forward);
    }
    m_drive.arcadeDrive(forward, rotation);

  }

  public void cheesyDrive(double forward, double rotation, boolean quickTurn) {
    m_drive.feed();

    if (enableSlewLimiting) {
      forward = filter.calculate(forward);
    }

    forward = .5 * forward + .5 * Math.pow(forward, 3);

    m_drive.curvatureDrive(forward, rotation, quickTurn);

  }

  public void etherDrive(double forward, double rotation) {
    m_drive.feed();

    // if(limitSlew) {
    // forward = filter.calculate(forward);
    // }
    forward = .5 * forward + .5 * Math.pow(forward, 3);
    rotation = .5 * rotation + .5 * Math.pow(rotation, 3);

    if (enableSlewLimiting) {
      forward = filter.calculate(forward);
      rotation = filter.calculate(rotation);
    }

    m_drive.arcadeDrive(forward, rotation, false);

  }

}
