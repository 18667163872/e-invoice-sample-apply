package com.wosai.e.invoice.sample.apply.entity;

import java.util.List;

public class InvoiceApply {
	
	
	private String channel;
	
	private String payer;
	
	private String store_sn;
	
	private String biz_no;
	
	private Long biz_time;
	
	private Long amount;
	
	private String sign;
	
	private List<InvoiceApplyItem> items;
	
	public static InvoiceApply getInstance(){
		return new InvoiceApply();
	}

	public String getChannel() {
		return channel;
	}

	public InvoiceApply setChannel(String channel) {
		this.channel = channel;
		return this;
	}

	public String getPayer() {
		return payer;
	}

	public InvoiceApply setPayer(String payer) {
		this.payer = payer;
		return this;
	}

	public String getStore_sn() {
		return store_sn;
	}

	public InvoiceApply setStore_sn(String store_sn) {
		this.store_sn = store_sn;
		return this;
	}

	public String getBiz_no() {
		return biz_no;
	}

	public InvoiceApply setBiz_no(String biz_no) {
		this.biz_no = biz_no;
		return this;
	}

	public Long getBiz_time() {
		return biz_time;
	}

	public InvoiceApply setBiz_time(Long biz_time) {
		this.biz_time = biz_time;
		return this;
	}

	public Long getAmount() {
		return amount;
	}

	public InvoiceApply setAmount(Long amount) {
		this.amount = amount;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public InvoiceApply setSign(String sign) {
		this.sign = sign;
		return this;
	}

	public List<InvoiceApplyItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceApplyItem> items) {
		this.items = items;
	}

}
