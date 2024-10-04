package yotazahra;
import java.util.Scanner;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
public class Gaji {
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

        // Inisialisasi variabel
        int numEmployees = 8;
        String[] names = new String[numEmployees];
        int[] jobCodes = new int[numEmployees];
        int[] maritalStatuses = new int[numEmployees];
        int[] numChildren = new int[numEmployees];
        double[] basicSalaries = {10500000, 6000000, 4000000, 3500000};
        double[] allowances = new double[numEmployees];
        double[] familyAllowances = new double[numEmployees];
        double[] childAllowances = new double[numEmployees];
        double[] pph = new double[numEmployees];
        double[] subtotals = new double[numEmployees];
        double totalGrossSalary = 0;
        double totalFamilyAllowance = 0;
        double totalChildAllowance = 0;
        double totalPph = 0;
        double totalNetSalary = 0;

        // Input data karyawan
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Data Karyawan " + (i + 1));
            System.out.print("Nomor Karyawan: " + (i + 1) + "\nNama Karyawan: ");
            names[i] = input.nextLine();
            System.out.print("Kode Jabatan (1-4): ");
            jobCodes[i] = input.nextInt();
            System.out.print("Status (1=Belum Kawin, 2=Kawin): ");
            maritalStatuses[i] = input.nextInt();
            
            // Tunjangan keluarga
            if (maritalStatuses[i] == 2) {
                familyAllowances[i] = 0.1 * basicSalaries[jobCodes[i] - 1];
            } else {
                familyAllowances[i] = 0;
            }

            System.out.print("Jumlah Anak (maksimal 3): ");
            numChildren[i] = Math.min(input.nextInt(), 3);
            // Tunjangan anak
            childAllowances[i] = 0.025 * basicSalaries[jobCodes[i] - 1] * numChildren[i];

            // PPH
            pph[i] = 0.025 * basicSalaries[jobCodes[i] - 1];

            // Hitung subtotal
            subtotals[i] = basicSalaries[jobCodes[i] - 1] + familyAllowances[i] +
                           childAllowances[i] - pph[i];

            // Hitung total
            totalGrossSalary += basicSalaries[jobCodes[i] - 1];
            totalFamilyAllowance += familyAllowances[i];
            totalChildAllowance += childAllowances[i];
            totalPph += pph[i];
            totalNetSalary += subtotals[i];

            // Bersihkan buffer input
            input.nextLine();

              // Konfirmasi input data lagi
            if (i < numEmployees - 1) {
                System.out.print("Ada data lagi? (y/t): ");
                char continueInput = input.next().charAt(0);
                if (continueInput != 'y') {
                    break;
                }
                // Bersihkan buffer input
                input.nextLine();
            }
        }

        // Tampilkan laporan
        System.out.println("\n=====================================================================");
        System.out.println("Laporan Gaji Karyawan");
        System.out.println("=====================================================================");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "No", "Nama", "Jabatan", "Gaji Pokok", "T. Keluarga", "T. Anak", "PPH", "Subtotal", "Total", " ");
        System.out.println("=====================================================================");

        // Tampilkan data karyawan
        for (int i = 0; i < numEmployees; i++) {
            System.out.printf("%-5d %-15s %-15s %-15.0f %-15.0f %-15.0f %-15.0f %-15.0f %-15.0f\n",
                    i + 1, names[i], getJobTitle(jobCodes[i]), basicSalaries[jobCodes[i] - 1],
                    familyAllowances[i], childAllowances[i], pph[i], subtotals[i], totalNetSalary);
            if ((i + 1) % 3 == 0 && i < numEmployees - 1) {
                System.out.println("=====================================================================");
                System.out.print("Press Enter to continue...");
                input.nextLine();
                System.out.println("\n=====================================================================");
                System.out.println("Laporan Gaji Karyawan (lanjutan)");
                System.out.println("=====================================================================");
                System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        "No", "Nama", "Jabatan", "Gaji Pokok", "T. Keluarga", "T. Anak", "PPH", "Subtotal", "Total", " ");
                System.out.println("=====================================================================");
            }
        }

        // Tampilkan total
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-53s %-15.0f %-15.0f %-15.0f %-15.0f %-15.0f\n",
                "Total", totalGrossSalary, totalFamilyAllowance, totalChildAllowance, totalPph, totalNetSalary);
        System.out.println("=====================================================================");
    }

    // Fungsi untuk mendapatkan jabatan berdasarkan kode jabatan
    private static String getJobTitle(int code) {
        switch (code) {
            case 1:
                return "Direktur";
            case 2:
                return "Kabag";
            case 3:
                return "Produksi";
            case 4:
                return "Pemasaran";
            default:
                return "";

        }
    }
    }