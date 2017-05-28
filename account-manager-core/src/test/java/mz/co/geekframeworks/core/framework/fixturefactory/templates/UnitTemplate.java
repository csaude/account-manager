/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.unit.model.Unit;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 *
 */
public class UnitTemplate implements TemplateLoader
{
	public static final String VALID = "valid";
	
	@Override
	public void load()
	{
		
		Fixture.of(Unit.class).addTemplate(VALID, new Rule()
		{
			{
				this.add("nuit", "102124774");
				this.add("name", this.random("Farmácia Moderna",
						"Farmácia Aputeko", "Farmácia Luis Valente",
						"Farmácia Alima"));
				this.add("email", "steliomo@gmail.com");
				this.add("contact", "+258822546100 ou +258840546824");
				this.add("address",
						"Bairro Djuba, Matola-Rio, Q.2 Casa nr. 375");
			}
		});
	}
	
}
