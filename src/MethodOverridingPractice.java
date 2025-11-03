public class MethodOverridingPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Method Overriding
         *
         * Instruksi: Lengkapi semua latihan untuk menguasai method overriding,
         * covariant return types, dan best practices.
         */

        // ===== BASIC METHOD OVERRIDING =====
        System.out.println("=== BASIC METHOD OVERRIDING ===");

        // Latihan 1: Override method toString()
        // - Buat object Mahasiswa dengan parameter:
        //   nim: "2024001", nama: "Budi", jurusan: "Informatika", ipk: 3.75
        // - Print object menggunakan toString()

        // Ekspektasi Output:
        // Mahasiswa{nim='2024001', nama='Budi', jurusan='Informatika', ipk=3.75}
        Mahasiswa m1 = new Mahasiswa("2024001", "Budi", "Informatika", 3.75);
        System.out.println(m1);

        // Latihan 2: Override method equals()
        // - Buat dua object Mahasiswa dengan NIM yang sama
        // - Compare menggunakan equals()
        // - Compare menggunakan == (reference equality)

        // Ekspektasi Output:
        // m1.equals(m2): true (karena NIM sama)
        // m1 == m2: false (referensi berbeda)
        Mahasiswa m2 = new Mahasiswa("2024001", "Budi", "Informatika", 3.75);
        System.out.println("m1.equals(m2): " + m1.equals(m2));
        System.out.println("m1 == m2: " + (m1 == m2));

        // ===== OVERRIDING WITH SUPER =====
        System.out.println("\n=== OVERRIDING WITH SUPER ===");

        // Latihan 3: Override dengan memanggil super
        // - Buat object Dosen dengan parameter:
        //   nip: "198901001", nama: "Dr. Sarah", jurusan: "Informatika",
        //   mataKuliah: "PBO", pengalamanMengajar: 10
        // - Panggil method displayInfo()

        // Ekspektasi Output:
        // === Info Pegawai ===
        // NIP: 198901001
        // Nama: Dr. Sarah
        // Jurusan: Informatika
        // Mata Kuliah: PBO
        // Pengalaman: 10 tahun
        Dosen d1 = new Dosen("198901001", "Dr. Sarah", "Informatika", "PBO", 10);
        d1.displayInfo();

        // Latihan 4: Chain of overriding
        // - Buat object DosenProfessor extends Dosen
        // - Override displayInfo() untuk menambah info gelar profesor
        // - Test chain dari Pegawai -> Dosen -> DosenProfessor

        // Ekspektasi Output:
        // Pegawai constructor called
        // Dosen constructor called
        // DosenProfessor constructor called
        // [info lengkap dengan gelar profesor]
        DosenProfessor dp = new DosenProfessor("198801002", "Prof. John", "Informatika", "AI", 15);
        dp.displayInfo();

        // ===== COVARIANT RETURN TYPES =====
        System.out.println("\n=== COVARIANT RETURN TYPES ===");

        // Latihan 5: Return type yang lebih spesifik
        // - Override method clone() untuk return type yang lebih spesifik
        // - Parent return type: Pegawai
        // - Child return type: Dosen (covariant)

        // Ekspektasi Output:
        // Clone berhasil dengan type yang lebih spesifik
        // original != clone (referensi berbeda)
        // original.equals(clone) (konten sama)
        Dosen dClone = d1.clone();
        System.out.println("Clone berhasil dengan type yang lebih spesifik");
        System.out.println("original != clone: " + (d1 != dClone));
        System.out.println("original.equals(clone): " + d1.equals(dClone));

        // ===== OVERRIDING RULES =====
        System.out.println("\n=== OVERRIDING RULES ===");

        // Latihan 6: Access modifier rules
        // - Override method dengan access yang sama atau lebih luas
        // - Test: protected -> public (VALID)
        // - Test: public -> protected (INVALID - compile error)

        // Ekspektasi Output:
        // Widening access modifier: ALLOWED
        // Narrowing access modifier: COMPILE ERROR
        System.out.println("Widening access modifier: ALLOWED");
        System.out.println("Narrowing access modifier: COMPILE ERROR (tidak bisa dijalankan di sini)");

        // Latihan 7: Return type rules
        // - Override dengan same return type
        // - Override dengan covariant return type
        // - Override dengan unrelated return type (ERROR)

        // Ekspektasi Output:
        // Same return type: VALID
        // Covariant return type: VALID
        // Unrelated return type: COMPILE ERROR
        System.out.println("Same return type: VALID");
        System.out.println("Covariant return type: VALID");
        System.out.println("Unrelated return type: COMPILE ERROR (tidak bisa dikompilasi)");

        // Latihan 8: Final method
        // - Try to override final method -> COMPILE ERROR
        // - Final method tidak bisa di-override

        // Ekspektasi Output:
        // Cannot override final method: COMPILE ERROR
        System.out.println("Cannot override final method: COMPILE ERROR (tidak bisa dijalankan di sini)");
    }
}

/* ================================================================
 * Class Definitions
 * ================================================================ */

class Mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;
    private double ipk;

    public Mahasiswa(String nim, String nama, String jurusan, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    @Override
    public String toString() {
        return "Mahasiswa{nim='" + nim + "', nama='" + nama + "', jurusan='" + jurusan + "', ipk=" + ipk + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mahasiswa)) return false;
        Mahasiswa other = (Mahasiswa) obj;
        return this.nim.equals(other.nim);
    }
}

/* ---------- CLASS PEGAWAI ---------- */
class Pegawai {
    protected String nip;
    protected String nama;
    protected String jurusan;

    public Pegawai(String nip, String nama, String jurusan) {
        System.out.println("Pegawai constructor called");
        this.nip = nip;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    protected void displayInfo() {
        System.out.println("=== Info Pegawai ===");
        System.out.println("NIP: " + nip);
        System.out.println("Nama: " + nama);
        System.out.println("Jurusan: " + jurusan);
    }

    // Method clone() yang akan dioverride (covariant return type)
    protected Pegawai clone() {
        return new Pegawai(nip, nama, jurusan);
    }

    // Final method (tidak bisa dioverride)
    public final void statusPegawai() {
        System.out.println("Status: Pegawai Tetap");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pegawai)) return false;
        Pegawai other = (Pegawai) obj;
        return this.nip.equals(other.nip);
    }
}

/* ---------- CLASS DOSEN ---------- */
class Dosen extends Pegawai {
    protected String mataKuliah;
    protected int pengalamanMengajar;

    public Dosen(String nip, String nama, String jurusan, String mataKuliah, int pengalamanMengajar) {
        super(nip, nama, jurusan);
        System.out.println("Dosen constructor called");
        this.mataKuliah = mataKuliah;
        this.pengalamanMengajar = pengalamanMengajar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Mata Kuliah: " + mataKuliah);
        System.out.println("Pengalaman: " + pengalamanMengajar + " tahun");
    }

    // Covariant return type (return Dosen, bukan Pegawai)
    @Override
    public Dosen clone() {
        return new Dosen(nip, nama, jurusan, mataKuliah, pengalamanMengajar);
    }
}

/* ---------- CLASS DOSEN PROFESSOR ---------- */
class DosenProfessor extends Dosen {
    public DosenProfessor(String nip, String nama, String jurusan, String mataKuliah, int pengalamanMengajar) {
        super(nip, nama, jurusan, mataKuliah, pengalamanMengajar);
        System.out.println("DosenProfessor constructor called");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Gelar Akademik: Profesor");
    }
}
