package com.wosai.e.invoice.sample.apply.service;

import com.wosai.e.invoice.sample.apply.entity.InvoiceApply;

public interface IQRCodeService {
	
	/**
	 * 生成二维码路径
	 * @param invoiceApply
	 * @return
	 */
	public String generatorQRCodeURL(InvoiceApply invoiceApply);

}
