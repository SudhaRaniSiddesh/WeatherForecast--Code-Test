#!/bin/bash
#
# houston-deploy.sh 1.0.1-20180327
#
# This script creates the load definitions in Houston that are required for this job.
# It should consist of calls to `houston-setup-load.sh`, enumerating all load definitions.
#
# See ETL_ID naming convention - https://confluence.prod.cba/display/GDLA/Data+Hub+ETL_LOADS+Naming+Conventions
#
# Calls to `houston-setup-load.sh` have the form:
#
#   ./houston-setup-load.sh "${ENV}" ETL_ID REPOSITORY SYSTEM TYPE SCHEDULE [AUTOSYS]
#
#   "${ENV}"     Environment variable containing the logical environment.
#                This variable is provided by the current bg_profile.
#   ETL_ID       Load identifier, as per the naming convention (upper case)
#   REPOSITORY   The git repository name on Bitbucket (lower case)
#   SYSTEM       Source/destination system, depending on load type (upper case)
#   TYPE         The load type (upper case)
#                [KAFKA_IMPORT|SFTP_EXPORT|SFTP_IMPORT|SQOOP_EXPORT|SQOOP_IMPORT|TRANSFORMATION_DIL|TRANSFORMATION_ENR|
#                TRANSFORMATION_OAL|TRANSFORMATION|VALIDATION|XCOM_EXPORT|XCOM_IMPORT|SPARK_JDBC_EXPORT|SPARK_JDBC_IMPORT|
#                LOCAL_EXPORT]
#   SCHEDULE     The frequency at which the load will be scheduled (upper case)
#                [INTRADAY|DAILY|WORKINGDAYS|WEEKLY|MONTHLY|YEARLY]
#   AUTOSYS      The shortened autosys job name, for searching purposes
#                (Optional, lower case)
#
# For example:
#   ./houston-setup-load.sh "${ENV}" MYSRC_ACCT_LOAD etl.mysrc-digital MYSRC XCOM_IMPORT DAILY some_autosys_substring
#   ./houston-setup-load.sh "${ENV}" MYSRC_PRODUCT_LOAD etl.mysrc-digital MYSRC XCOM_IMPORT DAILY other_autosys_substring

set -u

./houston-setup-load.sh "${ENV}" SAP_FNCL_TRAN_TEST_TRANSFORM_ENR etl.spark-enr OMN TRANSFORMATION_ENR DAILY au_cba_hado_p01_7798_dil_enr_sap_omn_dly_fncltrantest_tfm_cmd
