## Applitools Hackathon

My submission for Applitools Hackathon.

### Pre-requisite

- Java 8 JDK
- Maven
- IntelliJ IDEA / Eclipse
- Applitools Eyes API

### Framework / Libraries used

- `eyes-selenium-java3:3.158.4`
- `selenium-webdriver:3.141.59`
- `webdrivermanager:3.7.1`
- `testng:7.0.0`
- `truth:1.0`

### Test configuration

Test requires a config properties file named `hackathon.properties` file stored at `src/test/resources` directory.

#### Sample config

```properties
wait_explicit=5        # Explicit waits in secs.
wait_implicit=1        # Implicit waits in secs.
timeout_pageload=30    # Page load timeout in secs.
timeout_scriptload=30  # Script load timeout in secs.
app_v1_url=https://demo.applitools.com/hackathon.html    # v1 app url.
app_v2_url=https://demo.applitools.com/hackathonV2.html  # v2 app url.
eyes_debug=false       # Applitools SDK logging.
```

### Test classes

There are **two** test classes in this project.

1. `TraditionalTests`: For testing both the apps in traditional selenium approach.
1. `VisualAITests`: For verifying both the apps using Applitools Eyes SDK.

### Test methods

There are **five** test methods in both the test classes.

1. `testLoginElements`: Tests all the elements of the login page.
1. `testLoginFunctionality`: Tests all the scenarios of Login page mentioned on the Hackathon site.
1. `testTableSort`: Tests the table sorting functionality by sorting amount column.
1. `testCanvasChart`: Tests the Bar chart.
1. `testDynamicContent`: Tests dynamic ads page.

### How to run the tests?

#### 0. API key

You need to pass your API key by either of the 3 options.

1. Environment variable
1. System property
1. `hackathon.properties` file

Key to use for this is `eyes_api`

> :bulb: Pro Tip: First preference will be given to environment variable.

#### 1. Maven command line

Test can be run by executing the following command in your terminal with environment variable.

```shell script
$ eyes_api=<Your Applitools Key> mvn clean install 
```

Or, with system property.

```shell script
$ mvn clean install -Deyes_api=<Your Applitools Key> 
```

Or in `hackathon.properties` file.

```properties
. . .
eyes_api=<Your Applitools Key>
. . .
```

#### 2. Idea IntelliJ / Eclipse IDE

Open the project and create Run configuration for TestNG by selecting the `testng.xml` file and setting the API key in either Environment variable or VM arguments.

Once run configuration is created, use it to run the tests.