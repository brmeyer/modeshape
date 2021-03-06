/*
 * ModeShape (http://www.modeshape.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.modeshape.web.server.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.jcr.Credentials;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletContext;
import org.modeshape.common.logging.Logger;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;
import org.modeshape.web.client.RemoteException;
import org.modeshape.web.server.Connector;
import org.modeshape.web.server.LRepository;
import org.modeshape.web.shared.RepositoryName;

/**
 *
 * @author kulikov
 */
public class JsonConfigConnectorImpl implements Connector {
    
    //this object will be serialized by user session but 
    //we do not want to serialize any data so mark everything as transient
    private static final long serialVersionUID = 1L;
    
    private transient HashMap<String, LRepository> repositories = new HashMap<>();
    
    //names of the available repositories
    private transient Collection<RepositoryName> repositoryNames;
    
    //user's credentials
    private transient Credentials credentials;
    private transient String userName;
    
    private transient ModeShapeEngine engine; 
    private RepositoryList repoList;
    
    private final static Logger logger = Logger.getLogger(JsonConfigConnectorImpl.class);
    
    public JsonConfigConnectorImpl() {
    }
    
    @Override
    public void start(ServletContext context) throws RemoteException {
        String urlString = context.getInitParameter("config-url");
        
        repositoryNames = new ArrayList<>();
        
        engine = new ModeShapeEngine();
        engine.start();
                
        try {
            //we are using class loader to get the URL inside war
            URL configURL = getClass().getClassLoader().getResource(urlString);
            RepositoryConfiguration config = RepositoryConfiguration.read(configURL);
            engine.deploy(config);
            repoList = new RepositoryList(engine);
            repositoryNames = repoList.getRepositories(null);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    @Override
    public void login( String username, String password ) throws RemoteException {        
        this.userName = username;
        if (username == null) {
            credentials = null;
        }
        
        if (password == null) {
            credentials = new SimpleCredentials(username, null);
        } else {
            credentials = new SimpleCredentials(username, password.toCharArray());
        }
        repositoryNames = repoList.getRepositories(credentials);        
    }

    @Override
    public void logout() {
        credentials = null;
        userName = null;
        try {
            repositoryNames = repoList.getRepositories(null);
        } catch (RemoteException e) {
            repositoryNames.clear();
        }
    }
    
    @Override
    public String userName() {
        return userName;
    }
    
    @Override
    public Collection<RepositoryName> getRepositories() {
        return repositoryNames;
    }

    @Override
    public LRepository find(String name) throws RemoteException {
        if (!repositories.containsKey(name)) {
            try {
                logger.debug("Starting repository: " + name);
                repositories.put(name, new LRepositoryImpl(engine.getRepository(name), credentials));
            } catch (Exception e) {
                logger.debug("Could not start repository " + name, e);
                throw new RemoteException(e.getMessage());
            }
        }
        return repositories.get(name);
    }

    @Override
    public Collection<RepositoryName> search(String name) {
        ArrayList<RepositoryName> list = new ArrayList<>();
        for (RepositoryName n : repositoryNames) {
            if (n.getName().contains(name) || n.getDescriptor().contains(name)) {
                list.add(n);
            }
        }
        return list;
    }
    
    
}
