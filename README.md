
# Student Result Management System (SRMS)

A full-stack web application for managing and viewing student academic results.

# Features
- Admin login and student login
- Add, edit, and delete student records
- Add and update marks
- Automatic calculation of averages and percentages
- View results with a clean and simple interface
- Full-stack integration using REST APIs

 # Tech Stack
- Frontend:React.js, HTML, CSS, JavaScript, Axios
- Backend: Spring Boot, Spring Data JPA, Java
- Database: MySQL
- Build Tools: Maven & npm
- Server: Embedded Tomcat

# NOTE 
-since this project uses maven file the entire folder cannot be uploaded (eclipse is Used for backend and vs code for frontend )
-only the code files are uploaded in this repository 
-this project requires NPM to run and uses maven in backend 
-the output is attached in the PPT file
- the file structure is
- 
- student-result-management-systemm/ (this is the entire project file).
│
├── src/                      
│   ├── main/java/com/srms/
│   │   ├── controller/       
│   │   ├── model/            
│   │   ├── repository/       
│   │   └── SrmsApplication.java  
│   └── main/resources/
│       └── application.properties 
│
├── pom.xml                   
│
├── tempfrontend/            
│   ├── public/              
│   ├── src/                  
│   │   ├── components/
│   │   │   ├── AdminDashboard.js
│   │   │   ├── AdminLogin.js
│   │   │   ├── StudentDashboard.js
│   │   │   └── StudentLogin.js
│   │   ├── App.js
│   │   ├── index.js
│   │   └── App.css
│   ├── package.json          
|   └── README.md                          

