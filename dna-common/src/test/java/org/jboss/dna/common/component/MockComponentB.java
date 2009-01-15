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

package org.jboss.dna.common.component;

import java.util.concurrent.atomic.AtomicInteger;
import net.jcip.annotations.ThreadSafe;

/**
 * A sequencer that can be used for basic unit testing.
 * 
 * @author Randall Hauch
 * @author John Verhaeg
 */
@ThreadSafe
public class MockComponentB implements SampleComponent {

    private SampleComponentConfig config;
    private AtomicInteger counter = new AtomicInteger();

    /**
     * {@inheritDoc}
     */
    public void setConfiguration( SampleComponentConfig config ) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    public void doSomething() {
        // increment the counter and record the progress ...
        this.counter.incrementAndGet();
    }

    public int getCounter() {
        return this.counter.get();
    }

    public boolean isConfigured() {
        return this.config != null;
    }

    /**
     * @return config
     */
    public SampleComponentConfig getConfiguration() {
        return this.config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return (this.config != null ? this.config.getName() : "SampleComponent") + " [" + this.getCounter() + "]";
    }
}
