package org.example

static void main(String[] args) {
    variableAndBasicLogic()
    responseTime()
    findFailedTests()
    countTestResults()
    userCredentials()
    filterTestCases()
    extractFailedTestNames()
    reusableValidation()
    analyzeExecutionResults()
    miniProject()
}

static void miniProject() {
    println "Ex10: Mini Project"

    def executions = [
            [name: "TC001", browser: "Chrome", status: "PASSED", duration: 320],
            [name: "TC002", browser: "Chrome", status: "FAILED", duration: 1450],
            [name: "TC003", browser: "Firefox", status: "PASSED", duration: 620],
            [name: "TC004", browser: "Chrome", status: "FAILED", duration: 2100],
            [name: "TC005", browser: "Firefox", status: "PASSED", duration: 410]
    ]

    def getTotalTestCases = { List<Map> tests ->
        tests
    }.memoize()

    def getFailedTestCases = { List<Map> tests ->
        tests.findAll { it.status == "FAILED" }
    }.memoize()

    def getAverageExecutionTime = { List<Map> tests ->
        tests ? tests.sum { it.duration } / tests.size() : 0
    }.memoize()

    def getBrowserMostFailedTestCase = { List<Map> tests ->
        tests.countBy { it.browser }.max { it.value }
    }.memoize()

    def getExecutionOverview = { List<Map> tests ->
        def total = tests.size()
        def failed = getFailedTestCases(tests).size()
        def passed = total - failed
        def averageTime = getAverageExecutionTime(tests)
        def mostFailedBrowser = getBrowserMostFailedTestCase(
                getFailedTestCases(tests)
        )
        def passRate = total ? (passed * 100 / total) : 0

        """
- Total Test Cases: $total
- Passed Test Cases: $passed
- Failed Test Cases: $failed
- Most Failed Browser: ${mostFailedBrowser.key} (${mostFailedBrowser.value} failures)
- Average Execution Time: ${averageTime}ms
- Pass Rate: ${passRate}%
"""
    }.memoize()

    println """
1. Get the list of total test cases
2. Get the list of failed test cases
3. Get execution overview
4. Exit

Your choice:
"""

    def choice = System.in.newReader().readLine()

    switch (choice) {
        case '1':
            println getTotalTestCases(executions)
            break

        case '2':
            println getFailedTestCases(executions)
            break

        case '3':
            println getExecutionOverview(executions)
            break

        case '4':
            println "Exiting..."
            break

        default:
            println "Invalid choice."
    }
}

static void analyzeExecutionResults() {
    println "Ex9: Analyze Execution Results"

    def results = [
            [name: "Login", status: "PASSED", duration: 450],
            [name: "Search", status: "FAILED", duration: 1200],
            [name: "Checkout", status: "FAILED", duration: 800],
            [name: "Logout", status: "PASSED", duration: 300]
    ]

    def output = [
            total      : 0,
            passed     : 0,
            failed     : 0,
            passRate   : 0,
            failedTests: [],
            slowTests  : []
    ]

    results.each { test ->
        output.total++

        if (test.status == "PASSED") {
            output.passed++
        } else if (test.status == "FAILED") {
            output.failed++
            output.failedTests << test.name
        }

        if (test.duration > 1000) {
            output.slowTests << test.name
        }
    }

    output.passRate = output.total ?
            (output.passed / output.total) * 100 :
            0

    def toListContent = { tests ->
        tests.collect { "- $it" }.join("\n")
    }

    println """
Total: ${output.total}
Passed: ${output.passed}
Failed: ${output.failed}
Pass Rate: ${output.passRate as int}%

Failed Tests:
${toListContent(output.failedTests)}

Slow Tests:
${toListContent(output.slowTests)}
"""
}

static void reusableValidation() {
    println "Ex8: Reusable Validation"
    def isSuccessful = { int it -> it in 200..299 }

    println([isSuccessful(200), isSuccessful(201), isSuccessful(400), isSuccessful(500)])
}

