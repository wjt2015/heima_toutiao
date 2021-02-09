#!/bin/sh
commit_msg='update'
if(($# > 0));then
  commit_msg=$1
fi

source git_push.sh $commit_msg

mvn clean jetty:run






