#!/bin/bash
#
# deploy-stream-register.sh 1.0.1-20181008
#
# This script helps User to update details of a stream register
#
#**********************************************************************************************
#
# Calls to `deploy-stream-register.sh` to create or update stream details have the form:
#
#   ./stream-register.sh "${ENV}" UPDATE_DETAILS STREAM_KEY PRIORITY DESTINATION_HIVE_DATABASE DR_ENABLED AUTOSYS_PATTERN
#                                 SPG_GROUP REPOSITORY_NAME FRAMEWORK_TYPE DATA_ACQUISITION_METHOD
#                                 CI_NUMBER DOWNSTREAM_SYSTEM DOWNSTREAM_STREAM_ID SOURCE_DOWNSTREAM_IT_SERVICE
#                                 SOURCE_DOWNSTREAM_SPG
#
#   "${ENV}"                       Environment variable containing the logical environment. This variable is provided by the current bg_profile.
#
#    UPDATE_DETAILS                Action Keyword used to identify the operation being performed.
#    PRIORITY                      Priority for the stream [P1|P2|P3|P4]
#    DESTINATION_HIVE_DATABASE     Name of the destination hive database where the hive table is loaded to.
#    DR_ENABLED                    Stream is DR enabled (Follows DR-able pattern, has BDR trigger autosys jobs) [0 - NOT ENABLED|1 - ENABLED]
#    AUTOSYS_PATTERN               Autosys pattern used for the jobs
#    SPG_GROUP                     My Service SPG  name of the L3 team which owns the stream
#    REPOSITORY_NAME               Stash Repository Name where the code base is version controlled
#    FRAMEWORK_TYPE                This flag denotes whether a stream using  OCF / ETL CONTROLLER / HOUSTON [OCF - O|ETL_CONTROLLER - E|HOUSTON - H|NO_LOGGING - N]
#    DATA_ACQUISITION_METHOD       Method Used for loading - whether via Direct DB access or files via XCOM
#    CI_NUMBER                     This field is used to store the CI Number
#    DOWNSTREAM_SYSTEM             This field gives the information about Downstream whether it is GDW2 or GDW or NONGDW Systems [GDW2|GDW1|NONGDW|FDM]
#    DOWNSTREAM_STREAM_ID          This fields holds the Stream Details from Downstream if any. In case of GDW 2.0, Stream Key has to be populated.
#    SOURCE_DOWNSTREAM_IT_SERVICE  Source IT service CI NUMBER for LOAD type Streams. Downstream IT Service CI number for EGRESS streams
#    SOURCE_DOWNSTREAM_SPG         Source/Downstream System support Team's My Service SPG"
#
# For example:
#
#   ./stream-register.sh "${ENV}" UPDATE_DETAILS 5000 P3 prod_dil_npp 0 au_cba_hado_p01_5000_src_dil_npp_omn_dly% "CBA_ES_A&I_PAYMENTS" etl.spark-dil H NA CM0783510 NONGDW NA NA NA
#   ./stream-register.sh "${ENV}" UPDATE_DETAILS 5001 P3 NA 0 au_cba_hado_p01_5001_ing_src_frl_omn_dly% "CBA_ES_A&I_RISK" etl.frl-frl H XCOM CM0742078 NONGDW NA CM0219127 TCS_ES_ITSMO_GrpITOps_Risk
#**********************************************************************************************

set -u

./stream-register.sh "${ENV}" UPDATE_DETAILS 7798 P3 prod_enr 1 au_cba_hado_p01_7798_dil_enr_sap_omn_dly_fncltrantest_tfm_cmd "CBA_ES_A&I_SECURITY" etl.spark-enr H NA CM<TO BE FILLED> NONGDW NA NA NA
