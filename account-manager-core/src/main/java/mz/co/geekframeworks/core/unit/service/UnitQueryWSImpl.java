/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UnitWS;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

import org.springframework.stereotype.Service;

/**
 * @author Stélio Moiane
 *
 */
@Service(UnitQueryWS.NAME)
@Path("units")
public class UnitQueryWSImpl implements UnitQueryWS
{
	@Inject
	private UnitQueryService unitQueryService;
	
	@Override
	public Response getUnitByCode(final String unitCode)
			throws BusinessException
	{
		Unit unit = this.unitQueryService.findUnitByCode(new UserContext(),
				unitCode);
		
		UnitWS unitfound = new UnitWS(unit.getCode(), unit.getNuit(),
				unit.getName(), unit.getAddress(), unit.getContact(),
				unit.getEmail());
		
		return Response.ok(unitfound).build();
	}
	
}
