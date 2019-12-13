package com.sbhyun.functionalinterfaces;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.IntSupplier;

import org.junit.jupiter.api.Test;

class myIntSupplierImpTest {

	@Test
	void testGetAsInt() {

		assert(new myIntSupplierImp() instanceof IntSupplier);
		
		IntSupplier intS = new myIntSupplierImp();
		int result = intS.getAsInt();
		assertNotNull(result);
		
		assertTrue("Previous (" + result + ") should be smaller than current (6)", result < 6);

	}

}
