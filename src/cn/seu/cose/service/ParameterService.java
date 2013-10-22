package cn.seu.cose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ParameterDAO;
import cn.seu.cose.entity.Parameter;

@Service
public class ParameterService {

	@Autowired
	ParameterDAO parameterDAOImpl;

	public List<Parameter> getAllParameters() {
		return parameterDAOImpl.getAllParameters();
	}

	public Parameter getParameterByKey(String key) {
		return parameterDAOImpl.getParameterByKey(key);
	}

	public void updateParameterValue(Parameter parameter, String value) {
		parameter.setParameterValue(value);
		parameterDAOImpl.updateParameter(parameter);
	}

	public void updateParameterValue(Parameter parameter) {
		parameterDAOImpl.updateParameter(parameter);
	}

	public void updateParameterValue(String key, String value) {
		Parameter parameter = parameterDAOImpl.getParameterByKey(key);
		if (parameter != null) {
			parameter.setParameterValue(value);
			parameterDAOImpl.updateParameter(parameter);
		}
	}
}
