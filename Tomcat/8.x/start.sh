#!/bin/sh
export INSTANCE_OWNER=instance1
export CATALINA_HOME=/opt/tomcat
export CATALINA_BASE=/home/$INSTANCE_OWNER
export CATALINA_PID=/home/$INSTANCE_OWNER/conf/${INSTANCE_OWNER}.pid

export JMX_OPTS=" -Dcom.sun.management.jmxremote \
                 -Dcom.sun.management.jmxremote.port=8190 \
                 -Dcom.sun.management.jmxremote.authenticate=false \
                 -Djava.rmi.server.hostname=192.168.96.20 \
                -Dcom.sun.management.jmxremote.ssl=false "

#export MEM_OPTS="-Xms4G -Xmx4G -XX:PermSize=256m -XX:MaxPermSize=256m"
# ParallelGC
export JVM_OPTS="-XX:NewRatio=3 -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/${INSTANCE_OWNER}/logs -verbosegc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:/home/${INSTANCE_OWNER}/logs/gc_`date "+%Y%m%d%H"`.log -Djava.security.egd=file:/dev/./urandom"

# CMS GC
#export JVM_OPTS="-XX:NewRatio=7 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSFullGCsBeforeCompaction=0 -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/${INSTANCE_OWNER}/logs -verbosegc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:/home/${INSTANCE_OWNER}/logs/gc_`date "+%Y%m%d%H"`.log -Djava.security.egd=file:/dev/./urandom"

# G1
# export JVM_OPTS="-XX:+UseG1GC -XX:+UnlockDiagnosticVMOptions -XX:+G1SummarizeConcMark -XX:+PrintFlagsFinal -XX:+PrintReferenceGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintAdaptiveSizePolicy -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/${INSTANCE_OWNER}/logs -verbosegc -Xloggc:/home/${INSTANCE_OWNER}/logs/gc_`date "+%Y%m%d%H"`.log -Djava.security.egd=file:/dev/./urandom"

export CATALINA_OPTS=" ${JMX_OPTS} ${CATALINA_OPTS} ${JVM_OPTS} -DjvmRoute=${INSTANCE_OWNER}"

if [ $USER = "root" ]; then
	/bin/su -p -s /bin/sh $INSTANCE_OWNER $CATALINA_HOME/bin/startup.sh
else
	$CATALINA_HOME/bin/startup.sh
fi
