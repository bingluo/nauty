package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Parameter;

public interface ParameterDAO {
	List<Parameter> getAllParameters();

	Parameter getParameterByKey(String key);

	void updateParameter(Parameter paramete);
}
