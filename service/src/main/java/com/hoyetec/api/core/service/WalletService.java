package com.hoyetec.api.core.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hoyetec.api.dao.AppUserRepository;
import com.hoyetec.api.dao.CurrencyMainRepository;
import com.hoyetec.api.dao.UserWalletRepository;
import com.hoyetec.api.po.AppUser;
import com.hoyetec.api.po.CurrencyMain;
import com.hoyetec.api.po.UserWallet;
import com.hoyetec.api.util.RestTemplateUtil;
import com.hoyetec.api.util.SHA1;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: WalletService.java
*              Title:
*        Description:
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月23日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月23日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@Service
public class WalletService {
	private final Logger logger = LoggerFactory.getLogger(WalletService.class);
	
	@Value("${wallet.eth.api.host}")
	private String walletEthApiHost;
	
	@Value("${wallet.btc.api.host}")
	private String walletBtcApiHost;
	
	@Value("${wallet.usdt.api.host}")
	private String walletUsdtApiHost;
	
	@Value("${myip.api.host}")
	private String myIpApiHost;
	
	@Autowired private UserWalletRepository userWalletRepository;
	
	@Autowired private AppUserRepository appUserRepository;
	
	@Autowired private CurrencyMainRepository currencyMainRepository;
	
	/**
	 * 依據user.uuid建立錢包
	 * @param userUuid
	 */
    public void createUserWallet(String userUuid) {
    	List<CurrencyMain> list = currencyMainRepository.findCurrencyList();
    	for(CurrencyMain main : list) {
    		boolean result = this.createUserWalletByUserUidAndCurrencyId(userUuid, new Long(main.getCurrencyId()).intValue());
    		logger.info("createUserWallet="+userUuid+",result="+result);
    	}
    }
	
	/**
	 * 依據app_user.uuid及currency_main.currency_id新增錢包
	 * @param userUuid
	 * @param currencyId
	 * @return 成功/失敗
	 */
    @Transactional
	public boolean createUserWalletByUserUidAndCurrencyId(String userUuid,Integer currencyId) {	

		//1.檢查user是否存在
		AppUser appUser = this.appUserRepository.findByUuid(userUuid);
		if(null == appUser) {//檢查用戶帳號存在
			logger.info(userUuid+"用戶不存在");
			//不存在直接退出
			return false;
		}
		logger.info(userUuid+"用戶存在");
		
		//2.檢查錢包是否已經建立
		//UserWallet userWallet = this.userWalletRepository.findByUser_uidAndCurrency_id(String.valueOf(appUser.getUid()), currencyId); jean mark
		UserWallet userWallet = this.userWalletRepository.findByUser(String.valueOf(appUser.getUid()), currencyId);
		
		if(null != userWallet) {
			//已經建立 直接退出
			logger.info("app_user.uid="+userWallet.getUserUid()+",currency_main.currency_id"+userWallet.getCurrencyId()+"錢包已經建立");
			return false;
		}
		//3.確認錢包未建立 開始建立錢包
		//4.取得幣種
		Optional<CurrencyMain> optCurrencyMain = this.currencyMainRepository.findAll().stream()
			.filter(it->currencyId==it.getCurrencyId())
			.filter(it->1==it.getStatus())//上線
			.findFirst();
		
		if(false == optCurrencyMain.isPresent()) {
			logger.info("未上線");
			return false;
		}
		
		String currencyName = optCurrencyMain.get().getCurrencyName();

		//5.產生密碼
		String passwd = appUser.getUuid()+"_"+currencyId+"_"+UUID.randomUUID();
		logger.info("passwd = "+passwd);

		//6.取得外部ip
		String ip = this.getServerIp("text");
		if(null == ip) {
			logger.info("無法取得外部IP");
			return false;
		}
		logger.info("ip = "+ip);
		if("ETH".equals(currencyName)) {
			logger.info("開始建立ETH錢包");
			logger.info("SHA1("+"passwd="+passwd+"&"+ip+")");
			//7.SHA1完整性驗證
			String vcode=SHA1.encrypt("passwd="+passwd+"&"+ip);
			logger.info("vcode = "+vcode);
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("passwd",passwd);
			postParam.add("vcode",vcode);
			String address = this.callWalletAPI(this.walletEthApiHost,"CreateAddress", postParam);
			return this.saveWallet(address, appUser, currencyId, passwd);
		}else if("BTC".equals(currencyName)) {
			logger.info("建立BTC錢包");
			logger.info("SHA1("+"wallet_name="+passwd+"&"+ip+")");
			//7.SHA1完整性驗證
			String vcode=SHA1.encrypt("wallet_name="+passwd+"&"+ip);
			logger.info("vcode = "+vcode);
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("wallet_name",passwd);
			postParam.add("vcode",vcode);
			String address = this.callWalletAPI(this.walletBtcApiHost,"CreateAddress", postParam);
			return this.saveWallet(address, appUser, currencyId, passwd);
		}else if("USDT".equals(currencyName)) {
			logger.info("建立USDT錢包");
			logger.info("SHA1("+"account="+passwd+"&"+ip+")");
			//7.SHA1完整性驗證
			String vcode=SHA1.encrypt("account="+passwd+"&"+ip);
			logger.info("vcode = "+vcode);
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("account",passwd);
			postParam.add("vcode",vcode);
			String address = this.callWalletAPI(this.walletUsdtApiHost,"CreateAddress", postParam);
			return this.saveWallet(address, appUser, currencyId, passwd);
		}else {
			//TODO 其他幣種 
			logger.info("尚未支援");
			return false;
		}
	}

