import org.junit.jupiter.api.*;
/**
 * --------------------------------------------------------------------------
 *
 * JUnit 5 Framework recommends:
 *
 * --------------------------------------------------------------------------
 *
 * Methods annotated with @BeforeAll and @AfterAll must be static.
 *
 * --------------------------------------------------------------------------
 */

public class SetUpAndTearDownExample {
    @BeforeAll
    public static void setUpClass(){
        System.out.println("setUpClass() | Prints before everything, only once.");
    }

    @AfterAll
    public static void tearDownClass(){
        System.out.println("tearDownClass() | Prints after everything, only once.");
    }
    @BeforeEach
    public void setUp(){
        System.out.println("setUp() | Prints before each test.");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("tearDown() | Prints after each test.");
    }

    @Test
    public void test1(){
        System.out.println("Test 1");
    }
    @Test
    public void test2(){
        System.out.println("Test 2");
    }
    @Test
    public void test3(){
        System.out.println("Test 3");
    }
}
