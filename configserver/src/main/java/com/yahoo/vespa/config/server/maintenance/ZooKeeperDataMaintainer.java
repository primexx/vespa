// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.config.server.maintenance;

import com.yahoo.path.Path;
import com.yahoo.vespa.config.server.ApplicationRepository;
import com.yahoo.vespa.curator.Curator;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Removes unused zookeeper data
 *
 * @author hmusum
 */
public class ZooKeeperDataMaintainer extends Maintainer {

    private static final List<String> pathsToDelete = Arrays.asList(
            "/vespa/filedistribution", // Path to file distribution data used before Vespa 6.213
            "/vespa/config" //  Path to config data used before Vespa 6
    );

    ZooKeeperDataMaintainer(ApplicationRepository applicationRepository, Curator curator, Duration interval) {
        super(applicationRepository, curator, interval);
    }

    @Override
    protected void maintain() {
        pathsToDelete.forEach(path -> curator.delete(Path.fromString(path)));
    }
}
