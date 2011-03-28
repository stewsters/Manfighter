package com.stewsters;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;

public class Man
{
	PApplet parent;
	Color c;
	public PVector pos;
	int m_id;
	int allegiance;
	int life = 10;
	int speed = 1;

	int girth = 5;
	int range = 8;
	Order order;
	static int mancounter = 0;

	Man(PApplet tempParent, Color tempC, PVector tempPos, int tempAllegiance)
	{
		m_id = mancounter++;
		parent = tempParent;
		c = tempC;
		pos = tempPos;
		allegiance = tempAllegiance;
	}

	void display()
	{
		parent.stroke(0);
		parent.fill(c.getRed(), c.getGreen(), c.getBlue());
		parent.ellipseMode(PApplet.CENTER);
		parent.ellipse(pos.x, pos.y, girth, girth);
	}

	void act()
	{
		if (life < 1)
		{
			c = c.darker();
			return;
			// World.dudes.remove(m_id);
			// return;
		}
		// find nearest enemy man
		Man closest_opponent = null;
		Man closest_friend = null;
		float min_oppenent_distance = Float.MAX_VALUE;
		float min_friend_distance = Float.MAX_VALUE;
		for (Man m : World.dudes)
		{
			if (m.m_id != m_id && m.life > 0)
			{
				float dist = pos.dist(m.pos);
				if (m.allegiance == allegiance)
				{
					if (dist < min_friend_distance)
					{
						closest_friend = m;
						min_friend_distance = dist;
					}
				}
				else
				{
					if (dist < min_oppenent_distance)
					{
						closest_opponent = m;
						min_oppenent_distance = dist;
					}
				}

			}
		}
		// move toward him. If the distance is less than 1, attack

		if(closest_friend != null)
		{
			if(min_friend_distance < girth)
			{
				flee(closest_friend);
			}
		}
		
		if (closest_opponent != null)
		{
			if(min_oppenent_distance < girth || (min_oppenent_distance < 100 && life < 5))
			{
				//back off of them
				flee(closest_opponent);
			}
			else if (min_oppenent_distance < range)
			{
				// attack
				closest_opponent.life -= 1;
			}
			else
			{
				advance(closest_opponent);
			}
		}

		//worldwrap
		if (pos.x > World.x)
			pos.x-=World.x;
		else if(pos.x < 0)
			pos.x+=World.x;
		
		if(pos.y > World.y)
			pos.y += World.y;
		else if(pos.y < 0)
			pos.y+=World.y;
	}
	
	private void advance(Man m)
	{
		PVector travel = PVector.sub(m.pos, pos);
		travel.normalize();
		travel.mult(speed);
		pos.add(travel);
	}
	
	private void flee(Man m)
	{
		PVector travel = PVector.sub(m.pos, pos);
		travel.normalize();
		travel.mult((-.9f)*speed);
		pos.add(travel);
	}
}
