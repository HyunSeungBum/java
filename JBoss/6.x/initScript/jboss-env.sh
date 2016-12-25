#!/bin/sh

DATE=`date +%Y%m%d%H%M%S`
BASE_DIR=$(dirname $0)
 
export JBOSS_CONF=$BASE_DIR/jboss-env.conf
[ -r "$JBOSS_CONF" ] && . "${JBOSS_CONF}"

let JBOSS_CONTROLLER_PORT=9999+${JBOSS_PORT_OFFSET}
export JBOSS_CONTROLLER_PORT

let JBOSS_WEB_PORT=8080+${JBOSS_PORT_OFFSET}

########## JBoss System module and User module directory ##########
export JBOSS_MODULEPATH=$JBOSS_HOME/modules:$JBOSS_HOME/modules/ext

# JVM Options : Server
export JAVA_OPTS="-server $JAVA_OPTS"

# Linux Large Page Setting
#export JAVA_OPTS=" $JAVA_OPTS -XX:+UseLargePages "
export JAVA_OPTS=" $JAVA_OPTS -verbose:gc"
export JAVA_OPTS=" $JAVA_OPTS -Djava.net.preferIPv4Stack=true"
export JAVA_OPTS=" $JAVA_OPTS -Dorg.jboss.resolver.warning=true"
export JAVA_OPTS=" $JAVA_OPTS -Dsun.rmi.dgc.client.gcInterval=3600000 "
export JAVA_OPTS=" $JAVA_OPTS -Dsun.rmi.dgc.server.gcInterval=3600000"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.modules.system.pkgs=org.jboss.byteman"
export JAVA_OPTS=" $JAVA_OPTS -Djava.awt.headless=true"
export JAVA_OPTS=" $JAVA_OPTS -Dfile.encoding=${FILE_ENCODING}"
export JAVA_OPTS=" $JAVA_OPTS -Dsun.jnu.encoding=${SUN_JNU_ENCODING}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.home.dir=${JBOSS_HOME}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.server.base.dir=${JBOSS_NODE_BASE_DIR}/${JBOSS_NODE_NAME}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.server.log.dir=${JBOSS_BASE_LOG_DIR}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.socket.binding.port-offset=${JBOSS_PORT_OFFSET}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.node.name=${JBOSS_NODE_NAME}"

export JAVA_OPTS=" $JAVA_OPTS -Djboss.bind.address.management=${JBOSS_MANAGEMENT_ADDR}"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.bind.address=${JBOSS_BIND_ADDR}"
#export JAVA_OPTS=" $JAVA_OPTS -Djboss.bind_addr=$JBOSS_MULTICAST_ADDR"
#export JAVA_OPTS=" $JAVA_OPTS -Djboss.default.jgroups.stack=tcp"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.default.multicast.address=$JBOSS_MULTICAST_ADDR"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.messaging.group.address=$JBOSS_JMS_MULTICAST_ADDR"
export JAVA_OPTS=" $JAVA_OPTS -Djboss.modcluster.multicast.address=$JBOSS_MODCLUSTER_MULTICAST_ADDR"
export JAVA_OPTS=" $JAVA_OPTS -Dorg.jboss.as.logging.per-deployment=true"
export JAVA_OPTS=" $JAVA_OPTS -Dserver.mode=local"
#JBOSS_OPTS="$JBOSS_OPTS -Djboss.jvmRoute=jvm1"
#JBOSS_OPTS="$JBOSS_OPTS -Djboss.mod_cluster.jvmRoute=node1"
#JBOSS_OPTS=" $JBOSS_OPTS -Dexternal.deployment.dir=$DOMAIN_BASE/$SERVER_NAME/biz"
 
export JAVA_OPTS=" $JAVA_OPTS -Djboss.server.config.dir=${JBOSS_NODE_BASE_DIR}/${JBOSS_NODE_NAME}/configuration"
#export JAVA_OPTS=" $JAVA_OPTS -Djboss.domain.config.user.dir=${JBOSS_NODE_BASE_DIR}/${JBOSS_NODE_NAME}/configuration"

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH

echo "=================================================================="
echo "JAVA_HOME=$JAVA_HOME"
echo "JBOSS_HOME=$JBOSS_HOME"
echo "JBOSS_NODE_NAME=$JBOSS_NODE_NAME"
echo "JBOSS_NODE_BASE_DIR=$JBOSS_NODE_BASE_DIR"
echo "JBOSS_CONFIG=$JBOSS_CONFIG"
echo "JBOSS_PROFILE=$JBOSS_PROFILE"
 
echo "JBOSS_BIND_ADDR=$JBOSS_BIND_ADDR"
echo "JBOSS_PORT_OFFSET=$JBOSS_PORT_OFFSET"
echo "JBOSS_CONTROLLER_PORT=$JBOSS_CONTROLLER_PORT"
echo "JBOSS_WEB_PORT=$JBOSS_WEB_PORT"
 
echo "JBOSS_PIDFILE=$JBOSS_PIDFILE"
echo "JBOSS_CONSOLE_LOG=$JBOSS_CONSOLE_LOG"
echo "JBOSS_GC_LOG=$JBOSS_GC_LOG"
echo "=================================================================="
