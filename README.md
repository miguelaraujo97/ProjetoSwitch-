# Project Management (Scrum) Web App #

This is a webapp being developed for a Software Development Course.
Note: this is an unfinished, currently in development project used for learning purposes.

Some context of the Business needs:
```
An IT company needs to develop a project management system in order to improve its internal 
software development process (SDP) management, covering both the people and activities 
involved in developing software projects. The company uses scrum for project management. 
Taking into account the number of projects and the human resources involved, and the 
continuous changes that occur in this context, there is clearly the need for a centralized and 
flexible information system. The company needs a solution that adapts to each employee’s 
profile, providing the necessary features for each profile, maximizing the productivity and 
competitiveness of the organization. 
The objective of this project is to build a project management solution for this company, 
including its resources and activities, in functionalities that should cover the following 
functional groups: 
* Administration: manage user accounts and associate the profiles available to solution 
administrators, manage resources and general configuration; 
* Project management: project creation and management, including the creation of 
activities, association of resources to projects and record of progress in activities; 
* Reporting: Generate reports aligned with user profile’s needs. 
```

[Glossary](docs/Glossary.md)

To run the backend simply run the projectApplication. Currently, it is being exposed in http://localhost:8080/. This allows a simple view of the possible REST API endpoints with Swagger.

To run the frontend, which is being developed in React, please use the webapp folder and run the commands npm install and npm start. It is being exposed in http://localhost:3000/.

The architecture used is Onion.
The domain was modelled using Domain Driven Design. A simple diagram in svg format can be found below (open in a new tab to zoom in):

![](docs/Sprint07/DDD_Aggregates_latest_MacroVision.svg)

