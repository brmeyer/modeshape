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
package org.jboss.dna.graph;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.jboss.dna.graph.property.Name;
import org.jboss.dna.graph.property.Path;
import org.jboss.dna.graph.property.basic.BasicPathSegment;
import org.junit.matchers.IsCollectionContaining;
import org.junit.matchers.TypeSafeMatcher;

/**
 * @author Randall Hauch
 */
public class IsNodeWithChildren extends TypeSafeMatcher<List<Location>> {
    private final Matcher<Iterable<Path.Segment>> childMatcher;

    public IsNodeWithChildren( Matcher<Iterable<Path.Segment>> childMatcher ) {
        this.childMatcher = childMatcher;
    }

    @Override
    public boolean matchesSafely( List<Location> children ) {
        List<Path.Segment> childSegments = new ArrayList<Path.Segment>(children.size());
        for (Location child : children) {
            childSegments.add(child.getPath().getLastSegment());
        }
        return childMatcher.matches(childSegments);
    }

    public void describeTo( Description description ) {
        description.appendText("children").appendDescriptionOf(childMatcher);
    }

    @Factory
    public static IsNodeWithChildren hasChild( Name name,
                                               int sameNameSiblingIndex ) {
        Path.Segment segment = new BasicPathSegment(name, sameNameSiblingIndex);
        return new IsNodeWithChildren(IsCollectionContaining.hasItem(segment));
    }

    @Factory
    public static IsNodeWithChildren hasChild( Path.Segment child ) {
        return new IsNodeWithChildren(IsCollectionContaining.hasItem(child));
    }

    @Factory
    public static IsNodeWithChildren hasChildren( Path.Segment... childSegments ) {
        return new IsNodeWithChildren(IsCollectionContaining.hasItems(childSegments));
    }

    @Factory
    public static IsNodeWithChildren isEmpty() {
        Path.Segment[] childSegments = new Path.Segment[] {};
        return new IsNodeWithChildren(IsCollectionContaining.hasItems(childSegments));
    }

}
