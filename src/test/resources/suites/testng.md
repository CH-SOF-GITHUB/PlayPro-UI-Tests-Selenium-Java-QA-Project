<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite thread-count="4" name="BackOfficeSuite" parallel="tests">
    <test name="ChromeTest">
        <parameter name="browser" value="chrome"/>
        <parameter name="version" value="latest"/>
        <parameter name="platform" value="WIN10"/>
        <classes>
            <class name="tests.PlayPro.BackOfficeV3.ParallelTests.TC01"/>
        </classes>
    </test> <!-- Test -->
    <test name="FirefoxTest">
        <parameter name="browser" value="firefox"/>
        <parameter name="version" value="latest"/>
        <parameter name="platform" value="WIN10"/>
        <classes>
            <class name="tests.PlayPro.BackOfficeV3.ParallelTests.TC01"/>
        </classes>
    </test> <!-- Test -->
    <test name="EdgeTest">
        <parameter name="browser" value="microsoftedge"/>
        <parameter name="version" value="latest"/>
        <parameter name="platform" value="WIN10"/>
        <classes>
            <class name="tests.PlayPro.BackOfficeV3.ParallelTests.TC01"/>
        </classes>
    </test> <!-- Test -->
    <test name="MacChromeTest">
        <parameter name="browser" value="chrome"/>
        <parameter name="version" value="latest"/>
        <parameter name="platform" value="macOS Sequoia"/>
        <classes>
            <class name="tests.PlayPro.BackOfficeV3.ParallelTests.TC01"/>
        </classes>
    </test> <!-- Test -->
</suite> <!-- Suite -->
________________________________________________________________________________________________________________________
