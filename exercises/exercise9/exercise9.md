1. Explain how the number of defects remaining in your software at delivery affects
the product support. Draw a chart illustrating the correlation.

- #### Higher Support Costs: 
More defects mean more customer-reported issues, increasing the workload for support teams (tickets, troubleshooting, patches).

- #### Lower Customer Satisfaction: 
Frequent bugs lead to frustration, negative reviews, and loss of trust.

- #### Increased Maintenance Effort: 
Support teams spend more time fixing defects rather than improving the product.

- #### Longer Resolution Times:
A high backlog of defects slows down fixes for critical issues.

- #### Higher Risk of Escalations: 
Severe defects may require emergency patches or managerial involvement.

Exponential collaboration between nr. of support hours and nr. of bugs 

2. Give 5 arguments for and against developers testing their own programmes.

#### For:
- Deep understanding of the code : developers know the internal logic, edge cases, and intended behavior better than anyone else.
- Faster debugging & iteration: immediate feedback allows quick fixes without waiting for QA.
- Ownership & accountability: encourages developers to write cleaner, more maintainable code.
- Cost-Effective for small teams: in startups or small projects
- Early detection of defects: unit tests catch bugs before they reach integration.

#### Against :
- Blind spots & bias: developers may unconsciously test only "happy paths" and miss edge cases.
- Time constraints lead to rushed testing: pressure to deliver quickly may result in superficial tests.
- Lack of testing expertise: not all developers are trained in systematic testing
- Conflict of interest: developers may downplay bugs to avoid rework.
- Misses real-world usage scenarios: developers test how code should work, not how users actually use it.

3. Create equivalence classes for testing the software from Exercise 6. Generate
   corresponding test cases. Implement them as unit tests.
Think about edge testing:
- content of the website changes 
- content doesn't change 
- download of the website fails 
- different strategies 
- what if the website is empty 
- what if the server is not available 
- what if url is not valid 

import static org.junit.jupiter.api.Assertions.*; -> why static?

When a member is declared static , it belongs to the class rather than instances of the class.
That means that we can call it without the instance being created 

4. What is regression testing?

Regression testing is the process of re-running previously executed test cases on modified software to ensure that new changes (e.g., bug fixes, feature updates) do not introduce new defects or break existing functionality.

Prevents "one fix breaks another" scenarios. Ensures software stability after updates.


5. Draw an illustration of the term black box testing and white box testing and
   describe the difference. 

#### Black Box Testing:
is a software testing method where the internal structure, design, and implementation of the system are not known to the tester. The tester interacts with the software solely through its inputs and outputs, treating it as a "black box."

Example: 
- beta testing: an opportunity for real users to use a product in a production environment to uncover any bugs or issues before a general release. Beta testing is the final round of testing before releasing a product to a wide audience.
- ui/ux testing 

Use:
- Validating requirements
- Testing user workflows
- Checking UI/API contracts

Pro:
- User perspective
- No programming skill needed

Con:
- May miss edge cases
- Limited coverage

#### White Box Testing
is a method where the tester examines the internal code structure, logic, and implementation of the software to design test cases.

Example: unit test 

Use: 
- Ensuring all code paths are tested
- Finding memory leaks
- Optimizing critical algorithms

Pro:
- Finds hidden errors
- Optimizes code


Con:
- Time-consuming
- Requires coding skills