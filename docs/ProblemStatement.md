# Problem Statement

## 1. Preamble

This document presents a (simulated) context in which a company intends to explore and validate a new business idea. 
To this end, the company decided to start the development of a new software product (prototype) in collaboration with the faculty and students of LETI-ESOFT.
Thus, the software product described hereinafter is, on the one hand, adapted to promote:

* the students' consolidation and acquisition of new competencies related to software development;
* the practice and internalization of the recommended working method and presented best practices, which are commonly used in the software industry;

and, on the other hand, to facilitate presenting and lecturing the contents and competencies described in the LETI-ESOFT syllabus throughout the semester.

Therefore, it will be used as a (small) scenario for demonstrations and practical exercises.

## 2. Company Presentation

**Software for Joe, S.A.** (_**S4J**_)[1] is a startup company based in Porto (Portugal) whose mission is to provide IT solutions (applications) focused and oriented to the daily needs of individual people. 

After its last success with an application that allows the management, control, and monitoring of personal expenses, the company decided to expand its product portfolio by developing an application that facilitates the registration, control and monitoring of personal tasks. 

To pursue that goal, as _**S4J**_ does not currently have the free capacity, it decided to resort to subcontracting development services to LETI-ESOFT.



[1]: A fictional company.

## 3. Intended Software Product

The application to be developed essentially aims to allow the recording of personal tasks, as well as their control and monitoring. 
In this sense, all functionalities described hereinafter are centered and are carried out by the user/person registered to use the application. 
Regarding the user registration method, user data collection (e.g., name and email) and authentication process, it is expected that this will be done through an integration with the  _JoeProfiles_ system already in use in the company.
Unregistered users can only access to generic information as, for instance, a description of the application goals and to the application credits.

At this moment, it is envisaged that a user might categorize tasks using a set of categories maintained by him/her. By simplicity, a category just comprehends a unique alphanumeric code and a brief description.

On the other hand, a task is characterized by having a unique reference, a title, an informal description, and another of a more technical nature, and effort estimation as well as the deadline to be accomplished and the category in which it fits in.

It is intended that the application is capable of alerting the user for tasks whose deadline for completion is approaching and/or has already passed.
Hence, the user must be able to record the beginning and end of tasks.

Exploring the collected information, the user must be also able to access a set of reports comprehending statistical data about his/her performance in a given period of time (e.g. week, month). 
As an example, a report might state the number of completed tasks, the amount of time spent on completing such tasks, the average of time spent on each task; while another report might show a comparison between the predicted effort and the effort really spent on each task.

While developing this system, the team must: 

* adopt the best OO software development practices, such as TDD and the application of GRASP and SOLID patterns; 
* adopt the English language as the default for development artifacts, including in the code;
* implement the core software parts (i.e, the domain business and logic) in C++; 

At last, _**S4J**_ recommends the team to concentrate their efforts on the development of the domain business logic, which should be widely verified/validated by automatic regression tests.
A rudimentary console User Interface (UI) might be developed just for demonstration purposes (e.g., Sprint Review) since,
further on, it is envisaged that the UI will consist of a mobile application which, by now, is out of scope.


## 4. Sprints

As the team should adopt a Software Development Process (SDP) relying on the Iterative and Incremental (I&I) principles,
the projectDeprecated requirements and their priorities are organized in sprints and described by means of User Stories (US).

### 4.1 Sprint 1

- **US01 -** As Non-Registered User, I want to register as an application user.

    - AC01-1. Category code cannot be empty nor have less than five chars.
    - AC01-2. Category description cannot be empty.

- **US02 -**  As Non-Registered User, I want to activate a just registered user account.

- **US03 -**  As Visitor, I want to send a request to the administrator to assign him/her a given profile.

- **US04 -**  As Administrator, I want to search for users.

- **US05 -**  As Director, I want to register/create a new projectDeprecated.

    * AC05-1. Task reference, title, and category are mandatory. The remaining data is optional.

- **US06 -**  As Administrator, I want to update profiles assigned to a user account.

- **US07 -**  As Director, I want to associate a human resource (user) to a projectDeprecated.

- **US08 -**  As Project Manager, I want to edit some projectDeprecated information.

- **US09 -**  As Product Owner, I want to create a user story and add it to the Product Backlog.

- **US10 -**  As Authenticated User, I want to update its own data (e.g., photo, function).

- **US11 -**  As Authenticated User, I want to change his/her password.

- **US12 -**  As Director, I want to create a new projectDeprecated typology

- **US13 -**  As Administrator, I want to create user profiles.

### 4.2 Sprint 2

[//]: # (The main goal of **Sprint B** is to adapt/evolve the solution developed during Sprint A in order to:)

[//]: # ()
[//]: # (* easily support objects/data persistence on multiple target systems as, for instance, relational databases, NoSQL databases or in memory databases;)

[//]: # (* apply other software patterns and principles &#40;e.g.: GoF, Repository, Service&#41;;)

[//]: # (* promote the best practice, and therefore the students' habit, of coding to the interface and not to the implementation &#40;i.e. concrete classes&#41;;)

[//]: # (* testing using mocks and/or stubs.)

- **US14 -**  As Director, I want to define the SM of a projectDeprecated.

- **US15 -**  As Director, I want to get a list of all projects.

- **US16 -**  As Director/PM/PO/SM, I want to view status of activities in a projectDeprecated.

- **US17 -**  As Authenticated User, I want to get a list of all projects I'm currently allocated to.

- **US18 -**  As PO/SM/Team Member, I want to consult the product backlog, i.e. to get the list of user stories sorted by priority.

- **US19 -**  As Team Member, I want to estimate the effort of a user story.

- **US20 -**  As Product Owner, I want to refine a broad user story of the Product Backlog into more focused user stories.

- **US21 -**  As Product Owner, I want to change the priority of a user story in the product backlog.

- **US22 -**  As Project Manager, I want to create a sprint.

- **US23 -**  As Team Member, I want to add a user story in the product backlog 1 to the sprint backlog.


### 4.3 Sprint 3

[//]: # (The main goal of  **Strint C** is to enhance the solution with a HTTP REST API through which an HTTP Client &#40;to be further developed, but not in this sprint&#41; can request/execute the features of all User Stories developed so far. )

[//]: # ()
[//]: # (The HTTP REST API should be validated through **end-to-end tests** using Postman &#40;i.e. a generic HTTP Client application&#41;.)

- **US24 -**  As Administrator, I want to get a list all user accounts and their status.

- **US25 -**  As Administrator, I want to inactivate a user account.

- **US26 -**  As Administrator, I want to activate a user account.

- **US27 -**  As Director, I want to define the PO of a projectDeprecated.

- **US28 -**  As Director, I want to get a list of all human resources in a projectDeprecated.

- **US29 -**  As Project Manager, I want to start a sprint.

- **US30 -**  As Team Member, I want to view the Scrum Board of current sprint.

- **US31 -**  As Team Member, I want to create a task in a user story.

- **US32 -**  As SM/Team Member, I want to create task outside the scope of a user story.

- **US33 -**  As SM/Team Member, I want to register work done on a Task.

- **US34 -**  As Project Member, I want to update the status (i.e.: the Scrum Board category) of a user story in the scope of the current sprint backlog.

### 5. Version description

| Version	 | Description                                    |
|----------|------------------------------------------------|
| **0**    | Base version to be used for database modelling |
| **1**    | Base version to be used in the projectDeprecated         |
| **2**    | Updated for sprint 2 and 10 new user stories   |
| **3**    | 10 new user stories                            |
