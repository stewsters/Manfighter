package com.stewsters;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

public class ManFight extends PApplet
{

	private static final long serialVersionUID = -6056361700394447127L;

	public void setup()
	{
		World.x = 500;
		World.y = 500;

		this.setSize(World.x, World.y);
		this.scale(10);

		
		// smooth();
		World.dudes = Collections.synchronizedList(new ArrayList<Man>());

		Random r = new Random();
		for (int i = 0; i < 1000; i++)
		{
			Color c;
			PVector starting;
			if (i % 2 == 0)
			{
				c = new Color(255, 0, 0);
				starting = new PVector(r.nextInt(World.x/4), r.nextInt(World.y));
			}
			else
			{
				c = new Color(0, 0, 255);
				starting = new PVector(r.nextInt(World.x/4)+(3*World.x/4), r.nextInt(World.y));
			}
			World.dudes.add(i, new Man(this, c, starting, i % 2));

		}
	}

	public void draw()
	{

		background(255);
		for (Man m : World.dudes)
		{
			m.act();
		}
		for (Man m : World.dudes)
		{
			m.display();
		}

	}
	/*
	 * if(mousePressed) { fill(0); } else { fill(255); } ellipse(mouseX, mouseY,
	 * 80, 80); }
	 */

}
