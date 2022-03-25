# QA-FinalProject-Drydock

#### This repo houses my final project source code and deliverable for the QA Bootcamp I have attended
---
### What am I doing and why?

I have been attenting a QA Coding Bootcamp and have been issued with a final project to demonstrate the skills I have gained throughout. My completed project will aim to cover all of the areas studied during the bootcamp and will be documented accordingly below.

---
### How I expected the challenge to go

I expect I will be able to provide a satisfactory end result to the projects brief with the inclusion of some stretch goals that have been set, if not all. My biggest concern with the project and its completion is the documentation and the level of detail necessary. To circumvent this concern I have made the decision to break down the five day project into a number of 'sprints' (while acknowledging a traditional sprint is two weeks), this should not only help to illustrate my competency with Jira software, but will also serve me as a reminder to document and manage my continuous integration (with git version control) as my application develops.

---
### What went well? / What didn't go as planned?

I feel my implementation of a date field and its logic went well, given that I had not considered the difficulty it might pose at the point of planning; I soon discovered that when implementing a date field I needed a way to be able to stamp records with the current date as they are passed into the database. I am happy with my solution to this challenge. I believe I managed my time well throughout the project and kept consistent with my Git usage and documentation of my journey via my dev diary (link below). Even when issues presented themselves I was able to circumvent many of them with some dedication. Despite overall being disappointed with my finished product, I feel my execution was satisfactory, there is much I would change within the project architecture and implementations as a whole, however, there is not much I would have changed in my approach and planning tactics with perhaps the single exception of being more thorough.

Overall I am happy with the fact I satisfied most stretch goals, such as; making use of a DTO class, implementing Lombok, implementing custom queries, making use of custom exceptions within my service file and achieving over the 80% benchmark for coverage (85.6%).

What didn't go as planned?
As mentioned I am ultimately disappointed in my end product. I feel my code is messy and not the best it could be in many areas. I believe with better planning and more thought into the structuring of my code I would have produced a better finished product. I would have liked to have completed some of my personal stretch goals such as implement a basic UI, or begin a V2 version of the application making use of another database table to hold order history. I would have liked to have implemented some interactive functions and variables such as a resource amount field, and company balance field and a function to 'complete' orders which would impact the former mentioned fields appropriately. 

I believe I have much room for improvement when it comes to writing tests for my functions; there are a few things I would like to make note of here with regard to the projects testing.

Without full consideration to what implementing a generated date field would entail I soon realised that testing against a date which is (because of how it was implemented) generated at the time a request is processed, my create order integration test lacks a body check, I was unable to find a satisfactory way around the generated date field for comparison with the result returned from the service call. 
Within the service unit tests I made use of ".usingRecursiveComparison().ignoringFields" to satisfy my comparisons against generated dates and costs. One major revision I would make in future similar projects would be other ways to generate these values so that tests were not required to omit these fields.
 
---

### Possible improvements for future revisions of the project

I can think of many possible improvements. The most notable which come to mind would be how the dates and costs or rather where they are generated from.
Furthermore, the date field would be much better with an extra date field for TIMESTAMP; this would provide more flexibility with cutom queries such as filtering orders between specific times and or dates. It would also provide the ability to return specific times in the requests which return lists of orders as opposed to singular orders.

A project such as this would definitely benefit from more tables, an order history table and an accounts table. It is with these extra tables I could implement better and more interactive functionality.

With the projects current state, one immediate revision I would like to make would be to implement the only User story left on my "should have" section of my MoSCoW prioritisation - which was to implement a dummy "complete order" function, but at this point in time if implemented all it would do is delete the record formthe database simulating a completion whereas I would prefer to move said order to the order history table instead.

---
## Project Overview

Your objective with this project is to achieve the following:

To create a Spring Boot API, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training.

Specifically, you are required to create a Spring Boot API using:

    An application back-end developed using the language from your Programming Fundamentals module (e.g. Java)
    
    A managed database hosted locally or within the Cloud Provider examined during your Cloud Fundamentals module (e.g. H2 or MySQL (local / GCP)
    
    A means of making API calls (Postman) and a means of checking persistence (Workbench/H2 console)

---
## Important Links

Jira Board:
* https://ghqa.atlassian.net/jira/software/projects/QFD/boards/1/roadmap

MoSCoW Prioritisation Board:
* https://trello.com/b/GwIQmA7i/qa-finalproject-drydock-moscow

Developer Diary:
* https://ghqa.atlassian.net/wiki/spaces/QFD/pages/33170/Dry+Dock+Dev+Diary

Drydock Project Risk Assessment:
* https://ghqa.atlassian.net/wiki/spaces/QFD/pages/196613/Dry+Dock+Risk+Assessment

---
## Project Scenario

In this projects scenario I imagine I have been tasked as an employee of a Drydock to implement a new order management system which should utilise a Rest API application as a backend written in Java.

The Application must include the minimum CRUD functionality (Create, Read, Update and Delete) and handle HTTP requests to perform basic tasks such as create an order and commit said order to a database for data persistence. 

---
## Project Summary

To draw my project to a close I will provide some screenshots (which can all be found within the screenshot folder of the documentation folder) of some of my postman requests and responses. Below that I will provide some evidence in the form of screenshots to show peristence of data in mySQL. And finally a screenshot of my test results and coverage.

---
### Postman Responses

#### Get All Orders Response
![All](https://i.imgur.com/sVnpAye.png)

#### Get All Order By Date
![All](https://i.imgur.com/AA939kx.png)

#### Get All By Customer Specified
![All](https://i.imgur.com/PO7AlA4.png)

---
### MySQL Persistence Evidence
![All](https://i.imgur.com/oK8zyee.png)

---
### Test Results And Coverage
![All](https://i.imgur.com/MANvxzl.png)
