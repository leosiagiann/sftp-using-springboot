# Transfering a file through SFTP in Springboot

This Spring Boot application demonstrates secure file transfer through the SFTP protocol.
But before you run the application you need to configure the server in your local.
You can follow this link to configure the server using openSSH for Windows : https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_install_firstuse?tabs=gui#install-openssh-for-windows

## Requirements

- Java 17
- Spring Boot 3.2.2

## How to run
- Configure your sftp client host, user, etc according to your openSSH configuration
- Make sure the server port is available (Tips: Check on Command Prompt "netstat -aon")
### Using Maven
- Change directory to project
- mvn clean install
- mvn spring-boot:run
### Using Intellij
You can make it simple using Intellij