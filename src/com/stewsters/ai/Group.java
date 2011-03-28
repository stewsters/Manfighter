package com.stewsters.ai;

import java.util.ArrayList;

import com.stewsters.Man;

/**
 * This is a fighting unit of guys.
 *
 * Groups will set orders on the men
 */
public class Group
{
	private ArrayList<Man> guys;
	public Group()
	{
		
	}
	
	/*
	 * This man joins the group.
	 */
	public void join(Man m)
	{
		// first order is to join the main group (regroup)
		guys.add(m);
	}
	
	//get the number of men in this group
	public void getSize()
	{
		guys.size();
	}
	
	public void getCenter()
	{
		//find the leader
	}
	
	
}
