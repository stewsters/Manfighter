package com.stewsters;

import com.stewsters.stuffs.Man;
import com.stewsters.stuffs.Obstacle;
import processing.core.PImage;

import java.util.LinkedHashMap;

public class World {
    public static LinkedHashMap<Integer, Man> dudes;
    public static LinkedHashMap<Integer, Obstacle> obstacles;

    public static int x;
    public static int y;

    public static PImage casualty;
    public static PImage tree;

}
