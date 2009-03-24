/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors.
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.jcr;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.jackrabbit.test.JCRTestSuite;

/**
 * Test suite to wrap Apache Jackrabbit JCR technology compatibility kit (TCK) unit tests for Level 1 (L1) compliance. Note that
 * technically these are not the actual TCK, but these are unit tests that happen to be similar to (or provided the basis for) a
 * subset of the TCK.
 */
public class Jsr170LevelOneTckTest {

    /**
     * 
     */
    public Jsr170LevelOneTckTest() {
    }

    /**
     * Wrapper so that the Jackrabbit TCK test suite gets picked up by the DNA Maven test target.
     * 
     * @return a new instance of {@link JCRTestSuite}.
     */
    public static Test suite() {
        // Uncomment this to execute all tests
        // return new JCRTestSuite();

        // Or uncomment the following lines to execute the different sets/suites of tests ...
        TestSuite suite = new TestSuite("JCR 1.0 Level 1 Compliance tests");

        suite.addTest(new LevelOneFeatureTests());

        return suite;
    }

    /**
     * Test suite that includes the Level 1 JCR TCK API tests from the Jackrabbit project.
     */
    private static class LevelOneFeatureTests extends TestSuite {
        protected LevelOneFeatureTests() {
            super("JCR Level 1 API Tests");
            // We currently don't pass the tests in those suites that are commented out
            // See https://jira.jboss.org/jira/browse/DNA-285

            addTestSuite(org.apache.jackrabbit.test.api.RootNodeTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NodeReadMethodsTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.PropertyTypeTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NodeDiscoveringNodeTypesTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.BinaryPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.BooleanPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.DatePropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.DoublePropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.LongPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NamePropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.PathPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.ReferencePropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.StringPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.UndefinedPropertyTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NamespaceRegistryReadMethodsTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NamespaceRemappingTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.NodeIteratorTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.PropertyReadMethodsTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.RepositoryDescriptorTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.SessionReadMethodsTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.WorkspaceReadMethodsTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.ReferenceableRootNodesTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.ExportSysViewTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.ExportDocViewTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.RepositoryLoginTest.class);

            // These might not all be level one tests
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathPosIndexTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathDocOrderTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathOrderByTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathJcrPathTest.class);

            addTestSuite(org.apache.jackrabbit.test.api.query.ElementTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.OrderByDateTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.OrderByDoubleTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.OrderByLongTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.OrderByMultiTypeTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.OrderByStringTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.QueryResultNodeIteratorTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathDocOrderTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathJcrPathTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathOrderByTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.XPathPosIndexTest.class);

            addTestSuite(org.apache.jackrabbit.test.api.query.DerefQueryLevel1Test.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.GetLanguageTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.GetPersistentQueryPathLevel1Test.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.GetStatementTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.GetSupportedQueryLanguagesTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.GetPropertyNamesTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.PredicatesTest.class);
            addTestSuite(org.apache.jackrabbit.test.api.query.SimpleSelectionTest.class);

            // The tests in this suite are level one
            addTest(org.apache.jackrabbit.test.api.nodetype.TestAll.suite());
        }
    }

}
