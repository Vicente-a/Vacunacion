package com.vacunas.reportes;

import java.util.HashMap;
import java.util.Map;

import com.vacunas.utilitario.Log;

public class ReportsBean extends AbstractReportBean {
	
	private final String COMPILE_FILE_NAME = "comprobanteRetencion";

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("codigoCR", "Hello JasperReports");
        return reportParameters;

	}
	

}
