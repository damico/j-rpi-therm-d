#!/bin/bash
line=`ps ax | grep -e "j-rpi-therm-d.jar"  | grep -v "grep"`
killarr=(`echo "$line"`)
kill -9  ${killarr[0]}
exit 0