	/**
	 * 取得餘額
	 * @param userUuid
	 * @param currencyId
	 * @return
	 */
	public String getBalanceByByUserUidAndCurrencyId(String userUuid,Integer currencyId) {
		//1.檢查user是否存在
		AppUser appUser = this.appUserRepository.findByUuid(userUuid);
		if(null == appUser) {//檢查用戶帳號存在
			logger.info(userUuid+"用戶不存在");
			//不存在直接退出
			return null;
		}
		logger.info(userUuid+"用戶存在");

		//2.檢查錢包是否已經建立
		//UserWallet userWallet = this.userWalletRepository.findByUser_uidAndCurrency_id(String.valueOf(appUser.getUid()), currencyId); jean mark
		UserWallet userWallet = this.userWalletRepository.findByUser(String.valueOf(appUser.getUid()), currencyId);
		if(null == userWallet) {
			logger.info("錢包未建立");
			return null;
		}
		logger.info("app_user.uid="+userWallet.getUserUid()+",currency_main.currency_id"+userWallet.getCurrencyId()+"錢包已經建立");

		Optional<CurrencyMain> optCurrencyMain = this.currencyMainRepository.findAll().stream()
				.filter(it->currencyId==it.getCurrencyId())
				.filter(it->1==it.getStatus())//上線
				.findFirst();

		String currencyName = optCurrencyMain.get().getCurrencyName();

		//UserWallet wallet = this.userWalletRepository.findByUser_uidAndCurrency_id(String.valueOf(appUser.getUid()), currencyId); jean mark
		UserWallet wallet = this.userWalletRepository.findByUser(String.valueOf(appUser.getUid()), currencyId);
		String address = wallet.getCurrencyAddress();
		String ip = this.getServerIp("text");
		if("ETH".equals(currencyName)) {
			String vcode = SHA1.encrypt("address="+address+"&"+ip);

			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("address",address);
			postParam.add("vcode",vcode);
			String amountJson= this.callWalletAPI(walletEthApiHost, "GetEthBalance", postParam);

			return this.getJsonValue(amountJson, "amount");
		}else if("BTC".equals(currencyName)) {
			String vcode = SHA1.encrypt("wallet_name="+address+"&"+ip);

			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("wallet_name",address);
			postParam.add("vcode",vcode);
			String amountJson= this.callWalletAPI(walletBtcApiHost, "GetBtcBalance", postParam);

			return this.getJsonValue(amountJson, "amount");
		}else if("USDT".equals(currencyName)) {
			String vcode = SHA1.encrypt("address="+address+"&"+ip);

			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("address",address);
			postParam.add("vcode",vcode);
			String amountJson= this.callWalletAPI(walletUsdtApiHost, "GetUsdtBalance", postParam);

			return this.getJsonValue(amountJson, "amount");
		}else {
			//TODO 其他幣種 
			logger.info("尚未支援");
			return "";
		}
	}
	
