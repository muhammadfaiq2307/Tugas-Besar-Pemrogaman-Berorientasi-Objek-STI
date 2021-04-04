# TugasBesarOOP
HIJI, UNO look-alike game implemented on Java.

Cara menjalankan program:
1. Compile semua file di folder src dengan perintah javac *.java
2. Jalankan java Main di direktori yang sama dengan folder src

Cara memainkan:
1. Di awal permainan, semua pemain akan mendapatkan 7 buah kartu
2. Sistem akan memberikan 1 kartu acak sebagai kartu awal
3. Pemain yang akan memulai giliran pertama akan diacak.
4. Aturan permainan adalah sebagai berikut :
   a. Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu.
   b. Apabila pemain tidak mengeluarkan kartu, pemain wajib mengambil satu kartu dari deck dengan command Draw.
   c. Apabila kartu yang baru diambil tersebut bisa dikeluarkan, pemain boleh mengeluarkan kartu tersebut
   d. Apabila kartu tersebut tidak dapat dimainkan, maka giliran diselesaikan tanpa mengeluarkan kartu
5. Beberapa jenis kartu memiliki power tertentu yang dapat memengaruhi jalannya permainan, seperti :
   a. Draw 2 (+2) warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama draw)
      Pemain selanjutnya harus mengambil 2 kartu. Apabila pemain tersebut mengeluarkan Draw 2, maka pemain selanjutnya mengambil 4, dan seterusnya. 
      Pemain yang mengambil kartu tambahan kehilangan gilirannya dan dilewati.
   b. Draw 4 (+4). (Dikeluarkan kapanpun)
      Pemain selanjutnya harus mengambil 4 kartu, dan pemain yang mengeluarkan kartu Draw 4 dapat memilih warna yang dapat dimainkan selanjutnya.
   c. Wildcard. (Dikeluarkan kapanpun)
      Pemain dapat memilih warna yang dapat dikeluarkan oleh pemain selanjutnya.
   d. Reverse warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama reverse)
      Urutan pemain dibalik berkali-kali sebanyak kartu reverse yang dikeluarkan
   e. Skip warna Merah/Hijau/Kuning/Biru. (Dikeluarkan sesuai warna top card atau sesama skip)
      Pemain selanjut-selanjutnya dilewati sebanyak kartu skip yang dikeluarkan (kehilangan giliran).
6. Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan Declare HIJI dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck.
7. Pemain dinyatakan menang apabila kartu yang dipegangnya sudah habis, dan permainan selesai.

CATATAN
Jika pada cmd tulisan tidak ada warnanya atau warna tidak ter-render, maka anda harus mendownload windows terminal pada microsoft store dan membuka cmd dari aplikasi tersebut
