package registration;

import junit.framework.TestCase;
import java.util.List;
import java.util.Collection;

public class TestReport extends TestCase {

	public TestReport(String name) { 
		super(name); 
	}
	
	public void testEmptyReport() throws Exception {
		Persistence.deleteAllSchedule();
		Report report = new Report();
		StringBuffer buffer = new StringBuffer();
		report.write(buffer);
		assertEquals("Number of scheduled offerings: 0\n", buffer.toString());
	}
	
	public void testReport() throws Exception {
		Persistence.deleteAllSchedule();
		Course cs101 = Persistence.createCourse("CS101", 3);
		Persistence.updateCourse(cs101);
		Offering off1 = Persistence.createOffering(cs101, "M10");
		Persistence.updateOffering(off1);
		Offering off2 = Persistence.createOffering(cs101, "T9");
		Persistence.updateOffering(off2);
		Schedule s = Persistence.createSchedule("Bob");
		s.add(off1);
		s.add(off2);
		Persistence.updateSchedule(s);
		Schedule s2 = Persistence.createSchedule("Alice");
		s2.add(off1);
		Persistence.updateSchedule(s2);
		Report report = new Report();
		StringBuffer buffer = new StringBuffer();
		report.write(buffer);
		String result = buffer.toString();
		String valid1 = "CS101 M10\n\tBob\n\tAlice\n" + "CS101 T9\n\tBob\n" + "Number of scheduled offerings: 2\n";
		String valid2 = "CS101 T9\n\tAlice\n" + "CS101 M10\n\tBob\n\tAlice\n" + "Number of scheduled offerings: 2\n";
		assertTrue(result.equals(valid1) || result.equals(valid2));
	}
}