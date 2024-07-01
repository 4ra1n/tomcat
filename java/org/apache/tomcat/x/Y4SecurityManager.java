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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Permission;
import java.util.ArrayList;

public class Y4SecurityManager extends SecurityManager {
    private final static ArrayList<String> jspClassList = new ArrayList<String>();
    private final static ArrayList<String> otherClassList = new ArrayList<String>();

    private final static Path logPath = Paths.get("security-logs");
    private final static Path cmdLogPath = logPath.resolve(Paths.get("cmd.log"));
    private final static Path connLogPath = logPath.resolve(Paths.get("conn.log"));
    private final static Path listenLogPath = logPath.resolve(Paths.get("listen.log"));
    private final static Path linkLogPath = logPath.resolve(Paths.get("link.log"));
    private final static Path deleteLogPath = logPath.resolve(Paths.get("delete.log"));

    static {
        try {
            Files.createDirectories(logPath);
        } catch (Exception ignored) {
        }
        try {
            Files.createFile(cmdLogPath);
        } catch (Exception ignored) {
        }
        try {
            Files.createFile(connLogPath);
        } catch (Exception ignored) {
        }
        try {
            Files.createFile(listenLogPath);
        } catch (Exception ignored) {
        }
        try {
            Files.createFile(linkLogPath);
        } catch (Exception ignored) {
        }
        try {
            Files.createFile(deleteLogPath);
        } catch (Exception ignored) {
        }
        // JSP CLASS LIST
        jspClassList.add("org.apache.jasper.servlet.JspServlet");
        jspClassList.add("org.apache.jasper.servlet.JspServletWrapper");

        // OTHER CLASS LIST
        // COMMONS
        otherClassList.add("org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        // BCEL
        otherClassList.add("com.sun.org.apache.bcel.internal.util.ClassLoader");
        // CC
        otherClassList.add("org.apache.commons.collections4.functors.InvokerTransformer");
        otherClassList.add("org.apache.commons.collections.functors.InvokerTransformer");
        // RMI
        otherClassList.add("com.sun.jndi.rmi.registry.RegistryContext");
        otherClassList.add("java.rmi.server.UnicastRemoteObject");
        otherClassList.add("sun.rmi.server.UnicastRef");
    }

    public static void checkBase(String str) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (StackTraceElement stack : stacks) {
            for (String s : jspClassList) {
                if (stack.getClassName().equals(s)) {
                    throw new SecurityException(str + " IN JSP IS NOT ALLOWED");
                }
            }
            for (String s : otherClassList) {
                if (stack.getClassName().contains(s)) {
                    throw new SecurityException("NOT ALLOW " + str + " FOR SECURITY REASONS");
                }
            }
        }
    }

    public static void checkString(String s) {
        for (String str : otherClassList) {
            if (s.contains(str)) {
                throw new SecurityException("NOT ALLOW FOR SECURITY REASONS");
            }
        }
    }

    @Override
    public void checkExec(String cmd) {
        String log = String.format("%s\n", cmd);
        try {
            Files.write(cmdLogPath, log.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
        checkBase("RUN COMMAND");
    }

    @Override
    public void checkConnect(String host, int port) {
        if (port < 1) {
            return;
        }
        String log = String.format("%s:%d\n", host, Integer.valueOf(port));
        try {
            Files.write(connLogPath, log.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
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
    }

    @Override
    public void checkDelete(String file) {
        String log = String.format("%s\n", file);
        try {
            Files.write(deleteLogPath, log.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void checkExit(int status) {
    }

    @Override
    public void checkLink(String lib) {
        String log = String.format("%s\n", lib);
        try {
            Files.write(linkLogPath, log.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void checkListen(int port) {
        if (port == 0) {
            return;
        }
        String log = String.format("%d\n", Integer.valueOf(port));
        try {
            Files.write(listenLogPath, log.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
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
