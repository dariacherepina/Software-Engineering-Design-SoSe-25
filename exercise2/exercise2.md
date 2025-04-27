## Exercise 2
Please prepare a written documentation for each task before the exercise date. Repeated
failure to prepare the worksheet may result in not being admitted to the exam. You may
also be asked to present your solution in class.


1. #### Research the SaaS company personio.com. 
- What functionality does its software offer to its clients?
  
*The company develops software that simplifies or automates HR management processes for smaller companies.*

*For example:
Core HR,
Time Tracking,
Absence Management,
People Analytics,
Workflow Automation,
Documents & E-Signature,
Talent Management,
Recruiting,
Workforce Planning,
Performance & Development,
Compensation Management,
Employee Engagement,
Surveys,
Whistleblowing,
Payroll,
Preliminary Payroll*
- Please draw an structural architecture that models this HR software. Think about proper components and interfaces to existing 3rd party services. 
![img.png](img.png)
- Describe your architecture and categorise your work within existing architectural styles.

*I would use the microservice architecture, this means that each service is independent and self-contained(loosely coupled). They would interact with each other through the API Gateway. And this way they can be independently developed as well as independently deployed.*
2. Read the paper Yahyavi.pdf pages 1-20 (see CampUAS). Describe this architectural style and compare it to the styles mentioned in the lecture.
3. #### Develop illustrations of the 4 architectural views for the system design of a ticket machine used by passengers at a railway station.
I drew them in drawio, and the resolution would be better if it's open from the file. The link to the drawio file is below.
https://drive.google.com/file/d/1WIX7i4dUR_-jsKDzNALM21SidxNKzSat/view?usp=sharing
4. #### Suggest an architectural pattern for the following software systems. Why would you recommend this pattern here?

*a) A whistleblowing system on the internet: I would choose publisher-subscriber architecture, because it can notify investigators of new submissions without linking back to whistleblowers.*

*b) A video conferencing system: I would choose client-server architecture, mainly because it would manage room creation, authentication, and media routing through a central server.*

*c) A GPS tracker for cats: It could be either Even-driven architecture or IOT architecture. In even driven one the main point is to get messages(events) where the cat currently is, this would also keep it lightweight.* 

*In IOT architecture it would be implemented the following way: Edge Device (the collar) ➔ Cloud Backend ➔ Mobile/Web Frontend. That means we take the information(GPS location), send it to the cloud storage, and then we can send it to the frontend(mobile app), where the owners could check the location of the cat.*