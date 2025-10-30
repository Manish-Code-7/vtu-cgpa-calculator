package com.example.vtucgpa;

import com.example.vtucgpa.model.GoodCGPASemesterRequest;
import com.example.vtucgpa.model.GoodCGPASemesterResult;
import com.example.vtucgpa.service.GoodCGPAService;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.Collections;

public class GoodCGPAServiceTest {
    private GoodCGPAService service;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("[GoodCGPAServiceTest] --- BEFORE ALL TESTS ---");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("[GoodCGPAServiceTest] --- AFTER ALL TESTS ---");
    }
    @BeforeEach
    public void beforeEach() {
        System.out.println("[GoodCGPAServiceTest] Before Each Test");
        service = new GoodCGPAService();
    }
    @AfterEach
    public void afterEach() {
        System.out.println("[GoodCGPAServiceTest] After Each Test");
    }

    @Test
    public void testSingleSemester() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(1);
        req.setSgpaList(Arrays.asList(9.0));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(9.0, result.getCgpa(), 0.01);
        Assertions.assertEquals(85.5, result.getPercentage(), 0.01);
    }

    @Test
    public void testThreeSemesters() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(3);
        req.setSgpaList(Arrays.asList(8.0, 8.5, 7.5));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(8.0, result.getCgpa(), 0.01);
        Assertions.assertEquals(76.0, result.getPercentage(), 0.01);
    }

    @Test
    public void testEightSemesters() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(8);
        req.setSgpaList(Arrays.asList(7.1,7.2,7.3,7.4,7.5,7.6,7.7,7.8));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(7.45, result.getCgpa(), 0.01);
        Assertions.assertEquals(70.77, result.getPercentage(), 0.01);
    }

    @Test
    public void testAllTens() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(4);
        req.setSgpaList(Arrays.asList(10.0,10.0,10.0,10.0));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(10.0, result.getCgpa(), 0.01);
        Assertions.assertEquals(95.0, result.getPercentage(), 0.01);
    }

    @Test
    public void testAllZeros() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(6);
        req.setSgpaList(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(0.0, result.getCgpa(), 0.01);
        Assertions.assertEquals(0.0, result.getPercentage(), 0.01);
    }

    @Test
    public void testMixedEdgeSGPA() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(4);
        req.setSgpaList(Arrays.asList(0.0, 5.0, 10.0, 8.0));
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(5.75, result.getCgpa(), 0.01);
        Assertions.assertEquals(54.63, result.getPercentage(), 0.01); // 5.75*9.5
    }

    @Test
    public void testEmptyInput() {
        GoodCGPASemesterRequest req = new GoodCGPASemesterRequest();
        req.setSemestersCompleted(0);
        req.setSgpaList(Collections.emptyList());
        GoodCGPASemesterResult result = service.calculateCGPAAndPercentage(req);
        Assertions.assertEquals(0.0, result.getCgpa(), 0.01);
        Assertions.assertEquals(0.0, result.getPercentage(), 0.01);
    }
}
