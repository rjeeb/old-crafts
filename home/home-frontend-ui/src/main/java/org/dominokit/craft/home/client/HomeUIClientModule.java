package org.dominokit.craft.home.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="HomeUI")
public class HomeUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Home frontend UI module ...");
		new ModuleConfigurator().configureModule(new HomeUIModuleConfiguration());
	}
}
