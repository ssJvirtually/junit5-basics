package tech.ssjvirtually.MathUtils;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

    Calculator calculator;
    TestInfo testInfo;

    TestReporter testReporter;

    @BeforeAll
    void intiBeforeClass(){
        //System.out.println("called before instance created ");
    }


    @BeforeEach
    void init(TestInfo testInfo , TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        calculator = new Calculator();
        testReporter.publishEntry("running test " + testInfo.getDisplayName() + " with tag " + testInfo.getTags().toString());
        //System.out.println("instance created");
    }

    @AfterEach
    void cleanUp(){
        //System.out.println("cleaned");
    }


    @Nested
    @Tag("Math")
    class AddTest{


        //creating message using lambda improves performance
        @Test
        @DisplayName("addPositive")
        void addPositive() {
            int expected = 2;
            int actual = calculator.add(1,1);
            assertEquals(expected,actual,() -> "the add method adds two numbers");
            //System.out.println("add test ran");
        }

        @Test
        @DisplayName("addNegative")
        void addNegative() {
            int expected = 2;
            int actual = calculator.add(-1,3);
            assertEquals(expected,actual,"the add method adds two numbers");
            //System.out.println("add test ran");
        }
    }





    @Test
    @Tag("Math")
    @DisplayName("sub")
    void sub() {
        int expected = 2;
        int actual = calculator.sub(3,1);
        assertEquals(expected,actual,"subtracts two numbs");
        //System.out.println("sub test ran");
    }

    @Test
    @DisplayName("divide")
    @EnabledOnOs(OS.LINUX)
    void divide() {
        assertThrows(ArithmeticException.class,() -> calculator.divide(1,0));
    }


    @Test
    @Disabled
    void testDisabled(){
        fail("test failed");
    }

    @Test
    void assumeDemo(){
        boolean serverUp = true;
        assumeTrue(serverUp);
        System.out.println("assumptions are true");
    }


    @Test
    void assertAllDemo(){
        assertAll(()->assertEquals(4,calculator.multiply(2,2)),
                ()->assertEquals(6,calculator.multiply(2,3)));
    }

    @RepeatedTest(4)
    @DisplayName("rectangle area")
    @Tag("Rectangle")
    void repeatedTestDemo(){
        //System.out.println(repetitionInfo.getCurrentRepetition());
        assertEquals(6,calculator.rectangeArea(3,2));
    }
}