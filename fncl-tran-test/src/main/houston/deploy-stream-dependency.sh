#!/bin/bash
#
# deploy-stream-dependency.sh 1.0.2-20181008
#
# This script helps User to create dependencies between two streams.
#
#**********************************************************************************************
#
# Calls to `deploy-stream-register.sh` for creating dependencies between streams
#
#    ./stream-register.sh "${ENV}" ACTION [PARAMETER LIST]
#                                  Example: $0 DEV CREATE_DEPENDENCY stream_key upstream_stream_key"
#
# For example:
#    ./stream-register.sh "${ENV}" CREATE_DEPENDENCY 5000 5001
#    ./stream-register.sh "${ENV}" CREATE_DEPENDENCY 5000 5002
#**********************************************************************************************

set -u

./stream-register.sh "${ENV}" CREATE_DEPENDENCY 7798 5930
./stream-register.sh "${ENV}" CREATE_DEPENDENCY 7798 7830