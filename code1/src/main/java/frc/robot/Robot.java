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

  private static final double vel = 0;
  //motores derechos
  WPI_TalonSRX mD1Enc = new WPI_TalonSRX(1);
  WPI_TalonSRX mD2 = new WPI_TalonSRX(2);
  WPI_TalonSRX mD3 = new WPI_TalonSRX(3);

  //motores izquierdos 
  WPI_TalonSRX mI1Enc = new WPI_TalonSRX(4);
  WPI_TalonSRX mI2 = new WPI_TalonSRX(5);
  WPI_TalonSRX mI3 = new WPI_TalonSRX(6);

  //agrupacion de los motores
  MotorControllerGroup MotoresD = new MotorControllerGroup(mD1Enc, mD2, mD3);
  MotorControllerGroup MotoresI = new MotorControllerGroup(mI1Enc, mI2, mI3);

  //diferencia del chasis
  DifferentialDrive cuchao = new DifferentialDrive(MotoresI, MotoresD);

  //control dde xbox
  Joystick CTRL = new Joystick (0);

  //Primer Link
  WPI_TalonSRX Lnk1 = new WPI_TalonSRX(8);
  DigitalInput LSlnk1 = new DigitalInput(8);

  //Segundo Link
  CANSparkMax Lnk2 = new CANSparkMax(7,MotorType.kBrushless);
  DigitalInput LSlnk2 = new DigitalInput(9);

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {

  //Valor del encoder 1
  SmartDashboard.putNumber("Ecdr1", Lnk1.getSelectedSensorPosition());

  //Valo<r del Limit swich 1
  SmartDashboard.putBoolean("Limit1", LSlnk1.get());

  //Valor del encoder 2
  SmartDashboard.putNumber("Ecdr2", Lnk2.get());

  //Valo<r del Limit swich 2
  SmartDashboard.putBoolean("Limit2", LSlnk2.get());

  //Valor del encoder
  SmartDashboard.putNumber("encizq", mI1Enc.getSelectedSensorPosition() / 4096 * Math.PI * 6 * 10 * 2.54 / 100);
  SmartDashboard.putNumber("encder", mD1Enc.getSelectedSensorPosition() / 4096 * Math.PI * 6 * 10 * 2.54 / 100);
  }

  @Override
  public void autonomousInit() { }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

  //Control del chasis
  cuchao.arcadeDrive(CTRL.getRawAxis(1) - CTRL.getRawAxis(2), - CTRL.getRawAxis(3));

  /*if(CTRL.getRawAxis(0)==1){
    velosidad(kDefaultPeriod);
  }

  
  //Movimiento del link1 hacia arriba
  if(CTRL.getRawButton(4)==true){
    positivo();
  }else{
    neutro();
  }

  //Movimiento del link1 hacia abajo
  if(CTRL.getRawButton(5)==true){
    negativo();
  }else{
    neutro();
  }
   
  //Limit
  if(LSlnk1.get() == true){
    neutro();
  }else{
    Na();
   }
   */

   if(CTRL.getRawAxis(0) == vel){
  positivo();
   }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
  
  //Encoders 
    mD1Enc.getSelectedSensorPosition();
    mI1Enc.getSelectedSensorPosition();
    Lnk1.getSelectedSensorPosition();
    LSlnk1.get();
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  //Metodo chidote
  public void velosidad(double vel){
    Lnk1.set(vel);
  
    //(CTRL.getRawAxis(0), - CTRL.getRawAxis(1));
  }

  //metedo hacia arriba
  public void positivo (){
    Lnk1.set(0.5);
  }

  //metodo hacia abajo
  public void negativo (){
    Lnk1.set(-0.5);
  }

  //neutro
  public void neutro (){
    Lnk1.set(0);
  }


  //NA
  public void ChssD (){

  }
}
