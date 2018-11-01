#!/bin/bash

# When it is true, it does not permit two deployments with same BPMN and DEPLOYMENT_NAME.
# To this flag work as expected, we need to choose a uniq DEPLOYMENT_NAME every time we want to deploy.
# So, I recommend start it with a timestamp.
# See more: https://docs.camunda.org/manual/7.9/reference/rest/deployment/post-deployment/
ENABLE_DUPLICATE_FILTERING=true

CAMUNDA_URL=http://localhost:8080

function deploy() {
  curl -X POST \
    $CAMUNDA_URL/engine-rest/deployment/create \
    -H 'content-type: multipart/form-data; boundary=----a0b7961d-7e05-4f16-be5e-badafbe91233' \
    -F deployment-name="$TIMESTAMP - $DEPLOYMENT_NAME" \
    -F enable-duplicate-filtering=$ENABLE_DUPLICATE_FILTERING \
    -F bpmn=$BPMN
}

# ======== Deployments ========

TIMESTAMP='201810291438'
DEPLOYMENT_NAME="My Deployment"
BPMN=@src/main/resources/auto_refi/growth_process/$TIMESTAMP.bpmn
deploy
