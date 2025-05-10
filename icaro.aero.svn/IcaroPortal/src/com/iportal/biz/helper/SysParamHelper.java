package com.iportal.biz.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.iportal.biz.BaseHelper;
import com.iportal.model.system.SysParam;

/**
 * Helper para convertir un listado de SysParam objectos a objectos del 
 * tipo y valor establecido
 * 
 * @author  burkhard
 * @version 1.0
 */
public class SysParamHelper extends BaseHelper {

	/**
	 * Convierte un listado de {@link com.iportal.model.system.SysParam} 
	 * object into a Set&lt;Object&gt;
	 * 
	 * @param _params - Objecto de List&lt;SysParams&gt;
	 * @return - Objectos generados
	 * @throws Exception - thrown by {@link #getObject(String, String)}
	 */
	public static Set<Object> getObjectSet(List<SysParam> _params) throws Exception {
		TreeSet<Object> objs = new TreeSet<Object>();
		for (SysParam param :_params) {
			objs.add(SysParamHelper.getObject(param.getValue(), param.getType()));
		}
		return objs;
	}
	
	/**
	 * Convierte un listado de {@link com.iportal.model.system.SysParam} 
	 * object into a Map&lt;Object&gt;. El key del mapa se toma del 
	 * {@link com.iportal.model.system.SysParam#getName()}
	 * 
	 * @param _params - Objecto de List&lt;SysParams&gt;
	 * @return - Objectos generados
	 * @throws Exception - thrown by {@link #getObject(String, String)}
	 */
	public static Map<String,Object> getObjectMap(List<SysParam> _params) throws Exception {
		TreeMap<String,Object> objs = new TreeMap<String,Object>();
		for (SysParam param :_params) {
			objs.put(param.getName(), SysParamHelper.getObject(param.getValue(), param.getType()));
		}
		return objs;
	}
	
	/**
	 * Agrega un listado de {@link com.iportal.model.system.SysParam} 
	 * a un Properties object. El key de la propriedad se toma del 
	 * {@link com.iportal.model.system.SysParam#getName()}
	 * 
	 * @param _params - Objecto de List&lt;SysParams&gt;
	 * @param _props - Objecto de Properties para agregar los Objectos
	 * @return - Objecto de Properties alterado
	 * @throws Exception - thrown by {@link #getObject(String, String)}
	 */
	public static Properties addToProperties(List<SysParam> _params, Properties _props) throws Exception {
		for (SysParam param :_params) {
			if(null == _props) {
				_props = new Properties();
			}
			_props.put(param.getName(), SysParamHelper.getObject(param.getValue(), param.getType()));
		}
		return _props;
	}
	
	/**
	 * Convierte una combinaci&oacute;n de dos String de un tipo y un valor en un 
	 * objecto del dicho tipo con dicho valor
	 * 
	 * @param _value - Valor del Object creado (p.e. &quot;12345&quot;)
     * @param _type - Tipo para convertir hacia (p.e. &quot;java.lang.Long&quot;)
	 * @return - el Object generado (p.e. Long(12345))
	 * @throws ClassNotFoundException    - si el tipo dado no existe 
	 * @throws NoSuchMethodException     -  si no existe constructor para String (p.e. no existe com.test.SuClass(Sting)) 
	 * @throws InvocationTargetException -  error en la instanciaci&oacute;n del Object  
	 * @throws IllegalAccessException    -  error en la instanciaci&oacute;n del Object
	 * @throws InstantiationException    -  error en la instanciaci&oacute;n del Object
	 */
	public static Object getObject(String _value, String _type) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object obj = null;
		if(null != _type && null != _value) {
			Class clazz =  Class.forName(_type);
			if(String.class != clazz) {
				Constructor construct = clazz.getConstructor(java.lang.String.class);
				obj = construct.newInstance(_value);						
			} else {
				obj = _value;
			}
		}
		return obj;
	}
}
