package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
//Moteres Derechos
  WPI_TalonSRX mD1Enc = new WPI_TalonSRX (1);
  WPI_TalonSRX mD2 = new WPI_TalonSRX (2);
  WPI_TalonSRX mD3 = new WPI_TalonSRX (3);

//Motores Derechos
  WPI_TalonSRX mI1Enc = new WPI_TalonSRX (4);
  WPI_TalonSRX mI2 = new WPI_TalonSRX (5);
  WPI_TalonSRX mI3 = new WPI_TalonSRX (6);

//Agrupacion de Motores
  MotorControllerGroup MotoresD = new MotorControllerGroup(mD1Enc, mD2, mD3);
  MotorControllerGroup MotoresI = new MotorControllerGroup(mI1Enc, mI2, mI3);

//Diferencial del Chasis 
  DifferentialDrive Elcuchao = new DifferentialDrive (MotoresD, MotoresI);

//Control de Xbox
  Joystick Control1 = new Joystick(0);
  Joystick Control2 = new Joystick(1);

//Motor Lnk1
  WPI_TalonSRX Lnk1 = new WPI_TalonSRX(8);
  DigitalInput LSlnk1 = new DigitalInput(8);  
 
//Segundo Link
  CANSparkMax Lnk2 = new CANSparkMax(7,MotorType.kBrushless);
  DigitalInput LSlnk2 = new DigitalInput(9);

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {

  //===============================================================================================================================//

  //Valor del encoder 1
  SmartDashboard.putNumber("Ecdr1", Lnk1.getSelectedSensorPosition());

  //Valo<r del Limit swich 1
  SmartDashboard.putBoolean("Limit1", LSlnk1.get());

  //Valor del encoder 2
  SmartDashboard.putNumber("Ecdr2", Lnk2.getEncoder().getPosition());

  //Valo<r del Limit swich 2
  SmartDashboard.putBoolean("Limit2", LSlnk2.get());

  //Valor del encoder
  SmartDashboard.putNumber("encizq", mI1Enc.getSelectedSensorPosition() / 4096 * Math.PI * 6 * 10 * 2.54 / 100);
  SmartDashboard.putNumber("encder", mD1Enc.getSelectedSensorPosition() / 4096 * Math.PI * 6 * 10 * 2.54 / 100);

//===============================================================================================================================//
  


  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

//===============================================================================================================================//

//Control de chasis
  Elcuchao.arcadeDrive(Control1.getRawAxis(3) - Control1.getRawAxis(2), -Control1.getRawAxis(0));

//===============================================================================================================================//

/*control de motor del primer link
  Lnk1.set(Control2.getRawAxis(1));
*/

//bloqueo limit primero link
  if (LSlnk1.get() && Control2.getRawAxis(1) < 0.001 && grados1() < 80) {
    Lnk1.set(Control2.getRawAxis(1));

} else if (!LSlnk1.get() && Control2.getRawAxis(1) > 0.001 || grados1() > 80) {
    Lnk1.set(0);
}

/*bloqueo encoder primer link
  if (grados1() < 80) {
  Lnk1.set(Control2.getRawAxis(0));

  } else if (grados1() > 80) {
  Lnk1.set(0);
}*/

//===============================================================================================================================//

/*control de motor del segundo link
  Lnk2.set(Control2.getRawAxis(5));
*/

//bloqueo limit segundo link
 if (LSlnk2.get() && Control2.getRawAxis(5) < 0.001 && grados2() < 80) {
   Lnk2.set(Control2.getRawAxis(5));

 } else if (!LSlnk2.get() && Control2.getRawAxis(5) > 0.001 || grados1() > 80) {
   Lnk2.set(0);
}

/*bloqueo encoder segundo link
  if (grados2() < 80) {
  Lnk2.set(Control2.getRawAxis(1));

  } else if (grados2() > 80) {
  Lnk2.set(0);
}*/


}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

//Metodo de la velosidad del primer link 
  public void Vel_1(double Vel1){
  Lnk1.set(Vel1);
  }  

//Metodo de la velosidad del segundo link 
public void Vel_2(double Vel2){
  Lnk2.set(Vel2);
  }  
  
//grados primer link
  public double grados1(){
    double pulsosSensor1 = Lnk1.getSelectedSensorPosition();
    double VuelRdccn1 = ((pulsosSensor1) / (100));
    double gradoseje1 = (VuelRdccn1 / 4.5714) * (360) ;
    return gradoseje1;
  }

//grados de segundo link
  public double grados2(){
    double pulsosSensor2 = Lnk2.getEncoder().getPosition();
    double VuelRdccn2 = ((pulsosSensor2) / (100));
    double gradoseje2 = (VuelRdccn2 / 3.06667) * (360) ;
    return gradoseje2;
  }
}