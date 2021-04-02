import java.util.*;
public class Main {
    public static void main (String[] args){
        //UI
        System.out.println(Card.Red + "                    $$$$$$$$$$$");
        System.out.println(Card.Yellow +"  ___  ___  ___"+Card.Red+"$$$$$$$$$"+Card.Yellow+"__"+Card.Red+"$$"+Card.Yellow+"__"+Card.Red+"$$$$$$ ");
        System.out.println(Card.Yellow+" |\\  \\|\\  \\|\\  \\"+Card.Red+"$$$$$$"+Card.Yellow+"|\\  \\|\\  \\"+Card.Red+"$$");
        System.out.println(Card.Yellow+" \\ \\  \\_\\  \\ \\  \\"+Card.Red+"$$$$$"+Card.Yellow+"\\ \\  \\ \\  \\"+Card.Red+"$");
        System.out.println(Card.Yellow+"  \\ \\   "+"__"+Card.Yellow+"  \\ \\  \\"+Card.Red+"$$$$$"+Card.Yellow+"\\ \\  \\ \\  \\");
        System.out.println(Card.Red+"  $"+Card.Yellow+"\\ \\  \\ \\  \\ \\  \\|\\  \\\\_\\  \\ \\  \\");
        System.out.println(Card.Red+" $$$"+Card.Yellow+"\\ \\__\\ \\__\\ \\__\\ \\________\\ \\__\\");
        System.out.println(Card.Red+" $$$$"+Card.Yellow+"\\|__|\\|__|\\|__|\\|________|\\|__|");
        System.out.println(Card.Red+"$$$$$$$$$$$$$$$$$$$$$$$$  ");                  
        System.out.println("   $$$$$$$$$$$$      ");                          

        System.out.println(Card.Blue+"      << WELCOME TO HIJI GAME >>");
        System.out.println("         ===================="+Card.Reset);

        // Global variables
        List<Player> playerList = new ArrayList<Player>();
        boolean isVictory=false;
        int currentPlayerOrder=0;
        Scanner input = new Scanner(System.in);
        Deck deck = new Deck();
        Random rand = new Random();
        int skipCards;
        int playerRotateDirection=1;
        int nextPlayerOrder;
        int draw2Stacks=0;
        boolean nextDraw4=false;
        boolean chooseColor;
        String[] playerColors = {Card.Blue,Card.Red,Card.Green,Card.Cyan,Card.Purple,Card.Yellow};

        

        // Total players
        System.out.println("Enter the number of players (2-6):");
        int totalPlayers = input.nextInt();
        while (totalPlayers>6 || totalPlayers<2){
            System.out.println("Players must be between 2-6!");
            totalPlayers=input.nextInt();
        }
        // exception for wrong input ...
        System.out.println("Registered "+totalPlayers+" players.");

        // Generate individual players
        System.out.println("Please enter your names.");
        for (int i=0;i<totalPlayers;i++){
            String playerName = input.next();
            Player tempPlayer = new Player(playerName,playerColors[i]);
            playerList.add(tempPlayer);
            System.out.println("Welcome aboard, "+tempPlayer.getName()+".");
        }

        // Game start!
        System.out.println("The game has begun. The top card is currently: ");
        deck.getTop().printCard();
        LastCard lastCard = new LastCard(deck.getTop());
        deck.shuffle();

        // Randomize first player
        int firstPlayer = rand.nextInt(playerList.size());
        Collections.swap(playerList,0,firstPlayer);
        System.out.println(playerList.get(0).getName()+" has been randomly selected" +
                " as the first player.");

        // Print player order
        System.out.println("The current order of players is:");
        for (Player player: playerList){
            System.out.println(player.getName());
        }

        // Main game, runs until someone wins.
        while (!isVictory){
            // Turn-based variables.
            boolean endTurn=false;
            skipCards=0;
            int revCards=0;
            chooseColor=false;

            // Initialize current player
            Player currentPlayer=playerList.get(currentPlayerOrder);
            currentPlayer.setGiliran(true);

            // Draw cards
            if (draw2Stacks>0){
                for (int i=0;i<draw2Stacks;i++){
                    currentPlayer.draw(deck);
                    currentPlayer.draw(deck);
                }
                draw2Stacks=0;
            }
            if (nextDraw4){
                currentPlayer.draw(deck);
                currentPlayer.draw(deck);
                currentPlayer.draw(deck);
                currentPlayer.draw(deck);
                nextDraw4=false;
            }

            // Hiji Flag
            HijiTimer hijiTimer = new HijiTimer(currentPlayer,deck);
            boolean isPlayerHiji=false;

            // For "View player in turn" command.
            if (playerRotateDirection==1){
                nextPlayerOrder = (currentPlayerOrder+1) % totalPlayers;
            }
            else {
                nextPlayerOrder = (currentPlayerOrder-1);
                if (nextPlayerOrder==-1){
                    nextPlayerOrder=totalPlayers-1;
                }
            }

            // Bring up command until turn ends (discard).
            while (!endTurn){
                System.out.println("Current Card:");
                lastCard.getTop().printCard();
                System.out.println("What will "+currentPlayer.getName()+" do?");
                showIngameMenu();
                int playerCommand = input.nextInt();

                // Menu list:
                switch (playerCommand) {

                    // List Cards
                    case 1:
                        System.out.println(currentPlayer.getName() + "'s Deck:");
                        currentPlayer.showDeck();
                        break;

                    // Discard
                    case 2:
                        currentPlayer.showDeck();
                        System.out.print("Mau ngeluarin yg mana lur?: ");
                        int keluarin = input.nextInt();
                        Card checkHand = currentPlayer.checkHand(keluarin-1);
                        while (!isStackable(checkHand, lastCard.getTop())){
                            System.out.println("Whoops can't discard this");
                            System.out.print("Mau ngeluarin yg mana lur?: ");
                            keluarin = input.nextInt();
                            checkHand = currentPlayer.checkHand(keluarin-1);
                        }
                        Card discardedCard = currentPlayer.discard(keluarin - 1);
                        if (discardedCard.getNumber()==-1){
                            String currentCardPower = discardedCard.getPower();

                            switch (currentCardPower) {
                                case "Skip" -> skipCards++;
                                case "Reverse" -> revCards++;
                                case "Draw 2" -> draw2Stacks++;
                                case "Wild Draw 4" -> {
                                    nextDraw4 = true;
                                    chooseColor = true;
                                }
                                case "Wild Color" -> chooseColor = true;
                            }
                        }
                        if (chooseColor){
                            chooseWildColor();
                            int intSelectedColor = input.nextInt();
                            String selectedColor="Black";
                            switch (intSelectedColor){
                                case 1 -> selectedColor="Red";
                                case 2 -> selectedColor="Green";
                                case 3 -> selectedColor="Blue";
                                case 4 -> selectedColor="Yellow";
                            }
                            discardedCard.setColor(selectedColor);
                            chooseColor=false;
                        }
                        lastCard.setTop(discardedCard);

                        // Eligible for HIJI
                        isPlayerHiji=(currentPlayer.getRemainingCards()==1);
                        if (isPlayerHiji){
                            hijiTimer.start();
                            // TIDAK di end turn disini sampe timernya kelar/declare hiji!
                        }
                        break;

                    // Draw
                    case 3:
                        currentPlayer.draw(deck);
                        deck.shuffle();
                        break;

                    // Declare Hiji
                    case 4:
                        // Interrupt timer if player successfully declared HIJI before 3 seconds
                        if (isPlayerHiji && hijiTimer.isAlive()) {
                            hijiTimer.interrupt();
                            endTurn = true;
                        }
                        try {
                            currentPlayer.declareHiji(deck);
                        }
                        catch (Exception e) {
                            hijiRuleViolation(currentPlayer,deck);
                        }
                        break;

                    // List Players
                    case 5:
                        int iterVar = 1;
                        for (Player player : playerList) {
                            System.out.println("Player " + iterVar + ": " + player.getName());
                            System.out.println("Remaining cards: " + player.getRemainingCards());
                            if (player.isGiliran()) {
                                System.out.println(Card.Green + "Current Turn" + Card.Reset);
                            } else {
                                System.out.println(Card.Red + "Not in turn" + Card.Reset);
                            }
                            iterVar++;
                        }
                        break;

                    // View Player in Turn
                    case 6:
                        System.out.println("It's currently " + Card.Blue + currentPlayer.getName() + Card.Reset + "'s turn.");
                        System.out.println("Next turn: " + Card.Red + playerList.get(nextPlayerOrder).getName() + Card.Reset);
                        break;

                    // Help
                    case 7:
                        System.out.println("Help");
                        break;

                    // Debug: Skip
                    case 8:
                        System.out.print("Enter number of cards: ");
                        skipCards = input.nextInt();
                        endTurn = true;
                        break;

                    // Debug: Reverse
                    case 9:
                        System.out.print("Enter number of cards: ");
                        revCards = input.nextInt();
                        if (revCards % 2 == 1) {
                            playerRotateDirection *= -1;
                        }
                        endTurn = true;
                        break;

                    // Debug: multidisc
                    case 10:
                        if (canMultiDisc(lastCard.getTop(), currentPlayer)){
                            System.out.println("bisa bro");
                        } else System.out.println("gabisa bro");

                    // Debug: End Turn
                    case 0:
                        endTurn = true;
                        break;
                }
            }
            if (revCards % 2 == 1) {
                playerRotateDirection *= -1;
            }
            // Set the next player's turn.
            // Condition 1: Normal rotation
            if (playerRotateDirection==1){
                // Modulo function as in Circular Buffer.
                currentPlayerOrder = (nextPlayerOrder+(skipCards))% totalPlayers;
            }
            // Condition 2: Reverse rotation
            else {
                nextPlayerOrder=currentPlayerOrder-skipCards-1;
                // Condition 1: Reverse + Overflow
                if (nextPlayerOrder<0){
                    int negativeOverflow = nextPlayerOrder;
                    currentPlayerOrder = totalPlayers-((negativeOverflow*-1)%totalPlayers);
                }
                // Condition 2: Reverse, no overflow
                else {
                    currentPlayerOrder=nextPlayerOrder;
                }
            }
            currentPlayer.setGiliran(false);
            if (currentPlayer.getRemainingCards()==0){
                isVictory=true;
            }
        }
    }
    
