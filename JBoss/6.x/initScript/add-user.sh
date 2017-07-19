#!/bin/sh

BASE_DIR=$(dirname $0)

. $BASE_DIR/jboss-env.sh

export JAVA_OPTS=" -Djboss.server.config.user.dir=${JBOSS_NODE_BASE_DIR}/${JBOSS_NODE_NAME}/configuration $JAVA_OPTS"
export JAVA_OPTS=" -Djava.awt.headless=false $JAVA_OPTS"

$JBOSS_HOME/bin/add-user.sh $@
