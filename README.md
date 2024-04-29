Please note: Firefox throws JavaScriptError due to googlemanager, did not have time to fix this due to the ad blocker automatically enabling itself when an automated test is done. 
This prevented me from getting the coordinated for the sliders (happy to answer any questions around this). Chrome became extremely slow which also prevented me from getting the coordinates there.
but the code us there

The challenge - UI Automation

Instructions
 

Requirements:

You can use any language or programming tool of your choice

Please upload your code to github and share the project URL with us

Ensure that the code is runnable and the steps to set up the project are included in a readme

# BMW.comassessment
This is an assessment provided by bmw for Mlungisi Mbele to complete

# Selenium Framework

The aim of this project is to create a test automation framework that will be used for testing Web applications. This is a Selenium framework designed using Page Factory.

## Getting Started

This project is a Maven project. To access this project, import this project directly from github into your IntelliJ IDE or clone the Repository in your local machine and open the Maven project from your local directory into IntelliJ.

## Prerequisites

```bash
IntelliJ IDEA
JRE-8.0.x
JDK-8.0.x
Chrome/firefox/edge Web browser (for this test otherwise an error will be produced)

```

## Installing - following the instructions from the links
```bash
Download and Install:
1.	IntelliJ IDE  - https://www.guru99.com/intellij-selenium-webdriver.html#2
4.	JRE and JDK  - https://docs.oracle.com/javase/7/docs/webnotes/install
5.	Chrome, firefox and edge

```

## Framework
### Tools
```bash
Web Testing Framework: Selenium Webdriver
Build Tool: Maven
Testing Tool: TestNG
Programming Language: Java with some JavaScript Executor
```

### Design
Framework consists of following modules / packages:
•	common (web and driver utils class), 
•	pages (web applications page factory), 
•	resources( web config.properties file)

```bash
Web tests use TestNG annotations following the same structure:

@BeforeClass – This is the precondition / setup of the tests
@AfterClass – Post condition / resetting the driver into its initial state 

```

### To execute Tests

Right click on the test file and click on maven tests

### Reporting

EXTENT REPORTS which can be found under the reports folder

