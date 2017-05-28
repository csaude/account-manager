/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.unit.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
public interface UnitQueryWS
{
	public static final String NAME = "mz.co.geekframeworks.core.unit.service.UnitQueryWS";
	
	@GET
	@Path("/unit/{unitCode}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getUnitByCode(@PathParam("unitCode") final String unitCode)
			throws BusinessException;
}
