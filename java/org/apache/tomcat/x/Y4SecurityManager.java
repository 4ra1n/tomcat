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

package org.apache.tomcat.x;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

public class Y4SecurityManager extends SecurityManager {
    public static void checkBase(String str) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (StackTraceElement stack : stacks) {
            if (stack.getClassName().equals("org.apache.jasper.servlet.JspServlet") &&
                stack.getMethodName().equals("serviceJspFile")) {
                throw new RuntimeException(str + " IN JSP IS NOT ALLOWED");
            }
            if (stack.getClassName().contains("org.apache.xalan.internal.xsltc.trax.TemplatesImpl") ||
                stack.getClassName().contains("com.sun.org.apache.bcel.internal.util.ClassLoader")) {
                throw new RuntimeException("NOT ALLOW " + str + " FOR SECURITY REASONS");
            }
        }
    }

    @Override
    public void checkExec(String cmd) {
        checkBase("RUN COMMAND");
    }

    @Override
    public void checkConnect(String host, int port) {
    }

    @Override
    public void checkAccept(String host, int port) {
    }

    @Override
    public void checkAccess(Thread t) {
    }

    @Override
    public void checkAccess(ThreadGroup g) {
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
    }

    @Override
    public void checkCreateClassLoader() {
        checkBase("CREATE CLASS LOADER");
    }

    @Override
    public void checkDelete(String file) {
    }

    @Override
    public void checkExit(int status) {
    }

    @Override
    public void checkLink(String lib) {
    }

    @Override
    public void checkListen(int port) {
    }

    @Override
    public void checkMulticast(InetAddress maddr) {
    }

    @Override
    public void checkPackageAccess(String pkg) {
    }

    @Override
    public void checkPackageDefinition(String pkg) {
    }

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
    }

    @Override
    public void checkPrintJobAccess() {
    }

    @Override
    public void checkPropertiesAccess() {
    }

    @Override
    public void checkPropertyAccess(String key) {
    }

    @Override
    public void checkRead(String file) {
    }

    @Override
    public void checkRead(FileDescriptor fd) {
    }

    @Override
    public void checkRead(String file, Object context) {
    }

    @Override
    public void checkSecurityAccess(String target) {
    }

    @Override
    public void checkSetFactory() {
    }

    @Override
    public void checkWrite(String file) {
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
    }

    @Override
    @SuppressWarnings("all")
    public void checkMulticast(InetAddress maddr, byte ttl) {
    }
}
