/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ua.tests.cheatsheet;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.ua.tests.cheatsheet.parser.AllParserTests;
import org.eclipse.ua.tests.cheatsheet.views.AllViewTests;

/*
 * Tests all cheat sheet functionality (automated).
 */
public class AllCheatSheetTests extends TestSuite {

	/*
	 * Returns the entire test suite.
	 */
	public static Test suite() {
		return new AllCheatSheetTests();
	}

	/*
	 * Constructs a new test suite.
	 */
	public AllCheatSheetTests() {
		addTest(AllParserTests.suite());
		addTest(AllViewTests.suite());
	}
}