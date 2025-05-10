/**
 * Yagé 2007
 */
package com.yage.commons;

import java.io.Serializable;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on Apr 24, 2007
 *
 */
public class ImageString implements Serializable {
    private static final long serialVersionUID = -5954965717801023398L;
    private String action;
    private Integer x;
    private Integer y;
    
    
    /**
     * 
     */
    public ImageString() {
        super();
        this.action = null;
        this.x = null;
        this.y = null;
    }

    public ImageString(String action) {
        super();
        this.action = action;
        this.x = null;
        this.y = null;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return action;
    }


    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }


    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }


    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }


    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }


    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }
    
    
}

