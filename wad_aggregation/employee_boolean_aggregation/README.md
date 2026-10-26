# employee_boolean_aggregation

## Requirements
- Java 17+, Maven, Node.js 20+, MongoDB running at localhost:27017.
- Spring Boot backend: port 8080; Vue/Vite frontend: default port 5173.

## Start backend
```bash
cd backend
mvn spring-boot:run
```

## Start frontend (in a second terminal)
```bash
cd frontend
npm install
npm run dev
```
Open the URL Vite prints (normally http://localhost:5173). Run one project at a time because each uses port 8080.

## Notes
- IDs are entered manually. The database is created automatically when MongoDB accepts the first write.
- Uses a simplified exam-friendly Controller -> Service -> Repository structure, with MongoTemplate aggregation in the service.
- Boolean leave eligibility: a leave request is saved and eligible employee names are selected through aggregation. This version does not deduct balances or have approval statuses.
- This is learning/exam code, not concurrency-safe production booking software.
