# Day 1 Things acheived

- Spring Initialiser :- Created a project (Gradle project)
- Import to VS Code
- Controller class and annotated with @RestController and addded mappings

## Manually did CI Steps

- gradle clean build
- docker build
- docker push
- kubectl run and deployed in cluster and accessed it via busybox container

## Automated it with Jenkins

- Created github account and pushed this code
- Installed jenkins locally
- and configured employee management to run CI automatically

## Mysql

- k port-forward svc/mysql-db 3307:3306
- mysql -h 127.0.0.1 -P 3307 -u root -p
- show databases
- show tables
- k port-forward svc/employeemanagement-svc 8082:8080

## Jenkins

- docker run -d -p 8080:8080 -p 50000:50000 -v ~/Desktop/Sarthak/jenkins_data:/var/jenkins_home jenkins/jenkins:lts
- docker logs (To see the password)
- http://localhost:8080



![learningNotes](artefacts/learningNotes.png)