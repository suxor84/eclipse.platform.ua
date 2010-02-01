/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ua.tests.help.criteria;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.help.ICriteria;
import org.eclipse.help.IToc;
import org.eclipse.help.IToc2;
import org.eclipse.help.ITopic;
import org.eclipse.help.ITopic2;
import org.eclipse.help.internal.Topic;
import org.eclipse.help.internal.base.util.CriteriaUtilities;
import org.eclipse.help.internal.criteria.CriterionResource;
import org.eclipse.help.internal.toc.Toc;
import org.eclipse.help.internal.toc.TocContribution;
import org.eclipse.help.internal.toc.TocFile;
import org.eclipse.help.internal.toc.TocFileParser;
import org.eclipse.help.internal.workingset.CriteriaHelpScope;
import org.eclipse.ua.tests.help.other.UserCriteria;
import org.eclipse.ua.tests.help.other.UserToc2;
import org.eclipse.ua.tests.help.other.UserTopic2;
import org.eclipse.ua.tests.plugin.UserAssistanceTestPlugin;
import org.xml.sax.SAXException;

public class ParseTocWithCriteria extends TestCase {
	
	/*
	 * Returns an instance of this Test.
	 */
	public static Test suite() {
		return new TestSuite(ParseTocWithCriteria.class);
	}

	private IToc2 parseToc(String filename) throws IOException, SAXException,
			ParserConfigurationException {
		IToc toc;
		TocFileParser parser = new TocFileParser();
		TocContribution cToc = parser.parse(new TocFile(
				UserAssistanceTestPlugin.getPluginId(), filename, true, "en",
				null, null));
		toc = cToc.getToc();
		return (IToc2) toc;
	}

	public void testTocWithCriteria() throws Exception {
		IToc2 toc = parseToc("data/help/criteria/c1.xml");
		Map criteria = new HashMap();
		CriteriaUtilities.addCriteriaToMap(criteria, toc.getCriteria());
	    assertEquals(2, criteria.size());
	    Object  versions = criteria.get("version");
	    assertNotNull(versions);
	    Set versionSet = (Set)versions;
	    assertEquals(2, versionSet.size());
	    assertTrue(versionSet.contains("1.0"));
	    assertTrue(versionSet.contains("2.0"));

	    Object  platforms = criteria.get("platform");
	    assertNotNull(platforms);
	    Set platformSet = (Set)platforms;
	    assertEquals(2, platformSet.size());
	    assertTrue(platformSet.contains("linux"));
	    assertTrue(platformSet.contains("win32"));
	}
	
	public void testCopyTocWithCriteria() throws Exception {
		IToc toc0 = parseToc("data/help/criteria/c1.xml");
		Toc toc = new Toc(toc0);
		Map criteria = new HashMap();
		CriteriaUtilities.addCriteriaToMap(criteria, toc.getCriteria());
	    assertEquals(2, criteria.size());
	    Object  versions = criteria.get("version");
	    assertNotNull(versions);
	    Set versionSet = (Set)versions;
	    assertEquals(2, versionSet.size());
	    assertTrue(versionSet.contains("1.0"));
	    assertTrue(versionSet.contains("2.0"));	    

	    Object  platforms = criteria.get("platform");
	    assertNotNull(platforms);
	    Set platformSet = (Set)platforms;
	    assertEquals(2, platformSet.size());
	    assertTrue(platformSet.contains("linux"));
	    assertTrue(platformSet.contains("win32"));
	}
	
	public void testTopicWithCriteria() throws Exception {
		IToc toc = parseToc("data/help/criteria/c1.xml");
		ITopic[] topics = (ITopic[]) toc.getTopics();
		assertEquals(topics.length, 2);
		// First topic
		Map criteria = new HashMap();
		assertTrue(topics[0] instanceof ITopic2);
		CriteriaUtilities.addCriteriaToMap(criteria, ((ITopic2)topics[0]).getCriteria());
	    assertEquals(2, criteria.size());
	    Object  versions = criteria.get("version");
	    assertNotNull(versions);
	    Set versionSet = (Set)versions;
	    assertEquals(1, versionSet.size());
	    assertTrue(versionSet.contains("1.0"));
	    assertFalse(versionSet.contains("2.0"));

		// Second topic

		criteria = new HashMap();
		assertTrue(topics[1] instanceof ITopic2);
		CriteriaUtilities.addCriteriaToMap(criteria, ((ITopic2)topics[1]).getCriteria());	   
	    versions = criteria.get("version");
	    assertNotNull(versions);
	    versionSet = (Set)versions;
	    assertEquals(1, versionSet.size());
	    assertTrue(versionSet.contains("2.0"));
	    assertFalse(versionSet.contains("1.0"));
	}

	public void testCriteriaScoping1() throws Exception {
		IToc toc = parseToc("data/help/criteria/c1.xml");
		ITopic[] topics = toc.getTopics();
		assertEquals(topics.length, 2);
		CriterionResource[] resource = new CriterionResource[1];
		resource[0] = new CriterionResource("version");
		resource[0].addCriterionValue("1.0");
		CriteriaHelpScope scope = new CriteriaHelpScope(resource);
		assertTrue(scope.inScope(toc));
		assertTrue(scope.inScope(topics[0]));
		assertFalse(scope.inScope(topics[1]));
	}

