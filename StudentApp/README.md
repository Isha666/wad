# Student Management — Full Stack Practical

Based on the supplied `studentrouting.docx`: Vue 3 + Vite + Axios + Vue Router,
Spring Boot + MongoDB. Includes **routing, props, emits, GET and POST**.
The app is intentionally simple and follows the document's component layout.

## Requirements
- Java 17+ and Maven (`mvn`) installed
- MongoDB server listening at `localhost:27017`
- Node.js 22+ and npm (Node 20+ also works with the included Vite 6)

## Run
1. Start MongoDB. On Ubuntu, if installed as a system service:
   `sudo systemctl start mongod`
2. Terminal 1:
   ```
   cd backend
   mvn spring-boot:run
   ```
3. Terminal 2:
   ```
   cd frontend
   npm install
   npm run dev
   ```
4. Visit http://localhost:5173

Backend: http://localhost:8080/api/students
Database: `studentdb`; collection: `students`.

## Key files for revision
- `backend/src/main/java/com/example/studentapp/model/Student.java`: MongoDB document
- `backend/src/main/java/com/example/studentapp/repository/StudentRepository.java`: MongoRepository
- `backend/src/main/java/com/example/studentapp/controller/StudentController.java`: GET / POST
- `backend/src/main/resources/application.properties`: database URI + port
- `frontend/src/router.js`: `/add` and `/students` routes
- `frontend/src/App.vue`: `<router-link>` and `<router-view>`
- `frontend/src/components/StudentForm.vue`: parent; sends title and listens to addStudent; Axios POST
- `frontend/src/components/StudentInput.vue`: child; defineProps and defineEmits
- `frontend/src/components/StudentList.vue`: Axios GET; v-for
- `frontend/vite.config.js`: Vite proxy to port 8080

## Important compatibility change
The supplied document uses `spring.data.mongodb.uri`, which is appropriate for
Spring Boot 3. This ZIP uses **Spring Boot 4** and therefore uses
`spring.mongodb.uri`, the working configuration from your earlier lab debugging.

## Practical flow
StudentInput (child) --emit name--> StudentForm (parent)
--Axios POST--> StudentController --> StudentRepository --> MongoDB.

StudentList --Axios GET--> StudentController --> StudentRepository --> MongoDB.
