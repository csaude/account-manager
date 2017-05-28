/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.transaction.model.Transaction;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 *
 */
public class TransactionTemplate implements TemplateLoader
{
	
	public static final String VALID = "valid";
	
	@Override
	public void load()
	{
		Fixture.of(Transaction.class).addTemplate(VALID, new Rule()
		{
			{
				this.add("code", this.random("001", "002", "003"));
				this.add("name", this.random("Registar vendar",
						"Editar Vendas", "Relatório de Vendas"));
				this.add("application", this.one(Application.class, "valid"));
			}
		});
		
	}
}
