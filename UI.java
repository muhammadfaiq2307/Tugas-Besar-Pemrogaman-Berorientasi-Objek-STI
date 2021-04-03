public class UI {

    public static void titleScreen() {
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
        System.out.println(Card.Red+"Enter 1 to start..."+Card.Reset);
    }

    public static void showIngameMenu(){
        System.out.println("1. List Cards");
        System.out.println("2. Discard");
        System.out.println("3. Draw");
        System.out.println("4. Declare HIJI");
        System.out.println("5. List Players");
        System.out.println("6. View Player in Turn");
        System.out.println("7. Help");
    }

    public static void chooseWildColor(){
        System.out.println("Choose your color: ");
        System.out.println("1. "+Card.Red+"Red"+Card.Reset);
        System.out.println("2. "+Card.Green+"Green"+Card.Reset);
        System.out.println("3. "+Card.Blue+"Blue"+Card.Reset);
        System.out.println("4. "+Card.Yellow+"Yellow"+Card.Reset);
    }

    public static void showVictoryScreen(Player player){
        System.out.println(Card.Red +" '  "+Card.Yellow+"`  "+Card.Blue+".                        `            "+Card.Red+" .   "+Card.Cyan+"'      . /");
        System.out.println(Card.Blue+" \\ "+Card.Cyan+"'"+"   /"+"  ."+Card.Red +"  '              "+"` "+Card.Purple+"  .                 ' "+Card.Yellow+" \\"+Card.Blue+" /   `   ");
        System.out.println(Card.Cyan+"  // '"+Card.Red +" `     "+Card.Cyan+".  ` "+Card.Red +"       `                "+" /          '"+" ` \\\\ "+Card.Cyan+"/   "+Card.Red+".  ");
        System.out.println(Card.Cyan+"   ` "+Card.Blue+". "+" _     _  ___   __    _  __    _  _______  ______ "+Card.Blue+"  . "+Card.Yellow+" '");
        System.out.println(Card.Yellow+"    ' "+" | | _ | ||   | |  |  | ||  |  | ||       ||    _ |    "+Card.Purple+"`  . ");
        System.out.println(Card.Purple+" .    "+" | || || ||   | |   |_| ||   |_| ||    ___||   | ||  "+"/"+Card.Yellow+"  '");
        System.out.println(Card.Cyan+"    . "+" |       ||   | |       ||       ||   |___ |   |_||_   ");
        System.out.println(Card.Yellow+"   / "+"  |       ||   | |  _    ||  _    ||    ___||    __  |   "+Card.Yellow+"."+Card.Blue+"   '");
        System.out.println(Card.Blue+" '  "+"   |   _   ||   | | | |   || | |   ||   |___ |   |  | |"+Card.Blue+" `");
        System.out.println(" `"+Card.Red +"   . "+"|__| |__||___| |_|  |__||_|  |__||_______||___|  |_|  ' "+Card.Purple+" .");
        System.out.println(Card.Blue+" ."+Card.Purple+" \\\\ "+Card.Yellow+" '             '                                   "+Card.Cyan+"  // "+Card.Blue+"' "+Card.Reset);
        System.out.println("         ======================"+player.getName()+"======================");
    }

    public static void showHelp(){
        System.out.println("<< HELP>>");
        System.out.println("Alur permainan :");
        System.out.println("1. Di awal permainan, semua pemain akan mendapatkan 7 buah kartu");
        System.out.println("2. Sistem akan memberikan 1 kartu acak sebagai kartu awal");
        System.out.println("3. Pemain yang akan memulai giliran pertama akan diacak.");
        System.out.println("4. Aturan permainan adalah sebagai berikut :");
        System.out.println("   a. Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu.");
        System.out.println("   b. Apabila pemain tidak mengeluarkan kartu, pemain wajib mengambil satu kartu dari deck dengan command Draw.");
        System.out.println("   c. Apabila kartu yang baru diambil tersebut bisa dikeluarkan, pemain boleh mengeluarkan kartu tersebut");
        System.out.println("   d. Apabila kartu tersebut tidak dapat dimainkan, maka giliran diselesaikan tanpa mengeluarkan kartu");
        System.out.println("5. Beberapa jenis kartu memiliki power tertentu yang dapat memengaruhi jalannya permainan, seperti :");
        System.out.println("   a. Draw 2 (+2) warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama draw)");
        System.out.println("      Pemain selanjutnya harus mengambil 2 kartu. Apabila pemain tersebut mengeluarkan Draw 2, maka pemain selanjutnya mengambil 4, dan seterusnya. Pemain yang mengambil kartu tambahan kehilangan gilirannya dan dilewati.");
        System.out.println("   b. Draw 4 (+4). (Dikeluarkan kapanpun)");
        System.out.println("      Pemain selanjutnya harus mengambil 4 kartu, dan pemain yang mengeluarkan kartu Draw 4 dapat memilih warna yang dapat dimainkan selanjutnya.");
        System.out.println("   c. Wildcard. (Dikeluarkan kapanpun)");
        System.out.println("      Pemain dapat memilih warna yang dapat dikeluarkan oleh pemain selanjutnya.");
        System.out.println("   d. Reverse warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama reverse)");
        System.out.println("      Urutan pemain dibalik berkali-kali sebanyak kartu reverse yang dikeluarkan");
        System.out.println("   e. Skip warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama skip)");
        System.out.println("      Pemain selanjut-selanjutnya dilewati sebanyak kartu skip yang dikeluarkan (kehilangan giliran).");
        System.out.println("6. Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan Declare HIJI dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck.");
        System.out.println("7. Pemain dinyatakan menang apabila kartu yang dipegangnya sudah habis, dan permainan selesai.");
    }

    public static void hijiWarning(){
        System.out.println(Card.Cyan+"Isn't there something else you're supposed to be doing right now..?"+Card.Reset);
    }
}
