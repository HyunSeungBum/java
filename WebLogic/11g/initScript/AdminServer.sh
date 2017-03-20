#!/bin/bash
#
# /etc/init.d/wlserver.sh -- startup script for WebLogic 
#
# Written by SeungBum Hyun
#

SCRIPT_ARGS=$1

WEBLOGIC_NAME="AdminServer"
DOMAIN_HOME_ENV="/home/systemv/wls_domains/mCloud/bin/setDomainEnv.sh"
DESC="Weblogic10.3.6 Application Server for $WEBLOGIC_NAME"

# Check privileges
if [ `id -u` -ne 0 ]; then
	echo "You need root privileges to run this script"
	exit 1
fi

# Make sure WebLogic is started with system locale
if [ -r /etc/default/locale ]; then
	. /etc/default/locale
	export LANG
fi

. /lib/lsb/init-functions

if [ -r /etc/default/rcS ]; then
	. /etc/default/rcS
fi

# Fetch WL env
if [ -f "$DOMAIN_HOME_ENV" ]; then
	. "$DOMAIN_HOME_ENV"
fi

if [ $SERVER_NAME != $WEBLOGIC_NAME ]; then
	log_failure_msg "$WEBLOGIC_NAME deosn't match it"
	exit 1
fi

# Run as WebLogic User
# Example of user creation for Ubuntu based:
# adduser --system --group --no-create-home --home $WL_HOME --disabled-login wluser
if [ -z "$WL_USER" ]; then
	WL_USER=systemv
fi

# check Weblogic User
id $WL_USER > /dev/null 2>&1
if [ $? -ne 0 -o -z "$WL_USER" ]; then
	log_failure_msg "User \"$WL_USER\" does not exist..."
	exit 1
fi

# Check owner of WL_HOME
if [ ! $(stat -L -c "%U" "$WL_HOME") = $WL_USER ]; then
	log_failure_msg "The user \"$WL_USER\" is not owner of \"$WL_HOME\""
	exit 1
fi

# Check owner of DOMAIN_HOME
if [ ! $(stat -L -c "%U" "$DOMAIN_HOME") = $WL_USER ]; then
	log_failure_msg "The user \"$WL_USER\" is not owner of \"$DOMAIN_HOME\""
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
if [ -z "$WLS_REDIRECT_LOG" ]; then
	WLS_REDIRECT_LOG=$DOMAIN_HOME/servers/${SERVER_NAME}/logs/console.log
fi

export WLS_REDIRECT_LOG
touch $WLS_REDIRECT_LOG
chown $WL_USER $WLS_REDIRECT_LOG

# Location to set the pid file
WL_PIDFILE="$DOMAIN_HOME/servers/$SERVER_NAME/run/$SERVER_NAME.pid"
export WL_PIDFILE

# Launch Weblogic in backgroud
LAUNCH_WL_IN_BACKGROUND=1
export LAUNCH_WL_IN_BACKGROUND

function cleanCacheTmp()
{
	# clean Cache and Tmp dirs
	echo "clean cache and tmp dirs from ${SERVER_NAME}..."

	rm -Rf "$DOMAIN_HOME/servers/$SERVER_NAME/cache"
	rm -Rf "$DOMAIN_HOME/servers/$SERVER_NAME/tmp"
}

function wlPID()
{
	# try get the JVM PID
	local wlPID="x"
	wlPID=$(ps -eo pid,cmd | grep "${SERVER_CLASS}" | grep "${SERVER_NAME}" | grep -v grep | cut -c1-6)
	echo "$wlPID"
}

# Helper function to check status of WL service
check_status() 
{
	pidofproc -p "$WL_PIDFILE" "$JAVA_HOME/bin/java" > /dev/null 2>&1
}

START_SCRIPT="$DOMAIN_HOME/bin/startWebLogic.sh"
USER_ENV_SCRIPT="$DOMAIN_HOME/init.d/AdminServerEnv.sh"

case "$SCRIPT_ARGS" in
start)
	log_daemon_msg "Starting $DESC" 
	log_daemon_msg "instance $SERVER_NAME at Domain of Weblogic ..."
	check_status
	status_start=$?
	if [ $status_start -eq 3 ]; then
		mkdir -p $(dirname "$WL_PIDFILE")
		mkdir -p $(dirname "$WLS_REDIRECT_LOG")
		cat /dev/null > "$WLS_REDIRECT_LOG"

		su -l $WL_USER -c "source $USER_ENV_SCRIPT; $START_SCRIPT > ${WLS_REDIRECT_LOG} 2>&1 &"

		count=0
		launched=0
		until [ $count -gt $STARTUP_WAIT ]
		do 
			grep 'Server started in RUNNING mode' "$WLS_REDIRECT_LOG" > /dev/null 
			if [ $? -eq 0 ]; then
				launched=1
				break
			fi
			sleep 1
			count=$((count + 1 ));
		done
		
		if [ $launched -eq 1 ] ; then
			echo `wlPID` > $WL_PIDFILE
			chown $WL_USER $(dirname "$WL_PIDFILE") || true
		fi
		
		if check_status; then
			log_end_msg 0
		else
			log_end_msg 1
		fi

		if [ $launched -eq 0 ]; then
			log_warning_msg "$DESC hasn't started within the timeout allowed"
			log_warning_msg "please review file \"$WLS_REDIRECT_LOG\" to see the status of the service"
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
		read kpid < "$WL_PIDFILE"
		log_daemon_msg "Stopping $DESC"
	
		kill $kpid

		count=0
		until [ $count -gt $SHUTDOWN_WAIT ]
                do
                        if ! check_status; then
                                break
                        fi
                        sleep 1
                        count=$((count + 1));
                done	

		if [ "$CLEAR_WORK_TMP" = "Y" ]; then
			cleanCacheTmp
		fi

		rm "$WL_PIDFILE"

		log_end_msg 0
	elif [ $status_stop -eq 1 ]; then
		log_action_msg "$DESC is not running but the pid file exists, cleaning up"
		rm -f $WL_PIDFILE
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
                read pid < $WL_PIDFILE
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
esac

exit 0
