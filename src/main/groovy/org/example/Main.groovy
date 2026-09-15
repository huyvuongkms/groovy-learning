package org.example

import groovy.json.JsonSlurper



static void main(String[] args) {
//    variableAndBasicLogic()
//    responseTime()
//    findFailedTests()
//    countTestResults()
//    userCredentials()
//    filterTestCases()
//    extractFailedTestNames()
//    reusableValidation()
//    analyzeExecutionResults()
//    miniProject()
    def jsondata = new JsonSlurper().parseText('''
{
  "organization": {
    "name": "NextGen Quality Labs",
    "location": "Ho Chi Minh City",
    "teams": [
      {
        "id": "TEAM-001",
        "name": "Ruby",
        "lead": {
          "id": "USR-001",
          "name": "Alice Nguyen",
          "email": "alice@example.com",
          "skills": ["Groovy", "Katalon", "API", "Selenium"]
        },
        "members": [
          {
            "id": "USR-002",
            "name": "Bob Tran",
            "role": "Automation Engineer",
            "experienceYears": 4,
            "active": true,
            "skills": ["Java", "Groovy", "API"],
            "projects": ["PRJ-001", "PRJ-002"]
          },
          {
            "id": "USR-003",
            "name": "Charlie Le",
            "role": "QA Engineer",
            "experienceYears": 2,
            "active": true,
            "skills": ["Manual Testing", "SQL"],
            "projects": ["PRJ-001"]
          },
          {
            "id": "USR-004",
            "name": "David Pham",
            "role": "Automation Engineer",
            "experienceYears": 6,
            "active": false,
            "skills": ["Java", "Selenium", "Performance"],
            "projects": []
          }
        ]
      },
      {
        "id": "TEAM-002",
        "name": "Titan",
        "lead": {
          "id": "USR-005",
          "name": "Emma Vo",
          "email": "emma@example.com",
          "skills": ["Leadership", "Groovy", "Playwright"]
        },
        "members": [
          {
            "id": "USR-006",
            "name": "Frank Ho",
            "role": "Senior Automation Engineer",
            "experienceYears": 8,
            "active": true,
            "skills": ["Groovy", "Katalon", "API", "Docker"],
            "projects": ["PRJ-002", "PRJ-003"]
          },
          {
            "id": "USR-007",
            "name": "Grace Bui",
            "role": "QA Engineer",
            "experienceYears": 3,
            "active": true,
            "skills": ["Manual Testing", "API", "Postman"],
            "projects": ["PRJ-003"]
          }
        ]
      }
    ]
  },

  "projects": [
    {
      "id": "PRJ-001",
      "name": "Customer Portal",
      "status": "ACTIVE",
      "priority": "HIGH",
      "budget": 120000,
      "environments": [
        {
          "name": "QA",
          "url": "https://qa.portal.example.com",
          "enabled": true
        },
        {
          "name": "STAGING",
          "url": "https://staging.portal.example.com",
          "enabled": true
        },
        {
          "name": "PROD",
          "url": "https://portal.example.com",
          "enabled": false
        }
      ],
      "testSuites": [
        {
          "id": "TS-001",
          "name": "Login",
          "type": "UI",
          "tags": ["smoke", "regression", "authentication"],
          "testCases": [
            {
              "id": "TC-001",
              "name": "Login with valid credentials",
              "priority": "P1",
              "automated": true,
              "owner": "USR-002",
              "estimatedMinutes": 5,
              "steps": [
                "Open login page",
                "Enter username",
                "Enter password",
                "Click login",
                "Verify dashboard"
              ],
              "executions": [
                {
                  "environment": "QA",
                  "build": "1.10.0",
                  "status": "PASSED",
                  "durationSeconds": 12.5,
                  "retry": 0,
                  "executedBy": "USR-002"
                },
                {
                  "environment": "STAGING",
                  "build": "1.10.0",
                  "status": "FAILED",
                  "durationSeconds": 18.4,
                  "retry": 2,
                  "executedBy": "USR-006",
                  "failure": {
                    "type": "TimeoutException",
                    "message": "Dashboard did not load",
                    "component": "dashboard"
                  }
                }
              ]
            },
            {
              "id": "TC-002",
              "name": "Login with invalid password",
              "priority": "P1",
              "automated": true,
              "owner": "USR-002",
              "estimatedMinutes": 4,
              "steps": [
                "Open login page",
                "Enter username",
                "Enter wrong password",
                "Click login",
                "Verify error"
              ],
              "executions": [
                {
                  "environment": "QA",
                  "build": "1.10.0",
                  "status": "PASSED",
                  "durationSeconds": 8.2,
                  "retry": 0,
                  "executedBy": "USR-002"
                }
              ]
            },
            {
              "id": "TC-003",
              "name": "Account locked after five failed attempts",
              "priority": "P2",
              "automated": false,
              "owner": "USR-003",
              "estimatedMinutes": 12,
              "steps": [
                "Attempt invalid login five times",
                "Verify account is locked",
                "Verify warning message"
              ],
              "executions": []
            }
          ]
        },

        {
          "id": "TS-002",
          "name": "Customer API",
          "type": "API",
          "tags": ["api", "regression"],
          "testCases": [
            {
              "id": "TC-004",
              "name": "Get customer by ID",
              "priority": "P1",
              "automated": true,
              "owner": "USR-002",
              "estimatedMinutes": 3,
              "api": {
                "method": "GET",
                "endpoint": "/customers/{id}",
                "expectedStatus": 200
              },
              "executions": [
                {
                  "environment": "QA",
                  "build": "1.10.1",
                  "status": "PASSED",
                  "durationSeconds": 1.4,
                  "retry": 0,
                  "executedBy": "USR-006"
                },
                {
                  "environment": "STAGING",
                  "build": "1.10.1",
                  "status": "PASSED",
                  "durationSeconds": 1.9,
                  "retry": 0,
                  "executedBy": "USR-006"
                }
              ]
            },
            {
              "id": "TC-005",
              "name": "Create customer with invalid email",
              "priority": "P2",
              "automated": true,
              "owner": "USR-006",
              "estimatedMinutes": 4,
              "api": {
                "method": "POST",
                "endpoint": "/customers",
                "expectedStatus": 400
              },
              "executions": [
                {
                  "environment": "QA",
                  "build": "1.10.1",
                  "status": "FAILED",
                  "durationSeconds": 2.8,
                  "retry": 1,
                  "executedBy": "USR-006",
                  "failure": {
                    "type": "AssertionError",
                    "message": "Expected 400 but received 500",
                    "component": "customer-service"
                  }
                }
              ]
            }
          ]
        }
      ]
    },

    {
      "id": "PRJ-002",
      "name": "Payment Gateway",
      "status": "ACTIVE",
      "priority": "CRITICAL",
      "budget": 250000,
      "environments": [
        {
          "name": "QA",
          "url": "https://qa.payment.example.com",
          "enabled": true
        },
        {
          "name": "CTCERT",
          "url": "https://ctcert.payment.example.com",
          "enabled": true
        },
        {
          "name": "CTPROD",
          "url": "https://ctprod.payment.example.com",
          "enabled": true
        },
        {
          "name": "PROD",
          "url": "https://payment.example.com",
          "enabled": true
        }
      ],
      "testSuites": [
        {
          "id": "TS-003",
          "name": "Payments",
          "type": "API",
          "tags": ["payment", "critical", "api"],
          "testCases": [
            {
              "id": "TC-006",
              "name": "Create successful payment",
              "priority": "P1",
              "automated": true,
              "owner": "USR-006",
              "estimatedMinutes": 6,
              "api": {
                "method": "POST",
                "endpoint": "/payments",
                "expectedStatus": 201
              },
              "executions": [
                {
                  "environment": "QA",
                  "build": "3.4.2",
                  "status": "PASSED",
                  "durationSeconds": 3.2,
                  "retry": 0,
                  "executedBy": "USR-006"
                },
                {
                  "environment": "CTCERT",
                  "build": "3.4.2",
                  "status": "PASSED",
                  "durationSeconds": 4.8,
                  "retry": 0,
                  "executedBy": "USR-002"
                },
                {
                  "environment": "CTPROD",
                  "build": "3.4.2",
                  "status": "FAILED",
                  "durationSeconds": 7.5,
                  "retry": 3,
                  "executedBy": "USR-006",
                  "failure": {
                    "type": "ServiceUnavailable",
                    "message": "Bank connector unavailable",
                    "component": "bank-adapter"
                  }
                }
              ]
            },
            {
              "id": "TC-007",
              "name": "Reject duplicated transaction",
              "priority": "P1",
              "automated": true,
              "owner": "USR-006",
              "estimatedMinutes": 5,
              "api": {
                "method": "POST",
                "endpoint": "/payments",
                "expectedStatus": 409
              },
              "executions": [
                {
                  "environment": "QA",
                  "build": "3.4.2",
                  "status": "PASSED",
                  "durationSeconds": 2.7,
                  "retry": 0,
                  "executedBy": "USR-006"
                }
              ]
            }
          ]
        }
      ]
    },

    {
      "id": "PRJ-003",
      "name": "Analytics Dashboard",
      "status": "MAINTENANCE",
      "priority": "MEDIUM",
      "budget": 70000,
      "environments": [
        {
          "name": "QA",
          "url": "https://qa.analytics.example.com",
          "enabled": true
        }
      ],
      "testSuites": [
        {
          "id": "TS-004",
          "name": "Dashboard UI",
          "type": "UI",
          "tags": ["dashboard", "ui"],
          "testCases": [
            {
              "id": "TC-008",
              "name": "Display revenue chart",
              "priority": "P2",
              "automated": false,
              "owner": null,
              "estimatedMinutes": 8,
              "steps": [
                "Open dashboard",
                "Select date range",
                "Verify revenue chart"
              ],
              "executions": [
                {
                  "environment": "QA",
                  "build": "2.1.0",
                  "status": "SKIPPED",
                  "durationSeconds": 0,
                  "retry": 0,
                  "executedBy": null
                }
              ]
            }
          ]
        }
      ]
    }
  ],

  "executionSummary": {
    "totalExecutions": 12,
    "passed": 8,
    "failed": 3,
    "skipped": 1,
    "averageDurationSeconds": 5.47
  }
}
''');
//    jsonBasicTraversal(jsondata)
    jsonFindAndFindAll(jsondata)
}

