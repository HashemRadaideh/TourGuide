Jordan University of Science & Technology
SE432: Dr. Luay Alawneh, Software Engineering Department
Group Project: 2-3 students
**Deadline: 31-May- 2023**
A Tourist Guide for Traffic and Litter Violations
Following the MVC architectural pattern, you should use Java Servlets/JSPs/HTML to develop a tourist guide for traffic
and litter violations in a city. The application should do the following:
Actor: Tourist:

1. The tourist should be able to view a city and see information about safety and cleanliness.
Actor: Reporter (could be any user)
1. The user opens the home page
2. The user selects a button or link to report a violation
a. A new form will open and the user should enter the following:
i. Date (automatically entered)
ii. Enter phone number (required field)
iii. Country (required field) [dropdown list]
iv. City (required field) [dropdown list, based on the country]
v. Upload a picture/video – max 20 MB- of the violation (required field)
1. You can save the picture/video to the database or save a link to the file.
vi. Select the type of violation: (required field)
1. Red light crossing
2. Running a stop sign
3. Jaywalking
4. Littering
3. The user then submits the report
Actor: Admin
1. The admin checks the reports and approves/disapproves them
2. If the same user (phone number) reports false violations more than 3 times, the user will be blocked.
3. A violation will expire after 1 month.
4. An expired violation is (inactive) and will not be counted in deciding the status (see next).
Actor: System
1. If 10 active traffic light violations were reported in the same city, then this city is considered **Dangerous**.
2. If 10 active stop sign violations were reported in the same city, then this city is **Dangerous**.
3. If 10 active Jaywalking violations were reported in the same city, then the residents of this city are **Insane**.
4. If 10 active littering violations were reported in the same city, then this city is considered **Dirty**.
- Each city may be Clean or Dirty
- Each city may be Safe or Dangerous
- The residents of the city may be Sane or Insane
➔ You can add different countries and cities to the database.
➔ You need to provide test cases that cover all the scenarios.
