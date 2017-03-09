package examples;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_tag {
	@BeforeClass
	public void NavigateTotagPage() {
		System.out.println("");
		System.out.println("Click on tag page of wordpress-------------");
		System.out.println("");
	}

	@AfterClass
	public void writeStatusToExcel() {
		System.out.println("");
		System.out
				.println("Write Pass/Fail/Skip status to Excel / HTML report");
	}

	@BeforeMethod
	public void VerifyExecutionStatus() {
		System.out.println("");
		System.out.println("Verify testcase execution status from excel");

	}

	@AfterMethod
	public void WriteStatusToExcel() {

		System.out
				.println("Write Testcase Pass/Fail/Skip status to Excel / HTML report ");
		System.out.println("");
	}

	@Test
	public void AddnewTag() {
		System.out.println(" Testing Add new Tag");
	}

	@Test
	public void EditTag() {
		System.out.println(" Testing Edit Tag");
	}

	@Test
	public void ViewTag() {
		System.out.println(" Testing View Tag");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@Test
	public void DeleteTag() {
		System.out.println(" Testing Delete Tag");
	}
}