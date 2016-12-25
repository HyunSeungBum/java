#!/bin/sh

export INSTANCE_OWNER=instance1
export CATALINA_HOME=/opt/tomcat
export CATALINA_BASE=/home/$INSTANCE_OWNER
export CATALINA_PID=/home/$INSTANCE_OWNER/conf/${INSTANCE_OWNER}.pid

if [ $USER = "root" ]; then
	/bin/su -p -s /bin/sh $INSTANCE_OWNER $CATALINA_HOME/bin/shutdown.sh
else
	$CATALINA_HOME/bin/shutdown.sh
fi


