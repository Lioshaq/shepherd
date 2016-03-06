package md.mi.model.json.response;

import md.mi.model.base.ModelBase;

public class AccountInfo extends ModelBase{


    /**
     * 
     */
    private static final long serialVersionUID = 4871645275164407329L;
    private String id;
    private Integer balance;
    private Integer currencyCode;

    public AccountInfo() {
        super();
    }


    public AccountInfo(String id, Integer balance, Integer currencyCode) {
        super();
        this.id = id;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }


    public String getId() {
        return id;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

}