    private static void showIngameMenu(){
        System.out.println("0. <DEBUG> End Turn");
        System.out.println("1. List Cards");
        System.out.println("2. Discard");
        System.out.println("3. Draw");
        System.out.println("4. Declare HIJI");
        System.out.println("5. List Players");
        System.out.println("6. View Player in Turn");
        System.out.println("7. Help");
        System.out.println("8. <DEBUG> Skip");
        System.out.println("9. <DEBUG> Reverse");
    }

    // Draw 2 cards punishment for late HIJI / declaring when >1
    private static void hijiRuleViolation(Player player, Deck deck){
        player.draw(deck);
        player.draw(deck);
        deck.shuffle();
        System.out.println(Card.Red+"2 cards have been automatically added to your hand."+Card.Reset);
    }

    private static void chooseWildColor(){
        System.out.println("Choose your color: ");
        System.out.println("1. "+Card.Red+"Red"+Card.Reset);
        System.out.println("2. "+Card.Green+"Green"+Card.Reset);
        System.out.println("3. "+Card.Blue+"Blue"+Card.Reset);
        System.out.println("4. "+Card.Yellow+"Yellow"+Card.Reset);
    }

    private static class HijiTimer extends Thread {
        Player player;
        Deck deck;
        public HijiTimer(Player player, Deck deck){
            this.player=player;
            this.deck=deck;
        }
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(3000);
                System.out.println(player.getName()+" did not declare "+Card.Red+"HIJI!");
                hijiRuleViolation(player,deck);
                System.out.println("Don't forget to declare "+Card.Red+"HIJI"+Card.Reset+"!");
            }
            catch (InterruptedException e){

            }
        }
    }

    // Check if selected card stackable with current last card
    public static boolean isStackable(Card selectedCard, Card cardBefore) {

        // If both are numcards, Same number OR same color
        if (checkCard(cardBefore).equals("NumCard") && checkCard(selectedCard).equals("NumCard")){
            return cardBefore.getNumber() == selectedCard.getNumber() || cardBefore.getColor().equals(selectedCard.getColor());
        }

        // If numcard and Actioncard, Same color only
        else if (checkCard(cardBefore).equals("NumCard") && checkCard(selectedCard).equals("ActionCard")){
            return cardBefore.getColor().equals(selectedCard.getColor());
        }

        // If numcard and wildcard, always true
        else if (checkCard(cardBefore).equals("NumCard") && checkCard(selectedCard).equals("WildCard"))
            return true;

        // If actioncard and numcard, same color only
        else if (checkCard(cardBefore).equals("ActionCard") && checkCard(selectedCard).equals("NumCard")) {
            return cardBefore.getColor().equals(selectedCard.getColor());
        }

        // If both are Actioncards, same number OR color
        else if (checkCard(cardBefore).equals("ActionCard") && checkCard(selectedCard).equals("ActionCard")) {
            return cardBefore.getNumber() == selectedCard.getNumber() || cardBefore.getColor().equals(selectedCard.getColor());
        }

        // If actioncard and wildcard, always true
        else if (checkCard(cardBefore).equals("ActionCard") && checkCard(selectedCard).equals("WildCard"))
            return true;
        else return true;
    }

    //Check type of selected card
    public static String checkCard(Card selectedCard) {
        int checker =selectedCard.getNumber();
        if (checker==-1){
            if (selectedCard.getColor().equals("Black")) return "WildCard";
            else return "ActionCard";
        }
        else return "NumCard";
    }

    //Check if player can multiple discard based on first card drawed
    public static boolean canMultiDisc(Card selectedCard,Player currentPlayer){
        for (int i=0;i<currentPlayer.getPlayerHand().size();i++){
            Card card = currentPlayer.getPlayerHand().get(i);
            if (isStackable(card, selectedCard)){
                return true;
            }
        }
        return false;
    }
}
