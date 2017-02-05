aws-monitor
===========

aws-monitor is simple monitoring server for AWS, by one JAR file (Spring Boot).

NOTE: aws-monitor is still in development.

# How to use

## Setup

If you don't to setup aws configuration, you shoud setup now. (~/.aws/config etc...)

aws-monitor is requied aws profile named "default".

### To developers

If you want to use other profile, you shoud change code on AppConfiguration#defaultAwsApiConfig.

```java:AppConfiguration#defaultAwsApiConfig
return new AwsApiConfig().withRegions(Regions.AP_NORTHEAST_1).withProfile("default");
```

And, you want to monitor region other than "AP_NORTHEAST_1", you shoud change code on AppConfiguration#defaultAwsApiConfig, too.

## Start/Stop

* start
  *  ```java -jar aws-monitor-0.1.0.jar```
* stop
  * Press Ctrl+C or Send SIG_TERM

## Request/Response

* ```/aws```

```bash
% curl -XGET "http://localhost:8080/aws" | jq .                                                                                                                                    ✭
{
  "statusMap": {
    "Ec2Status": {
      "countAll": 8,
      "countRunning": 1,
      "name": "Ec2Status"
    },
    "IamStatus": {
      "countUsers": 13,
      "countGroups": 11,
      "countRoles": 27,
      "name": "IamStatus"
    }
  },
  "name": "AwsStatus"
}
```

* ```/aws/ec2```
  * countAll: Count of All EC2 Instances.
  * countRunning: Count of EC2 Instances that is "runngin" state.

```bash
% curl -XGET "http://localhost:8080/aws/ec2" | jq .                                                                                                                                ✭
{
  "countAll": 8,
  "countRunning": 1,
  "name": "Ec2Status"
}
```

* ```/aws/iam```

```bash
% curl -XGET "http://localhost:8080/aws/iam" | jq .                                                                                                                                ✭
{
  "countUsers": 13,
  "countGroups": 11,
  "countRoles": 27,
  "name": "IamStatus"
}
```

## Errors

* Not found AWS Profile

```bash
% curl -XGET "http://localhost:8080/aws" | jq .                                                                                                                                    ✹
{
  "timestamp": 1486281022415,
  "status": 500,
  "error": "Internal Server Error",
  "exception": "com.amazonaws.SdkClientException",
  "message": "Unable to load credentials into profile [api_aws_iam]: AWS Access Key ID is not specified.",
  "path": "/aws"
}
```
* No permission to read status for AWS Service

```bash
% curl -XGET "http://localhost:8080/aws" | jq .                                                                                                                                    ✹
{
  "timestamp": 1486281054396,
  "status": 500,
  "error": "Internal Server Error",
  "exception": "com.amazonaws.services.ec2.model.AmazonEC2Exception",
  "message": "You are not authorized to perform this operation. (Service: AmazonEC2; Status Code: 403; Error Code: UnauthorizedOperation; Request ID: 4ae0e8e7-0bcb-4858-a875-a970070cc991)",
  "path": "/aws"
}
```
