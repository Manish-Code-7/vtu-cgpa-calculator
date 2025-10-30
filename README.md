# SQA Demonstration: Good vs Bad CGPA Calculator

This project is a live demonstration of **software quality assurance (SQA)** principles using two implementations of a VTU-style CGPA calculator:

- **Good CGPA Calculator** (accurate, robust, fully tested)
- **Bad CGPA Calculator** (deliberate mistakes, logic bugs, and poor SQA)

The UI and backend are set up for you to easily compare how software quality, validation, and testing impact functionality and user experience.

## 🚀 Project Structure

- `/src/main/java/com/example/vtucgpa/`
    - `controller/` — Routing for good/bad demos
    - `service/` — Core business logic for both versions
    - `model/` — Request/response models for both flows
- `/src/main/resources/templates/`
    - `index.html` — Beautiful SQA landing page
    - `good-cgpa.html` — Good calculator flow
    - `bad-cgpa.html` — Bad calculator flow
- `/src/test/java/com/example/vtucgpa/`
    - `GoodCGPAServiceTest.java` — Demonstrates correct logic and robust tests
    - `BadCGPAServiceTest.java` — Demonstrates logic and SQA failures

## ✨ Try it out

1. **Landing page:** see the SQA lesson. Pick Good or Bad example to explore.
2. **Good Calculator:** input semesters/SGPAs, get correct CGPA and accurate percentage (VTU: CGPA × 9.5).
3. **Bad Calculator:** uses flawed logic (missing latest SGPA, wrong conversion), poor/no validation!
4. **Tests:** run both test classes to see SQA impact.

## 🧑‍🎓 Learning outcomes
- SQA means defending against mistakes, validating user input, and making code/test maintenance easy.
- You instantly see how bugs and anti-patterns surface in the bad flow.
- Automated tests show how easily bad software is caught vs. passing, robust code.

## 🏗️ How to run

Standard Spring Boot project:
- Use your favorite IDE, or run `mvn spring-boot:run` in project root.
- Visit `http://localhost:8080/` and follow links.
- To test: `mvn test` (JUnit)

---

Built for SQA demonstration and classroom use. Contributions welcome!
