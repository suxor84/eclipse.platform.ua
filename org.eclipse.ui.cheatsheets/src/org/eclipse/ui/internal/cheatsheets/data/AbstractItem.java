/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.cheatsheets.data;

/**
 * <p>Implementations of IAbstractItem are returned from the ICheatSheetManager
 * on a call to ICheatSheetManager.getItem(String itemID).</p>
 * 
 * <p>These implementations can be checked to see if they implement IItem or
 * IItemWithSubItems, and can be casted appropriately.</p>
 * 
 *<p>If an IItem representing a step in a cheat sheet needs to be converted to an
 * IItemWithSubItems that represents a step in the cheat sheet with sub steps,
 * the IAbstractItem is passed to the ICheatSheetManager to convert it.</p>
 */
public abstract class AbstractItem {
	
	protected String id;

	public AbstractItem() {
		super();
	}
	
	/**
	 * This method sets the id of this item.  This id is used to access this item
	 * later and must be unique from the id of the other items added to the same base item.
	 * @param id the unique id to assign to this item
	 */
	public void setID(String id){
		this.id = id;
	}
	
	/**
	 * This method returns the id of the item.
	 * Items are recognized by their id.
	 * @return the id of the item
	 */
	public String getID(){
		return id;	
	}
	
}