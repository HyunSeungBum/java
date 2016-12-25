#!/bin/bash
#
# /etc/init.d/jboss-SvrDemo01 -- startup script for jboss of SvrDemo01
#
# Written by SeungBum Hyun
#

NAME="SvrDemo01"
DEFAULT="/home/jboss/${NAME}/jboss-env.sh"
DESC="Jboss EAP 5.2 Application Server for $NAME"
# clear work and tmp dirs?
CLEAR_WORK_TMP="Y"

# Check privileges
if [ `id -u` -ne 0 ]; then
        echo "You need root privileges to run this script"
        exit 1
fi

# Make sure wildfly is started with system locale
if [ -r /etc/default/locale ]; then
        . /etc/default/locale
        export LANG
fi

. /lib/lsb/init-functions

if [ -r /etc/default/rcS ]; then
        . /etc/default/rcS
fi

# Overwrite settings from default file
if [ -f "$DEFAULT" ]; then
        . "$DEFAULT"
fi

# Setup the JVM
if [ -z "$JAVA" ]; then
        if [ -n "$JAVA_HOME" ]; then
                JAVA="$JAVA_HOME/bin/java"
        else
                JAVA="java"
        fi
fi

# Check if wildfly is installed
if [ ! -f "$JBOSS_HOME/bin/run.jar" ]; then
        log_failure_msg "$NAME is not installed in \"$JBOSS_HOME\""
        exit 1
fi

# Run as wildfly user
# Example of user creation for Debian based:
# adduser --system --group --no-create-home --home $JBOSS_HOME --disabled-login wildfly
if [ -z "$JBOSS_USER" ]; then
        JBOSS_USER=jboss
fi

# Check wildfly user
id $JBOSS_USER > /dev/null 2>&1
if [ $? -ne 0 -o -z "$JBOSS_USER" ]; then
        log_failure_msg "User \"$JBOSS_USER\" does not exist..."
        exit 1
fi

# Check owner of JBOSS_HOME
if [ ! $(stat -L -c "%U" "$JBOSS_HOME") = $JBOSS_USER ]; then
        log_failure_msg "The user \"$JBOSS_USER\" is not owner of \"$JBOSS_HOME\""
        exit 1
fi

# The amount of time to wait for startup
if [ -z "$STARTUP_WAIT" ]; then
        STARTUP_WAIT=120
fi

# The amount of time to wait for shutdown
if [ -z "$SHUTDOWN_WAIT" ]; then
        SHUTDOWN_WAIT=120
fi

# Location to keep the console log
if [ -z "$JBOSS_CONSOLE_LOG" ]; then
        JBOSS_CONSOLE_LOG="$SERVER_HOME/$SERVER_NAME/log/console.log"
fi
export JBOSS_CONSOLE_LOG

touch $JBOSS_CONSOLE_LOG
chown $JBOSS_USER $JBOSS_CONSOLE_LOG

# Location to set the pid file
JBOSS_PIDFILE="$SERVER_HOME/$SERVER_NAME/run/$SERVER_NAME.pid"
export JBOSS_PIDFILE

# Launch Jboss in background
LAUNCH_JBOSS_IN_BACKGROUND=1
export LAUNCH_JBOSS_IN_BACKGROUND

function cleanWorkTmp()
{
   # clean tmp and work dirs
   echo "clean work and tmp dirs from ${SERVER_NAME}..."

   rm -Rf "${SERVER_HOME}/${SERVER_NAME}/work"
   rm -Rf "${SERVER_HOME}/${SERVER_NAME}/tmp"
}

function jbossPID() 
{
   # try get the JVM PID
   local jbossPID="x"
   jbossPID=$(ps -eo pid,cmd | grep "org.jboss.Main" | grep "${DOMAIN_IP}" | grep "${SERVER_NAME}" | grep -v grep | cut -c1-6)
   echo "$jbossPID"
}

# Helper function to check status of Jboss service
check_status() {
        pidofproc -p "$JBOSS_PIDFILE" "$JAVA" >/dev/null 2>&1
}