	/**
	 * 數位貨幣轉址
	 * @param currencyName 貨幣名稱
	 * @param from_addr BTC ETH USDT 必填
	 * @param passwd ETH 必填
	 * @param to_addr BTC ETH USDT 必填
	 * @param amount BTC ETH USDT 必填
	 * @param currentServerIp BTC ETH USDT 必填
	 * @param conmmant BTC USDT 選填
	 * @return
	 */
	public String transferCoin(String currencyName,String from_addr,String passwd,String to_addr,double amount,String currentServerIp,String comment) {
		if("ETH".equals(currencyName)) {
			String vcode =SHA1.encrypt(
					  "from_addr="+from_addr+
					  "&passwd="+passwd+
					  "&to_addr="+to_addr+
					  "&amount="+amount+
					  "&"+currentServerIp);
			
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("from_addr",from_addr);
			postParam.add("passwd",passwd);
			postParam.add("to_addr",to_addr);
			postParam.add("amount",String.valueOf(amount));
			postParam.add("vcode",vcode);
			String txidJson= this.callWalletAPI(walletEthApiHost, "EthTransfer", postParam);
			
			return this.getJsonValue(txidJson, "txid");
		}else if("BTC".equals(currencyName)) {
			String vcode =SHA1.encrypt(
					  "from_wallet="+from_addr+
					  "&to_addr="+to_addr+
					  "&amount="+amount+
					  "&comment="+comment+
					  "&"+currentServerIp);
			
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("from_wallet",from_addr);
			postParam.add("to_addr",to_addr);
			postParam.add("amount",String.valueOf(amount));
			postParam.add("comment",comment);
			postParam.add("vcode",vcode);
			String txidJson= this.callWalletAPI(walletBtcApiHost, "BtcTransfer", postParam);
			
			return this.getJsonValue(txidJson, "txid");
		}else if("USDT".equals(currencyName)) {
			String vcode =SHA1.encrypt(
					  "from_addr="+from_addr+
					  "&to_addr="+to_addr+
					  "&amount="+amount+
					  "&comment="+comment+
					  "&"+currentServerIp);
			
			LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
			postParam.add("from_addr",from_addr);
			postParam.add("to_addr",to_addr);
			postParam.add("amount",String.valueOf(amount));
			postParam.add("comment",comment);
			postParam.add("vcode",vcode);
			String txidJson= this.callWalletAPI(walletUsdtApiHost, "UsdtTransfer", postParam);

			return this.getJsonValue(txidJson, "txid");
		}else {
			//TODO 其他幣種 
			logger.info("尚未支援");
			return "";
		}
	}
	
	public String getJsonValue(String json,String key) {
		if(null == json) {
			logger.info("無法取得");
			return "";
		}
		logger.info("json = "+json);
		JsonObject jsonObj = new Gson().fromJson(json, JsonObject.class);
		String value = jsonObj.get(key).getAsString();
		logger.info(key+" = "+value);
		return value;
	}

	/**
	 * DB新增一個錢包
	 * @param address 錢包地址
	 * @param appUser user物件
	 * @param currencyId 貨幣種類
	 * @param passwd 密碼
	 * @return
	 */
	private boolean saveWallet(String address,AppUser appUser,Integer currencyId,String passwd) {
		try {
			
			String currencyAddress = this.getJsonValue(address, "Address");
			
			logger.info("currencyAddress = "+currencyAddress);
			
			if(null==currencyAddress || "".equals(currencyAddress)) {
				logger.info("無法取得地址");
				return false;
			}
			currencyAddress = currencyAddress.replaceAll("\r\n|\r|\n", "");//去除換行字元

			//寫入一個錢包
			UserWallet wallet=new UserWallet();
			wallet.setUserUid(appUser.getUid());
			wallet.setCurrencyId(currencyId);
			wallet.setAddressPasswd(passwd);
			wallet.setCurrencyAddress(currencyAddress);
			this.userWalletRepository.saveAndFlush(wallet);
			logger.info("建立錢包成功");
			return true;
		}catch(Exception e) {
			logger.error("",e);
			logger.error("產生錢包失敗:JSON格式解析錯誤");
			return false;
		}
	}
	
	/**
	 * 呼叫錢包api
	 * @param apiHost api網域
	 * @param apiName api名稱
	 * @param postParam post參數
	 * @return
	 */
	private String callWalletAPI(String apiHost,String apiName,MultiValueMap<String, String> postParam) {
		try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> entity =new HttpEntity<MultiValueMap<String, String>>(postParam, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName,HttpMethod.POST,entity,String.class);
			String address=response.getBody();
			return address;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 取得外部IP
	 * @param format
	 * @return
	 */
	public String getServerIp(String format) {
		//https://api.ipify.org/?format=text
		RestTemplate restTemplate;
		try {
			restTemplate = RestTemplateUtil.createRestTemplate(String.format(this.myIpApiHost, format));
			Map<String, String> map = new HashMap<>();
		    map.put("format", format);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(this.myIpApiHost, String.class, map);
			return responseEntity.getBody();
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		return null;
	}
}
