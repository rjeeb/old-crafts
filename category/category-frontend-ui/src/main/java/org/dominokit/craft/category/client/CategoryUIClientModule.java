package org.dominokit.craft.category.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="CategoryUI")
public class CategoryUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Category frontend UI module ...");
		new ModuleConfigurator().configureModule(new CategoryUIModuleConfiguration());
	}
}
