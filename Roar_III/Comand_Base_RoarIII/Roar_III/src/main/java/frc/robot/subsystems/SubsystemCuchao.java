package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SubsystemCuchao extends SubsystemBase {

//Moteres Derechos
WPI_TalonSRX mD1Enc = new WPI_TalonSRX (1);
WPI_TalonSRX mD2 = new WPI_TalonSRX (2);
WPI_TalonSRX mD3 = new WPI_TalonSRX (3);

//Motores Derechos
WPI_TalonSRX mI1Enc = new WPI_TalonSRX (4);
WPI_TalonSRX mI2 = new WPI_TalonSRX (5);
WPI_TalonSRX mI3 = new WPI_TalonSRX (6);

//agrupacion de los motores
MotorControllerGroup MotoresD = new MotorControllerGroup(mD1Enc, mD2, mD3);
MotorControllerGroup MotoresI = new MotorControllerGroup(mI1Enc, mI2, mI3);

//diferencia del chasis
DifferentialDrive cuchao = new DifferentialDrive(MotoresI, MotoresD);


  public SubsystemCuchao() {}

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> { });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    return false;
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}

  public void Motores (double VelD, double VelI){
    MotoresD.set(VelD);
    MotoresI.set(VelI);
    
  }
}
