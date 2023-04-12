// package lib;


// public class TaxFunction {

	
// 	/**
// 	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
// 	 * 
// 	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
// 	 * 
// 	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
// 	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
// 	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
// 	 * 
// 	 */
	
	
// 	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
// 		int tax = 0;
		
// 		if (numberOfMonthWorking > 12) {
// 			System.err.println("More than 12 month working per year");
// 		}
		
// 		if (numberOfChildren > 3) {
// 			numberOfChildren = 3;
// 		}
		
// 		if (isMarried) {
// 			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
// 		}else {
// 			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
// 		}
		
// 		if (tax < 0) {
// 			return 0;
// 		}else {
// 			return tax;
// 		}
			 
// 	}
	
// }

package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int INCOME_TAX_FREE = 54000000;
    private static final int INCOME_TAX_FREE_MARRIED = INCOME_TAX_FREE + 4500000;
    private static final int INCOME_TAX_FREE_CHILD = 1500000;
    private static final int MAX_CHILDREN = 3;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     *
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
            return 0;
        }

        if (numberOfChildren > MAX_CHILDREN) {
            numberOfChildren = MAX_CHILDREN;
        }

        final int otherMonthly = otherMonthlyIncome * numberOfMonthWorking;
        final int taxableIncome;
        if (isMarried) {
            taxableIncome = (monthlySalary * numberOfMonthWorking + otherMonthly) - deductible - INCOME_TAX_FREE_MARRIED - (numberOfChildren * INCOME_TAX_FREE_CHILD);
        } else {
            taxableIncome = (monthlySalary * numberOfMonthWorking + otherMonthly) - deductible - INCOME_TAX_FREE;
        }

        final int tax = (int) Math.round(taxableIncome * TAX_RATE);

        return Math.max(tax, 0);
    }
}
