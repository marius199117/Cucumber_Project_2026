package cucumberProject.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        features = "src/test/java/cucumberProject/features",
        glue = "cucumberProject",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/Cucumber.json"
        })
public class GenericTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Enable parallel execution of Cucumber scenarios by TestNG data provider
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/Cucumber.json");

        String projectName = "Cucumber Test Automation Demo";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Platform", "Windows");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}