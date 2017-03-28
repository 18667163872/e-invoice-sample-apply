package com.wosai.e.invoice.sample.apply.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wosai.e.invoice.sample.apply.entity.InvoiceApply;
import com.wosai.e.invoice.sample.apply.entity.InvoiceApplyItem;
import com.wosai.e.invoice.sample.apply.exception.SignException;
import com.wosai.e.invoice.sample.apply.util.SignUtil;

@Service
public class QRCodeService implements IQRCodeService {
	
	private String domain;
	
	private String secret;
	
	private String appid;
	
	@Value("${wosai.api.domain:https://m.wosai.cn}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Value("${wosai.appid:INDITEX}")
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Value("${wosai.secret:MH1UEKPTSMFEITNG0VQYP7BAKFPOH4RE}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 签名
	 * @param invoiceApply
	 * @return
	 */
	private String getSignByApply(InvoiceApply invoiceApply) {
		try {
			//验证签名
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("channel", invoiceApply.getChannel());
			map.put("payer", invoiceApply.getPayer());
			map.put("appid", appid);
			map.put("secret", secret);
			map.put("store_sn", invoiceApply.getStore_sn());
			map.put("biz_no", invoiceApply.getBiz_no());
			map.put("biz_time", invoiceApply.getBiz_time());
			map.put("amount", invoiceApply.getAmount());
			if(invoiceApply.getItems()!=null&&invoiceApply.getItems().size()>0){
				if(invoiceApply.getItems().size()==1){
					InvoiceApplyItem item = invoiceApply.getItems().get(0);
					map.put("id", item.getId());
					map.put("name", item.getName());
					map.put("num", item.getNum());
					map.put("item_amount", item.getItem_amount());
				}else{
					for(int i=0;i<invoiceApply.getItems().size();i++){
						InvoiceApplyItem item = invoiceApply.getItems().get(i);
						map.put(new StringBuilder("id[").append(i).append("]").toString(), item.getId());
						map.put(new StringBuilder("name[").append(i).append("]").toString(), item.getName());
						map.put(new StringBuilder("num[").append(i).append("]").toString(), item.getNum());
						map.put(new StringBuilder("item_amount[").append(i).append("]").toString(), item.getItem_amount());
					}
				}
			}
			return SignUtil.SignMD5(map);
		} catch (SignException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String generatorQRCodeURL(InvoiceApply invoiceApply) {
		String sign = this.getSignByApply(invoiceApply);
		return String.format("%1$s/api/invoice/apply/v1?appid=%2$s&channel=%3$s&store_sn=%4$s&biz_no=%5$s&biz_time=%6$s&amount=%7$s&sign=%8$s",
				domain,
				appid,
				invoiceApply.getChannel(),
				invoiceApply.getStore_sn(),
				invoiceApply.getBiz_no(),
				invoiceApply.getBiz_time(),
				invoiceApply.getAmount(),
				sign
				);
	}
}
