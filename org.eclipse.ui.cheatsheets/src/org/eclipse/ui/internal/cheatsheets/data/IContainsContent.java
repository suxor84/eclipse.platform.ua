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

import java.util.ArrayList;

public interface IContainsContent {

	/**
	 * Returns the helpLink.
	 * @return String
	 */
	public String getHref();

	/**
	 * Returns the text.
	 * @return String
	 */
	public String getText();

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle();

	/**
	 * Sets the helpLink.
	 * @param helpLink The helpLink to set
	 */
	public void setHref(String helpLink);

	/**
	 * Sets the text.
	 * @param text The text to set
	 */
	public void setText(String text);

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title);

	public boolean isDynamic();

	public void setIsDynamic(boolean b);
	
	public void setContent(Content c);
	
	public Content getContent();
	
	public void setItemExtensions(ArrayList itemExtensions);
	
	public ArrayList getItemExtensions();

}