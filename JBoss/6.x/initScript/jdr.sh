#!/bin/sh

BASE_DIR=$(dirname $0)

. $BASE_DIR/jboss-env.sh

export JAVA_OPTS=" -Djava.awt.headless=false $JAVA_OPTS"

$JBOSS_HOME/bin/jdr.sh --host $JBOSS_CONTROLLER_IP --port $JBOSS_CONTROLLER_PORT
