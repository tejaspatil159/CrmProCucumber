package com.abc.crmproRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="D:\\TejasSelOwn\\CrmproByCucumber\\feature",
                 glue={"com.abc.crmproStepDefinition"},
                 format= {"pretty", "html:taget-output", "junit:junit_xml/cucumber.xml"},
                 plugin= {"html:taget-output"},
                 monochrome=true,
                 strict=true,
                 dryRun=false ,
                 tags= {"@EndToEndTest"})
public class RunnerTest {

}
