#!/bin/bash

CAMUNDA_URL=http://localhost:8080
DEPLOYMENT_NAME='My Deployment'

 curl -X POST \
    $CAMUNDA_URL/engine-rest/deployment/create \
    -H 'content-type: multipart/form-data; boundary=----a0b7961d-7e05-4f16-be5e-badafbe91233' \
    -F deployment-name=$DEPLOYMENT_NAME \
    -F bpmn=@src/main/resources/growthProcess.bpmn