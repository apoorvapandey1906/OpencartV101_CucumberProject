package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		      features = {"src/test/resources/features"},
		     // features = {"src/test/resources/features/Login.feature"},
		    //features = {"src/test/resources/features/Login.feature"},
		    //  features ={"@target/rerun.txt"},
		      glue = {"stepDefinitions","hooks"},
		      plugin = {"pretty", "html:reports/myreport.html", //junit report
		    		  "rerun:target/rerun.txt",
		    		  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" //extentreports
		    		  },
		      dryRun = false, // checks mapping between scenario steps and step definitions methods
		      monochrome=true, //to avoid junk characters in output
		      publish = true, // to publish report in cucumber server
		       tags="@Regression"  // this will execute scenarios tagged with @sanity
				//tags="@regression"
				//tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
				//tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
				//tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
 
		 
		)
public class TestRunner {

}
