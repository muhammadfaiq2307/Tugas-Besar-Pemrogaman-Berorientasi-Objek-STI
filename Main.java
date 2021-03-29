import java.util.*;
public class Main {
    public static void main (String args[]){
        List<Player> playerList = new ArrayList<Player>();
        boolean isVictory=false;
        int currentPlayerOrder=0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of players (2-6):");
        int totalPlayers = input.nextInt();
        while (totalPlayers>6 || totalPlayers<2){
            System.out.println("Players must be between 2-6!");
            totalPlayers=input.nextInt();
        }
        System.out.println("Registered "+totalPlayers+" players.");
        System.out.println("Please enter your names.");
        for (int i=0;i<totalPlayers;i++){
            String playerName = input.next();
            Player tempPlayer = new Player(playerName);
            playerList.add(tempPlayer);
            System.out.println("Welcome aboard, "+tempPlayer.getName()+".");
        }

        Deck deck = new Deck();
        System.out.println("The game has begun. The top card is currently: ");
        deck.getTop().printCard();
        LastCard lastCard = new LastCard(deck.getTop());
        deck.newTop();
        Random rand = new Random();
        int firstPlayer = rand.nextInt(playerList.size());
        Collections.swap(playerList,0,firstPlayer);
        System.out.println(Card.Blue+playerList.get(0).getName()+Card.Reset+" has been randomly selected" +
                " as the first player.");
        System.out.println("The current order of players is:");
        for (Player player: playerList){
            System.out.println(player.getName());
        }
        // Default Values
        int skipCards=0;
        int playerRotateDirection=1;
        int nextPlayerOrder;
        while (!isVictory){
            boolean endTurn=false;
            Player currentPlayer=playerList.get(currentPlayerOrder);
            skipCards=0;
            System.out.println("Skip: "+skipCards+", Reverse: "+playerRotateDirection+", CurrOrder: "+currentPlayerOrder);
            if (playerRotateDirection==1){
                nextPlayerOrder = (currentPlayerOrder+1) % totalPlayers;
            }
            else {
                nextPlayerOrder = (currentPlayerOrder-1);
                if (nextPlayerOrder==-1){
                    nextPlayerOrder=totalPlayers-1;
                }

            }
            currentPlayer.setGiliran(true);
            while (!endTurn){
                System.out.println("Current Card:");
                lastCard.getTop().printCard();
                System.out.println("What will "+currentPlayer.getName()+" do?");
                //check kartuuuu dulu
                if(currentPlayer.checkPlayerHandNumCard(lastCard.getTop())||currentPlayer.checkPlayerHandPowCard(lastCard.getTop())){
                    showIngameMenuCanDiscard();
                    int playerCommand = input.nextInt();
                    switch (playerCommand){
                        case 1:
                            System.out.println(currentPlayer.getName()+"'s Deck:");
                            currentPlayer.showDeck();
                            break;
                        case 2:
                            currentPlayer.showDeck();
                            System.out.print("Mau ngeluarin yg mana lur?: ");
                            int keluarin = input.nextInt();
                            Card kartuKeluarin = currentPlayer.Discard(keluarin-1);
                            lastCard.setTop(kartuKeluarin);
                            while (currentPlayer.checkPlayerHandNumCard(lastCard.getTop())||currentPlayer.checkPlayerHandPowCard(lastCard.getTop())){
                                System.out.println("Mau ngeluarin lagi ga lur, ketik 1 untuk ya 2 untuk tidak");
                                int mauNgga = input.nextInt();
                                if (mauNgga==1){
                                    System.out.print("Mau ngeluarin yg mana lur?: ");
                                    keluarin = input.nextInt();
                                    kartuKeluarin = currentPlayer.Discard(keluarin-1);
                                    lastCard.setTop(kartuKeluarin);
                                } else {
                                    break;
                                }
                            }
                            break;
                        case 3:
                            currentPlayer.Draw(deck);
                            deck.newTop();
                            break;
                        case 4:
                            break;
                        case 5:
                            int iterVar=1;
                            for (Player player: playerList){
                                System.out.println("Player "+iterVar+": "+player.getName());
                                System.out.println("Remaining cards: "+player.getRemainingCards());
                                if (player.isGiliran()){
                                    System.out.println(Card.Green+"Current Turn"+Card.Reset);
                                } else {
                                    System.out.println(Card.Red+"Not in turn"+Card.Reset);
                                }
                                iterVar++;
                            }
                            break;
                        case 6:
                            System.out.println("It's currently "+Card.Blue+currentPlayer.getName()+Card.Reset+"'s turn.");
                            System.out.println("Next turn: "+Card.Red+playerList.get(nextPlayerOrder).getName()+Card.Reset);
                            break;
                        case 7:
                            System.out.println("kontol lah");
                            break;
                        case 8:
                            System.out.print("Enter number of cards: ");
                            skipCards= input.nextInt();
                            endTurn=true;
                            break;
                        case 9:
                            System.out.print("Enter number of cards: ");
                            int revCards=input.nextInt();
                            if (revCards%2==1){
                                playerRotateDirection*=-1;
                            }
                            endTurn=true;
                            break;
                        case 0:
                            endTurn=true;
                            break;
                    }
                } else {
                    showIngameMenuCantDiscard();
                    int playerCommand = input.nextInt();
                    switch (playerCommand){
                        case 1:
                            System.out.println(currentPlayer.getName()+"'s Deck:");
                            currentPlayer.showDeck();
                            break;
                        case 2:
                            currentPlayer.Draw(deck);
                            if(currentPlayer.checkPlayerHandNumCard(lastCard.getTop())||currentPlayer.checkPlayerHandPowCard(lastCard.getTop())){
                                System.out.println("Ini mau dikeluarin ga lur, ketik 1 untuk ya 2 untuk tidak");
                                int mauNgga = input.nextInt();
                                if (mauNgga==1){
                                    Card mau = currentPlayer.Discard(currentPlayer.getRemainingCards()-1);
                                    lastCard.setTop(mau);
                                }
                            }
                            deck.newTop();
                            break;
                        case 3:
                            break;
                        case 4:
                            int iterVar=1;
                            for (Player player: playerList){
                                System.out.println("Player "+iterVar+": "+player.getName());
                                System.out.println("Remaining cards: "+player.getRemainingCards());
                                if (player.isGiliran()){
                                    System.out.println(Card.Green+"Current Turn"+Card.Reset);
                                } else {
                                    System.out.println(Card.Red+"Not in turn"+Card.Reset);
                                }
                                iterVar++;
                            }
                            break;
                        case 5:
                            System.out.println("It's currently "+Card.Blue+currentPlayer.getName()+Card.Reset+"'s turn.");
                            System.out.println("Next turn: "+Card.Red+playerList.get(nextPlayerOrder).getName()+Card.Reset);
                            break;
                        case 6:
                            System.out.println("kontol lah");
                            break;
                        case 0:
                            endTurn=true;
                            break;
                }
            }
            if (playerRotateDirection==1){
                currentPlayerOrder = (nextPlayerOrder+(skipCards))% totalPlayers;
            }
            else {
                nextPlayerOrder=currentPlayerOrder-skipCards-1;
                if (nextPlayerOrder<0){
                    int negativeOverflow = nextPlayerOrder;
                    System.out.println(negativeOverflow);
                    currentPlayerOrder = totalPlayers-((negativeOverflow*-1)%totalPlayers);
                }
                else {
                    currentPlayerOrder=nextPlayerOrder;
                }
            }
            System.out.println(currentPlayerOrder);
            currentPlayer.setGiliran(false);
//            if (currentPlayerOrder==5){
//                isVictory=true;
//            }
        }}
    }
    public static void showIngameMenuCanDiscard(){
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
    public static void showIngameMenuCantDiscard(){
        System.out.println("0. <DEBUG> End Turn");
        System.out.println("1. List Cards");
        System.out.println("2. Draw");
        System.out.println("3. Declare HIJI");
        System.out.println("4. List Players");
        System.out.println("5. View Player in Turn");
        System.out.println("6. Help");
}}
