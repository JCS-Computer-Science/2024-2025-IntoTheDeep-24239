package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;
import org.firstinspires.ftc.teamcode.systems.Swinger;
import org.firstinspires.ftc.teamcode.systems.Wrist;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TeleOpWithActionsDemo")
public class TeleOpWithActions extends OpMode {
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public GamepadEx driver, operator;
    public MecanumDrive drive;
    public ExampleSystem exampleSystem;
    public Swinger swinger;

    public Wrist wrist;

    public Elevator elevator;

    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        operator=new GamepadEx(gamepad2);
        drive=new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        exampleSystem = new ExampleSystem(hardwareMap);
        swinger = new Swinger(hardwareMap);
        elevator = new Elevator(hardwareMap);
        wrist = new Wrist(hardwareMap);

        runningActions.add(drive.driveAction(driver));
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();
        operator.update();
        packet.put("swingerPosition", swinger.getPosition());
        packet.put("elevatorPosition", elevator.getPosition());

        //add actions as needed here, eg:
        if(operator.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(exampleSystem.setServo(1));
        }

        if(operator.getButton(GamepadEx.Button.B).justPressed){
            runningActions.add(exampleSystem.setServo(0));
        }

        if(operator.getButton(GamepadEx.Button.Y).justPressed) {
            runningActions.add(wrist.setServo(0.3));
        }

        if(operator.getButton(GamepadEx.Button.X).justPressed){
            runningActions.add(wrist.setServo(0));
        }

        if(operator.getButton(GamepadEx.Button.DPAD_UP).isHeld){
            runningActions.add(swinger.setPosition(swinger.getTargetPosition()+5));
        }
        
        if(operator.getButton(GamepadEx.Button.DPAD_DOWN).isHeld){
            runningActions.add(swinger.setPosition(swinger.getTargetPosition()-5));
        }

        if(operator.getButton(GamepadEx.Button.DPAD_RIGHT).isHeld){
            runningActions.add(elevator.setPosition(elevator.getTargetPosition()+100));
        }

        if(operator.getButton(GamepadEx.Button.DPAD_LEFT).isHeld){
            runningActions.add(elevator.setPosition(elevator.getTargetPosition()-100));
        }


        updateActions(packet);
        telemetry.addData("swingerPosition", swinger.getPosition());
        telemetry.addData("swingerTarget", swinger.getTargetPosition());
        telemetry.addData("swingerPID", swinger.getPID());
        telemetry.addData("elevatorPosition", elevator.getPosition());
        telemetry.addData("elevatorTarget", elevator.getTargetPosition());
//        telemetry.addData("elevatorPID", elevator.getPID());
        telemetry.update();

    }

    private void updateActions(TelemetryPacket packet){
        List<Action> newActions = new ArrayList<>();
        for(Action action : runningActions){
            action.preview(packet.fieldOverlay());
            if(action.run(packet)){
                newActions.add(action);
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);
        }
    }
}
