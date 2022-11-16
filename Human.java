import javax.management.RuntimeErrorException;

public class Human //implements Contract 
{
    /**
     * Declaring all variables, initializing a few
     */
    protected static int nCoffee;
    protected static boolean hasCoffee;
    protected static int nPages=10;
    protected static boolean hasBook;
    protected static boolean walking, flying;
    protected static int currentX, currentY = 0;
    protected static int braincells=25;
    protected static int northBound = 100, southBound = -100, eastBound = 300, westBound = -300;

    /**
     * Grab item to use in later methods
     * @param String item
     */
    public static void grab(String item){
        if(item.contains("coffee")){
            if(hasCoffee==true){
                System.out.println("You're already holding a " + item);
            }else{
                hasCoffee=true;
                System.out.println("You grabbed the coffee");
            }
        }
        if(item.contains("book")){
            if(hasBook==true){
                System.out.println("You're already holding a " + item);
            }else{
                hasBook=true;
                System.out.println("You grabbed the book");
            }
        }
        else{
            System.out.println("Hm I don't see that here..try 'coffee', or 'book'");
        }
    }
    /**
     * Dropping item and setting its boolean to false
     * @param String item
     * @return String item
     */
    public static String drop(String item){
        if(item=="coffee"){
            if(hasCoffee==false){
                System.out.println("You're not holding a " + item);
            }else{
                hasCoffee=false;
            }
        }
        if(item=="book"){
            if(hasBook==false){
                System.out.println("You're not holding a " + item);
            }else{
                hasBook=false;
            }
        }
        else{
            System.out.println("I would if I could--I don't seem to be holding a " + item);
        }
        System.out.println("You dropped the " + item);
        return item;
    }
    /**
     * Decrease amount of coffee in cup
     */
    protected static void drinkCoffee() {
        nCoffee=getnCoffee()-2;
    }
    /**
     * Gets amount of coffee in cup
     */
    public static int getnCoffee() {
        if(nCoffee>0){
            System.out.println(nCoffee + "oz left in your cup");
        }else{
            System.out.println("You don't have any more coffee in your cup!");
        }
        return nCoffee;
    }
    /**
     * Decreases number of pages remaining in book
     */
    protected static void readBook() {
        if(nPages>5){
            nPages=nPages-5;
            System.out.println("Reading book...");
            getnPages();
        }if(nPages<=0){
            System.out.println("It seems you've finished your book");
        }
    }
    /**
     * Gets number of pages in book
     */
    public static int getnPages() {
        if(nPages>0){
            System.out.println(nPages + " pages left in your book");
        }else{
            System.out.println("You've read the entire book!"); //could say to call grow method
        }
        return nPages;
    }
    /**
     * Examine item, methods to change values correspoinding to item
     * @param String item
     */
    public static void examine(String item){ 
        if(walking || flying){
            System.out.println("You can't examine something while you're moving! Take a seat and get some rest()");
        }else{
            if(item.contains("coffee") && hasCoffee){ //random hot or cold
                getnCoffee(); //this prints ounces before drinnking...useless?
                drinkCoffee(); //this will allow drinking from empty cup
                shrink();
                System.out.println("Wow, coffee is delicious, but it sure does kill off some of those...what are they called again?");
            }if(item.contains("book") && hasBook){
                readBook();
                
                grow();
                System.out.println("Reading really does make you smarter...");
            }else{
                System.out.println("I'm not holding a " + item + " to examine");
            }
        }    
    }
    /**
     * Print specific messages if certain Strings passed in
     * @param String item
     */
    public static void use(String item){
        if(item.contains("headphones")){
            System.out.println("Yesss let's jam out with headphones for a bit");
        }if(item.contains("phone")){
            System.out.println("Okay we can only use our phone for like five minutes then lets get focused");
        }else{
            System.out.println("I can't figure out how to use the " + item + ", so try 'headphones' or 'phone' instead");
        }
    }
    /**
     * Returns message with current coordinates
     * @return String "you are at position " + currentX + "," + currentY
     */
    public static String currentPosition(){
        return "you are at position " + currentX + "," + currentY;
    }
    /**
     * Walk method takes in directions to walk in, and changes coordinates accordingly, unless out of bounds
     * @param String direction
     * @return
     */
    public static boolean walk(String direction){
        if(direction.contains("right") || direction.contains("left") || direction.contains("straight") || direction.contains("forward") || direction.contains("back")){
            if(direction.contains("right")){
                currentX=currentX+1;
            }if(direction.contains("left")){
                currentX=currentX-1;
            }if(direction.contains("straight") || direction.contains("forward")){
                currentY=currentY+1;
            }if(direction.contains("back")){
                currentY=currentY-1;
            }
            walking=true;
            System.out.println("Walked " + direction + ", " + currentPosition());
        }else{
            walking=false;
            System.out.println("Your direction must contain 'right', 'left', 'straight', 'forward', 'back'--you're not going anywhere with what you're saying.");
        }
        if(currentX>eastBound || currentX<westBound || currentY>northBound || currentY<southBound){ 
            walking=false;
            throw new RuntimeException("You can't walk outside of the limits! Try again");
        }
        return walking;
    }
    /**
     * Fly method takes in desired coordinates, assesses validity, and changes coordinates
     * @param int x
     * @param int y
     * @return boolean flying
     */
    public static boolean fly(int x, int y){
        if(x==currentX && y==currentY){
            System.out.println("You didn't fly anywhere. Try changing your coordinates");
            flying=false;
        }if(x>eastBound || x<westBound || y>northBound || y<southBound){
            System.out.println("You can only fly through air--not through walls. Your new x has to be " + westBound + " to " + eastBound + " and your y " + northBound + " to " + southBound); //map method to be called
            flying=false;
        }else{
            flying=true;
            currentX=x;
            currentY=y;
            System.out.println("You flew, " + currentPosition());
        }
        return flying;
    }
    /**
     * Sets walking and flying booleans to false
     */
    public static void rest(){ 
        walking=false;
        flying=false;
        System.out.println("Taking a seat now");
    }
    /**
     * Shrinks int braincells and prints messages about value
     * @return int braincells
     */
    public static Number shrink(){
        braincells=braincells-2;
        System.out.println("The number of braincells you have is shrinking!!");
        if(braincells<=0){
            System.out.println("You're not doing too well on your braincell count...you currently have " + braincells + "...maybe you should try picking up a book and examining it");
        }
        return braincells;
    }
    /**
     * Grows int braincells
     * @return int braincells
     */
    public static Number grow(){ 
        braincells=braincells+3;
        System.out.println("The number of braincells you have is growing!");
        return braincells;
    }
    /**
     * Reset braincells to default value
     */
    public static void undo(){ //if make action log, this should be used to remove last action from array, and would have to reset the variables to their previous values--could be through overloading the methods adding a parameter or something
        braincells=25;
        System.out.println("Your braincell count has been reset, and is now " + braincells + ".");
    }

    public static void main(String args[]){
        //fly(3,4);
        //grab("book");
        //grab("coffee");
        //System.out.println(hasCoffee);
        //drop("coffee");
        //System.out.println(hasCoffee);
        //walk("forward right");
        //rest();
        //examine("book");
        //undo();
    }
}
