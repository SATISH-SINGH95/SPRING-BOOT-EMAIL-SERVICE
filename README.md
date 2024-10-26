# SPRING-BOOT-EMAIL-SERVICE
This project contains, How to send email using Spring boot. You can send email to multiple peoples and can also attach files in your email.

## Overview
This project demonstrates how to send emails using Spring Boot. It covers sending email with/without attachment, with/without html content.

## Features

- Send email to single or multiple persons with/wihtout attachments, with/without HTML content.

## Prerequisites

- Java 8 or higher
- An email account (e.g., Gmail) for testing the email sending functionality
  
## Getting Started

- **Clone the Repository**
```bash
git clone https://github.com/SATISH-SINGH95/SPRING-BOOT-EMAIL-SERVICE cd SPRING-BOOT-EMAIL-SERVICE
```

- **Configure Email settings**
Update the application.properties file with your email server details. For example, if you are using Gmail, you can configure it as follows:

```bash
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```
Note: It is recommended to use application-specific passwords and not your actual email password for security reasons. Additionally, consider using environment variables or a secure vault for sensitive configurations.

## Build and Run the application
```bash
mvn clean install
mvn spring-boot:run
```

## Usage
### Sending a Plain Text Email
To send email, make a POST request to **/email/send** with the following JSON payload:

- **to**: Recipient email address (e.g., 'recipient@example.com')
- **subject**:  Email subject (e.g., 'Test Email with Attachment')
- **message**: Recipient email address (You can add html tag as well in message area)
- **file**: File to be attached (select a file from your system)

```bash
{
    "to": ["recipient@example.com", "XYZ@gmail.com"],
    "cc": ["cfr@example.com","abc@gmail.com"],
    "bcc": ["rte@example.com", "pqr@gmail.com"],
    "subject": "Meeting Reminder",
    "message": "This is a reminder for our meeting scheduled tomorrow at 10 AM. <h1>This is an HTML email</h1><p>with some <b>bold</b> text.</p>""
}
```
```

- **API Endpoints:**
    - **`POST** /api/v1/email/send   -> Send email with/without attachemt, with/without html content

## URL
POST URL for the endpoint will be
```bash
http://localhost:8081/api/v1/email/send
```

## Dependencies
- **spring-boot-starter-mail**
- **spring-boot-starter-web**
- **lombok**
- **spring-boot-starter-tomcat**
- **spring-boot-starter-validatior**
- **springdoc-openapi-starter-webmvc-ui**

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## Contact
For questions or feedback, please contact sk95satish@gmail.com



