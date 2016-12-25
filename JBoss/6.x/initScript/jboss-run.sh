#!/bin/sh
 
BASE_DIR=$(dirname $0)

# jvm setting
export JBOSS_JVM=$BASE_DIR/jvm-env.conf
[ -r "$JBOSS_JVM" ] && . "${JBOSS_JVM}"

. ${BASE_DIR}/jboss-env.sh

export JAVA_OPTS=" $JAVA_OPTS $JVM_OPTS"
 
if [ "x$1" == "xstart" ]; then
  if [ -e $JBOSS_CONSOLE_LOG ]; then
    mv $JBOSS_CONSOLE_LOG ${JBOSS_CONSOLE_LOG}.${DATE}
  fi
 
  if [ -e $JBOSS_GC_LOG ]; then
    mv $JBOSS_GC_LOG ${JBOSS_GC_LOG}.${DATE}
  fi
fi
 
${JBOSS_HOME}/bin/init.d/jboss-as-standalone.sh $1
