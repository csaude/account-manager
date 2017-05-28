/**
 * 
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.role.model.Role;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 * 
 */
public class RoleTemplate implements TemplateLoader
{
	
	@Override
	public void load()
	{
		
		Fixture.of(Role.class).addTemplate(
				FixtureFactoryConstants.VALID_OBJECT, new Rule()
				{
					{
						this.add("name",
								this.random("Administrador", "Operador"));
						this.add("description", this.random(
								"Administrador do Sistema",
								"Operador Contabilistico"));
					}
				});
	}
}
