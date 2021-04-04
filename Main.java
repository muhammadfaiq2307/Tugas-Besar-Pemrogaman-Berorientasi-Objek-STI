import java.util.*;
public class Main {
    public static <Property> void main (String[] args){
        Scanner input = new Scanner(System.in);
        UI.titleScreen();
        String startGame=input.next();
        while (!startGame.equals("1")){
            System.out.println("Please enter 1!");
            startGame = input.next();
        }


        // Global variables
        List<Player> playerList = new ArrayList<Player>();
        boolean isVictory=false;
        int currentPlayerOrder=0;

        Deck deck = new Deck();
        Random rand = new Random();
        int skipCards;
        int playerRotateDirection=1;
        int nextPlayerOrder;
        int draw2Stacks=0;
        int draw4=0;
        boolean chooseColor;
        Player winner = new Player("placeholder","placeholder");
        String[] playerColors = {Card.Blue,Card.Red,Card.Green,Card.Cyan,Card.Purple,Card.Yellow};



        // Total players
        int totalPlayers = -1;
        while (totalPlayers>6 || totalPlayers<2){
            System.out.println("Enter the number of players (2-6):");
            try{
            totalPlayers=input.nextInt();
            } catch(InputMismatchException e){
                input.next();
            }
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
        System.out.println();
        System.out.println("The game has begun!");
        LastCard lastCard = new LastCard(deck.getFirstCard());
        System.out.print("The first card is: ");
        lastCard.getTop().printCard();

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
                // Exit measure
                if (isPlayerHiji && !hijiTimer.isAlive()) {
                    endTurn=true;
                    break;
                }
                System.out.println("Current Card:");
                lastCard.getTop().printCard();
                System.out.println("What will "+currentPlayer.getName()+" do?");
                UI.showIngameMenu();

                // To ensure player command input is an integer
                int playerCommand = -1;
                while (playerCommand>7 || playerCommand<1){
                    System.out.println("Input must be between 1-7!");
                    try{
                    playerCommand=input.nextInt();
                    } catch(InputMismatchException e){
                        input.next();
                    }
                }

                // Safety exit measure
                if (isPlayerHiji && !hijiTimer.isAlive()) {
                    endTurn=true;
                    break;
                }
                // Menu list:
                switch (playerCommand) {

                    // List Cards
                    case 1:
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
                        System.out.println(currentPlayer.getName() + "'s Deck:");
                        currentPlayer.showDeck();
                        break;

                    // Discard
                    case 2:
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
                        //cant discard
                        if (!canDisc(lastCard.getTop(), currentPlayer) || draw4>0 ){
                            System.out.println("Whoops you can't discard");
                            break;
                        }
                        //must discard draw 2
                        if (draw2Stacks>0) {
                            //if didnt have +2
                            int banyakKartuDraw2 = 0;
                            for (int i=0;i<currentPlayer.getPlayerHand().size();i++){
                                Card card = currentPlayer.getPlayerHand().get(i);
                                if (card.getProperty().equals("Draw 2")){
                                   banyakKartuDraw2++; 
                                }
                            }
                            if (banyakKartuDraw2==0) {
                                System.out.println("Whoops you can't discard");
                                break;
                            }
                            //if have +2
                            System.out.println("You must discard your draw 2");
                            currentPlayer.showDeck();
                            System.out.println("Select card to discard: ");


                            
                            int keluarin = -2;
                            while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                System.out.println("Select card to discard: ");
                                try{
                                    keluarin=input.nextInt();
                                } catch(InputMismatchException e){
                                    input.next();
                                }
                            }



                            Card checkHand = currentPlayer.checkHand(keluarin-1);
                            while (!(checkCard(checkHand).equals("ActionCard") &&
                                    checkHand.getProperty() instanceof String && checkHand.getProperty().equals("Draw 2"))){
                                System.out.println("Whoops can't discard this");


                                keluarin = -2;
                                while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                    System.out.println("Select card to discard: ");
                                    try{
                                        keluarin=input.nextInt();
                                    } catch(InputMismatchException e){
                                        input.next();
                                    }
                                }


                                checkHand = currentPlayer.checkHand(keluarin-1);
                            }
                            Card discardedCard = currentPlayer.discard(keluarin - 1);
                            int timesDiscard = 1;
                            while (canMultiDisc(discardedCard, currentPlayer)) {

                                int pilihan = -1;
                                while (pilihan>2 || pilihan<1){
                                    System.out.println("Do you want to discard again? ");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    try{
                                        pilihan=input.nextInt();
                                    } catch(InputMismatchException e){
                                        input.next();
                                    }
                                }

                                if (pilihan == 1) {
                                    currentPlayer.showDeck();


                                    keluarin = -2;
                                    while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                        System.out.println("Select card to discard: ");
                                        try{
                                            keluarin=input.nextInt();
                                        } catch(InputMismatchException e){
                                            input.next();
                                        }
                                    }


                                    checkHand = currentPlayer.checkHand(keluarin-1);
                                    while (!(checkCard(checkHand).equals("ActionCard") &&
                                            checkHand.getProperty() instanceof String && checkHand.getProperty().equals("Draw 2") && checkHand.getColor().equals(discardedCard.getColor()))){
                                        System.out.println("Whoops can't discard this");


                                        keluarin = -2;
                                        while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                            System.out.println("Select card to discard: ");
                                            try{
                                                keluarin=input.nextInt();
                                            } catch(InputMismatchException e){
                                                input.next();
                                            }
                                        }


                                        checkHand = currentPlayer.checkHand(keluarin-1);
                                    }
                                    discardedCard = currentPlayer.discard(keluarin - 1);
                                    timesDiscard++;
                                } else {
                                    break;
                                }
                            }
                            lastCard.setTop(discardedCard);
                            draw2Stacks = draw2Stacks + timesDiscard;
                            // Eligible for HIJI
                            isPlayerHiji=(currentPlayer.getRemainingCards()==1);
                            if (isPlayerHiji){
                                hijiTimer.start();
                                break;
                            }
                            endTurn=true;
                            break;
                        }
                        //normal discard
                        currentPlayer.showDeck();


                        int keluarin = -2;
                        while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                            System.out.println("Select card to discard: ");
                            try{
                                keluarin=input.nextInt();
                            } catch(InputMismatchException e){
                                input.next();
                            }
                        }


                        Card checkHand = currentPlayer.checkHand(keluarin-1);
                        while (!isStackable(checkHand, lastCard.getTop())){
                            System.out.println("Whoops can't discard this");


                            keluarin = -2;
                            while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                System.out.println("Select card to discard: ");
                                try{
                                    keluarin=input.nextInt();
                                } catch(InputMismatchException e){
                                    input.next();
                                }
                            }


                            checkHand = currentPlayer.checkHand(keluarin-1);
                        }
                        Card discardedCard = currentPlayer.discard(keluarin - 1);
                        int timesDiscard = 1;
                        while (canMultiDisc(discardedCard, currentPlayer)) {
                            
                            int pilihan = -1;
                            while (pilihan>2 || pilihan<1){
                                System.out.println("Do you want to discard again? ");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                try{
                                    pilihan=input.nextInt();
                                } catch(InputMismatchException e){
                                    input.next();
                                }
                            }

                            if (pilihan == 1) {
                                currentPlayer.showDeck();


                                keluarin = -2;
                                while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                    System.out.println("Select card to discard: ");
                                    try{
                                        keluarin=input.nextInt();
                                    } catch(InputMismatchException e){
                                        input.next();
                                    }
                                }


                                checkHand = currentPlayer.checkHand(keluarin-1);
                                while (!
                                        ((checkHand.getProperty() instanceof Integer && discardedCard.getProperty() instanceof Integer &&
                                            checkHand.getProperty()==discardedCard.getProperty() && checkHand.getColor().equals(discardedCard.getColor()))
                                            ||
                                        (checkHand.getProperty() instanceof String && discardedCard.getProperty() instanceof String &&
                                            checkHand.getProperty().equals(discardedCard.getProperty()) && checkHand.getColor().equals(discardedCard.getColor())))
                                ){
                                    System.out.println("Whoops can't discard this");


                                    keluarin = -2;
                                    while (keluarin>currentPlayer.getRemainingCards() || keluarin<1){
                                        System.out.println("Select card to discard: ");
                                        try{
                                            keluarin=input.nextInt();
                                        } catch(InputMismatchException e){
                                            input.next();
                                        }
                                    }

                                    checkHand = currentPlayer.checkHand(keluarin-1);
                                }
                                discardedCard = currentPlayer.discard(keluarin - 1);
                                timesDiscard++;
                            } else {
                                break;
                            }
                        }
                        if (discardedCard.getProperty() instanceof String){
                            String currentCardPower = discardedCard.getProperty();

                            switch (currentCardPower) {
                                case "Skip" : skipCards = timesDiscard;break;
                                case "Reverse" : revCards = timesDiscard;break;
                                case "Draw 2" : draw2Stacks = timesDiscard;break;
                                case "Wild Draw 4" :
                                    draw4 = timesDiscard;
                                    chooseColor = true;
                                break;
                                case "Wild Color" : chooseColor = true;break;
                            }
                        }
                        if (chooseColor){

                            int intSelectedColor = -1;
                            while (intSelectedColor>4 || intSelectedColor<1){
                                UI.chooseWildColor();
                                try{
                                    intSelectedColor=input.nextInt();
                                } catch(InputMismatchException e){
                                    input.next();
                                }
                            }

                            String selectedColor="Black";
                            switch (intSelectedColor){
                                case 1 : selectedColor="Red";break;
                                case 2 : selectedColor="Green";break;
                                case 3 : selectedColor="Blue";break;
                                case 4 : selectedColor="Yellow";break;
                            }
                            discardedCard.setColor(selectedColor);
                            chooseColor=false;
                        }
                        lastCard.setTop(discardedCard);

                        // Eligible for HIJI
                        isPlayerHiji=(currentPlayer.getRemainingCards()==1);
                        if (isPlayerHiji){
                            hijiTimer.start();
                            break;
                        }
                        endTurn=true;
                        break;

                    // Draw
                    case 3:
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
                        if (draw2Stacks>0) {
                            System.out.println("Sorry, you must draw.");
                            System.out.println("Drew "+Card.Red+draw2Stacks*2+" cards."+Card.Reset);
                            for (int i=0;i<draw2Stacks;i++){
                                currentPlayer.draw(deck);
                                currentPlayer.draw(deck);
                            }
                            draw2Stacks=0;
                            endTurn=true;
                            break;
                        }
                        if (draw4>0) {
                            System.out.println("Sorry, you must draw.");
                            System.out.println("Drew +"+Card.Red+draw4*4+" cards.");
                            for (int i=0;i<draw4;i++){
                                currentPlayer.draw(deck);
                                currentPlayer.draw(deck);
                                currentPlayer.draw(deck);
                                currentPlayer.draw(deck);
                            }
                            draw4=0;
                            endTurn=true;
                            break;
                        }
                        currentPlayer.draw(deck);
                        Card drawedCard = currentPlayer.checkHand(currentPlayer.getRemainingCards()-1);
                        System.out.println("Card Drawed: ");
                        drawedCard.printCard();
                        if (isStackable(drawedCard, lastCard.getTop())) {

                            int pilihan = -1;
                            while (pilihan>2 || pilihan<1){
                                System.out.println("You can discard your recent card, do you want to discard it? ");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                try{
                                    pilihan=input.nextInt();
                                } catch(InputMismatchException e){
                                    input.next();
                                }
                            }

                            if (pilihan == 1) {
                                discardedCard = currentPlayer.discard(currentPlayer.getRemainingCards()-1);
                                if (discardedCard.getProperty() instanceof String){
                                    String currentCardPower = discardedCard.getProperty();
        
                                    switch (currentCardPower) {
                                        case "Skip" : skipCards++;break;
                                        case "Reverse" : revCards++;break;
                                        case "Draw 2" : draw2Stacks++;break;
                                        case "Wild Draw 4" :
                                            draw4++;
                                            chooseColor = true;
                                        break;
                                        case "Wild Color" : chooseColor = true;break;
                                    }
                                }
                                if (chooseColor){

                                    int intSelectedColor = -1;
                                    while (intSelectedColor>4 || intSelectedColor<1){
                                        UI.chooseWildColor();
                                        try{
                                            intSelectedColor=input.nextInt();
                                        } catch(InputMismatchException e){
                                            input.next();
                                        }
                                    }

                                    String selectedColor="Black";
                                    switch (intSelectedColor){
                                        case 1 : selectedColor="Red";break;
                                        case 2 : selectedColor="Green";break;
                                        case 3 : selectedColor="Blue";break;
                                        case 4 : selectedColor="Yellow";break;
                                    }
                                    discardedCard.setColor(selectedColor);
                                    chooseColor=false;
                                }
                                lastCard.setTop(discardedCard);
                                isPlayerHiji=(currentPlayer.getRemainingCards()==1);
                                if (isPlayerHiji){
                                    hijiTimer.start();
                                    break;
                                }
                            }
                        }
                        deck.shuffle();
                        endTurn = true;
                        break;

                    // Declare Hiji
                    case 4:
                        // Interrupt timer if player successfully declared HIJI before 3 seconds
                        if (isPlayerHiji && hijiTimer.isAlive()) {
                            hijiTimer.interrupt();
                        }
                        try {
                            currentPlayer.declareHiji(deck);
                        }
                        catch (Exception e) {
                            hijiRuleViolation(currentPlayer,deck);
                        }
                        endTurn=true;
                        break;

                    // List Players
                    case 5:
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
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
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
                        System.out.println("It's currently " + Card.Blue + currentPlayer.getName() + Card.Reset + "'s turn.");
                        System.out.println("Next turn: " + Card.Red + playerList.get(nextPlayerOrder).getName() + Card.Reset);
                        break;

                    // Help
                    case 7:
                        if (hijiTimer.isAlive()) {
                            UI.hijiWarning();
                            break;
                        }
                        UI.showHelp();
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
                winner=currentPlayer;
            }
        }

        UI.showVictoryScreen(winner);
    }

    // Draw 2 cards punishment for late HIJI / declaring when >1
    private static void hijiRuleViolation(Player player, Deck deck){
        player.draw(deck);
        player.draw(deck);
        deck.shuffle();
        System.out.println(Card.Red+"2 cards have been automatically added to your hand."+Card.Reset);
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
                System.out.println("Input any number to end your turn...");
            }
            catch (InterruptedException e){

            }
        }
    }


    // Check if selected card stackable with current last card
    public static boolean isStackable(Card selectedCard, Card cardBefore) {

        // If both are numcards, Same number OR same color
        if (checkCard(cardBefore).equals("NumCard") && checkCard(selectedCard).equals("NumCard")){
            return cardBefore.getProperty() == selectedCard.getProperty() || cardBefore.getColor().equals(selectedCard.getColor());
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
            return cardBefore.getProperty().equals(selectedCard.getProperty()) || cardBefore.getColor().equals(selectedCard.getColor());
        }

        // If actioncard and wildcard, always true
        else if (checkCard(cardBefore).equals("ActionCard") && checkCard(selectedCard).equals("WildCard"))
            return true;
        else return true;
    }

    //Check type of selected card
    public static String checkCard(Card selectedCard) {
        if (selectedCard.getProperty() instanceof String){
            if (selectedCard.getColor().equals("Black")) return "WildCard";
            else return "ActionCard";
        }
        else return "NumCard";
    }

    //Check if player can multiple discard based on first card drawed
    public static boolean canMultiDisc(Card selectedCard,Player currentPlayer){
        for (int i=0;i<currentPlayer.getPlayerHand().size();i++){
            Card card = currentPlayer.getPlayerHand().get(i);
            if (card.getProperty()==selectedCard.getProperty() && card.getColor().equals(selectedCard.getColor())){
                return true;
            }
        }
        return false;
    }
    //check if player can discard
    public static boolean canDisc(Card selectedCard, Player currentPlayer){
        for (int i=0;i<currentPlayer.getPlayerHand().size();i++){
            Card card = currentPlayer.getPlayerHand().get(i);
            if (isStackable(card, selectedCard)){
                return true;
            }
        }
        return false;
    }
}
