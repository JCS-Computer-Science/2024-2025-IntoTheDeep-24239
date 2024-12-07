package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeep {
    public static void main(String[] args) {

        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-20, -60, Math.toRadians(90)))
                //MeepMeep Demo
                //set X position to 0, set Y posistion to 0
//                .lineToX(30)
//                .waitSeconds(1)
//                .lineToX(0)

                //set x -20 or 20, y 60, drg -90
//                .waitSeconds(3)
//                .strafeToLinearHeading(new Vector2d(-60,60),Math.toRadians(-90))


                //set x -20 or 20, y -60, drg 90
//                .waitSeconds(3)
//                .strafeToLinearHeading(new Vector2d(60,-60),Math.toRadians(-90))

//                Plan Name: A
//                set X position to 20, set Y posistion to -60, rotate to 180

                .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(-135))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-48,-40), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-48,-30), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-48,-40), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(-135))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-58,-40), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-58,-30), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-58,-40), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(-135))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-55,-25), Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-62,-25), Math.toRadians(180))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(-135))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(60,-55), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(60,-60), Math.toRadians(90))


//                plan B
//                .strafeToLinearHeading(new Vector2d(20,-40), Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(55,-40), Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(55,-55), Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(36,-30), Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(36,-10), Math.toRadians(270))
//                .strafeToLinearHeading(new Vector2d(45,-10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(45,-55), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(45,-10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(55,-10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(55,-55), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(55,-10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(64,-10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(64,-55), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(34,-55), Math.toRadians(270))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-10,-36), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(30,-60), Math.toRadians(270))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-5,-36), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(32,-60), Math.toRadians(270))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-0,-36), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(36,-60), Math.toRadians(270))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(5,-36), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(60,-60), Math.toRadians(90))

//                .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .turn(Math.toRadians(90))
//                .lineToY(0)
//                .turn(Math.toRadians(90))
                .build());


        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}