#!/bin/sh

export USER_MEM_ARGS="-Xms256m -Xmx512m -XX:CompileThreshold=8000 -XX:PermSize=128m  -XX:MaxPermSize=256m -Djava.security.egd=file:/dev/./urandom"
