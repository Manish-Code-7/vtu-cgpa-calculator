package com.example.vtucgpa;

import com.example.vtucgpa.model.BadCGPASemesterRequest;
import com.example.vtucgpa.model.BadCGPASemesterResult;
import com.example.vtucgpa.service.BadCGPAService;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.Collections;

public class BadCGPAServiceTest {
    private BadCGPAService service;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("[BadCGPAServiceTest] --- BEFORE ALL TESTS ---");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("[BadCGPAServiceTest] --- AFTER ALL TESTS ---");
    }
    @BeforeEach
    public void beforeEach() {
        System.out.println("[BadCGPAServiceTest] Before Each Test");
        service = new BadCGPAService();
    }
    @AfterEach
    public void afterEach() {
        System.out.println("[BadCGPAServiceTest] After Each Test");
    }

    @Test
    public void testSkipLastSemesterBug() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(3);
        req.setSgpaList(Arrays.asList(7.0, 8.0, 9.0));
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        // CORRECT CGPA: 8.0, BAD LOGIC returns 7.5 (skips last semester)
        Assertions.assertEquals(7.5, result.getCgpa(), 0.01, "BUG: Skipped last semester, CGPA should be 8.0!");
        Assertions.assertEquals(75.0, result.getPercentage(), 0.01, "BUG: Percentage is also wrong due to bad conversion!");
    }

    @Test
    public void testWrongPercentageFormulaBug() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(4);
        req.setSgpaList(Arrays.asList(6.0, 7.0, 8.0, 9.0));
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        // BAD: not using the VTU (CGPA*9.5) formula, but CGPA*10 instead
        Assertions.assertNotEquals(result.getPercentage(), result.getCgpa()*9.5, 0.01, "BUG: Percentage formula (should be 9.5 multiplier, not 10)");
    }

    @Test
    public void testAllTens() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(5);
        req.setSgpaList(Arrays.asList(10.0,10.0,10.0,10.0,10.0));
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        // Skips the last semester, so CGPA < 10! Should be 10.
        Assertions.assertEquals(10.0, result.getCgpa(), 0.1, "BUG: Skipped last semester, CGPA can never reach 10.0!");
    }

    @Test
    public void testNegativeSGPA() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(3);
        req.setSgpaList(Arrays.asList(-1.0, 5.0, 6.0));
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        // Bad logic doesn't protect against negative SGPAs or validate.
        Assertions.assertTrue(result.getCgpa() < 0, "BUG: There should be validation for negative SGPA (SQA improvement)");
    }

    @Test
    public void testEmptyInput() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(0);
        req.setSgpaList(Collections.emptyList());
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(0.0, result.getCgpa(), 0.01, "OKAY: Returns 0 for empty input");
        Assertions.assertEquals(0.0, result.getPercentage(), 0.01, "OKAY: Returns 0 for empty input");
    }

    @Test
    public void testMixedHighLowBug() {
        BadCGPASemesterRequest req = new BadCGPASemesterRequest();
        req.setSemestersCompleted(4);
        req.setSgpaList(Arrays.asList(0.0, 10.0, 0.0, 10.0));
        BadCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        // Correct avg would be 5.0, bug will be (0+10+0)/3 = 3.33
        Assertions.assertEquals(3.33, result.getCgpa(), 0.01, "BUG: Skipped last value, CGPA incorrect (should be 5.0)");
    }
}