	public void testCriteriaScoping2() throws Exception {
		IToc toc = parseToc("data/help/criteria/c1.xml");
		ITopic[] topics = toc.getTopics();
		assertEquals(topics.length, 2);
		CriterionResource[] resource = new CriterionResource[1];
		resource[0] = new CriterionResource("platform");
		resource[0].addCriterionValue("linux");
		CriteriaHelpScope scope = new CriteriaHelpScope(resource);
		assertTrue(scope.inScope(toc));
		assertTrue(scope.inScope(topics[0]));
		assertFalse(scope.inScope(topics[1]));
	}

	public void testMultipleCriteriaScoping() throws Exception {
		IToc toc = parseToc("data/help/criteria/c1.xml");
		ITopic[] topics = toc.getTopics();
		assertEquals(topics.length, 2);
		CriterionResource[] resource = new CriterionResource[2];
		resource[0] = new CriterionResource("platform");
		resource[0].addCriterionValue("linux");
		resource[1] = new CriterionResource("version");
		resource[1].addCriterionValue("1.0");
		CriteriaHelpScope scope = new CriteriaHelpScope(resource);
		assertTrue(scope.inScope(toc));
		assertTrue(scope.inScope(topics[0]));
		assertFalse(scope.inScope(topics[1]));
	}
	
	public void testMultipleCriteriaOnlyOneSatisfied() throws Exception {
		IToc toc = parseToc("data/help/criteria/c1.xml");
		ITopic[] topics = toc.getTopics();
		CriterionResource[] resource = new CriterionResource[2];
		resource[0] = new CriterionResource("platform");
		resource[0].addCriterionValue("linux");
		resource[1] = new CriterionResource("version");
		resource[1].addCriterionValue("2.0");
		assertEquals(topics.length, 2);
		CriteriaHelpScope scope = new CriteriaHelpScope(resource);
		assertTrue(scope.inScope(toc));
		assertFalse(scope.inScope(topics[0]));
		assertFalse(scope.inScope(topics[1]));
	}

	public void testUserTocWithCriteria() throws Exception {
		UserToc2 toc = new UserToc2("myToc", null, true);
		UserCriteria criterion1 = new UserCriteria("version", "1.0", true);
		UserCriteria criterion2 = new UserCriteria("version", "2.0", true);
		toc.addCriterion(criterion1);
		toc.addCriterion(criterion2);
		
		ICriteria[] criteria = toc.getCriteria();
	    assertEquals(2, criteria.length);
	    assertEquals("version", criteria[0].getName());
	    assertEquals("1.0", criteria[0].getValue());
	    assertEquals("version", criteria[1].getName());
	    assertEquals("2.0", criteria[1].getValue());
	}
	
	public void testCopyUserTocWithCriteria() throws Exception {
		UserToc2 toc = new UserToc2("myToc", null, true);
		UserCriteria criterion1 = new UserCriteria("version", "1.0", true);
		UserCriteria criterion2 = new UserCriteria("version", "2.0", true);
		toc.addCriterion(criterion1);
		toc.addCriterion(criterion2);
		
		Toc copy = new Toc(toc);
		
		ICriteria[] criteria = copy.getCriteria();
	    assertEquals(2, criteria.length);
	    assertEquals("version", criteria[0].getName());
	    assertEquals("1.0", criteria[0].getValue());
	    assertEquals("version", criteria[1].getName());
	    assertEquals("2.0", criteria[1].getValue());
	}

	public void testUserTopicWithCriteria() throws Exception {
		UserTopic2 topic = new UserTopic2("myToc", null, true);
		UserCriteria criterion1 = new UserCriteria("version", "1.0", true);
		UserCriteria criterion2 = new UserCriteria("version", "2.0", true);
		topic.addCriterion(criterion1);
		topic.addCriterion(criterion2);
		
		Topic copy = new Topic(topic);
		
		ICriteria[] criteria = copy.getCriteria();
	    assertEquals(2, criteria.length);
	    assertEquals("version", criteria[0].getName());
	    assertEquals("1.0", criteria[0].getValue());
	    assertEquals("version", criteria[1].getName());
	    assertEquals("2.0", criteria[1].getValue());
	}

	public void testCopyUserTopicWithCriteria() throws Exception {
		UserTopic2 topic = new UserTopic2("myToc", null, true);
		UserCriteria criterion1 = new UserCriteria("version", "1.0", true);
		UserCriteria criterion2 = new UserCriteria("version", "2.0", true);
		topic.addCriterion(criterion1);
		topic.addCriterion(criterion2);
		ICriteria[] criteria = topic.getCriteria();
	    assertEquals(2, criteria.length);
	    assertEquals("version", criteria[0].getName());
	    assertEquals("1.0", criteria[0].getValue());
	    assertEquals("version", criteria[1].getName());
	    assertEquals("2.0", criteria[1].getValue());
	}

}
