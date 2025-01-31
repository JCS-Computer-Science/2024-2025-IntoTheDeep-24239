package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {
    public int OUT_TICKS = 5680;
    public int IN_TICKS = 4657;

    private DcMotorEx motor;
    private DcMotorEx motor2;

    public Elevator(HardwareMap hardwareMap){
        motor=hardwareMap.get(DcMotorEx.class,"elevator");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2=hardwareMap.get(DcMotorEx.class,"elevator2");
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setTargetPosition(0);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public Action togglePosition(){
        return new Action (){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(motor.getTargetPosition()<=OUT_TICKS){
                    motor.setTargetPosition(IN_TICKS);
                    motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    motor.setPower(0.4);
                }else{
                    if(motor.getTargetPosition()>=IN_TICKS) {
                        motor.setTargetPosition(OUT_TICKS);
                        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                        motor.setPower(1.0);
                    }
                }
                return false;
            }
        };
    }


    public class SetPosistion implements Action {
        private int position;
        private boolean initialized = false;
        public SetPosistion(int position){
            this.position=position;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!this.initialized) {
               if (position >= 0 && position <= OUT_TICKS) {
                   motor.setTargetPosition(position);
                   motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                   motor.setPower(1);
                   motor2.setTargetPosition(position);
                   motor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                   motor2.setPower(1);
               }
                initialized = true;
                telemetryPacket.addLine("moving to position "+position);
            }
            if (this.position != motor.getTargetPosition()) {
                return false;
            }
            return motor.isBusy() || motor2.isBusy();
        }
    }
    public int getPosition(){
        return motor.getCurrentPosition();
    }

    public int getTargetPosition() {return motor.getTargetPosition();}
    public Action setPosition(int position){
        return new SetPosistion(position);
    }
}
