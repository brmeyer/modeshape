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

import javax.jcr.Item;
import javax.jcr.RepositoryException;

/**
 * @author jverhaeg
 */
abstract class AbstractJcrItem implements Item {

    protected final String getPath( String absolutePath,
                                    String relativePath ) {
        assert absolutePath != null;
        assert absolutePath.length() > 0;
        assert relativePath != null;
        if (absolutePath.charAt(absolutePath.length() - 1) == '/') {
            return absolutePath + relativePath;
        }
        return absolutePath + '/' + relativePath;
    }

    /**
     * {@inheritDoc}
     * 
     * @return <code>false</code>
     * @see javax.jcr.Item#isModified()
     */
    public final boolean isModified() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @return <code>false</code>
     * @see javax.jcr.Item#isNew()
     */
    public final boolean isNew() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.jcr.Item#isSame(javax.jcr.Item)
     */
    public boolean isSame( Item otherItem ) throws RepositoryException {
        return (getSession().getWorkspace() == otherItem.getSession().getWorkspace());
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException always
     * @see javax.jcr.Item#refresh(boolean)
     */
    public void refresh( boolean keepChanges ) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException always
     * @see javax.jcr.Item#remove()
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException always
     * @see javax.jcr.Item#save()
     */
    public void save() {
        throw new UnsupportedOperationException();
    }
}
