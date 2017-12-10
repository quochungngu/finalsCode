package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

import org.apache.poi.ss.formula.functions.*;
public class TestFinance {

	
	@Test 
	public void TestAmounts()
	{
		int iYearsWork = 40;
		double dReturnWorkPercent = 7;
		int iYearsRetire = 20;
		double dReturnRetirePercent = 2;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		
		
		Retirement inst = new Retirement(iYearsWork, dReturnWorkPercent, iYearsRetire
				, dReturnRetirePercent, dRequiredIncome, dMonthlySSI);
		
		double totalSaved = inst.TotalAmountSaved();
		double amountToSave = inst.AmountToSave();
		
		
		assertEquals(totalSaved, 1454485.55,0.01);
		assertEquals(amountToSave, 554.13,0.01);
		
	}
	
	
	@Test
	public void TestPV()
	{
		double r = 0.02 / 12;
		double n = 20 * 12;
		double y = 10000-2642;
		double f = 0;
		boolean t = false;
		double pv = Math.abs(FinanceLib.pv(r, n, y, f, t));
		
		System.out.println(pv);
		
	}
	
	
	@Test
	public void TestPMT() {
		double r = 0.07 / 12;
		double n = 40*12;
		double p = 0;
		double f = 1454485.55;
		boolean t = false;
		
		double d = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		System.out.println(d);
		
		
		
	}

}
