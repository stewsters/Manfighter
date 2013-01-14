package com.stewsters.ai;

import java.util.ArrayList;
import java.util.List;

import com.stewsters.Man;
import com.stewsters.Order;
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
        leaderId = guys.get(0).m_id;
	}
	
	/*
	 * This man joins the group.
	 */
	public void join(Man m)
	{

		guys.add(m);

        // first order is to join the main group (regroup)
        m.order = new Order(getCenter());

	}
	
	//get the number of men in this group
	public void getSize()
	{
		guys.size();
	}
	
	public PVector getCenter()
	{
		//find the leader
        return new PVector();
	}
	
	
}
