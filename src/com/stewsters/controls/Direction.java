package com.stewsters.controls;

import processing.core.PVector;

public enum Direction {
    N(0f, -1f),
    S(0f, 1f),
    W(-1f, 0f),
    E(1f, 0f),
    none(0f, 0f); //should this be null if we use it?

    public final PVector unitVector;

    Direction(float x, float y) {
        unitVector = new PVector(x, y);
    }
}