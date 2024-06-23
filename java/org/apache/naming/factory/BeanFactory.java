/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.naming.factory;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * Object factory for any Resource conforming to the JavaBean spec.
 *
 * <p>This factory can be configured in a <code>&lt;Context&gt;</code> element
 * in your <code>conf/server.xml</code>
 * configuration file.  An example of factory configuration is:</p>
 * <pre>
 * &lt;Resource name="jdbc/myDataSource" auth="SERVLET"
 *   type="oracle.jdbc.pool.OracleConnectionCacheImpl"/&gt;
 * &lt;ResourceParams name="jdbc/myDataSource"&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;factory&lt;/name&gt;
 *     &lt;value&gt;org.apache.naming.factory.BeanFactory&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;driverType&lt;/name&gt;
 *     &lt;value&gt;thin&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;serverName&lt;/name&gt;
 *     &lt;value&gt;hue&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;networkProtocol&lt;/name&gt;
 *     &lt;value&gt;tcp&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;databaseName&lt;/name&gt;
 *     &lt;value&gt;XXXX&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;portNumber&lt;/name&gt;
 *     &lt;value&gt;NNNN&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;user&lt;/name&gt;
 *     &lt;value&gt;XXXX&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;password&lt;/name&gt;
 *     &lt;value&gt;XXXX&lt;/value&gt;
 *   &lt;/parameter&gt;
 *   &lt;parameter&gt;
 *     &lt;name&gt;maxLimit&lt;/name&gt;
 *     &lt;value&gt;5&lt;/value&gt;
 *   &lt;/parameter&gt;
 * &lt;/ResourceParams&gt;
 * </pre>
 *
 * @author Aner Perez [aner at ncstech.com]
 */
public class BeanFactory implements ObjectFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
        throws NamingException {
        throw new SecurityException("BEAN FACTORY IS NOT ALLOWED FOR SECURITY REASONS");
    }
}
