package battleshipmy.battleshipmy;

import battleship.Battleship;
import configurationManagement.ConfigurationLoader;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class battleshipprojApplication extends Application<battleshipprojConfiguration> {

	//@Action(actionType = ActionType.START, message = "d1.startBattleship", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.startBattleship")
	//@Action(actionType = ActionType.END, message = "d1.endBattleship", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.endBattleship")
    public static void main(final String[] args) throws Exception {
    	ConfigurationLoader configurationLoader = new ConfigurationLoader("resources/battleshipConfig.json");
		Battleship battleshipGame = new Battleship();
    }

    @Override
    public String getName() {
        return "battleshipproj";
    }

    @Override
    public void initialize(final Bootstrap<battleshipprojConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final battleshipprojConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
