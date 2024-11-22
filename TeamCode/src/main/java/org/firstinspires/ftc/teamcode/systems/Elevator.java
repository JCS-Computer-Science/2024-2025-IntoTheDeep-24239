package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {
    private int OUT_TICKS = 900;
    private int IN_TICKS = 0;

    private DcMotorEx motor;

    public Elevator(HardwareMap hardwareMap){
        motor=hardwareMap.get(DcMotorEx.class,"elevator");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
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
                        motor.setPower(2.0);
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
               if (position <= 0 && position >= -10000) {
                   motor.setTargetPosition(position);
                   motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                   motor.setPower(1);
               }
                initialized = true;
                telemetryPacket.addLine("moving to position "+position);
            }
            if (this.position != motor.getTargetPosition()) {
                return false;
            }
            return motor.isBusy();
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
