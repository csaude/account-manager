/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.geekframeworks.core.userapplicationrole.model.UserApplicationRole;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public class UserContextFactory
{
	private static Application application;
	
	public static UserContext getUserContext(final User user,
			final Application appplication, final Unit unit)
	{
		UserContext userContext = getUsercoContext(user, appplication);
		userContext
				.setUnit(new mz.co.mozview.frameworks.core.webservices.model.UnitWS(
						unit.getCode(), unit.getNuit(), unit.getName(), unit
								.getAddress(), unit.getContact(), unit
								.getEmail()));
		
		return userContext;
	}
	
	public static UserContext getUsercoContext(final User user,
			final Application application)
	{
		UserContextFactory.application = application;
		
		UserContext userContext = UserContextFactory.getUsercoContext(user);
		
		Role role = getRole(user.getAuthorities());
		userContext
				.setRole(new mz.co.mozview.frameworks.core.webservices.model.Role(
						role.getName(), role.getDescription()));
		
		userContext.setTransactionCodes(getTransactionCodes(user, application));
		
		return userContext;
	}
	
	public static UserContext getUsercoContext(final User user)
	{
		UserContext userContext = new UserContext();
		Application application = user.getAuthorities().iterator().next()
				.getApplicationRole().getApplication();
		
		userContext.setId(user.getId());
		userContext.setFullName(user.getFullName());
		userContext.setUsername(user.getUsername());
		userContext.setEmail(user.getEmail());
		userContext.setAccountNonExpired(user.isAccountNonExpired());
		userContext.setAccountNonLocked(user.isAccountNonLocked());
		userContext.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userContext.setEnabled(user.isEnabled());
		
		Role role = user.getAuthorities().iterator().next()
				.getApplicationRole().getRole();
		userContext
				.setRole(new mz.co.mozview.frameworks.core.webservices.model.Role(
						role.getName(), role.getDescription()));
		
		userContext.setTransactionCodes(getTransactionCodes(user, application));
		
		Unit unit = getUnit(user);
		
		userContext
				.setUnit(new mz.co.mozview.frameworks.core.webservices.model.UnitWS(
						unit.getCode(), unit.getNuit(), unit.getName(), unit
								.getAddress(), unit.getContact(), unit
								.getEmail()));
		
		userContext.setSessionId(getSessionId(userContext, application));
		
		return userContext;
	}
	
	private static Unit getUnit(final User user)
	{
		for (UserApplicationRole userApplicationRole : user.getAuthorities())
		{
			return userApplicationRole.getUnits().iterator().next();
		}
		
		return null;
	}
	
	private static List<String> getTransactionCodes(final User user,
			final Application application)
	{
		List<String> transactionCodes = new ArrayList<>();
		
		for (UserApplicationRole userApplicationRole : user.getAuthorities())
		{
			if (application.equals(userApplicationRole.getApplicationRole()
					.getApplication()))
			{
				for (Transaction transaction : userApplicationRole
						.getApplicationRole().getTransactions())
				{
					transactionCodes.add(transaction.getCode());
				}
			}
		}
		
		return transactionCodes;
	}
	
	private static Role getRole(
			final Collection<UserApplicationRole> authorities)
	{
		for (UserApplicationRole userApplicationRole : authorities)
		{
			if (application.equals(userApplicationRole.getApplicationRole()
					.getApplication()))
			{
				return userApplicationRole.getApplicationRole().getRole();
			}
		}
		
		return null;
	}
	
	private static String getSessionId(final UserContext userContext,
			final Application application)
	{
		StringBuilder sessionId = new StringBuilder();
		sessionId.append(application.getCode())
				.append(userContext.getUnit().getCode())
				.append(userContext.getUsername());
		
		return sessionId.toString();
	}
}
