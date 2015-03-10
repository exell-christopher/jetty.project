//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.cdi.websocket;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.Session;

@Singleton
public class JettyWebSocketSessionProducer
{
    private static final Logger LOG = Log.getLogger(JettyWebSocketSessionProducer.class);
    private ThreadLocal<Session> sessionInstance;

    public JettyWebSocketSessionProducer()
    {
        LOG.debug("ctor<>");
        sessionInstance = new ThreadLocal<Session>();
    }

    public void setSession(Session sess)
    {
        LOG.debug("setSession()");
        sessionInstance.set(sess);
    }

    @Produces
    public Session getSession(InjectionPoint injectionPoint)
    {
        LOG.debug("getSession(" + injectionPoint + ")");
        return this.sessionInstance.get();
    }
}
