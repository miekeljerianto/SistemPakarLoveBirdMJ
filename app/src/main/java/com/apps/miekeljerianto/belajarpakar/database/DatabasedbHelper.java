package com.apps.miekeljerianto.belajarpakar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabasedbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "sistempakar.db";

    public DatabasedbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Table Gejala
    public void createTableGejala(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS tbl_gejala");
        db.execSQL("CREATE TABLE if not exists tbl_gejala (kode_gejala TEXT PRIMARY KEY, nama_gejala TEXT);");
    }

    //tbl_penyakit
    public void createTablePenyakit(SQLiteDatabase db2) {
        db2.execSQL("DROP TABLE IF EXISTS tbl_penyakit");
        db2.execSQL("CREATE TABLE if not exists tbl_penyakit (kode_penyakit TEXT PRIMARY KEY , nama_penyakit TEXT, penyebab TEXT, solusi TEXT);");
    }

    // Table Aturan atau rules
    public void createTableRules(SQLiteDatabase db3) {
        db3.execSQL("DROP TABLE IF EXISTS tbl_rules");
        db3.execSQL("CREATE TABLE if not exists tbl_rules (id_rules TEXT PRIMARY KEY, nama_gejala_rules TEXT, " +
                "ya_rules VARCHAR(5), tidak_rules VARCHAR(5), mulai_rules VARCHAR(5), selesai_rules VARCHAR(5));");
    }


// ==============================isi table gejala
    public void isiTableGejala(SQLiteDatabase db) {
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G01', 'Mata burung terlihat merah');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G02', 'Bagian mata sering mengeluarkan cairan');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G03', 'Pembengkakan di daerah bagian mata');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G04', 'Mata terlihat sayup');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G05', 'Burung sering terlihat tidur pada pagi hari atau siang hari');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G06', 'Burung sering bersin');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G07', 'Mengesek-gesekan paruh ke tangkringan');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G08', 'Ada ingus atau lendir pada burung');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G09', 'Burung lemas');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G10', 'Warna bulu burung terlihat kusam');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G11', 'Burung mencabuti bulunya sendiri');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G12', 'Banyak kutu pada bulu brung');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G13', 'Kotoran menjadi cair');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G14', 'Kotoran mejadi seperti cacing');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G15', 'Sulit buang kotoran');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G16', 'Kotoran menjadi putih seperti kapur');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G17', 'Kotoran menepel pada anus');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G18', 'Bagian perut nampak membesar');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G19', 'Pembengkakan pada anus');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G20', 'Susah buat kotoran');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G21', 'Bulu burung sering mengembang');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G22', 'Kaki terlihat membengkak');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G23', 'Kuku burung memanjang');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G24', 'Permukaan kaki membengkak');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G25', 'Berat badan burung berkurang');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G26', 'Tulang bagian dada menonjol');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G27', 'Nafsu makan menurun');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G28', 'Leher terlihat miring atau sering berputar');");
        db.execSQL("INSERT INTO tbl_gejala VALUES ('G29', 'Burung terlihat tegang atau gemetaran');");

        //diagnosa memilih gejala ya / tidak maka akan
    }

    //isi table rules
    public void isiTablePenyakit(SQLiteDatabase db2) {
        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P01', 'Penyakit SNOT CORZYA', 'Penyakit Snot " +
                "penyakit . Gastritis kronik baru merupakan diagnosa setelah didasarkan pada hasil " +
                "pemeriksaan hispatologi.', " +
                "'-Makan tepat waktu\n' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P02', 'Penyakit Gangguan Pernafasan', 'Tukak " +
                "gaster merupakan luka terbuka dengan pinggir edema disertai indurasi dengan dasar " +
                "tukak ditutupi debris.', " +
                "'-Istirahat dan hindari stress' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P03', 'Penyakit Kutu', 'Burung Menjadi Sering " +
                "Garuk-garuk dan mandi.', " +
                "'-Makan dalam ju' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P04', 'Penyakit Pencernaan', 'Gastroenteritis " +
                "merupakan infeksi pada saluran pencernaan yang biasanya disebabkan oleh infeksi yang " +
                "menyebar melalui air dan makanan yang sudah tercemar oleh tinja yang terinfeksi.', " +
                "'-Biasakan mencuci tangan sebelum makan dan masaklah ' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P05', 'Penyakit Egg Binding', 'Ileus merupakan " +
                "suatu keadaan dimana pergerakan kontraksi normal dinding usus untuk sementara waktu " +
                "berhenti. Ileus dapat disebabkan oleh suatu infeksi atau bekuan darah di alam perut. " +
                "Untuk menetapkan diagnosa perlu dilakukan pemeriksaan foto rontgen perut.', " +
                "'-Segera berobat ke dokter untuk mendapatkan infus dan pengobatan medis lainnya\n' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P06', 'Penyakit Bubul', 'Demam tifoid merupakan " +
                "salah satu gangguan pencernaan yang cukup bayak diderita di Indonesia. Tipus disebabkan " +
                "oleh kuman atau bakteri yang disebut Salmonella Typi. Penyakit ini menular melalui " +
                "makanan atau minuman yang terkontaminasi kuman tipus.', " +
                "'-Makanlah yang lunak dan mudah ' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P07', 'Penyakit Nyilet', 'Disentri basilar adalah " +
                "suatu infeksi akut radang kolon yang disebabkan kuman genus shigella yang terdapat " +
                "di lingkungan yang buruk.', " +
                "'-Istirahat yang cukup' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P08', 'Penyakit Tetelo atau Stroke', 'Diare akut " +
                "adalah pengeluaran kotoran (tinja) yang lebih sering dari biasanya dan perut terasa sakit dan kembung.', " +
                "'-Makan dalam jumlah sedikit ' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P09', 'Penyakit Kaki Lemas', 'Apendisitis akut " +
                "adalah radang yang timbul secara mendadak pada apendik, merupakan salah satu kasus " +
                "akut abdomen yang paling sering ditemui dan jika tidak ditangani dapat menyebabkan perforasi.', " +
                "'-Bed rest total posisi fowler (setengah duduk' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P10', 'Penyakit  Berak Kapur', 'Apendisitis " +
                "akut adalah radang yang timbul secara mendadak pada apendik, merupakan salah satu " +
                "kasus akut abdomen yang paling sering ditemui dan jika tidak ditangani dapat menyebabkan perforasi.', " +
                "'-Bed rest total posisi fowler (setengah dudu');");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P11', 'Penyakit Stres', 'Apendisitis akut adalah " +
                "radang yang timbul secara mendadak pada apendik, merupakan salah satu kasus akut abdomen " +
                "yang paling sering ditemui dan jika tidak ditangani dapat menyebabkan perforasi.', " +
                "'-Bed rest total ' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P12', 'Penyakit Egg Binding ', 'Apendisitis akut " +
                "adalah radang yang timbul secara mendadak pada apendik, merupakan salah satu kasus akut " +
                "abdomen yang paling sering ditemui dan jika tidak ditangani dapat menyebabkan perforasi.', " +
                "'-Bed res' );");

        db2.execSQL("INSERT INTO tbl_penyakit VALUES ('P20', 'Burung Anda Masih Sehat ', '', " +
                "'' );");
    }





