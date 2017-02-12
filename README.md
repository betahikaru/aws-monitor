aws-monitor
===========

aws-monitor is simple monitoring server for AWS, by one JAR file (Spring Boot).

NOTE: aws-monitor is still in development.

# How to use

## Setup

Two ways to specify Aws Credentials,
IAM User Credential Keys or AWS Profile Name, by Environment Valiable.
Default is AWS Profile named "default".

* IAM User Credential Keys
  * ```AWS_ACCESS_KEY_ID```
  * ```AWS_SECRET_ACCESS_KEY```
* AWS Profile Name (~/.aws/config)
  * ```AWS_PROFILE```


Specify Region by Environment Valiable. Default is "ap-northeast-1".

* ```AWS_REGION```

## Start/Stop

* start
  *  ```java -jar aws-monitor-0.2.0.jar```
* stop
  * Press Ctrl+C or Send SIG_TERM

## Request/Response

* ```/aws```

```bash
% curl -XGET "http://localhost:8080/aws" | jq .
{
  "statusMap": {
    "S3Status": {
      "countBuckets": 5,
      "bucketNames": [
        "aaa.betahikaru.com",
        "bbb.betahikaru.com",
        "ccc.betahikaru.com",
        "ddd.betahikaru.com",
        "eee.betahikaru.com"
      ],
      "name": "S3Status"
    },
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
  * countRunning: Count of EC2 Instances that is "running" state.

```bash
% curl -XGET "http://localhost:8080/aws/ec2" | jq .
{
  "countAll": 8,
  "countRunning": 1,
  "name": "Ec2Status"
}
```

* ```/aws/iam```
  * countUsers: Count of IAM Users.
  * countGroups: Count of IAM Groups.
  * countRoles: Count of IAM Roles.

```bash
% curl -XGET "http://localhost:8080/aws/iam" | jq .
{
  "countUsers": 13,
  "countGroups": 11,
  "countRoles": 27,
  "name": "IamStatus"
}
```

* ```/aws/s3```
  * countBuckets: Count of S3 Buckets.
  * bucketNames: S3 Buckets Name list.

```bash
% curl -XGET "http://localhost:8080/aws/s3" | jq .
{
  "countBuckets": 5,
  "bucketNames": [
    "aaa.betahikaru.com",
    "bbb.betahikaru.com",
    "ccc.betahikaru.com",
    "ddd.betahikaru.com",
    "eee.betahikaru.com"
  ],
  "name": "S3Status"
}
```

## Errors

* Not found AWS Profile

```bash
% curl -XGET "http://localhost:8080/aws" | jq .
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
% curl -XGET "http://localhost:8080/aws" | jq .
{
  "timestamp": 1486281054396,
  "status": 500,
  "error": "Internal Server Error",
  "exception": "com.amazonaws.services.ec2.model.AmazonEC2Exception",
  "message": "You are not authorized to perform this operation. (Service: AmazonEC2; Status Code: 403; Error Code: UnauthorizedOperation; Request ID: 4ae0e8e7-0bcb-4858-a875-a970070cc991)",
  "path": "/aws"
}
```
