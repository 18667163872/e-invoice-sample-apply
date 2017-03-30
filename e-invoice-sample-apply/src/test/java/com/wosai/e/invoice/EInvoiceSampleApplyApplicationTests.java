package com.wosai.e.invoice;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wosai.e.invoice.sample.apply.entity.InvoiceApply;
import com.wosai.e.invoice.sample.apply.service.IQRCodeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EInvoiceSampleApplyApplicationTests {
	

	@Autowired
	IQRCodeService qRCodeService;

	public void setqRCodeService(IQRCodeService qRCodeService) {
		this.qRCodeService = qRCodeService;
	}


	@Test
	public void testGeneratorQRCodeURL() {
		
		String url = qRCodeService.generatorQRCodeURL(InvoiceApply.getInstance()
				.setChannel("alipay")
				.setStore_sn("3679")
				.setBiz_no("22000000012")
				.setBiz_time(Calendar.getInstance().getTimeInMillis()/1000)
				.setAmount(10000l)
				);
		System.out.println(url);
		assertTrue(url!=null);
	}


}
