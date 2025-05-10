package com.iportal.biz.facade;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.iportal.biz.BaseBussinessLogic;
import com.iportal.biz.RowItem;

public class RowItemFacade extends BaseBussinessLogic{
    private static Logger logger = Logger.getLogger(RowItemFacade.class);
    public RowItem getRowItem(Long code, Session openSession, Class clazz) throws Exception {
        RowItem content = null;
        Session sess = null;
        String name = clazz.getSimpleName();
        
        try {
            if (code == null)
                throw new Exception("Null is not a valid value to code in content Object");
            
            if (openSession == null)
                sess = getHibernateSession();
            else
                sess = openSession;
            
            StringBuffer str = new StringBuffer();
           
            str.append("select new com.iportal.biz.RowItem (code, title) ");
           
            str.append("from ");
            str.append(name);
            str.append(" where code = ? ");
            
            List<RowItem> result = (ArrayList<RowItem>)sess.createQuery(str.toString()).setLong(0, code).list();
            
            if (result != null && result.size() > 0)
                content = result.get(0);
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception("Unexpected error while trying to get data for code: " + code);
        } finally {
            if (sess != null && openSession==null)
                try {
                    sess.clear();
                    sess.close();
                } catch (Exception e) {
                }
        }
        return content;
    }

    public void setInRequest(Long code, Session sess, Class clazz, HttpServletRequest request){
        
        try {
            RowItem ri = getRowItem(code, sess, clazz);
            request.setAttribute("currentItem", ri);
        }catch (Exception e){
           logger.error(e.getMessage(),e);
        }
        
    }
}
