package com.qa.api.apirunners;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("junit-jupiter")
@SelectPackages("com/qa/api")
@IncludeTags("api")  // optional: remove if you don't tag tests
public class ApiTestSuite {
}
