/*
 * Created Jul 31, 2006
 *	MapSiteBean.java
 */
package com.iportal.biz;

import java.util.List;

import com.yage.Globals;

/**
 * @author hernan
 *
 */
public class MapSiteBean {
	
	private Long code;
	
	private Long parentCode;
	
	private String name;
	
	private String intro;
	
	private Integer level;
	
	private String link;
	
	private String desc;
	
	private List<MapSiteBean> children; 
	
	
	
	
	/**
	 * @param code
	 * @param name
	 * @param level
	 */
	public MapSiteBean(Long code, String name, Integer level) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.level = level;
	}
	
	/**
	 * @param code
	 * @param name
	 * @param level
	 */
	public MapSiteBean(Long code, String name, String intro, Integer level, Long parentCode) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.intro = intro;
		this.level = level;
		this.parentCode = parentCode;
	}

	public MapSiteBean(Long code, String name, Integer level, Long parentCode) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.level = level;
		this.parentCode = parentCode;
	}
	
	

	public MapSiteBean(Long code, Long parentCode, String name, Integer level, String link) {
		super();
		this.clear();
		this.code = code;
		this.parentCode = parentCode;
		this.name = name;
		this.level = level;
		this.link = link;
	}
	
	public MapSiteBean(Long code, Long parentCode, String name, Integer level, String link, String desc) {
		super();
		this.clear();
		this.code = code;
		this.parentCode = parentCode;
		this.name = name;
		this.level = level;
		this.link = link;
		this.desc = desc;
	}

	/**
	 * 
	 */
	public MapSiteBean() {
		super();
		this.clear();
	}
	
	private void clear () {
		this.code  = null;
		this.name  = null;
		this.level = null;
		this.children = null;
		this.link = null;
		if(this.children != null) {
			this.children.clear();
		}
		this.children = null;
	}

	/**
	 * @return Returns the children.
	 */
	public List<MapSiteBean> getChildren() {
		return children;
	}

	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the intro.
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @return Returns the level.
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the parentCode.
	 */
	public Long getParentCode() {
		return parentCode;
	}

	/**
	 * @param children The children to set.
	 */
	public void setChildren(List<MapSiteBean> children) {
		this.children = children;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param intro The intro to set.
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param parentCode The parentCode to set.
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}
	
	public int getChildrenSize () {
		if (this.children != null) {
			return new Integer (this.children.size());
		} else {
			return Globals.ZERO;
		
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((code == null) ? 0 : code.hashCode());
        result = PRIME * result + ((level == null) ? 0 : level.hashCode());
        result = PRIME * result + ((parentCode == null) ? 0 : parentCode.hashCode());
        return result;
    }

    /**
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
        final MapSiteBean other = (MapSiteBean) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (level == null) {
            if (other.level != null)
                return false;
        } else if (!level.equals(other.level))
            return false;
        if (parentCode == null) {
            if (other.parentCode != null)
                return false;
        } else if (!parentCode.equals(other.parentCode))
            return false;
        return true;
    }
	

}
