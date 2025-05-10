/**
 * Yagé 2007
 */
package com.iportal.cart.biz.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iportal.biz.BaseHelper;
import com.iportal.biz.MapSiteBean;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on May 15, 2007
 *
 */
public class MapHelper extends BaseHelper {

    private static Log logger = LogFactory.getLog(MapHelper.class);
    
    public static List<MapSiteBean> organizeMapBeanList (List<MapSiteBean> results ) {
        List<MapSiteBean> finalResults =  new ArrayList<MapSiteBean> (results.size());
        MapSiteBean oldParent = null;
       MapSiteBean parent = null;
       List<MapSiteBean> children = null; 
       for (MapSiteBean map : results) {
           if (map.getParentCode() != null) {
               parent = findMapSiteBean(finalResults, map.getParentCode());
               if (parent != null) {
                   if ( oldParent != null && !parent.getCode().equals(oldParent.getCode())) {
                       if (oldParent != null) {
                           oldParent.setChildren(children);
                           //finalResults.add(oldParent);
                       }
                       children = new ArrayList<MapSiteBean> ();
                   }
                   if (children == null) {
                       children = new ArrayList<MapSiteBean> ();                               
                   }
                   children.add(map);
                   oldParent= parent;
               } else {
                   logger.error("parent not Found in finalResultList code: "+map.getParentCode());
               }
           } else {
               finalResults.add(map);
           }
       }
       if (oldParent != null) {
           oldParent.setChildren(children);
       }
       
       return finalResults;
    }
    
    private static MapSiteBean findMapSiteBean (List<MapSiteBean> source, Long code) {
        for (MapSiteBean map : source) {
            if (map.getCode().equals(code)) {
                return map;
            } else {
                if (map.getChildren() != null) {
                    MapSiteBean result = findMapSiteBean(map.getChildren(), code);
                    if (result != null) {
                        return result;
                    }
                }
            }     
        }
        return null;
    }    
    
}

