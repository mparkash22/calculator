# Calculator Application

This project is a full-featured calculator application that includes both basic and scientific calculation functionalities. The frontend is built with React.js, providing a responsive and user-friendly interface, while the backend is developed using Java Spring Boot, offering robust and scalable API endpoints for calculations.

## Features

- **Basic Calculations**: Addition, subtraction, multiplication, division, modulus, etc.
- **Scientific Calculations**: Trigonometric functions, logarithms, exponentiation, roots, and more.
- **RESTful API**: Backend endpoints to perform all calculator operations.
- **Interactive Frontend**: Built with React.js for a modern and intuitive user experience.
- **Separation of Concerns**: Clean separation between frontend and backend for maintainability and scalability.

## Technologies Used

- **Frontend**: React.js, JavaScript, HTML, CSS
- **Backend**: Java, Spring Boot, REST API
- **Build Tools**: Maven or Gradle (for Java), npm/yarn (for React)

## Getting Started

### Prerequisites

- Node.js and npm installed (for React frontend)
- Java 11+ and Maven or Gradle (for backend)
- Git

### Clone the Repository

```bash
git clone https://github.com/mparkash22/calculator.git
cd calculator
```

### Backend Setup (Spring Boot)

1. Navigate to the backend directory if applicable (e.g., `cd backend`).
2. Install dependencies and run the application:

```bash
./mvnw spring-boot:run
# or with Gradle
./gradlew bootRun
```

3. The backend will start on [http://localhost:8080](http://localhost:8080).

### Frontend Setup (React.js)

1. Navigate to the frontend directory if applicable (e.g., `cd frontend`).
2. Install dependencies and start the development server:

```bash
npm install
npm start
```

3. The frontend will start on [http://localhost:3000](http://localhost:3000).

## API Endpoints

The backend exposes RESTful endpoints for calculation. Example:

- `POST /api/calculate/basic` – for basic operations (expects JSON body)
- `POST /api/calculate/scientific` – for scientific operations (expects JSON body)

Request and response formats can be found in the API documentation or by inspecting the code.

## Folder Structure

```
calculator/
├── backend/        # Java Spring Boot backend
│   └── src/
│       └── main/
│           └── java/
│               └── ... (Controllers, Services, Models)
├── frontend/       # React.js frontend
│   └── src/
│       └── ... (Components, Pages, Styles)
├── README.md
```

## Contributing

Contributions are welcome! Please open issues or pull requests for enhancements and bug fixes.

## License

This project is licensed under the MIT License.

---

**Author:** [mparkash22](https://github.com/mparkash22)

For any questions, feel free to open an issue in this repository.
