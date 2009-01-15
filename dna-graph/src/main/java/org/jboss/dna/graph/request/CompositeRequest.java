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
package org.jboss.dna.graph.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jboss.dna.common.util.CheckArg;

/**
 * A request that wraps multiple other requests, allowing multiple requests to be treated as a single request.
 * <p>
 * Note that {@link #isCancelled()} and {@link #cancel()} apply to all requests contained by the composite request. In other
 * words, cancelling this request immediately marks all contained requests as cancelled. However, cancelling any request in the
 * request has the effect of cancelling all other requests in the composite, including the composite. (This is implemented by
 * having all {@link Request} objects in the composite share the same cancelled flag object.)
 * </p>
 * 
 * @author Randall Hauch
 */
public class CompositeRequest extends Request implements Iterable<Request> {

    private static final long serialVersionUID = 1L;

    /**
     * Return a request that either wraps multiple requests, or the single request if only one is supplied.
     * 
     * @param requests the requests to wrap
     * @return the requests wrapped in a CompositeRequest, or if only one request is supplied that single request
     * @throws IllegalArgumentException if there requests are null, empty, or contains only nulls
     */
    public static Request with( Request... requests ) {
        CheckArg.isNotEmpty(requests, "requests");
        if (requests.length == 1) {
            CheckArg.isNotNull(requests[0], "requests[0]");
            return requests[0];
        }
        boolean readOnly = true;
        List<Request> list = new ArrayList<Request>(requests.length);
        for (Request request : requests) {
            if (request == null) continue;
            if (request instanceof CompositeRequest) {
                CompositeRequest composite = (CompositeRequest)request;
                list.addAll(composite.getRequests());
                if (!composite.isReadOnly()) readOnly = false;
            } else {
                list.add(request);
                if (!request.isReadOnly()) readOnly = false;
            }
        }
        CheckArg.isNotEmpty(list, "requests");
        return new CompositeRequest(list, readOnly);
    }

    /**
     * Return a request that either wraps multiple requests, or the single request if only one is supplied.
     * 
     * @param requests the requests to wrap
     * @return the requests wrapped in a CompositeRequest, or if only one request is supplied that single request
     * @throws IllegalArgumentException if there requests are null, empty, or contains only nulls
     */
    public static Request with( Iterator<? extends Request> requests ) {
        CheckArg.isNotNull(requests, "requests");
        boolean readOnly = true;
        List<Request> list = new LinkedList<Request>();
        while (requests.hasNext()) {
            Request request = requests.next();
            if (request == null) continue;
            if (request instanceof CompositeRequest) {
                CompositeRequest composite = (CompositeRequest)request;
                list.addAll(composite.getRequests());
                if (!composite.isReadOnly()) readOnly = false;
            } else {
                list.add(request);
                if (!request.isReadOnly()) readOnly = false;
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        CheckArg.isNotEmpty(list, "requests");
        return new CompositeRequest(list, readOnly);
    }

    /**
     * Return a request that either wraps multiple requests, or the single request if only one is supplied.
     * 
     * @param requests the requests to wrap
     * @return the requests wrapped in a CompositeRequest, or if only one request is supplied that single request
     * @throws IllegalArgumentException if there requests are null or empty
     */
    public static Request with( List<? extends Request> requests ) {
        CheckArg.isNotEmpty(requests, "requests");
        if (requests.size() == 1) {
            return requests.get(0);
        }
        boolean readOnly = true;
        for (Request request : requests) {
            if (request.isReadOnly()) continue;
            readOnly = false;
            break;
        }
        return new CompositeRequest(requests, readOnly);
    }

    /**
     * Add requests to the supplied composite request.
     * 
     * @param composite the composite request to which the requests are to be added
     * @param requests the requests to wrap
     * @return the requests wrapped in a CompositeRequest, or if only one request is supplied that single request, or null if
     *         there are no request
     * @throws IllegalArgumentException if the composite request is null
     */
    public static CompositeRequest add( CompositeRequest composite,
                                        Request... requests ) {
        CheckArg.isNotNull(composite, "composite");
        if (requests == null || requests.length == 0) return composite;
        List<Request> list = new ArrayList<Request>(requests.length + composite.size());
        boolean readOnly = composite.isReadOnly();
        if (composite.size() != 0) list.addAll(composite.getRequests());
        for (Request request : requests) {
            if (request == null) continue;
            if (request instanceof CompositeRequest) {
                CompositeRequest compositeRequest = (CompositeRequest)request;
                list.addAll(compositeRequest.getRequests());
                if (!compositeRequest.isReadOnly()) readOnly = false;
            } else {
                list.add(request);
                if (!request.isReadOnly()) readOnly = false;
            }
        }
        return new CompositeRequest(list, readOnly);
    }

    /**
     * Add requests to the supplied composite request.
     * 
     * @param composite the composite request to which the requests are to be added
     * @param requests the requests to wrap
     * @return the requests wrapped in a CompositeRequest, or if only one request is supplied that single request, or null if
     *         there are no request
     * @throws IllegalArgumentException if the composite request is null
     */
    public static CompositeRequest add( CompositeRequest composite,
                                        Iterator<? extends Request> requests ) {
        CheckArg.isNotNull(composite, "composite");
        List<Request> list = new LinkedList<Request>();
        boolean readOnly = composite.isReadOnly();
        if (composite.size() != 0) list.addAll(composite.getRequests());
        while (requests.hasNext()) {
            Request request = requests.next();
            if (request == null) continue;
            if (request instanceof CompositeRequest) {
                CompositeRequest compositeRequest = (CompositeRequest)request;
                list.addAll(compositeRequest.getRequests());
                if (!compositeRequest.isReadOnly()) readOnly = false;
            } else {
                list.add(request);
                if (!request.isReadOnly()) readOnly = false;
            }
        }
        return new CompositeRequest(list, readOnly);
    }

    private final List<Request> requests;
    private final boolean readOnly;

    /**
     * Create a composite request from the supplied list of requests.
     * 
     * @param requests the modifiable list of requests; may not be null
     * @param readOnly true if all of the requests are {@link Request#isReadOnly() read-only}
     */
    /*package*/CompositeRequest( List<? extends Request> requests,
                                  boolean readOnly ) {
        // Iterate through the requests and set the cancelled flag of each request to this object's flag ...
        final AtomicBoolean flag = super.getCancelledFlag();
        for (Request request : requests) {
            request.setCancelledFlag(flag);
        }
        this.requests = Collections.unmodifiableList(requests);
        this.readOnly = readOnly;
    }

    /**
     * Return the unmodifiable requests contained in this composite request.
     * 
     * @return requests
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * Get the number of requests.
     * 
     * @return the number of requests
     */
    public int size() {
        return requests.size();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Request> iterator() {
        return requests.iterator();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.graph.request.Request#isReadOnly()
     */
    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if (obj instanceof CompositeRequest) {
            CompositeRequest that = (CompositeRequest)obj;
            if (this.size() != that.size()) return false;
            Iterator<Request> thisIter = this.iterator();
            Iterator<Request> thatIter = that.iterator();
            while (thisIter.hasNext()) {
                Request thisRequest = thisIter.next();
                Request thatRequest = thatIter.next();
                if (thisRequest == null) {
                    if (thatRequest != null) return false;
                } else {
                    if (!thisRequest.equals(thatRequest)) return false;
                }
            }
            return true;
        }
        return false;
    }

}
