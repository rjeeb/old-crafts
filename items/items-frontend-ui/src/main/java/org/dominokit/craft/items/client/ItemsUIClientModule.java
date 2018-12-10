package org.dominokit.craft.items.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="ItemsUI")
public class ItemsUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Items frontend UI module ...");
		new ModuleConfigurator().configureModule(new ItemsUIModuleConfiguration());
	}
}
