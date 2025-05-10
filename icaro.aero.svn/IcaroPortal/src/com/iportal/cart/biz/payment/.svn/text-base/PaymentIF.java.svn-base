package com.iportal.cart.biz.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.iportal.cart.model.cart.Order;
import com.iportal.cart.model.cart.Payment;
import com.iportal.cart.model.cart.PaymentType;

public interface PaymentIF {

    /**
     * This methosd should be called initializing the Session to register all available
     * payment types.
     *
     * @return - The payment module instance class implementing this PaymentIF
     */
    public PaymentIF registerPaymentType();


    /**
     * Method that should generate based on the registered payment-Type the necessary
     * form to submit to the payment provider. In the best case this may be made by
     * means of a HTML redirect.
     *
     * @param _order
     * @return
     */
    public String initPayment(HttpServletRequest _request, HttpServletResponse _response, Order _order) throws Exception;


    /**
     * This Method will take the reponse from the payment provider and dicifer it.
     *
     * @param _request
     * @return
     */
    public Payment processPaymentResponse(HttpServletRequest _request, HttpServletResponse _response) throws Exception;

    public Payment getPayment(Order order, PaymentType paymentType, String extraInfo);

    public Payment getPayment();

    public Payment cancelPayment(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void setSession(Session sess);

    /**
     * Indica si pago fue procesado exitosamente. Actualizado y almacenado
     * @return
     */
    public Boolean getProcessed();
}

