#!/bin/bash
		
export APP_HOME="${PWD}"
export APPJAR=$APP_HOME/jar
export APP3RDJAR=$APP_HOME/3rdparty
export CONFIG=$APP_HOME/config

export APPCLASSPATH="\
:$APP3RDJAR/log4j-1.2.14.jar\
:$APP3RDJAR/commons-logging-1.0.4.jar\
:$APPJAR/phone2word.jar\
"
inputParam=
shiftnum=0
dictionary=
phoneFile=

while getopts "d:p:" opt; do
	case $opt in
	d ) dictionary=$OPTARG
		inputParam="${inputParam} -d ${dictionary}"
		shiftnum=`expr $shiftnum + 2`;;
	p ) phoneFile=$OPTARG
	    inputParam="${inputParam} -p ${phoneFile}"
		shiftnum=`expr $shiftnum + 2`;;
		esac
done

echo ${inputParam}
java -mx256m -classpath $APPCLASSPATH com.fiona.phone2word.Phone2Word ${inputParam}