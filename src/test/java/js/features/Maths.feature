Feature: Maths operations
    
    @maths
    Scenario Outline: Adding some numbers <num1> and <num2> together makes <sum>
        Given I have entered a first number <num1>
        And I have entered a second number <num2>
        When I choose to add the numbers together
        Then the result should be the sum of the numbers <sum>
    Examples:
        | num1 | num2 | sum |
        | 5    | 3    | 8   |
        | 2    | 2    | 4   |
        | 10   | 20   | 30  |
        | 0    | 0    | 0   |
        | 3    | 1    | 4   |