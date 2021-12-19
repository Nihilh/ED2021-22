import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AVLTest.AllTests.class,BSTTest.AllTests.class,
	ColasTest.AllTests.class})
public class AllTests {
	
}
