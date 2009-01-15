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
package org.jboss.dna.common.jdbc.model.spi;

import org.jboss.dna.common.jdbc.model.api.TableType;

/**
 * Provides database table type specific metadata.
 * <P>
 * The table type is:
 * <OL>
 * <LI><B>TABLE_TYPE</B> String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
 * "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
 * </OL>
 *   @author <a href="mailto:litsenko_sergey@yahoo.com">Sergiy Litsenko</a>
 */
public class TableTypeBean extends DatabaseNamedObjectBean implements TableType {
    private static final long serialVersionUID = -5095835769360603900L;
 
    /**
     * Default constructor
     */
    public TableTypeBean() {
    }

    /**
     * Is table type represents TABLE
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents TABLE
     */
    public Boolean isTable( String tableTypeName ) {
        return DEF_TABLE_TYPE_TABLE.equals(tableTypeName);
    }

    /**
     * Is current table type represents TABLE
     * 
     * @return true if current table type represents TABLE
     */
    public Boolean isTable() {
        return isTable(getName());
    }

    /**
     * Is table type represents VIEW
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents VIEW
     */
    public Boolean isView( String tableTypeName ) {
        return DEF_TABLE_TYPE_VIEW.equals(tableTypeName);
    }

    /**
     * Is current table type represents VIEW
     * 
     * @return true if current table type represents VIEW
     */
    public Boolean isView() {
        return isView(getName());
    }

    /**
     * Is table type represents SYSTEM TABLE
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents SYSTEM TABLE
     */
    public Boolean isSystemTable( String tableTypeName ) {
        return DEF_TABLE_TYPE_SYS_TABLE.equals(tableTypeName);
    }

    /**
     * Is current table type represents SYSTEM TABLE
     * 
     * @return true if current table type represents SYSTEM TABLE
     */
    public Boolean isSystemTable() {
        return isSystemTable(getName());
    }

    /**
     * Is current table type represents GLOBAL TEMPORARY
     * 
     * @param tableTypeName the table type string
     * @return true if current table type represents GLOBAL TEMPORARY
     */
    public Boolean isGlobalTemporary( String tableTypeName ) {
        return DEF_TABLE_TYPE_GLOBAL_TEMP.equals(tableTypeName);
    }

    /**
     * Is current table type represents GLOBAL TEMPORARY
     * 
     * @return true if table type represents GLOBAL TEMPORARY
     */
    public Boolean isGlobalTemporary() {
        return isGlobalTemporary(getName());
    }

    /**
     * Is table type represents LOCAL TEMPORARY
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents LOCAL TEMPORARY
     */
    public Boolean islocalTemporary( String tableTypeName ) {
        return DEF_TABLE_TYPE_LOCAL_TEMP.equals(tableTypeName);
    }

    /**
     * Is current table type represents LOCAL TEMPORARY
     * 
     * @return true if current table type represents LOCAL TEMPORARY
     */
    public Boolean isLocalTemporary() {
        return islocalTemporary(getName());
    }

    /**
     * Is table type represents ALIAS
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents ALIAS
     */
    public Boolean isAlias( String tableTypeName ) {
        return DEF_TABLE_TYPE_ALIAS.equals(tableTypeName);
    }

    /**
     * Is current table type represents ALIAS
     * 
     * @return true if current table type represents ALIAS
     */
    public Boolean isAlias() {
        return isAlias(getName());
    }

    /**
     * Is table type represents SYNONYM
     * 
     * @param tableTypeName the table type string
     * @return true if table type represents SYNONYM
     */
    public Boolean isSynonym( String tableTypeName ) {
        return DEF_TABLE_TYPE_SYNONYM.equals(tableTypeName);
    }

    /**
     * Is current table type represents SYNONYM
     * @return true if current table type represents SYNONYM
     */
    public Boolean isSynonym() {
        return isSynonym(getName());
    }
}
