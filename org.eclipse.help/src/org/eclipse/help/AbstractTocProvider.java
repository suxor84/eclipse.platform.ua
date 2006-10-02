/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.help;

import org.eclipse.help.internal.HelpPlugin;

/**
 * An <code>AbstractTocProvider</code> is a mechanism to provide arbitrary
 * content to the table of contents (TOC). <code>AbstractTocProvider</code>s
 * must be registered via the <code>org.eclipse.help.toc</code> extension point.
 * 
 * @since 3.3
 */
public abstract class AbstractTocProvider {

	/**
	 * Returns all <code>ITocContribution</code>s for this provider. Providers
	 * are free to provide any number of contributions (zero or more).
	 * 
	 * @param locale the locale for which to get contributions
	 * @return all the contributions for this provider
	 */
	public abstract ITocContribution[] getTocContributions(String locale);
	
	/**
	 * Notifies the platform that the content managed by this provider may
	 * have changed since the last time <code>getTocContributions()</code>
	 * was called, and needs to be updated.
	 */
	protected void contentChanged() {
		// will force a reload next time around
		HelpPlugin.getTocManager().clearCache();
	}
}