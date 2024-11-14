import java.lang.ref.Cleaner;

class Mahasiswa {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;

    // SOAL 1: Atribut privat
    private Long nim;
    private String nama;
    private String jurusan;
    private double ipk;

    // Setter dan Getter untuk NIM
    public void setNim(Long nim) {
        this.nim = nim;
    }

    public Long getNim() {
        return nim;
    }

    // Setter dan Getter untuk Nama Mahasiswa
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    // Setter dan Getter untuk Jurusan
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getJurusan() {
        return jurusan;
    }

    // SOAL 3: Konstruktor tanpa parameter
    public Mahasiswa() {
        this.nim = 9999999999L;
        this.nama = "Tidak Ditemukan";
        this.jurusan = "Tidak diketahui";
        this.cleanable = cleaner.register(this, new Finalizer(nama));
    }

    // SOAL 3: Konstruktor dengan parameter
    public Mahasiswa(Long nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.cleanable = cleaner.register(this, new Finalizer(nama));
    }

    // SOAL 5: Setter dan Getter untuk IPK
    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    public double getIpk() {
        return ipk;
    }

    // SOAL 8: Method finalizer
    private static class Finalizer implements Runnable {
        private final String nama;

        Finalizer(String nama) {
            this.nama = nama;
        }

        @Override
        public void run() {
            System.out.println("Object Mahasiswa " + nama + " is destroyed.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // SOAL 2: Membuat object Mhs1 dan menampilkan datanya
        Mahasiswa Mhs1 = new Mahasiswa(1915051001L, "Adi Guna", "Pendidikan Teknik Informatika");
        System.out.println("==============================================");
        System.out.println("|                Output Soal 2               |");
        System.out.println("----------------------------------------------");
        System.out.println("| NIM : " + Mhs1.getNim() + "                           |");
        System.out.println("| Nama Mahasiswa : " + Mhs1.getNama() + "                  |");
        System.out.println("| Jurusan: " + Mhs1.getJurusan() + "     |");
        System.out.println("==============================================");

        System.out.println("\n");

        // SOAL 4: Membuat object Mhs2 dan Mhs3 dengan konstruktor tanpa parameter dan dengan parameter
        Mahasiswa Mhs2 = new Mahasiswa();
        System.out.println("==============================================");
        System.out.println("|       Output Soal 3 - Tanpa Parameter      |");
        System.out.println("----------------------------------------------");
        System.out.println("| NIM : " + Mhs2.getNim() + "                           |");
        System.out.println("| Nama Mahasiswa: " + Mhs2.getNama() + "            |");
        System.out.println("| Jurusan: " + Mhs2.getJurusan() + "                   |");
        System.out.println("==============================================");

        System.out.println("\n");

        Mahasiswa Mhs3 = new Mahasiswa(1915051002L, "Gede Anto", "Pendidikan Teknik Informatika");
        System.out.println("==============================================");
        System.out.println("|      Output Soal 3 - Dengan Parameter      |");
        System.out.println("----------------------------------------------");
        System.out.println("| NIM : " + Mhs3.getNim() + "                           |");
        System.out.println("| Nama Mahasiswa : " + Mhs3.getNama() + "                 |");
        System.out.println("| Jurusan: " + Mhs3.getJurusan() + "     |");
        System.out.println("==============================================\n");

        // SOAL 7: Mengatur IPK untuk Mhs1 dan Mhs3, lalu membandingkan
        Mhs1.setIpk(3.51);
        Mhs3.setIpk(3.42);
        compareIPK(Mhs1, Mhs3);
    }

    // SOAL 6: Method untuk membandingkan IPK dua Mahasiswa
    public static void compareIPK(Mahasiswa mhs1, Mahasiswa mhs2) {
        if (mhs1.getIpk() > mhs2.getIpk()) {
            System.out.println("IPK " + mhs1.getNama() + " lebih besar dari IPK " + mhs2.getNama() + ".");
        } else if (mhs1.getIpk() < mhs2.getIpk()) {
            System.out.println("IPK " + mhs2.getNama() + " lebih besar dari IPK " + mhs1.getNama() + ".");
        } else {
            System.out.println("IPK " + mhs1.getNama() + " sama dengan IPK " + mhs2.getNama() + ".");
        }
        System.out.println("\n==============================================");
    }
}
