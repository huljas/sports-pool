package controllers;

import models.Player;
import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * @author huljas
 */
@OnApplicationStart
public class DataLoader extends Job {

    @Override
    public void doJob() throws Exception {
        if (Play.mode == Play.Mode.DEV) {
            if (Player.count() == 0) {
                Fixtures.loadModels("initial-data.yaml");
                Logger.info("Data loaded OK");
            }
        }
    }
}