static void extractFailedTestNames() {
    println "Ex7: Extract Failed Test Names"
    def tests = [
            [name: "Login", status: "PASSED"],
            [name: "Checkout", status: "FAILED"],
            [name: "Search", status: "PASSED"],
            [name: "Payment", status: "FAILED"]
    ]

    Closure getFailedClosure = { it.status == "FAILED" }

    // Approach 1
    println tests.findAll(getFailedClosure)*.name
}

static void filterTestCases() {
    println "Ex6: Filter Test Cases"
    def testCases = [
            [name: "Login", type: "UI", priority: "High"],
            [name: "Get User", type: "API", priority: "High"],
            [name: "Search", type: "UI", priority: "Low"],
            [name: "Payment", type: "API", priority: "High"]
    ]

    def highPriorityAPIClosure = { it.type == "API" && it.priority == "High" }

    // Approach 1
    testCases
            .findAll(highPriorityAPIClosure)
            .each { println it.name }


    // Approach 2
    testCases.findAll(highPriorityAPIClosure)*.name.each(this::println)
}

static void userCredentials() {
    println "Ex5: User Credentials"
    def users = [
            clientA: [username: "userA", password: "passA"],
            clientB: [username: "userB", password: "passB"],
            clientC: [username: "userC", password: "passC"],
            clientE: [password: "passE"]
    ]

    def client = "clientD"
    def usrFoundWithoutUsernameMsg = "User found does not have [username]"
    def usrDoesNotExistMsg = "User does not exist."

    // Approach 1
    if (users[client])
        println users[client].username ?: usrFoundWithoutUsernameMsg
    else
        println usrDoesNotExistMsg

    // Approach 2
    println users[client]
            ? users[client].getOrDefault("username", usrFoundWithoutUsernameMsg)
            : usrDoesNotExistMsg
}

static void countTestResults() {
    println "Ex4: Count Test Results"
    def results = [
            "PASSED",
            "FAILED",
            "PASSED",
            "FAILED",
            "PASSED",
            "SKIPPED"
    ]

    // Approach 1 (concise)
    println results.countBy { it }

    // Approach 2
    println results.groupBy { it }.collectEntries { key, value ->
        [(key): value.size()]
    }
}

static void findFailedTests() {
    println "Ex3: Find Failed Tests"
    def results = [
            "Login - PASSED",
            "Checkout - FAILED",
            "Search - PASSED",
            "Payment - FAILED"
    ]

    Closure getFailedClosure = (String it) -> it.endsWith(" - FAILED")
    Closure getPassedClosure = (String it) -> it.endsWith(" - PASSED")

    // Approach 1
    println results.findAll(getFailedClosure).join("\n")

    // Vanilla
    for (def x : results)
        if (x.endsWith(" - FAILED")) println x

}

static void responseTime() {
    println "Ex2: Response Tiem"
    def responseTime = 1250

    // Approach 1
    if (responseTime < 500)
        println "FAST"
    else if (responseTime < 1000)
        println "ACCEPTABLE"
    else
        println "SLOW"

    // Approach 2
    switch (responseTime) {
        case 0..<500:
            println "FAST"
            break
        case 500..<1000:
            println "ACCEPTABLE"
            break
        default:
            println "SLOW"
    }

    // Approach 3
    println responseTime < 500 ? "FAST" :
            responseTime < 1000 ? "ACCEPTABLE" :
                    "SLOW"
}

static void variableAndBasicLogic() {
    println "Ex1: Variables and Basic Logic"
    def status = "FAILED";

    // Approach 1
    if (status == "PASSED")
        println "Test passed"
    else
        println "Test failed"

    // Approach 2
    println "Test ${status == "PASSED" ? "passed" : "failed"}"

    // Approach 3
    switch (status) {
        case "PASSED":
            println "Test passed"
            break
        default:
            println "Test failed"
    }

    // Approach 4
    println(["PASSED": "Test passed"][status] ?: "Test failed")
    println(["PASSED": "Test passed"].getOrDefault(status, "Test failed"))
}