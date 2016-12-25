#!/bin/sh
 
BASE_DIR=$(dirname $0)
 
. $BASE_DIR/jboss-env.sh
 
export JAVA_OPTS=" -Djava.awt.headless=false $JAVA_OPTS"
 
$JBOSS_HOME/bin/jboss-cli.sh --controller=$JBOSS_CONTROLLER_IP:$JBOSS_CONTROLLER_PORT --connect $@
