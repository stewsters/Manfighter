package com.stewsters.ai;

import java.util.ArrayList;
import java.util.List;

import com.stewsters.Man;
import com.stewsters.Order;
import com.stewsters.World;
import processing.core.PVector;

/**
 * This is a fighting unit of guys.
 *
 * Groups will set orders on the men
 */
public class Group
{
	private List<Man> guys;
    //leader id
    int leaderId = 0;

	public Group(List<Man> guys)
	{
        this.guys = guys;
        leaderId = guys.get(0).id;
	}

    public Group()
    {
        this.guys = new ArrayList<Man>();
        leaderId = 0;
    }

	/*
	 * This man joins the group.
	 */
	public void join(Man m)
	{
		guys.add(m);
        m.group = this;

	}
	
	//get the number of men in this group
	public void getSize()
	{
		guys.size();
	}
	
	public PVector getCenter()
	{
        return getLeader().pos;
	}
	

    public Man getLeader(){

        if(leaderId != 0){

            Man leader = World.dudes.get(leaderId);
            if(leader != null && leader.life >0){
                return leader;
            }
        }

        return promoteLeader();
    }

    private Man promoteLeader(){
        Man newLeader = guys.get(0);
        leaderId = newLeader.id;
        return newLeader;
    }
}
