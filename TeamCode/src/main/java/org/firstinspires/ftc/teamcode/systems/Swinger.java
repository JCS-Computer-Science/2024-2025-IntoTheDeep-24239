package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Swinger {
    public int UP_TICKS = -830;
    public int DOWN_TICKS = 105;

    private int ADD_TICKS = 0;

    private int SUBT_TICKS = 0;

    private DcMotorEx motor;

    public Swinger(HardwareMap hardwareMap){
        motor=hardwareMap.get(DcMotorEx.class,"swingarm");
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
    }

    public Action togglePosition(){
        return new Action (){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(motor.getTargetPosition()==UP_TICKS){
                    motor.setPositionPIDFCoefficients(2);
                    motor.setTargetPosition(DOWN_TICKS);
                    motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

                    motor.setPower(0.8);
                }else {
                        motor.setPositionPIDFCoefficients(6);
                        motor.setTargetPosition(UP_TICKS);

                        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                        motor.setPower(0.6);

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
                if (position >= UP_TICKS && position <= DOWN_TICKS) {
                    motor.setTargetPosition(position);
                    motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    motor.setPower(0.7);
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

    public String getPID() {return motor.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION).toString();}
    public Action setPosition(int position){
        return new SetPosistion(position);
    }
}
