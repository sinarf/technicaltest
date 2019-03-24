package fr.blavin.oui.sncf.technicaltest.cli;

import org.junit.Test;

import fr.blavin.oui.sncf.technicaltest.business.WrongBatchException;

public class MainClassTest {

	@Test
	public void testMain() throws Exception {
		String[] cmdArgs = new String[] { "--batch", "163841689525773" };
		MainClass.main(cmdArgs);
	}

	@Test(expected = WrongBatchException.class)
	public void testMainEmptyBatch() throws Exception {
		String[] cmdArgs = new String[] { "--batch" };
		MainClass.main(cmdArgs);
	}
}
