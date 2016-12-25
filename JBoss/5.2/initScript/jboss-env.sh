#!/bin/sh
# jboss-env.sh - starts a new shell with instance variables set
 
## set jboss base env
export JBOSS_HOME="/opt/jboss/jboss-as"
export SERVER_HOME="/home/jboss"
export LD_LIBRARY_PATH="${LD_LIBRARY_PATH}:/opt/jboss/lib"
 
export JBOSS_USER=jboss
export SERVER_NAME=SvrDemo01
export DOMAIN_IP=0.0.0.0
export JNP_PORT=1199
 
export JAVA_HOME=/opt/java
export CLASSPATH=.:/opt/java/jre/lib
 
export PATH=$JAVA_HOME/bin:$JBOSS_HOME/bin:$PATH

# JMX Credentials 
JMX_CREDETIALS_FILE="$SERVER_HOME/$SERVER_NAME/conf/props/jmx-console-users.properties" 
JMX_USER=$(cat $JMX_CREDETIALS_FILE | grep -v '#' | cut -d '=' -f 1 | head -n 1) 
JMX_PWD="$(cat $JMX_CREDETIALS_FILE | grep -v '#' | cut -d '=' -f 2 | head -n 1 | tr -d '\r')" 

JBOSS_ADMIN_USER=${JMX_USER:-"admin"} 
JBOSS_ADMIN_PWD=${JMX_PWD:-"admin"}
 
if [ "x$JBOSS_OPTS" = "x" ]; then
    JBOSS_OPTS="-Dserver=$SERVER_NAME"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.server.base.dir=$SERVER_HOME"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.server.base.url=file://$SERVER_HOME"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.server.log.dir=$SERVER_HOME/$SERVER_NAME/log"
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.server.log.threshold=DEBUG"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.messaging.ServerPeerID=2"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.service.binding.set=ports-01"
    JBOSS_OPTS="$JBOSS_OPTS -Djboss.partition.name=SvrDemo"
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.default.jgroups.stack=tcp"
    #JBOSS_OPTS="$JBOSS_OPTS -Djgroups.tcpping.initial_hosts=228.2.2.2[7600],228.2.2.2[7700]"
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.jgroups.tcp.tcp_port=7600" 
    #JBOSS_OPTS="$JBOSS_OPTS -Djgroups.bind_addr=228.2.2.2"
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.jgroups.tcp.tcp_port=7600" 
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.partition.udpGroup=228.2.2.2"
    #JBOSS_OPTS="$JBOSS_OPTS -Djboss.hapartition.mcast_port=44552"
fi
 
if [ "x$JAVA_OPTS" = "x" ]; then
    JAVA_OPTS="-server"
    JAVA_OPTS="$JAVA_OPTS -noverify"
    JAVA_OPTS="$JAVA_OPTS -Xms512m"
    JAVA_OPTS="$JAVA_OPTS -Xmx512m"
    JAVA_OPTS="$JAVA_OPTS -XX:PermSize=128m"
    JAVA_OPTS="$JAVA_OPTS -XX:MaxPermSize=128m"
    #JAVA_OPTS="$JAVA_OPTS -Xss128k"
 
    JAVA_OPTS="$JAVA_OPTS -verbose:gc"
    JAVA_OPTS="$JAVA_OPTS -Xloggc:$SERVER_HOME/$SERVER_NAME/gclog/gc.log"
    JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails"
    JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCTimeStamps"
    JAVA_OPTS="$JAVA_OPTS -XX:+PrintHeapAtGC"
    JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError"
    JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpPath=$SERVER_HOME/$SERVER_NAME/gclog/java_pid.hprof"
 
#    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote"
#    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8286"
#    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false"
#    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false"
 
    JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"
    JAVA_OPTS="$JAVA_OPTS -Dsun.rmi.dgc.client.gcInterval=3600000"
    JAVA_OPTS="$JAVA_OPTS -Dsun.rmi.dgc.server.gcInterval=3600000"
    JAVA_OPTS="$JAVA_OPTS -Dsun.lang.ClassLoader.allowArraySyntax=true "
fi
 
export JBOSS_OPTS JAVA_OPTS
