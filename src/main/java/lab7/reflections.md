1. Explain the aspects of the design of your program that allow lambda expressions and/or anonymous
   classes to be used to test your searchRecipes function. Your discussion should include terms such as the
   following: dependency injection, mock, interface, polymorphism, couple/decouple.

This is possible because my function depends on the interface and not on the class. 
SearchRecipes accept DataService object as a parameter instead of creating it internally, this is called dependency injection. 
Because DataService is an interface I can pass in different implementations when I test. 
By using lambda expression I can create a mock data source that returns me exactly the recipes I need for each test case.
Polymorphism here is that searchRecipes works with any DataService implementation, whether it is SqliteDataService or a mock. 
Because the program relies on the interface instead of the class, the program is decoupled and easier for testing.
