/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.help.search;

import org.eclipse.core.runtime.*;

/**
 * A search engine that is a participant in the help search. All
 * engines selected by the user to run are executed as background
 * jobs using the same search expression. Engine is
 * expected to accept the expression and the scope object. The
 * results should be passed to the result collector object, and
 * the search progress should be tracked by the progress monitor.
 * <p>
 * Search engines that have API access to the search server
 * can provide a list of individual search results. Search engines
 * for which only the URL is known are expected to return one search
 * result containing a URL that is sufficient to open a web browser
 * and see the search results there.
 * <p>
 * Search engine must be cancelable.
 * 
 * @since 3.1
 */

public interface ISearchEngine {
	/**
	 * 
	 * @param query the search expression
	 * @param scope the engine-specific scope object that is used to narrow the search
	 * or <code>null</code> if the default scope is assumed.
	 * @param collector the search result collector
	 * @param monitor progress monitor to track the search progress
	 * @throws CoreException when there are problems in the engine
	 */
	void run(String query, ISearchScope scope, ISearchEngineResultCollector collector, IProgressMonitor monitor) throws CoreException;
	/**
	 * Called when the entire search or this individual engine has been
	 * cancelled.
	 */
	void cancel();
}