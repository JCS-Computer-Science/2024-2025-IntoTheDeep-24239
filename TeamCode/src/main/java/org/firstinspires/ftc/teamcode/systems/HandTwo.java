package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HandTwo {


    private Servo servo;

    public HandTwo(HardwareMap hardwareMap){
        servo=hardwareMap.get(Servo.class,"handTwo");
    }


    public class SetServo implements Action {
        private double position;
        private boolean initialized = false;
        public SetServo(double position){
            this.position=position;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!this.initialized){
                servo.setPosition(position);

                initialized=true;
            }
            return false;
        }
    }

    public Action setServo(double position){
        return new SetServo(position);
    }
}
