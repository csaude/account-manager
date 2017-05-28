/**
 * 
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.framework.fixturefactory.util.FixtureFactoryConstants;
import mz.co.geekframeworks.core.user.model.User;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 * 
 *         Este template devolve usuários externos ou internos depenendo do
 *         pedido
 * 
 */
public class UserTemplate implements TemplateLoader
{
	
	@Override
	public void load()
	{
		
		Fixture.of(User.class).addTemplate(
				FixtureFactoryConstants.VALID_OBJECT, new Rule()
				{
					{
						this.add("fullName", this.random("St�lio Moiane",
								"Eudson Bambo", "Kamilah Moiane"));
						this.add("username", this.random("stelio", "eudson",
								"kamilah", "nailah", "camilo"));
						this.add("password", "${username}");
						this.add("email", "${username}@mozview.co.mz");
						this.add("accountNonExpired", true);
						this.add("accountNonLocked", true);
						this.add("credentialsNonExpired", true);
						this.add("enabled", true);
					}
				});
	}
	
}
