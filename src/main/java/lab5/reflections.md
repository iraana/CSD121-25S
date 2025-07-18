REFLECTION: Why can you change the type of the returned value in promptForPlayer
without changing the return type in the function signature? (Your answer should refer
to polymorphism.)
- It is possible because of polymorphism. Since we made a Player the abstract superclass of all player types in our program, and each of those classes (HumanPlayer,..) we created extends Player.
The method promptForPlayer has a return type Player, so any class that extends Player can be returned as well. This works because in java a method that returns a superclass type can return any subclass instance.

REFLECTION: Explain why the error occurred initially and why adding the abstract
method signature fixes the error. (HINT: What is the type of the ‘whoseTurn’ variable in
TicTacToeGame.doNextTurn?)
- We moved pickNextMove method out of the Player class but variable whoseTurn in TicTacToeGame.doNextTurn has type of Player, so when we tried to call
pickNextMove on whoseTurn the compiler couldn't find the method. So we added an abstract method signature for pickNextMove in the Player class, which means that every subclass of Player must implement pickNextMove.
This fixed an error because the compiler knows that all classes that extend Player will have this method, and even if the variable type is Player the program still can reach and call the method.

REFLECTION: Explain in detail (using the terminology we have discussed in
class) how it is possible that neither our main method nor our TicTacToeGame class need
change at all when adding new Player types to our game. Your discussion must include an
explanation of how the single call to pickNextMove in TicTacToeGame.doNextTurn works
correctly no matter whose turn it is or which types the players are.
- It is possible because of polymorphism and dynamic dispatch. All player types are subclasses of the abstract Player class.
They all inherit from the same superclass and implement the same method pickNextMove and this allows us to use them all the same in our code as same type Player. In TicTacToeGame.doNextTurn we only have one call to pickNextMove, and it is called on a variable whoseTurn. So when we run the program java
uses dynamic dispatch to figure out which version of pickNextMove to call based on the actual object type. Because of this, we don’t need to change anything in our main methods or TicTacToeGame class, it automatically picks the correct method for us depending on which player is playing. 