START_SCRIPT="$JBOSS_HOME/bin/run.sh"
SHUTDOWN_SCRIPT="$JAVA -classpath $JBOSS_HOME/bin/shutdown.jar:$JBOSS_HOME/client/jnet.jar org.jboss.Shutdown --shutdown -s jnp://$DOMAIN_IP:$JNP_PORT -u $JBOSS_ADMIN_USER -p $JBOSS_ADMIN_PWD"

case "$1" in
 start)
        log_daemon_msg "Starting $DESC" 
	log_daemon_msg "instance $SERVER_NAME at $DOMAIN_IP ..."
        check_status
        status_start=$?
        if [ $status_start -eq 3 ]; then
                mkdir -p $(dirname "$JBOSS_PIDFILE")
                mkdir -p $(dirname "$JBOSS_CONSOLE_LOG")
                cat /dev/null > "$JBOSS_CONSOLE_LOG"

		su -l $JBOSS_USER -c "source $DEFAULT; $START_SCRIPT $JBOSS_OPTS -c $SERVER_NAME -b $DOMAIN_IP > ${JBOSS_CONSOLE_LOG} 2>&1 &"

		
                count=0
                launched=0
                until [ $count -gt $STARTUP_WAIT ]
                do
                        grep 'Started in' "$JBOSS_CONSOLE_LOG" > /dev/null
                        if [ $? -eq 0 ] ; then
                                launched=1
                                break
                        fi
                        sleep 1
                        count=$((count + 1));
                done

		if [ $launched -eq 1 ] ; then
   			echo `jbossPID` > $JBOSS_PIDFILE
                	chown $JBOSS_USER $(dirname "$JBOSS_PIDFILE") || true
		fi

                if check_status; then
                        log_end_msg 0
                else
                        log_end_msg 1
                fi

                if [ $launched -eq 0 ]; then
                        log_warning_msg "$DESC hasn't started within the timeout allowed"
                        log_warning_msg "please review file \"$JBOSS_CONSOLE_LOG\" to see the status of the service"
                fi
        elif [ $status_start -eq 1 ]; then
                log_failure_msg "$DESC is not running but the pid file exists"
                exit 1
        elif [ $status_start -eq 0 ]; then
                log_success_msg "$DESC (already running)"
        fi
 ;;
 stop)
        check_status
        status_stop=$?
        if [ $status_stop -eq 0 ]; then
                read kpid < "$JBOSS_PIDFILE"
                log_daemon_msg "Stopping $DESC"

		su -l $JBOSS_USER -c "source $DEFAULT; $SHUTDOWN_SCRIPT"
	
                count=0
                until [ $count -gt $SHUTDOWN_WAIT ]
                do
                        if ! check_status; then
                                break
                        fi
                        sleep 1
                        count=$((count + 1));
                done

		if check_status; then
                        kill -9 $kpid
                fi

	   	if [ "$CLEAR_WORK_TMP" = "Y" ]; then
      			cleanWorkTmp
   		fi

		rm "$JBOSS_PIDFILE"

                log_end_msg 0
        elif [ $status_stop -eq 1 ]; then
                log_action_msg "$DESC is not running but the pid file exists, cleaning up"
                rm -f $JBOSS_PIDFILE
        elif [ $status_stop -eq 3 ]; then
                log_action_msg "$DESC is not running"
        fi
 ;;

 restart)
        check_status
        status_restart=$?
        if [ $status_restart -eq 0 ]; then
                $0 stop
        fi
        $0 start
 ;;

 status)
        check_status
        status=$?
        if [ $status -eq 0 ]; then
                read pid < $JBOSS_PIDFILE
                log_action_msg "$DESC is running with pid $pid"
                exit 0
        elif [ $status -eq 1 ]; then
                log_action_msg "$DESC is not running and the pid file exists"
                exit 1
        elif [ $status -eq 3 ]; then
                log_action_msg "$DESC is not running"
                exit 3
        else
                log_action_msg "Unable to determine $NAME status"
                exit 4
        fi
 ;;
 *)
 log_action_msg "Usage: $0 {start|stop|restart|reload|force-reload|status}"
 exit 2
 ;;
esac

exit 0

