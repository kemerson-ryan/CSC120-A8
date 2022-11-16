Use this file to record your reflection on this assignment.

- Tell me about your class! What does it represent?
- What additional methods (if any) did you implement alongside those listed in the interface?
- What worked, what didn't, what advice would you give someone taking this course in the future?


My human class represents a human exploring the neilson library (the only library I've been in that allows coffee). They can interact by picking up coffee or a book, their braincell count increases or decreases accordingly, and they can move around the library.

I have the methods from the interface, as well as drinkCoffee() which decreases the value of the integer nCoffee, and getnCoffee() to print a message and return how much coffee left in the cup. I have readBook() to serve a parallel service to drinkCoffee, but for my book item, and its corresponding getPages(). You can examine the items, and use other things like "headphones" and "phone", which only print messages about their use. I added the currentPosition() method so that I could conveniantly print a message with the coordinates currentX and currentY. rest sets my booleans of walking and flying to false, so that examining an item is possible.

I think advice is to think through it all on paper before trying to type it out. Then once done, implement one method at a time with verification that it works into the program. 