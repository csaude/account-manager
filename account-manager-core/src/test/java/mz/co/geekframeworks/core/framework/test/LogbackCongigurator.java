package mz.co.geekframeworks.core.framework.test;

import java.io.FileNotFoundException;
import java.net.URL;

import mz.co.geekframeworks.core.util.ResourceUtils;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Classe para ativar configuração do LogBack de ficheiros não padrão
 * (diferentes de logback.xml, logback-test.xml e logback.groovy).
 * 
 * @author Eudson Bambo
 *
 */
public class LogbackCongigurator
{
	/**
	 * Inicialização do LogBack.
	 * 
	 * @param location
	 *            : Localização do ficheiro com as configurações.
	 * @throws FileNotFoundException
	 */
	public static void initLogging(final String location)
			throws FileNotFoundException
	{
		final URL url = ResourceUtils.getResource(location);
		
		LoggerContext context = (LoggerContext) LoggerFactory
				.getILoggerFactory();
		
		JoranConfigurator configurator = new JoranConfigurator();
		
		context.reset();
		
		configurator.setContext(context);
		
		try
		{
			configurator.doConfigure(url);
		}
		catch (JoranException e)
		{
			// StatusPrinter vai imprimir o erro
		}
		
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);
	}
	
	/**
	 * Desligar o logback
	 */
	public static void shutdownLogging()
	{
		((LoggerContext) LoggerFactory.getILoggerFactory()).stop();
	}
}
