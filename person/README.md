# Person Management — Spring Boot + MongoDB + Vue 3

Ready-to-run source project based on `personmanagement.docx`. Uses one Vue component (no Router or Pinia), Axios, and a Vite `/api` proxy.

## Requirements
- Java 17 or newer
- Maven installed (`mvn -v`)
- MongoDB running locally on port 27017
- Node.js 20.19+ or 22.12+ and npm
- Internet access for the first Maven and npm dependency installation (unless dependencies are already cached)

## Start backend (Terminal 1)
```bash
cd backend
mvn spring-boot:run
```
Backend URL: http://localhost:8080/api/people

## Start frontend (Terminal 2)
```bash
cd frontend
npm install
npm run dev
```
Open the local URL shown by Vite (usually http://localhost:5173).

## CRUD endpoints
- GET `/api/people` — all people
- GET `/api/people/{id}` — one person
- POST `/api/people` — add
- PUT `/api/people/{id}` — update
- DELETE `/api/people/{id}` — delete

POST example JSON: `{ "name": "Rahul", "age": 20 }`

## Notes
- Uses Spring Boot 4.1.1 and `spring.mongodb.uri` (the correct property for this version).
- Database is `persondb`; collection is **`people`** as in the document (not `person`). MongoDB creates them when first written.
- Backend port is **8080**, matching the document and Vite proxy. If you switch to 9090, change `server.port` and `frontend/vite.config.js` together.
- Fixed three malformed Axios URL expressions in the uploaded document: GET by ID, PUT, DELETE. Added simple error messages.
- The document's simple behavior is retained: unknown IDs return null; PUT with a new ID may insert.
- `node_modules`, `target`, and Maven dependencies are not bundled; install once via npm/Maven.
