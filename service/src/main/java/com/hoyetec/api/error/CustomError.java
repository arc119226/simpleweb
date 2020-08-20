package com.hoyetec.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CustomError {

    // 基本資料維護
    existed("資料已存在", "000"),
    notExisted("資料不存在", "001"),
    notEnabled("資料已被刪除", "002"),
    isEnabled("無法回復未被刪除的資料", "003"),
    notEnableFlag("資料已被停用", "004"),
    isEnableFlag("無法回復未停用的資料", "005"),
    checked("資料已覆核", "006"),
    notChecked("主檔資料未覆核", "007"),
    branchIdExisted("分店代號重複", "032"),


    // 文件重新導入
    fileNotExisted("檔案不存在", "008"),
    noPathInDb("資料庫中找不到移動目標路徑", "009"),
    pathNotExisted("移動目標路徑不存在", "010"),
    moveFailed("移動失敗", "011"),
    fileExisted("檔名已存在不可上傳", "012"),
    configCodeErr("目錄設定有誤", "013"),
    fileUploadErr("檔案上傳錯誤", "014"),
    inoutConfigDirErr("目錄不存在", "015"),


    // 報表
    errorGeneratingReport("產生報表發生錯誤", "016"),

    // 批次
    ncccFileFormatError("NCCC請款檔格式錯誤(檔案開頭非FH/結尾非FT2)", "017"),
    unexpectedEndOfFile("未預期的檔案結束", "018"),

    // SFTP
    ftpRetrieveFailed("FTP下載檔案發生錯誤", "019"),
    ftpSendFailed("FTP傳送檔案發生錯誤", "020"),
    ftpConfigInvalid("批次工作未設定使用SFTP", "021"),
    ftpLocalPathNotExist("FTP本地路徑不存在", "022"),
    ftpRemotePathNotExist("FTP遠端路徑不存在", "023"),
    targetPathNotExist("路徑不存在", "024"),
    ftpConnectFailed("FTP連線失敗", "025"),
    ftpInfoInsufficient("FTP連線資訊不足", "026"),

    //會員資料搜尋
    memberDataNotSuccess("會員搜尋API狀態 ERROR", "028"),

    smsSendError("簡訊發送失敗", "029"),
    emailSendError("mail發送失敗", "030"),

    // 修改使用者密碼
    userpwdError("原密碼錯誤", "065"),

    // 未知錯誤
    unknownError("未知的錯誤", "100"),

    // 帳號註冊/修改資料錯誤
    accountExistError("帳號已存在", "001", "01"),
    accountValidateError("發送驗證碼失敗", "002", "01"),
    resetAccountPassError("忘記登入密碼-重設失敗", "005", "01"),
    resetPayPinError("忘記付款PIN碼-重設失敗", "006", "01"),
    resetPasswordValidateError("忘記登入密碼-驗證失敗", "007", "01"),

    // 登入/認證錯誤
    loginFailed("登入失敗", "200"),
    tokenMissing("未帶token", "201"),
    invalidSession("session失效", "202"),
    sessionAuthError("session驗證錯誤", "203"),

    // login for keycloak
    kcLoginFailed("登入失敗-帳號或密碼錯誤", "401"),
    kcRefreshTokenFailed("延展Token-失敗", "470"),
    kcLoginUnknownError("帳號管理-登入未知錯誤", "480"),
    //create user for keycloak
    kcAccountexisted("帳號已存在", "409"),
    kcCreateUserUnknownError("帳號管理-新增未知錯誤", "490"),
    // unknown error for keycloak
    kcUnknownError("帳號管理未知錯誤", "499"),
    kcLoginValidateFail("keycloak登入帳密錯誤", "002", "97"),
    kcUpdatePasswordFail("keycloak更新密碼失敗", "004", "97"),
    // http response error
    hostResponseError("主機回應失敗", "500"),


    notExist("查無資料", "001", "03"),
    // ap customize serverCode
    formatError("資料格式錯誤", "001", "04"),
    checkDateRequired("缺少必要資料", "002", "04"),

    // ap Keycloak error
    keycloakChangePasswordError("修改密碼失敗", "004", "97"),
    // ap DB error
    dbReadError("資料讀取錯誤", "001", "98"),
    dbWriteError("資料寫入錯誤", "002", "98"),
    dbChangeDataError("修改資料錯誤", "003", "98"),
    dbDataFormateError("修改格式錯誤", "004", "98"),
    dbDataDuplicateError("資料已存在，無法重複新增", "005", "98"),
    dbDeleteError("修改刪除錯誤", "006", "98"),
    //ePayment交易錯誤
    eUsageTransFail("eUsage交易錯誤", "003", "92"),
    //taishinApPos error
    taishinConnectFail("連線失敗", "001", "93"),
    taishinCreditCardDataFormatError("信用卡資料格式錯誤", "003", "93"),
    taishinDualSignUpCreditCard("重複綁卡", "004", "93"),
    taishinSignUpFail("綁卡失敗", "005", "93"),
    taishinDisableSignUpFail("取消綁卡失敗", "006", "93"),
    taishinGetCardTokenFail("無法取回CardToken", "007", "93"),
    taishinECAuthFail("EC授權交易失敗(Auth)", "008", "93"),
    taishinUnknowError("未知錯誤", "099", "93"),
    aishinGetCardPageError("未知錯誤", "050", "93"),
    //CryptoServer錯誤
    cryptoConnectFail("連線失敗", "001", "94"),
    cryptoCreditCardDataFormatError("信用卡資料格式錯誤", "003", "94"),
    cryptoDualSignUpCreditCard("重複綁卡", "004", "94"),
    cryptoSignUpFail("綁卡失敗", "005", "94"),
    cryptoDisableSignUpFail("取消綁卡失敗", "006", "94"),
    cryptoECAuthFail("eUsage授權交易失敗(Auth)", "008", "94"),
    cryptoUnknowError("未知錯誤", "099", "94"),
    cryptoGetCardInfoError("卡片資料讀取有誤", "400", "94"),
    // aps 主機錯誤
    apsDataFormatError("資料格式錯誤", "400", "95"),
    apsInternalServerError("InternalServerError 內部處理錯誤", "500", "95"),
    apsSessionUnavialableError("指定的 SESSION_ID 不存在", "601", "95"),
    apsCarIsBusyError("已有其他繳費機或 SESSION 正在處理查詢的車輛", "602", "95"),
    apsStoreNotFoundError("拒絕使用折扣: 找不到指定的店家", "603", "95"),
    apsCodeUsedError("拒絕使用折扣: 已使用過", "604", "95"),
    apsDateNotMatchError("拒絕使用折扣: 非當日票券", "605", "95"),
    apsLimitDiscountTimeError("拒絕使用折扣: 折扣時數已達上限", "606", "95"),
    apsNotCorbranderError("拒絕使用折扣: 非聯名卡", "607", "95"),
    apsCredicardDiscountReachedError("拒絕使用折扣: 信用卡使用次數已達上限", "608", "95"),
    apsCloseSessionFail("Close Session失敗", "700", "95"),
    apsConnectionError("主機連線失敗", "001", "95"),
    apsReadCarImageError("讀取車輛圖檔錯誤", "005", "95"),
    //付款PIN碼輸入驗證
    payPinNotError("付款PIN碼驗證失敗", "004", "02"),
    //台新交易錯誤
    tsTxnServiceError("EC授權交易失敗(Auth)", "008", "93");

    private CustomError(String message, String responseCode) {
        this.responseCode = responseCode;
        this.message = message;
    }

    private CustomError(String message, String responseCode, String responseStatus) {
        this.responseStatus = responseStatus;
        this.responseCode = responseCode;
        this.message = message;
    }

    private String responseStatus = "30";
    private String responseCode;
    private String message;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
    
}
