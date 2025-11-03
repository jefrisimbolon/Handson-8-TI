public class BasicInheritancePractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Basic Inheritance
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * konsep dasar inheritance, penggunaan super, dan hierarki class.
         */

        // ===== INHERITANCE DASAR =====
        System.out.println("=== INHERITANCE DASAR ===");

        Mobil mobil = new Mobil("Toyota", "Hitam", 2022, 4, "Bensin");
        mobil.displayInfo();
        mobil.nyalakanAC();

        Motor motor = new Motor("Honda", "Merah", 2023, "Sport");
        motor.displayInfo();
        motor.klakson();
        motor.lakukanWheely();

        // ===== PENGGUNAAN SUPER =====
        System.out.println("\n=== PENGGUNAAN SUPER ===");

        Truk truk = new Truk("Mitsubishi", "Putih", 2021, 5.0, 6);
        truk.displayInfo();
        truk.klakson();

        // ===== MULTILEVEL INHERITANCE =====
        System.out.println("\n=== MULTILEVEL INHERITANCE ===");

        MobilSport mobilSport = new MobilSport("Ferrari", "Merah", 2024, 2, "Bensin", true, 320);
        mobilSport.displayInfo();
        mobilSport.aktifkanTurbo();

        // ===== TESTING INHERITANCE =====
        System.out.println("\n=== TESTING INHERITANCE ===");

        System.out.println("mobilSport instanceof MobilSport: " + (mobilSport instanceof MobilSport));
        System.out.println("mobilSport instanceof Mobil: " + (mobilSport instanceof Mobil));
        System.out.println("mobilSport instanceof Kendaraan: " + (mobilSport instanceof Kendaraan));
        System.out.println("mobilSport instanceof Object: " + (mobilSport instanceof Object));

        System.out.println("Protected property (merk): " + mobilSport.merk);
        System.out.println("Private property via getter (nomorRangka): " + mobilSport.getNomorRangka());
    }
}

class Kendaraan {
    protected String merk;
    protected String warna;
    protected int tahunProduksi;
    private String nomorRangka;

    public Kendaraan(String merk, String warna, int tahunProduksi) {
        System.out.println("Constructor Kendaraan dipanggil");
        this.merk = merk;
        this.warna = warna;
        this.tahunProduksi = tahunProduksi;
        this.nomorRangka = generateNomorRangka(merk, tahunProduksi);
    }

    private String generateNomorRangka(String merk, int tahun) {
        return merk.substring(0, 3).toUpperCase() + tahun + "001";
    }

    public String getNomorRangka() {
        return nomorRangka;
    }

    public void displayInfo() {
        System.out.println("=== Info Kendaraan ===");
        System.out.println("Merk: " + merk);
        System.out.println("Warna: " + warna);
        System.out.println("Tahun Produksi: " + tahunProduksi);
    }

    public void klakson() {
        System.out.println("Tin tin!");
    }
}

class Mobil extends Kendaraan {
    private int jumlahPintu;
    private String jenisBahanBakar;

    public Mobil(String merk, String warna, int tahunProduksi, int jumlahPintu, String jenisBahanBakar) {
        super(merk, warna, tahunProduksi);
        System.out.println("Constructor Mobil dipanggil");
        this.jumlahPintu = jumlahPintu;
        this.jenisBahanBakar = jenisBahanBakar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Jumlah Pintu: " + jumlahPintu);
        System.out.println("Bahan Bakar: " + jenisBahanBakar);
    }

    public void nyalakanAC() {
        System.out.println("AC menyala");
    }
}

class Motor extends Kendaraan {
    private String jenisMotor;

    public Motor(String merk, String warna, int tahunProduksi, String jenisMotor) {
        super(merk, warna, tahunProduksi);
        System.out.println("Constructor Motor dipanggil");
        this.jenisMotor = jenisMotor;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Jenis Motor: " + jenisMotor);
    }

    @Override
    public void klakson() {
        System.out.println("Tiiin tiiin! (suara motor)");
    }

    public void lakukanWheely() {
        System.out.println(merk + " melakukan wheelie!");
    }
}

class Truk extends Kendaraan {
    private double kapasitasMuatan;
    private int jumlahRoda;

    public Truk(String merk, String warna, int tahunProduksi, double kapasitasMuatan, int jumlahRoda) {
        super(merk, warna, tahunProduksi);
        System.out.println("Constructor Truk dipanggil");
        this.kapasitasMuatan = kapasitasMuatan;
        this.jumlahRoda = jumlahRoda;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Kapasitas Muatan: " + kapasitasMuatan + " ton");
        System.out.println("Jumlah Roda: " + jumlahRoda);
    }

    @Override
    public void klakson() {
        super.klakson();
        System.out.println("HONK HONK! (suara klakson truk yang lebih keras)");
    }
}

class MobilSport extends Mobil {
    private boolean turbo;
    private int kecepatanMaksimal;

    public MobilSport(String merk, String warna, int tahunProduksi, int jumlahPintu,
                      String jenisBahanBakar, boolean turbo, int kecepatanMaksimal) {
        super(merk, warna, tahunProduksi, jumlahPintu, jenisBahanBakar);
        System.out.println("Constructor MobilSport dipanggil");
        this.turbo = turbo;
        this.kecepatanMaksimal = kecepatanMaksimal;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Turbo: " + (turbo ? "Ya" : "Tidak"));
        System.out.println("Kecepatan Maksimal: " + kecepatanMaksimal + " km/jam");
    }

    public void aktifkanTurbo() {
        if (turbo) {
            System.out.println("TURBO DIAKTIFKAN! " + merk + " melaju dengan kecepatan maksimal!");
        } else {
            System.out.println("Mobil ini tidak memiliki turbo.");
        }
    }
}
