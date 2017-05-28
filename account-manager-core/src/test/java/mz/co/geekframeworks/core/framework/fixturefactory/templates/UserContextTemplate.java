/**
 * 
 */
package mz.co.geekframeworks.core.framework.fixturefactory.templates;

import mz.co.geekframeworks.core.user.model.UserContext;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 *
 */
public class UserContextTemplate implements TemplateLoader
{
	
	@Override
	public void load()
	{
		Fixture.of(UserContext.class).addTemplate("valid", new Rule()
		{
			{
				this.add("fullName", this.random("Stélio Moiane",
						"Alima Moiane", "Kamilah Moiane"));
				this.add("username", this.random("stelio", "alima", "kamilah",
						"nailah", "camilo"));
				this.add("newPassword", "${username}");
				this.add("oldPassword", "${username}");
				this.add("sessionId", "${username}");
				this.add("email", "${username}@mozview.co.mz");
				this.add("accountNonExpired", true);
				this.add("accountNonLocked", true);
				this.add("credentialsNonExpired", true);
				this.add("enabled", true);
			}
		});
	}
}
