/**
 *
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.application.model.Application;
import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 *
 */
public class ApplicationTemplate implements TemplateLoader
{
	
	@Override
	public void load()
	{
		
		Fixture.of(Application.class).addTemplate(
				FixtureFactoryConstants.VALID_OBJECT, new Rule()
				{
					{
						this.add("code", "01");
						this.add("description",
								"Sistema de Gestão de Finaceira");
						this.add("contextName", "/finaces-web");
					}
				});
	}
}
