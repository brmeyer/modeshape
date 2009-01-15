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
package org.jboss.dna.sequencer.java.metadata;


/**
 * WildcardTypeFieldMetadata represents meta data for wild card type.
 * <p>
 * It is important to know that, it is nonsense if a wild card type appears anywhere other than as an argument of a
 * <code>ParameterizedTypeFieldMetadata</code> node.
 * </p>
 * 
 * @author Serge Pagop
 */
public class WildcardTypeFieldMetadata extends FieldMetadata {

    public WildcardTypeFieldMetadata() {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.jboss.dna.sequencer.java.metadata.FieldMetadata#isWildcardType()
     */
    @Override
    public boolean isWildcardType() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WildcardTypeFieldMetadata [ " + getType() + " ]";
    }
}