static void jsonFindAndFindAll(def data = [:]) {
    println """
Find all ACTIVE projects. => ${data["projects"].find { it["status"] == "ACTIVE" }} \n
Find all projects with budget greater than 100000. => ${data["projects"].find { it["budget"] > 100000 }} \n
Find all P1 test cases. => ${data.projects.collectMany { it["testSuites"] }.collectMany { it["testCases"] }.findAll { it["priority"] == "P1" } } \n
Find all automated test cases. => ${data.projects.collectMany { it["testSuites"] }.collectMany { it["testCases"] }.findAll { it["automated"] == true }} \n
Find all non-automated test cases. => ${data.projects.collectMany { it["testSuites"] }.collectMany { it["testCases"] }.findAll { it["automated"] != true }} \n
Find all API test suites. => ${data.projects.collectMany { it["testSuites"] }.findAll { it["type"] == "API" } } \n
Find all enabled environments. => ${data.projects.collectMany { it["environments"] }.findAll { it["enabled"] == true }} \n
Find all active team members. => ${data.organization.teams.collectMany { it["members"] }.findAll { it["active"] == true }} \n
Find engineers with at least 5 years of experience. => ${data.organization.teams.collectMany { it["members"] }.findAll { it["experienceYears"] >= 5 }} \n
Find members having Groovy in their skills. => ${data.organization.teams.collectMany { it["members"] }.findAll { "Groovy" in it["skills"] }}
"""
}

static void jsonBasicTraversal(def data = [:]) {
    println """
Print the organization name. => ${data.organization.name} \n
Print every team name. => ${data.organization.teams*.name} \n
Print every project name. => ${data.projects*.name} \n
Print all environment names of PRJ-002. => ${data.projects.find { it.id == "PRJ-002" }["environments"]} \n
Print all test suite names. => ${data.projects*.testSuites["name"]} \n
Print every test case ID and name. => ${data.projects.collectMany { it.testSuites }.collectMany { it.testCases }.collect { "${it.id} - ${it.name}" }} \n
Print all members of the Ruby team. => ${data.organization.teams.find { it.name == "Ruby" }["members"]} \n
Print Alice's skills. => ${data.organization.teams.find { it.lead.name == "Alice Nguyen" }["lead"]["skills"]} \n
Count the number of projects. ${data.projects.size()} \n
Count the number of test cases across all projects. ${data.projects.collectMany { it.testSuites }.collectMany { it.testCases }.size()} \n
"""
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