// ================================> TABLE RULES ATAU ATURAN
    //Mengecek diagnosa jika G001 Y > ke G002 jika tidak T > ke G0013 > hasil ke Pneyakit
    // =>
    public void isiTabelRules(SQLiteDatabase db3){
        ContentValues cv3 = new ContentValues();
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G01', 'Kotoran menjadi cair', 'G07', 'G02', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G02', 'Kotoran mejadi seperti cacing ', 'G01', 'G04', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G03', 'Kotoran menjadi kehijauan kemudian putih seperti kapur', 'G04', 'G10', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G04', 'Kotoran menepel pada anus', 'G05', 'G06', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G05', 'Pembengkakan pada anus', 'P09', 'G07', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G06', 'Bagian perut nampak membesar', 'G06', 'G09', 'Y', 'T');");

        //membaca dari sini
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G07', 'Susah buat kotoran', 'G12', 'G03', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G08', 'Kaki terlihat membengkak', 'G08', 'G02', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G09', 'Kuku burung memanjang', 'G03', 'G12', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G10', 'Permukaan kaki membengkak', 'G11', 'G13', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G11', 'Berat badan burung berkurang', 'P09', 'G12', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G12', 'Kotoran LB berubah jadi Cair', 'G10', 'P20', 'Y', 'T');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('G13', 'Kotoran LB putih seperti kapur', 'G01', 'G01', 'Y', 'T');");

        //diagnosa memilih gejala ya / tidak maka akan
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P01', 'Penyakit Mata atau Snot', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P02', 'Ganguan Pernafasan', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P03', 'Penyakit Bubul', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P04', 'Penyakit Cacar', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P05', 'Penyakit Kutu', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P06', 'Penyakit Nyilet', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P07', 'Penyakit Kaki Lemas', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P08', 'Penyakit Tetelo', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P09', 'Penyakit Berak Kapur', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P10', 'Penyakit Berak Kapur', '0', '0', 'T', 'Y');");
        db3.execSQL("INSERT INTO tbl_rules VALUES ('P20', 'Burung Anda Sehat', '0', '0', 'T', 'Y');");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public void onCreate(SQLiteDatabase db) {


    }



}

