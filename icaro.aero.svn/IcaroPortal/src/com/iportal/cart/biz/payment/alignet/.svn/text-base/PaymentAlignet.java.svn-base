package com.iportal.cart.biz.payment.alignet;

import com.iportal.cart.model.cart.Payment;

public class PaymentAlignet extends Payment {

    private static final long serialVersionUID = -7298428604178016848L;

    private String creditCardIssuer;

    private Long shopBranchCode;

    private Integer shopCode;

    private String terminalCode;

    private String currency;

    public enum Language {ES, US};
    private Language lang;

    private String source;

    private String eci;

    private String vci;

    private String creditCard;

    private String creditCardValidDate;

    /**
     *
     */
    public PaymentAlignet() {
        super();
        this.creditCardIssuer = null;
        this.shopBranchCode = Long.valueOf(0);
        this.shopCode = null;
        this.terminalCode = null;
        this.currency = null;
        this.lang = Language.ES;
        this.source = null;
        this.eci = null;
        this.vci = null;
        this.creditCard = null;
        this.creditCardValidDate = null;
    }

    public PaymentAlignet(Payment payment) {
        super();
        setStatus(payment.getStatus());
        setType(payment.getType());
        setLastStatus(payment.getLastStatus());
        setRequestDate(payment.getRequestDate());
        setSuccessDate(payment.getSuccessDate());
        setRejectDate(payment.getRejectDate());
        setExtraInfo(payment.getExtraInfo());
        setRequestSessionKey(payment.getRequestSessionKey());
        setResponseSessionKey(payment.getResponseSessionKey());
        setResponseDate(payment.getResponseDate());
        setAuthorizationCode(payment.getAuthorizationCode());
        setErrorCode(payment.getErrorCode());
        setResultMessage(payment.getResultMessage());
        setResult(payment.getResult());
        setOrder(payment.getOrder());
        setValue(payment.getValue());
//        setTax(payment.getTax());
//        setTotal(payment.getTotal());
        this.creditCardIssuer = null;
        this.shopBranchCode = Long.valueOf(0);
        this.shopCode = null;
        this.terminalCode = null;
        this.currency = null;
        this.lang = Language.ES;
        this.source = null;
        this.eci = null;
        this.vci = null;
        this.creditCard = null;
        this.creditCardValidDate = null;
    }

    /**
     * @return the creditCard
     */
    public String getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * @return the creditCardIssuer
     */
    public String getCreditCardIssuer() {
        return creditCardIssuer;
    }

    /**
     * @param creditCardIssuer the creditCardIssuer to set
     */
    public void setCreditCardIssuer(String creditCardIssuer) {
        this.creditCardIssuer = creditCardIssuer;
    }

    /**
     * @return the creditCardValidDate
     */
    public String getCreditCardValidDate() {
        return creditCardValidDate;
    }

    /**
     * @param creditCardValidDate the creditCardValidDate to set
     */
    public void setCreditCardValidDate(String creditCardValidDate) {
        this.creditCardValidDate = creditCardValidDate;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the eci
     */
    public String getEci() {
        return eci;
    }

    /**
     * @param eci the eci to set
     */
    public void setEci(String eci) {
        this.eci = eci;
    }

    /**
     * @return the lang
     */
    public Language getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(Language lang) {
        this.lang = lang;
    }

    /**
     * @return the shopBranchCode
     */
    public Long getShopBranchCode() {
        return shopBranchCode;
    }

    /**
     * @param shopBranchCode the shopBranchCode to set
     */
    public void setShopBranchCode(Long shopBranchCode) {
        this.shopBranchCode = shopBranchCode;
    }

    /**
     * @return the shopCode
     */
    public Integer getShopCode() {
        return shopCode;
    }

    /**
     * @param shopCode the shopCode to set
     */
    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the terminalCode
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * @param terminalCode the terminalCode to set
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    /**
     * @return the vci
     */
    public String getVci() {
        return vci;
    }

    /**
     * @param vci the vci to set
     */
    public void setVci(String vci) {
        this.vci = vci;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((creditCard == null) ? 0 : creditCard.hashCode());
        result = PRIME * result + ((creditCardIssuer == null) ? 0 : creditCardIssuer.hashCode());
        result = PRIME * result + ((creditCardValidDate == null) ? 0 : creditCardValidDate.hashCode());
        result = PRIME * result + ((currency == null) ? 0 : currency.hashCode());
        result = PRIME * result + ((eci == null) ? 0 : eci.hashCode());
        result = PRIME * result + ((lang == null) ? 0 : lang.hashCode());
        result = PRIME * result + ((shopBranchCode == null) ? 0 : shopBranchCode.hashCode());
        result = PRIME * result + ((shopCode == null) ? 0 : shopCode.hashCode());
        result = PRIME * result + ((source == null) ? 0 : source.hashCode());
        result = PRIME * result + ((terminalCode == null) ? 0 : terminalCode.hashCode());
        result = PRIME * result + ((vci == null) ? 0 : vci.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PaymentAlignet other = (PaymentAlignet) obj;
        if (creditCard == null) {
            if (other.creditCard != null)
                return false;
        } else if (!creditCard.equals(other.creditCard))
            return false;
        if (creditCardIssuer == null) {
            if (other.creditCardIssuer != null)
                return false;
        } else if (!creditCardIssuer.equals(other.creditCardIssuer))
            return false;
        if (creditCardValidDate == null) {
            if (other.creditCardValidDate != null)
                return false;
        } else if (!creditCardValidDate.equals(other.creditCardValidDate))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (eci == null) {
            if (other.eci != null)
                return false;
        } else if (!eci.equals(other.eci))
            return false;
        if (lang == null) {
            if (other.lang != null)
                return false;
        } else if (!lang.equals(other.lang))
            return false;
        if (shopBranchCode == null) {
            if (other.shopBranchCode != null)
                return false;
        } else if (!shopBranchCode.equals(other.shopBranchCode))
            return false;
        if (shopCode == null) {
            if (other.shopCode != null)
                return false;
        } else if (!shopCode.equals(other.shopCode))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (terminalCode == null) {
            if (other.terminalCode != null)
                return false;
        } else if (!terminalCode.equals(other.terminalCode))
            return false;
        if (vci == null) {
            if (other.vci != null)
                return false;
        } else if (!vci.equals(other.vci))
            return false;
        return true;
    }


}
